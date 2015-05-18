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
import model.User;
import model.ProfilModel;
import view.EditProfilView;
import view.ProfilView;

/**
 *
 * @author blegoh
 */
public class EditProfilController {
    
    private ProfilModel theModel;
    private EditProfilView theView;
    private User user;
    
    EditProfilController(ProfilModel theModel, EditProfilView theView) {
        this.theModel = theModel;
        this.theView = theView;
        user = this.theModel.getUser();
        this.theView.setNama(user.getNama());
        this.theView.addSaveListener(new SaveListener());
    }
    
    class SaveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                theModel.setNama(theView.getNama());
                theModel.saveUser();
                theView.dispose();
                ProfilView theView = new ProfilView();
                ProfilModel theModel = new ProfilModel(user);
                ProfilController theController = new ProfilController(theModel, theView);
                theView.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(EditProfilController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EditProfilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
