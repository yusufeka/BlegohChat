/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import lib.User;
import java.sql.SQLException;
import lib.Koneksi;

/**
 *
 * @author blegoh
 */
public class AddModel {
    private User user;
    private User kontak;
    private Koneksi kon;
    
    public AddModel(User user){
        this.user = user;
        this.kon = new Koneksi();
    }
    
    public void setKontak(User kontak){
        this.kontak = kontak;
    }
    
    public User getUser(){
        return user;
    }
    
    public boolean isUserExist(String username) throws SQLException{
        return user.isUsernameExist(username);
    }
    
    public void saveKontak() throws SQLException{
        int userID = this.user.getUserId();
        int kontakID = this.kontak.getUserId();
        String sql = "insert into kontak values("+userID+","+kontakID+")";
        kon.setQuery(sql);
        kon.executeUpdate();
        kon.close();
    }

}
