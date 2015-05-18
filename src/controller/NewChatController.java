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
import javax.swing.JButton;
import javax.swing.JLabel;
import model.User;
import model.*;
import view.*;

public class NewChatController {
    private NewChatModel theModel;
    private NewChatView theView;
    private User user;

    public NewChatController(NewChatModel theModel, NewChatView theView) throws IOException {
        this.theModel = theModel;
        this.theView = theView;
        user = this.theModel.getUser();
        JLabel nama[];
        nama = this.theModel.getNama();
        JLabel foto[] = this.theModel.getFoto();
        JLabel status[] = this.theModel.getStatus();
        this.theView.addBtnKontak(nama,foto,status);
        User kontakUser[] = this.theModel.getKontakUser();
        for (int i = 0; i < nama.length; i++) {
            this.theView.addChatListener(i, new ChatListener(kontakUser[i]));
        }
        this.theView.addBackListener(new BackListener());
        this.theView.addAddListener(new AddListener());
    }
    
    class ChatListener implements ActionListener {

        private User kontakUser;

        public ChatListener(User kontakUser) {
            this.kontakUser = kontakUser;

        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                theView.dispose();
                ChatView theView = new ChatView();
                ChatModel theModel = new ChatModel(user, kontakUser);
                ChatController theController = new ChatController(theModel, theView);
                theView.setVisible(true);
            } catch (Exception ex) {
                System.out.println(ex);
            }

        }

    }
    
    class AddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            theView.dispose();
            AddView theView = new AddView();
            AddModel theModel = new AddModel(user);
            AddController theController = new AddController(theModel, theView);
            theView.setVisible(true);
        }
        
    }
    
    class BackListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            theView.dispose();
            try {
                HomeView theView = new HomeView();
                HomeModel theModel = new HomeModel(user);
                System.out.println("as "+user.getUsername());
                HomeController theController = new HomeController(theModel, theView);
                theView.setVisible(true);
            } catch (SQLException ex) {
                System.out.println(ex);
            } catch (IOException ex) {
                Logger.getLogger(NewChatController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
