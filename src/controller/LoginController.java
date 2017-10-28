package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import lib.User;
import model.ConfirmModel;
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
            try {
                theView.dispose();
                RegisterView theView = new RegisterView();
                RegisterModel theModel = new RegisterModel();
                RegisterController theController = new RegisterController(theModel, theView);
                theView.setVisible(true);
            } catch (SQLException ex) {

            }
        }
    }

    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                theModel.setUsername(theView.getUsername());
                theModel.setPassword(theView.getPassword());
                boolean isValid = theModel.getLogin();
                if (isValid) {
                    theView.dispose();
                    User user = theModel.getUser();
                    if (user.isAktif()) {
                        HomeView theView = new HomeView();
                        theView.setVisible(true);
                        HomeModel theModel = new HomeModel(user);
                        HomeController theController = new HomeController(theModel, theView);
                    } else {
                        ConfirmView theView = new ConfirmView();
                        theView.setVisible(true);
                        ConfirmModel theModel = new ConfirmModel(user);
                        ConfirmController theController = new ConfirmController(theModel, theView);
                    }
                } else {
                    theView.showPopUp(theModel.getMsg());
                }

            } catch (NumberFormatException | SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}
