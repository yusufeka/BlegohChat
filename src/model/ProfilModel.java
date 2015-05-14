/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.SQLException;
import lib.User;

/**
 *
 * @author blegoh
 */
public class ProfilModel {

    private User user;
    private User friendUser;
    
    public ProfilModel(User user) {
        this.user = user;
    }
    
    public ProfilModel(User user,User friendUser) {
        this.user = user;
        this.friendUser = friendUser;
    }
    
    public void setNama(String nama){
        this.user.SetNama(nama);
    }
    
    public void saveUser() throws SQLException{
        this.user.updateUser();
    }
    
    public User getUser(){
        return user;
    }
    
    public User getFriendUser(){
        return friendUser;
    }
}
