package lib;

import model.User;

/**
 *
 * @author blegoh
 */
public class Chat {
    
    private int id;
    private String isi;
    private String chatTime;
    private User sender;
    private User receiver;
    
    public Chat(int id, User sender, User Receiver){
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
    }
    
    public void setIsi(String isi){
        this.isi = isi;
    }
    
    public void setChatTime(String chatTime){
        this.chatTime = chatTime;
    }
    
    public User getSender(){
        return sender;
    }
    
    public User getReceiver(){
        return receiver;
    }
    
    public String getIsi(){
        return isi;
    }
    
    public String getChatTime(){
        return chatTime;
    }
}
