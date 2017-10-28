package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.SettingModel;
import view.HelpView;
import view.SettingView;

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
        this.theView.addBackListener(new BackListener());
        this.theView.addAboutListener(new AboutListener());
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
    
    class AboutListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            if (theView.isShow()) {
                theView.hideAbout();
            }else{
                theView.showAbout();
            }
        }
    }
    
}
