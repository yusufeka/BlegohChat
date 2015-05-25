package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.SettingModel;
import view.AccountSettingView;

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
    }
    
    class BackListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            
        }
    }
    
}
