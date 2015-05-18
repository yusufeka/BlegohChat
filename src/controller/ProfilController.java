/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import model.User;
import model.ProfilModel;
import view.EditProfilView;
import view.ProfilView;

/**
 *
 * @author blegoh
 */
public class ProfilController {

    private ProfilModel theModel;
    private ProfilView theView;
    private User user;

    public ProfilController(ProfilModel theModel, ProfilView theView) throws IOException {
        this.theModel = theModel;
        this.theView = theView;
        user = this.theModel.getUser();
        if (this.theModel.getFriendUser() != null) {
            this.theView.removeEdit();
            this.theView.setNama(this.theModel.getFriendUser().getNama());
            this.theView.setFoto(this.theModel.getFriendUser().getFoto());
        } else {
            this.theView.setNama(user.getNama());
            this.theView.setFoto(user.getFoto());
            this.theView.addEditListener(new EditListener());
        }
    }

    class EditListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            theView.dispose();
            EditProfilView theView = new EditProfilView();
            ProfilModel theModel = new ProfilModel(user);
            EditProfilController theController = new EditProfilController(theModel, theView);
            theView.setVisible(true);
        }
    }

}
