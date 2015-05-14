/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFileChooser;
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
            try {
                String s;
                JFileChooser jfc = new JFileChooser();
                jfc.showDialog(jfc, "Choose an image file");
                s = jfc.getSelectedFile().getAbsolutePath();
                theView.setPath(s);

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    class SubmitListener implements ActionListener {
        String nama = theView.getNama();
        String email = theView.getEmail();
        String username = theView.getUsername();
        String password = theView.getPassword();
        String confirm = theView.getConfirm();
        String foto = theView.getPath();
            

        @Override
        public void actionPerformed(ActionEvent ae) {
            theModel.setNama(nama);
            theModel.setEmail(email);
            theModel.setUsername(username);
            theModel.setPassword(password);
            theModel.setFoto(foto);
            boolean isValid = this.isValid();
            //if (isValid) {
                theModel.saveUser();
                theModel.uploadFoto();
                theView.dispose();
                LoginView theView = new LoginView();
                LoginModel theModel = new LoginModel();
                LoginController theController = new LoginController(theModel, theView);
                theView.setVisible(true);
            //}else{
            //    System.out.println("taek");
            //}

        }
        
        public boolean isValid(){
            boolean isValid = true;
            if (nama.trim().length() == 0) {
                isValid = false;
            }
            if (email.trim().length() == 0) {
                isValid = false;
            } else {
                try {
                    if (theModel.isEmailExist()) {
                        isValid = false;
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
            if (username.trim().length() == 0) {
                isValid = false;
            } else {
                try {
                    if (theModel.isUsernameExist()) {
                        isValid = false;
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
            if (password.trim().length() == 0) {
                isValid = false;
            } else if (!password.equals(confirm)) {
                isValid = false;
            }
            return isValid;
        }
    }

    class SignInListener implements ActionListener{

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
