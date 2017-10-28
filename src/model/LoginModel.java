package model;

import lib.User;
import java.sql.SQLException;
import lib.Koneksi;

/**
 *
 * @author blegoh
 */
public class LoginModel {

    private User user;
    private String username;
    private String password;
    private Koneksi kon;
    private String msg;

    public LoginModel() {
        kon = new Koneksi();
        msg = "";
    }

    public String getMsg() {
        String a = msg;
        msg = "";
        return a;
    }

    public void setUsername(String username) {
        if (username.length() == 0) {
            msg += "Username kosong\n";
        } else {
            this.username = username;
        }
    }
    
    public User getUser(){
        return user;
    }

    public void setPassword(String password) {
        if (password.length() == 0) {
            msg += "Password kosong\n";
        } else {
            this.password = password;
        }
    }

    public boolean getLogin() throws SQLException {
        if (msg.equals("")) {
            kon = new Koneksi();
            kon.from("user");
            kon.where("username = '" + addSlash(username) + "' and password = '" + addSlash(password) + "'");
            kon.executeQuery();
            int i = kon.getRow();
            kon.close();
            msg = (i == 1) ? "" : "Username atau password tidak sesuai";
            user = (i == 1)? new User(username):null;
            return (i == 1) ? true : false;
        } else {
            return false;
        }
    }

    public String addSlash(String s) {
        String a = "";
        for (int i = 0; i < s.length(); i++) {
            a += ("'".equals(s.charAt(i) + "")) ? "\\'" : s.charAt(i);
        }
        return a;
    }

}
