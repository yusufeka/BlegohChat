
package blegohchat;

import controller.LoginController;
import model.LoginModel;
import view.LoginView;

/**
 *
 * @author blegoh
 */
public class BlegohChat {

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e){
            
        }
        LoginView theView = new LoginView();
        LoginModel theModel = new LoginModel();
        LoginController theController = new LoginController(theModel, theView);
        theView.setVisible(true);
    }
    
}
