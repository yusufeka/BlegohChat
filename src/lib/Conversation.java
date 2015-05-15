package lib;

import java.sql.SQLException;

/**
 *
 * @author blegoh
 */
public class Conversation {

    private User user;
    private User friendUser;
    private Chat[] chat;
    private Koneksi kon;

    public Conversation(User user, User friendUser) throws SQLException {
        this.user = user;
        this.friendUser = friendUser;
        kon = new Koneksi();
        kon.from("chat");
        kon.where("(user_id_sender = "+user.getUserId()+" and user_id_receiver = "+friendUser.getUserId()+") "
                + "or  (user_id_sender = "+friendUser.getUserId()+" and user_id_receiver = "+user.getUserId()+")");
        kon.executeQuery();
        chat = new Chat[kon.getRow()];
        int i = 0;
        while(kon.getResult().next()){
            int id = kon.getResult().getInt("id_chat");
            int senderId = kon.getResult().getInt("user_id_sender");
            int receiverId = kon.getResult().getInt("user_id_receiver");
            User sender = new User(senderId);
            User receiver = new User(receiverId);
            chat[i] = new Chat(id,sender,receiver);
            chat[i].setIsi(kon.getResult().getString("isi_chat"));
            chat[i++].setChatTime(kon.getResult().getString("chat_time"));
        }
        kon.close();
    }
    
    public User getUser(){
        return user;
    }

    public Chat[] getChat() {
        return chat;
    }
}
