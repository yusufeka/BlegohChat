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
import lib.User;
import model.AddModel;
import model.NewChatModel;
import view.AddView;
import view.NewChatView;

/**
 *
 * @author blegoh
 */
class AddController {

    private AddModel theModel;
    private AddView theView;
    private User user;

    AddController(AddModel theModel, AddView theView) {
        this.theModel = theModel;
        this.theView = theView;
        this.user = this.theModel.getUser();
        this.theView.addCancelListener(new CancelListener());
        this.theView.addTambahListener(new TambahListener());
    }

    class TambahListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                User kontak = new User(theView.getUsername());
                theModel.setKontak(kontak);
                theModel.saveKontak();
            } catch (SQLException ex) {

            }
            theView.clear();
        }

    }

    class CancelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                theModel.close();
                theView.dispose();
                NewChatView theView = new NewChatView();
                NewChatModel theModel;
                theModel = new NewChatModel(user);
                NewChatController theController = new NewChatController(theModel, theView);
                theView.setVisible(true);
            } catch (SQLException ex) {
               
            } catch (IOException ex) {
                Logger.getLogger(AddController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
