/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import model.User;
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
        JLabel nama[];
        User friendUser[];
        friendUser = this.theModel.getFriendUser();
        nama = this.theModel.getNama();
        JLabel foto[] = theModel.getFoto();
        JLabel lastChat[] = theModel.getLastChat();
        theView.addBtnChat(nama,foto,lastChat);
        for (int i = 0; i < nama.length; i++) {
            this.theView.addChatListener(i, new ChatListener(friendUser[i]));
        }
        this.theView.addSettingListener(new SettingListener());
        this.theModel.close();
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
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    class NewChatListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                theModel.close();
                theView.dispose();
                NewChatView theView = new NewChatView();
                NewChatModel theModel = new NewChatModel(user);
                NewChatController theController = new NewChatController(theModel, theView);
                theView.setVisible(true);
            } catch (SQLException e) {
                //System.out.println(e);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
