package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import lib.User;
import model.ChatModel;
import model.ProfilModel;
import model.SettingModel;
import view.ChatView;
import view.EditProfilView;
import view.ProfilView;
import view.SettingView;

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
        this.theView.addBackListener(new BackListener());
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
    
    class BackListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            User friendUser = theModel.getFriendUser();
            if (friendUser != null) {
                try {
                    theView.dispose();
                    ChatView theView = new ChatView();
                    ChatModel theModel = new ChatModel(user, friendUser);
                    ChatController theController = new ChatController(theModel, theView);
                    theView.setVisible(true);
                } catch (SQLException ex) {
                   ex.printStackTrace();
                }
            }else{
                theView.dispose();
                SettingView theView = new SettingView();
                SettingModel theModel = new SettingModel(user);
                SettingController theController = new SettingController(theModel, theView);
                theView.setVisible(true);
            }
        }
        
    }

}
