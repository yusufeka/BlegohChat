/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.LoginModel;
import model.RegisterModel;
import view.LoginView;
import view.RegisterView;


/*
 *
 * @author blegoh
 */
public class RegisterController {

    private RegisterModel theModel;
    private RegisterView theView;

    RegisterController(RegisterModel theModel, RegisterView theView) {
        this.theModel = theModel;
        this.theView = theView;
        this.theView.addBrowseListener(new BrowseListener());
        this.theView.addSubmitListener(new SubmitListener());
        this.theView.addBrowseListener(new BrowseListener());
        this.theView.addSignInListener(new SignInListener());
    }

    class BrowseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            theView.showChooser();
        }
    }

    class SubmitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                theModel.setUserID();
                theModel.setEmail(theView.getEmail());
                theModel.setNama(theView.getNama());
                theModel.setUsername(theView.getUsername());
                theModel.setPassword(theView.getPassword(), theView.getConfirm());
                theModel.setFoto(theView.getPath());
                if (theModel.isValid()) {
                    theModel.saveUser();
                    theModel.uploadFoto(theView.getPath());
                    theView.dispose();
                    LoginView theView = new LoginView();
                    LoginModel theModel = new LoginModel();
                    LoginController theController = new LoginController(theModel, theView);
                    theView.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(theView, theModel.getPesan());
                }

            } catch (SQLException ex) {
                
            }
        }

    }

    class SignInListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            theView.dispose();
            LoginView theView = new LoginView();
            LoginModel theModel = new LoginModel();
            LoginController theController = new LoginController(theModel, theView);
            theView.setVisible(true);
        }

    }
}
