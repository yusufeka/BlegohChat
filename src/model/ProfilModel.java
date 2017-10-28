/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import lib.User;
import java.sql.SQLException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

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
        this.user.setNama(nama);
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
