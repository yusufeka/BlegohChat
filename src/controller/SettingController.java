/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.User;
import model.LoginModel;
import model.ProfilModel;
import model.SettingModel;
import view.LoginView;
import view.ProfilView;
import view.SettingView;

/**
 *
 * @author blegoh
 */
public class SettingController {

    private SettingModel theModel;
    private SettingView theView;
    private User user;

    SettingController(SettingModel theModel, SettingView theView) {
        this.theModel = theModel;
        this.theView = theView;
        user = this.theModel.getUser();
        this.theView.addLogOutListener(new LogOutListener());
        this.theView.addProfilListener(new ProfilListener());
    }

    class LogOutListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            theView.dispose();
            LoginView theView = new LoginView();
            LoginModel theModel = new LoginModel();
            LoginController theController = new LoginController(theModel, theView);
            theView.setVisible(true);
        }
    }

    class ProfilListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                theView.dispose();
                ProfilView theView = new ProfilView();
                ProfilModel theModel = new ProfilModel(user);
                ProfilController theController = new ProfilController(theModel, theView);
                theView.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(SettingController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
