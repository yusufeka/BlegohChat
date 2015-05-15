/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import lib.Koneksi;
import lib.User;

/**
 *
 * @author blegoh
 */
public class RegisterModel {

    private User user;
    private Koneksi kon;
    private static final int BUFFER_SIZE = 4096;

    public RegisterModel() {
        kon = new Koneksi();
        user = new User();
        user.setFoto("");
    }

    public void setUsername(String username) {
        this.user.setUsername(username);
    }

    public void setEmail(String email) {
        this.user.setEmail(email);
    }

    public void setNama(String nama) {
        this.user.SetNama(nama);
    }

    public void setFoto(String foto) {
        this.user.setFoto(foto);
    }

    public void setPassword(String password) {
        this.user.setPassword(password);
    }

    public boolean isUsernameExist() throws SQLException {
        return user.isUsernameExist();
    }

    public boolean isEmailExist() throws SQLException {
        return user.isEmailExist();
    }

    public void uploadFoto() {
        String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
        String host = "laos.cs.unej.ac.id";
        String user = "st_laos";
        String pass = "L405pssi";
        String filePath = this.user.getFoto();
        String uploadPath = "/blegoh-project/a.jpg";

        ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);

        try {
            URL url = new URL(ftpUrl);
            URLConnection conn = url.openConnection();
            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(filePath);

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();

            System.out.println("File uploaded");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void saveUser() {
        String sql = "insert into user values (null,'" + user.getUsername() + "', "
                + "'" + user.getPassword()+ "','" + user.getNama()+ "','" + user.getUsername() + "." + this.getEkstensi() + "',"
                + "now(),'"+user.getEmail()+"')";
    }

    private String getEkstensi() {
        String a = "";
        int mulai = 0;
        for (int i = this.user.getFoto().length() - 1; i >= 0; i--) {
            if (this.user.getFoto().charAt(i) == '.') {
                mulai = i + 1;
                break;
            }
        }
        a = this.user.getFoto().substring(mulai);
        return a;
    }
}
