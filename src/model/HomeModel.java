package model;

import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import lib.Chat;
import lib.Conversation;
import lib.Koneksi;
import lib.User;

/**
 *
 * @author blegoh
 */
public class HomeModel {

    private User user;
    private JLabel nama[];
    private JLabel foto[];
    private JLabel lastChat[];
    private User friendUser[];
    private Koneksi kon;

    public HomeModel(User user) throws SQLException {
        this.user = user;
        kon = new Koneksi();
        String sql = "select user_id_sender,sender.username,sender.nama\n" +
                    "from chat " +
                    "left join user sender on chat.user_id_sender = sender.user_id \n" +
                    "left join user receiver on chat.user_id_receiver = receiver.user_id \n" +
                    "where user_id_receiver = " + user.getUserId()+" "+
                    "union " +
                    "select user_id_receiver,receiver.username,receiver.nama\n" +
                    "from chat " +
                    "left join user sender on chat.user_id_sender = sender.user_id \n" +
                    "left join user receiver on chat.user_id_receiver = receiver.user_id \n" +
                    "where user_id_sender = " +user.getUserId();
        kon.setQuery(sql);
        kon.executeQuery();
        //System.out.println(kon.getRow());
        nama = new JLabel[kon.getRow()];
        friendUser = new User[kon.getRow()];
        int i = 0;
        while (kon.getResult().next()) {
            nama[i] = new JLabel();
            String uname = kon.getResult().getString("username");
            friendUser[i] = new User(uname);
            nama[i++].setText(kon.getResult().getString("nama"));
        }
    }

    public JLabel[] getNama() {
        return nama;
    }
    
    public User[] getFriendUser(){
        return friendUser;
    }
    
    public JLabel[] getFoto(){
        foto = new JLabel[friendUser.length];
        for (int i = 0; i < foto.length; i++) {
            foto[i] = new JLabel(friendUser[i].getFoto());
        }
        return foto;
    }
    
    public JLabel[] getLastChat() throws SQLException{
        lastChat = new JLabel[friendUser.length];
        for (int i = 0; i < lastChat.length; i++) {
            Conversation c = new Conversation(user, friendUser[i]);
            Chat[] ch = c.getChat();
            lastChat[i] = new JLabel(ch[ch.length-1].getIsi());
        }
        return lastChat;
    }
    
    public User getUser(){
        return user;
    }

    public void close() throws SQLException{
        kon.close();
    }
}
