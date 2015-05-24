/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import lib.Koneksi;
import lib.User;

/**
 *
 * @author blegoh
 */
public class ConfirmModel {
    
    private User user;
    private String code;
    private Koneksi kon;
    
    public ConfirmModel(User user){
        this.user = user;
    }
    
    public void setCode(String code){
        this.code = code;
    }
    
    public User getUser(){
        return user;
    }
    
    //edit class User perlu gak ya?
    public boolean isCorrect() throws SQLException{
        if (user.getCode().equals(this.code)) {
            kon = new Koneksi();
            kon.setQuery("update user set is_active = 1 where user_id = "+user.getUserId());
            kon.executeUpdate();
            kon.close();
            user.setAktif();
            return true;
        }else{
            return false;
        }
    }
    
}
