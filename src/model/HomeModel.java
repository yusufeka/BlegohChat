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
        String table = "from chat c\n"
                + "left join user sender on c.user_id_sender = sender.user_id\n"
                + "left join user receiver on c.user_id_receiver = receiver.user_id\n";
        String sql = "select user_id_sender,sender.username,sender.nama,(\n"
                + "select max(chat_time) from chat \n"
                + "where (user_id_receiver = " + user.getUserId() + " and user_id_sender = c.user_id_sender) or\n"
                + "(user_id_receiver = c.user_id_sender and user_id_sender = " + user.getUserId() + ")\n"
                + ") time\n"
                + table + "where user_id_receiver = " + user.getUserId() +"\n"
                + "group by user_id_sender,sender.username ,sender.nama\n"
                + "union \n"
                + "select user_id_receiver,receiver.username,receiver.nama,\n"
                + "(\n"
                + "select max(chat_time) from chat \n"
                + "where (user_id_sender = " + user.getUserId() + " and user_id_receiver = c.user_id_receiver) or\n"
                + "(user_id_sender = c.user_id_receiver and user_id_receiver = " + user.getUserId() + " )\n"
                + ") time\n"
                + table + "where user_id_sender = " + user.getUserId()+"\n"
                + "group by user_id_receiver,receiver.username,receiver.nama\n"
                + "order by time desc";
        kon.setQuery(sql);
        kon.executeQuery();
        while (kon.getResult().next()) {
            String uname = kon.getResult().getString("username");
            friendUser.add(new User(uname));
        }
        kon.close();
    }

    public ArrayList<User> getFriendUser() {
        return friendUser;
    }

    public User getUser() {
        return user;
    }

}
