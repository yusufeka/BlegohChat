/*
 * Author Blegoh aka Yusuf Eka
 * Copyright 2015
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import lib.Koneksi;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;


/**
 *
 * @author blegoh
 */
public class RegisterModel {

    private User user;
    private Koneksi kon;
    private static final int BUFFER_SIZE = 4096;
    private boolean isValid;

    public RegisterModel() {
        kon = new Koneksi();
        user = new User();
    }
    
    public void setUserID() throws SQLException{
        user.setUserID();
    }

    public void setUsername(String username) throws SQLException {
        this.user.setUsername(username);
    }

    public void setEmail(String email) throws SQLException {
        this.user.setEmail(email);
    }

    public void setNama(String nama) {
        this.user.SetNama(nama);
    }

    public void setFoto(String foto) {
        this.user.setFoto(foto);
    }

    public void setPassword(String password, String confirm) {
        this.user.setPassword(password, confirm);
    }

    public void uploadFoto(String path) {
        String server = "localhost";
        int port = 21;
        String user = "user1";
        String pass = "itsme";

        FTPClient ftpClient = new FTPClient();
        try {

            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File(path);

            String firstRemoteFile = this.user.getFoto();
            InputStream inputStream = new FileInputStream(firstLocalFile);

            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }
            inputStream.close();

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveUser() throws SQLException {
        user.saveUser();
    }

    public boolean isValid() {
        return user.isValid();
    }

    public String getPesan() {
        return user.getPesan();
    }
}
