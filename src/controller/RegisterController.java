/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.mail.MessagingException;
import lib.Mail;
import lib.User;
import model.ConfirmModel;
import model.LoginModel;
import model.RegisterModel;
import view.ConfirmView;
import view.LoginView;
import view.RegisterView;


/*
 *
 * @author blegoh
 */
public class RegisterController {

    private RegisterModel theModel;
    private RegisterView theView;
    private User user;

    RegisterController(RegisterModel theModel, RegisterView theView) throws SQLException {
        this.theModel = theModel;
        this.theView = theView;
        user = this.theModel.getUser();
        this.theView.addBrowseListener(new BrowseListener());
        this.theView.addSubmitListener(new SubmitListener());
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
                theModel.setEmail(theView.getEmail());
                theModel.setNama(theView.getNama());
                theModel.setUsername(theView.getUsername());
                theModel.setPassword(theView.getPassword(), theView.getConfirm());
                theModel.setFoto(theView.getPath());
                if (theModel.isValid()) {
                    theModel.saveUser();
                    if (!theView.getPath().equals("")) {
                        theModel.uploadFoto(theView.getPath());
                    }
                    Mail m = new Mail(theView.getEmail(), theModel.getCode());
                    m.Send();
                    theView.dispose();
                    ConfirmView theView = new ConfirmView();
                    ConfirmModel theModel = new ConfirmModel(user);
                    ConfirmController theController =  new ConfirmController(theModel, theView);
                    theView.setVisible(true);
                }else{
                    theView.showPopUp(theModel.getPesan());
                }

            } catch (SQLException | MessagingException ex) {

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
