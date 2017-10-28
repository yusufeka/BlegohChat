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
    private String code;
    private boolean valid = true;
    private String pesan = "";

    public User() throws SQLException {
        setUserID();
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

    private void setUserProperty(String field) throws SQLException {
        kon.from("user");
        String where = (String) ((field.equals("username")) ? "username = '" + this.username + "'" : "user_id=" + this.userId);
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
        this.password = kon.getResult().getString("password");
        this.code = kon.getResult().getString("confirmation_code");
        kon.close();
    }

    public boolean isUsernameExist(String username) throws SQLException {
        kon = new Koneksi();
        kon.from("user");
        kon.where("username = '" + username + "'");
        kon.executeQuery();
        int i = kon.getRow();
        kon.close();
        return (i == 1) ? true : false;
    }

    public boolean isEmailExist(String email) throws SQLException {
        kon = new Koneksi();
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
        return (!this.foto.equals(""))?this.foto:"default.jpg";
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
    
    public boolean isAktif(){
        return (this.isActive == 1)?true:false;
    }
    
    public String getCode(){
        return this.code;
    }
    
    public boolean isValid() {
        boolean a = this.valid;
        this.valid = true;
        return a;
    }

    public String getPesan() {
        String a = this.pesan;
        pesan = "";
        return a;
    }

    //mengeset User ID dengan Next Auto Increment
    private void setUserID() throws SQLException {
        kon = new Koneksi();
        kon.setQuery("SHOW TABLE STATUS WHERE Name LIKE 'user'");
        kon.executeQuery();
        kon.getResult().next();
        this.userId = kon.getResult().getInt("Auto_increment");
        kon.close();
    }

    public void setPassword(String password, String confirm) {
        if (password.trim().length() == 0) {
            this.valid = false;
            this.pesan += "password kosong\n";
        } else if (confirm.trim().length() == 0) {
            this.valid = false;
            this.pesan += "confirm password kosong\n";
        } else if (!password.equals(confirm)) {
            this.valid = false;
            this.pesan += "confirm password tidak sesuai\n";
        } else {
            this.password = password;
        }
    }

    public void setPassword(String password) {
        if (password.trim().length() == 0) {
            this.valid = false;
            this.pesan += "password kosong\n";
        } else {
            this.password = password;
        }
    }

    public void setUsername(String username) throws SQLException {
        if (username.trim().length() == 0) {
            this.valid = false;
            this.pesan += "username kosong\n";
        } else if (isUsernameExist(username)) {
            this.valid = false;
            this.pesan += "username sudah digunakan\n";
        } else {
            this.username = username;
        }
    }

    public void setNama(String nama) {
        if (nama.trim().length() == 0) {
            this.valid = false;
            this.pesan += "nama kosong\n";
        } else {
            this.nama = nama;
        }
    }

    public void setFoto(String foto) {
        if (foto.trim().length() == 0) {
            this.foto = "";
        } else {
            this.foto = getUserId() + getEkstensi(foto);
        }
    }
    
    public void setAktif(){
        this.isActive = 1;
    }

    //blm fix
    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmail(String email) throws SQLException {
        String emailregex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (email.trim().length() == 0) {
            this.valid = false;
            this.pesan += "email kosong\n";
        } else if (!email.matches(emailregex)) {
            this.valid = false;
            this.pesan += "pola email salah\n";
        } else if (isEmailExist(email)) {
            this.valid = false;
            this.pesan += "email sudah digunakan\n";
        } else {
            this.email = email;
        }
    }

    private String getEkstensi(String fileName) {
        String a = "";
        int mulai = 0;
        for (int i = fileName.length() - 1; i >= 0; i--) {
            if (fileName.charAt(i) == '.') {
                mulai = i;
                break;
            }
        }
        a = fileName.substring(mulai);
        return a;
    }

    public void updateUser() throws SQLException {
        if (valid) {
            kon = new Koneksi();
            String sql = "update user set username = '" + getUsername() + "',password = '"
                    + getPassword() + "', nama = '" + getNama() + "',status = '"
                    + getStatus() + "',email='" + getEmail() + "',is_active = " + isActive + " "
                    + "where user_id = " + getUserId();
            kon.setQuery(sql);
            kon.executeUpdate();
            kon.close();
        }
    }

}
