/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import lib.User;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import lib.Koneksi;

/**
 *
 * @author blegoh
 */
public class NewChatModel {

    private Koneksi kon;
    private User user;
    private ArrayList<User> kontakUser;

    public NewChatModel(User username) throws SQLException {
        this.user = username;
        kon = new Koneksi();
        String sql = "SELECT k.user_id, me.username, kontak_id,them.username kontak_user, them.nama\n"
                + "FROM kontak k\n"
                + "left join user me on k.user_id = me.user_id\n"
                + "left join user them on k.kontak_id = them.user_id "
                + "where me.username = '"+user.getUsername()+"'";
        kon.setQuery(sql);
        kon.executeQuery();
        this.kontakUser = new ArrayList<>();
        while(kon.getResult().next()){
            kontakUser.add(new User(kon.getResult().getString("kontak_user")));
        }
    }
    
    public ArrayList<User> getKontakUser(){
        return kontakUser;
    }

    public User getUser() {
        return user;
    }
}
