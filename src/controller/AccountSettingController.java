package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.SettingModel;
import view.AccountSettingView;
import view.SettingView;

/**
 *
 * @author blegoh
 */
class AccountSettingController {
    private SettingModel theModel;
    private AccountSettingView theView;
    
    public AccountSettingController(SettingModel theModel,AccountSettingView theView){
        this.theModel = theModel;
        this.theView = theView;
        this.theView.addBackListener(new BackListener());
    }
    
    class BackListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            theView.dispose();
            SettingView theView = new SettingView();
            SettingController theController = new SettingController(theModel, theView);
            theView.setVisible(true);
        }
    }
   
}
