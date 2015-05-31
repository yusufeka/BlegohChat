/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import lib.User;
import model.HomeModel;
import model.LoginModel;
import model.ProfilModel;
import model.SettingModel;
import view.AccountSettingView;
import view.HelpView;
import view.HomeView;
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
        this.theView.addHelpListener(new HelpListener());
        this.theView.addAccountListener(new AccountListener());
        this.theView.addBackListener(new BackListener());
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
                ex.printStackTrace();
            }

        }
    }
    
    class HelpListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            theView.dispose();
            HelpView theView = new HelpView();
            HelpController theController = new HelpController(theModel, theView);
            theView.setVisible(true);
        }
    }
    
    class AccountListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            theView.dispose();
            AccountSettingView theView = new AccountSettingView();
            AccountSettingController theController = new AccountSettingController(theModel, theView);
            theView.setVisible(true);
        }
    }
    
    class BackListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                theView.dispose();
                HomeView theView = new HomeView();
                HomeModel theModel = new HomeModel(user);
                HomeController theController = new HomeController(theModel, theView);
                theView.setVisible(true);
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
