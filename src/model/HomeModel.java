package model;

import lib.User;
import lib.Conversation;
import java.sql.SQLException;
import java.util.ArrayList;
import lib.Chat;
import lib.Koneksi;

/**
 *
 * @author blegoh
 */
public class HomeModel {

    private User user;
    private ArrayList<User> friendUser;
    private Koneksi kon;

    public HomeModel(User user) throws SQLException {
        this.user = user;
        kon = new Koneksi();
        friendUser = new ArrayList<>();
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
        while (kon.getResult().next()) {
            String uname = kon.getResult().getString("username");
            friendUser.add(new User(uname));
        }
        kon.close();
    }
    
    public ArrayList<User> getFriendUser(){
        return friendUser;
    }
    
    public User getUser(){
        return user;
    }

}
