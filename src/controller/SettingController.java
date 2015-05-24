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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private SettingView theSettingView;
    private AccountSettingView theAccountSettingView;
    
    private User user;

    SettingController(SettingModel theModel, SettingView theView) {
        this.theModel = theModel;
        this.theSettingView = theView;
        user = this.theModel.getUser();
        this.theSettingView.addLogOutListener(new LogOutListener());
        this.theSettingView.addProfilListener(new ProfilListener());
        this.theSettingView.addHelpListener(new HelpListener());
        this.theSettingView.addAccountListener(new AccountListener());
    }

    class LogOutListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            theSettingView.dispose();
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
                theSettingView.dispose();
                ProfilView theView = new ProfilView();
                ProfilModel theModel = new ProfilModel(user);
                ProfilController theController = new ProfilController(theModel, theView);
                theView.setVisible(true);
            } catch (IOException ex) {
            
            }

        }
    }
    
    class HelpListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            theSettingView.dispose();
            HelpView theView = new HelpView();
            HelpController theController = new HelpController(theModel, theView);
            theView.setVisible(true);
        }
    }
    
    class AccountListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            theSettingView.dispose();
            theAccountSettingView = new AccountSettingView();
            theAccountSettingView.setVisible(true);
        }
    }
    
    class BackListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                theSettingView.dispose();
                HomeView theView = new HomeView();
                HomeModel theModel = new HomeModel(user);
                HomeController theController = new HomeController(theModel, theView);
                theView.setVisible(true);
            } catch (SQLException | IOException ex) {
            
            }
        }
    }
}
