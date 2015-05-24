/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import lib.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import lib.Koneksi;

/**
 *
 * @author blegoh
 */
public class LoginModel {
    private User user;
    private Koneksi kon;
    
    public LoginModel() {
        kon = new Koneksi();
    }
    
    public void setUser(User user){
        this.user = user;
    }
    
    public boolean getLogin() throws SQLException{
        kon = new Koneksi();
        kon.from("user");
        kon.where("username = '" + addSlash(user.getUsername()) + "' and password = '" + addSlash(user.getPassword()) + "'");
        kon.executeQuery();
        int i = kon.getRow();
        kon.close();
        return (i == 1) ? true : false;
    }
    
    public String addSlash(String s) {
        String a = "";
        for (int i = 0; i < s.length(); i++) {
            a += ("'".equals(s.charAt(i) + "")) ? "\\'" : s.charAt(i);
        }
        return a;
    }
    
}
