/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import lib.Koneksi;
import lib.User;

/**
 *
 * @author blegoh
 */
public class NewChatModel {

    private Koneksi kon;
    private User user;
    private JLabel nama[];
    private JLabel foto[];
    private JLabel status[];
    private User kontakUser[];

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
        this.nama = new JLabel[kon.getRow()];
        this.kontakUser = new User[kon.getRow()];
        int i = 0;
        while(kon.getResult().next()){
            nama[i] = new JLabel();
            kontakUser[i] = new User(kon.getResult().getString("kontak_user"));
            nama[i++].setText(kon.getResult().getString("nama"));
        }
    }
    
    public User[] getKontakUser(){
        return kontakUser;
    }
    
    public JLabel[] getFoto(){
        foto = new JLabel[kontakUser.length];
        for (int i = 0; i < foto.length; i++) {
            foto[i] = new JLabel(kontakUser[i].getFoto());
        }
        return foto;
    }
    
    public JLabel[] getStatus(){
        status = new JLabel[kontakUser.length];
        for (int i = 0; i < status.length; i++) {
            status[i] = new JLabel(kontakUser[i].getStatus());
        }
        return status;
    }

    public User getUser() {
        return user;
    }
    
    public JLabel[] getNama(){
        return nama;
    }
}
