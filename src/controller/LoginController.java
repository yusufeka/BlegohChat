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
import javax.swing.JOptionPane;
import lib.User;
import model.HomeModel;
import model.LoginModel;
import model.RegisterModel;
import view.*;

/**
 *
 * @author blegoh
 */
public class LoginController {
    private LoginModel theModel;
    private LoginView theView;

    public LoginController(LoginModel theModel, LoginView theView) {
        this.theModel = theModel;
        this.theView = theView;
        this.theView.addLoginListener(new LoginListener());
        this.theView.addRegisterListener(new RegListener());
    }

    class RegListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //try{
                theView.dispose();
                RegisterView theView = new RegisterView();
                RegisterModel theModel = new RegisterModel();
                RegisterController theController = new RegisterController(theModel, theView);
                theView.setVisible(true);
            
        }
    }
    
    class LoginListener implements ActionListener { 

        @Override
        public void actionPerformed(ActionEvent ae) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String username, password;
            try{
                username = theView.getUsername();
                password = theView.getPassword();
                User user = new User(username);
                user.setPassword(password);
                theModel.setUser(user);
                theModel.setIsValid();
                boolean isValid = theModel.getLogin();
                if (isValid) {
                    theView.dispose();
                    HomeView theView = new HomeView();
                    theView.setVisible(true);
                    HomeModel theModel = new HomeModel(user);
                    HomeController theController = new HomeController(theModel, theView);
                }else{
                    System.out.println(isValid);
                    JOptionPane.showMessageDialog(theView, "login gagal");
                }
            }catch(NumberFormatException e){
                
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
