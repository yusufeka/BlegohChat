package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.Chat;
import lib.User;
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
    private ArrayList<String> conversation;
    private ArrayList<Boolean> isSender;
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
        this.addConversation();
        this.theView.sundul();
        this.theView.injek();
        this.theView.addSendListener(new SendListener());
        this.theView.addFotoListener(new FotoListener());
        this.theView.detailListener(new DetailListener());
    }

    public void addConversation() {
        ArrayList<Chat> conv = theModel.getConversation().getChat();
        conversation = new ArrayList<>();
        isSender = new ArrayList<>();
        for (int i = 0; i < conv.size(); i++) {
            conversation.add(conv.get(i).getIsi());
            int userId = this.user.getUserId();
            int senderId = conv.get(i).getSender().getUserId();
            isSender.add((userId == senderId) ? true : false);
            theView.addNewChat(conversation.get(i), isSender.get(i));
        }
    }

    class SendListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                String isi = theView.getChat();
                theView.clearChat();
                theView.addNewChat(isi, true);
                theView.injek();
                theModel.chat(isi);
            } catch (SQLException ex) {

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
            } catch (SQLException | IOException ex) {

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

            }
        }
    }
}
