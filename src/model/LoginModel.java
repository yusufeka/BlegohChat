/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import lib.Koneksi;
import lib.User;

/**
 *
 * @author blegoh
 */
public class LoginModel {
    private User user;
    private boolean isValid;
    private Koneksi kon;
    private int row;
    
    public LoginModel() {
        kon = new Koneksi();
    }
    
    public void setUser(User user){
        this.user = user;
    }
    
    public void setIsValid() throws SQLException{
        isValid = user.isUserExist();
    }
    
    public boolean getLogin(){
        return isValid;
    }
    
}
