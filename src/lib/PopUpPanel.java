/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.awt.Component;
import javax.swing.Popup;

/**
 *
 * @author blegoh
 */
public class PopUpPanel extends Popup{
    
    public PopUpPanel(Component owner, Component contents, int x, int y){
        super(owner, contents, x, y);
    }
}
