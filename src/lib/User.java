/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.sql.SQLException;

/**
 *
 * @author blegoh
 */
public class User {

    private int userId;
    private String username;
    private String password;
    private String nama;
    private String email;
    private Koneksi kon;
    private String lastSeen;
    private String foto;
    private String status;
    private int isActive;
    

    public User(){
        kon = new Koneksi();
    }
    
    public User(String username) throws SQLException {
        this.username = username;
        kon = new Koneksi();
        setUserProperty("username");
        kon.close();
    }

    public User(int userId) throws SQLException {
        this.userId = userId;
        kon = new Koneksi();
        setUserProperty("user_id");
        kon.close();
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private void setUserProperty(String field) throws SQLException {
        kon.from("user");
        String where = (String) ((field.equals("username"))?"username = '"+this.username+"'":"user_id="+this.userId);
        kon.where(where);
        kon.executeQuery();
        kon.getResult().next();
        this.userId = kon.getResult().getInt("user_id");
        this.username = kon.getResult().getString("username");
        this.nama = kon.getResult().getString("nama");
        this.email = kon.getResult().getString("email");
        this.lastSeen = kon.getResult().getString("last_seen");
        this.foto = kon.getResult().getString("foto");
        this.status = kon.getResult().getString("status");
        this.isActive = kon.getResult().getInt("is_active");
    }

    public String addSlash(String s) {
        String a = "";
        for (int i = 0; i < s.length(); i++) {
            a += ("'".equals(s.charAt(i) + "")) ? "\\'" : s.charAt(i);
        }
        return a;
    }

    public boolean isUserExist() throws SQLException {
        kon = new Koneksi();
        kon.from("user");
        kon.where("username = '" + addSlash(username) + "' and password = '" + addSlash(password) + "'");
        kon.executeQuery();
        int i = kon.getRow();
        kon.close();
        return (i == 1) ? true : false;
    }

    public boolean isUsernameExist() throws SQLException {
        kon.from("user");
        kon.where("username = '" + addSlash(username) + "'");
        kon.executeQuery();
        int i = kon.getRow();
        return (i == 1) ? true : false;
    }

    public boolean isEmailExist() throws SQLException {
        kon.from("user");
        kon.where("email = '" + email + "'");
        kon.executeQuery();
        int i = kon.getRow();
        return (i == 1) ? true : false;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return password;
    }

    public String getNama() {
        return this.nama;
    }

    public String getFoto() {
        return this.foto;
    }
    
    public String getStatus() {
        return this.status;
    }

    public String getLastSeen() {
        return this.lastSeen;
    }

    public String getEmail() {
        return this.email;
    }

    public void SetNama(String nama) {
        this.nama = nama;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void updateUser() throws SQLException {
        kon = new Koneksi();
        String sql = "update user set username = '" + getUsername() + "',password = '" +
                getPassword() + "', nama = '"+getNama()+"',foto='"+getFoto()+"',status = '"+
                getStatus()+"',email='"+getEmail()+"',is_active = "+isActive+" "
                + "where user_id = "+getUserId();
        System.out.println(sql);
        kon.setQuery(sql);
        kon.executeUpdate();
        kon.close();
    }
    
}
