package controller;

import model.SettingModel;
import view.HelpView;

/**
 *
 * @author blegoh
 */
class HelpController {
    
    private SettingModel theModel;
    private HelpView theView;
    
    public HelpController(SettingModel theModel,HelpView theView){
        this.theModel = theModel;
        this.theView = theView;
    }
}
