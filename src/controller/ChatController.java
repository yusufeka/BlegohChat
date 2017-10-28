package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
        conversation = new ArrayList<>();
        isSender = new ArrayList<>();
        AddConversation ac = new AddConversation();
        ac.start();
        this.theView.addSendListener(new SendListener());
        this.theView.addFotoListener(new FotoListener());
        this.theView.detailListener(new DetailListener());
        this.theView.addHapusChatListener(new HapusChatListener());
    }

    class Refresher extends Thread {

        @Override
        public void run() {
            try {
                while (true) {
                    theModel.refreshConversation();
                    int real = theModel.getConversation().getChat().size();
                    int current = conversation.size();
                    if (real > current) {
                        String c = theModel.getNewFriendChat(current);
                        conversation.add(c);
                        theView.addNewChat(c, false);
                        theView.injek();
                    }
                    sleep(5);
                }
            } catch (SQLException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    class AddConversation extends Thread {

        @Override
        public void run() {
            ArrayList<Chat> conv = theModel.getConversation().getChat();
            for (int i = 0; i < conv.size(); i++) {
                conversation.add(conv.get(i).getIsi());
                int userId = user.getUserId();
                int senderId = conv.get(i).getSender().getUserId();
                isSender.add((userId == senderId) ? true : false);
                theView.addNewChat(conversation.get(i), isSender.get(i));
                theView.injek();
            }
            new Refresher().start();
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
                conversation.add(isi);
                theModel.chat(isi);
            } catch (SQLException ex) {
                ex.printStackTrace();
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
    
    class HapusChatListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                theModel.hapusChat();
                theView.removeConversation();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
    }
}
