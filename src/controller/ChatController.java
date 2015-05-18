package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.border.AbstractBorder;
import lib.Chat;
import lib.TextBubbleBorder;
import model.User;
import model.ChatModel;
import model.HomeModel;
import model.ProfilModel;
import view.ChatView;
import view.HomeView;
import view.ProfilView;

/**
 *
 * @author blegoh
 */
public class ChatController {

    private ChatModel theModel;
    private ChatView theView;
    private JLabel conversation[];
    private boolean isSender[];
    private User user;
    private User friendUser;

    ChatController(ChatModel theModel, ChatView theView) throws SQLException {
        this.theModel = theModel;
        this.theView = theView;
        this.user = this.theModel.getUser();
        this.theView.setNama(theModel.getName());
        this.theView.setLastSeen(theModel.getLastSeen());
        this.friendUser = theModel.getFriendUser();
        this.theView.addFoto(friendUser.getFoto());
        this.refreshConversation();
        this.theView.sundul();
        this.theView.injek();
        this.theView.addSendListener(new SendListener());
        this.theView.addFotoListener(new FotoListener());
        this.theView.detailListener(new DetailListener());
    }
    
    public void refreshConversation() {
        Chat[] conv = theModel.getConversation().getChat();
        conversation = new JLabel[conv.length];
        isSender = new boolean[conv.length];
        for (int i = 0; i < conv.length; i++) {
            conversation[i] = new JLabel(conv[i].getIsi());
            conversation[i].setOpaque(true);
            conversation[i].setBackground(Color.white);
            AbstractBorder brdrLeft = new TextBubbleBorder(Color.BLACK, 1, 5, 5);
            conversation[i].setBorder(brdrLeft);
            int userId = this.user.getUserId();
            int senderId = conv[i].getSender().getUserId();
            isSender[i] = (userId == senderId) ? true : false;
        }
        theView.addConversation(conversation, isSender);
    }

    class SendListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                theModel.chat(theView.getChat());
                theView.clearChat();
                theView.removeConversation();
                refreshConversation();
                theView.sundul();
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }

    }

    class FotoListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {

            try {
                theView.dispose();
                HomeView theView = new HomeView();
                HomeModel theModel;
                theModel = new HomeModel(user);
                HomeController theController = new HomeController(theModel, theView);
                theView.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    class DetailListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                theView.dispose();
                ProfilView theView = new ProfilView();
                ProfilModel theModel;
                theModel = new ProfilModel(user, friendUser);
                ProfilController theController = new ProfilController(theModel, theView);
                theView.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
