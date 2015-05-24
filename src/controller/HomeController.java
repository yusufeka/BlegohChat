package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.Chat;
import lib.Conversation;
import lib.User;
import model.*;
import view.*;

/**
 *
 * @author blegoh
 */
public class HomeController {

    private HomeModel theModel;
    private HomeView theView;
    private User user;

    HomeController(HomeModel theModel, HomeView theView) throws SQLException, IOException {
        this.theModel = theModel;
        this.theView = theView;
        this.user = this.theModel.getUser();
        this.theView.addNewChatListener(new NewChatListener());
        ArrayList<User> friendUser = this.theModel.getFriendUser();
        for (int i = 0; i < friendUser.size(); i++) {
            Conversation c = new Conversation(user, friendUser.get(i));
            Chat ch = c.getChat().get(c.getChat().size()-1);
            String lastChat = ch.getIsi();
            this.theView.addBtnChat(friendUser.get(i).getNama(),friendUser.get(i).getFoto(), lastChat);
            this.theView.addChatListener(i, new ChatListener(friendUser.get(i)));
        }
        this.theView.addSettingListener(new SettingListener());
    }
    
    public HomeController(HomeModel theModel, HomeView theView,ArrayList<String> nama){
        
    }

    class SettingListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            theView.dispose();
            SettingView theView = new SettingView();
            SettingModel theModel = new SettingModel(user);
            SettingController theController = new SettingController(theModel, theView);
            theView.setVisible(true);
        }

    }

    class ChatListener implements ActionListener {

        private User friendUser;

        public ChatListener(User friendUser) {
            this.friendUser = friendUser;

        }

        @Override
        public void actionPerformed(ActionEvent ae) {

            try {
                theView.dispose();
                ChatView theView = new ChatView();
                ChatModel theModel = new ChatModel(user, friendUser);
                ChatController theController = new ChatController(theModel, theView);
                theView.setVisible(true);
            } catch (SQLException ex) {
            
            }

        }

    }

    class NewChatListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                theView.dispose();
                NewChatView theView = new NewChatView();
                NewChatModel theModel = new NewChatModel(user);
                NewChatController theController = new NewChatController(theModel, theView);
                theView.setVisible(true);
            } catch (SQLException | IOException e) {
                
            }
        }
    }
}
