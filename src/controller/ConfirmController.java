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
import javax.mail.MessagingException;
import lib.Mail;
import lib.User;
import model.ConfirmModel;
import model.HomeModel;
import view.ConfirmView;
import view.HomeView;

/**
 *
 * @author blegoh
 */
public class ConfirmController {

    private ConfirmModel theModel;
    private ConfirmView theView;
    private User user;

    public ConfirmController(ConfirmModel theModel, ConfirmView theView) {
        this.theModel = theModel;
        this.theView = theView;
        user = this.theModel.getUser();
        this.theView.addAktifListener(new AktifListener());
        this.theView.addResendListener(new ResendListener());
    }

    class AktifListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                theModel.setCode(theView.getCode());
                if (theModel.isCorrect()) {
                    theView.dispose();
                    //refresh user
                    user = theModel.getUser();
                    HomeView theView = new HomeView();
                    theView.setVisible(true);
                    HomeModel theModel = new HomeModel(user);
                    HomeController theController = new HomeController(theModel, theView);
                } else {
                    theView.showPopUp("Kode salah");
                }
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    class ResendListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            new Thread() {
                public void run() {
                    try {
                        Mail m = new Mail(user.getEmail(), user.getCode());
                        m.Send();
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }
                }
            }.start();
            theView.showPopUp("Silakan cek email kembali");
        }
    }
}
