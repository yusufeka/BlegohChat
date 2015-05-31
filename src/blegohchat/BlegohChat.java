package blegohchat;

import controller.LoginController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import model.LoginModel;
import view.LoginView;

/**
 *
 * @author blegoh
 */
public class BlegohChat {

    public static void runSystemCommand(String command) {

        try {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader inputStream = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));

            String s = "";
            // reading output stream of the command
            while ((s = inputStream.readLine()) != null) {
                System.out.println(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {

        }
        
        LoginView theView = new LoginView();
        LoginModel theModel = new LoginModel();
        LoginController theController = new LoginController(theModel, theView);
        theView.setVisible(true);
    }

}
