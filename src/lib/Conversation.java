package lib;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author blegoh
 */
public class Conversation {

    private User user;
    private User friendUser;
    private ArrayList<Chat> chat;
    private Koneksi kon;

    public Conversation(User user, User friendUser) throws SQLException {
        this.user = user;
        this.friendUser = friendUser;
        kon = new Koneksi();
        kon.from("chat");
        kon.where("(user_id_sender = "+user.getUserId()+" and user_id_receiver = "+friendUser.getUserId()+") "
                + "or  (user_id_sender = "+friendUser.getUserId()+" and user_id_receiver = "+user.getUserId()+")");
        kon.executeQuery();
        chat = new ArrayList<>();
        while(kon.getResult().next()){
            int id = kon.getResult().getInt("id_chat");
            int senderId = kon.getResult().getInt("user_id_sender");
            int receiverId = kon.getResult().getInt("user_id_receiver");
            User sender = new User(senderId);
            User receiver = new User(receiverId);
            Chat x = new Chat(id,sender,receiver);
            x.setIsi(kon.getResult().getString("isi_chat"));
            x.setChatTime(kon.getResult().getString("chat_time"));
            chat.add(x);
        }
        kon.close();
    }
    
    public User getUser(){
        return user;
    }

    public ArrayList<Chat> getChat() {
        return chat;
    }
    
    public void addChat(String chat){
        //this.chat.add(chat);
    }
}
