/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import lib.User;
import lib.Conversation;
import java.sql.SQLException;
import lib.Koneksi;

/**
 *
 * @author blegoh
 */
public class ChatModel {

    private User user;
    private User friendUser;
    private Koneksi kon;
    private Conversation convertation;

    public ChatModel(User user, User friendUser) throws SQLException {
        this.user = user;
        this.friendUser = friendUser;
        kon = new Koneksi();
        convertation = new Conversation(user, friendUser);
    }

    public void refreshConversation() throws SQLException {
        convertation = new Conversation(user, friendUser);
    }

    public Conversation getConversation() {
        return convertation;
    }

    public String getNewFriendChat(int i) {
        return convertation.getChat().get(i).getIsi();
    }

    public User getUser() {
        return user;
    }

    public User getFriendUser() {
        return friendUser;
    }

    public String getName() throws SQLException {
        return friendUser.getNama();
    }

    public String getLastSeen() {
        return friendUser.getLastSeen();
    }

    public void chat(String isiChat) throws SQLException {
        int userIdSender = this.user.getUserId();
        int userIdReceiver = this.friendUser.getUserId();
        String sql = "insert into chat values(null," + userIdSender + "," + userIdReceiver + ",'" + isiChat + "',now(),0,0)";
        kon.setQuery(sql);
        kon.executeUpdate();
    }

    public void hapusChat() throws SQLException {
        int userIdSender = this.user.getUserId();
        int userIdReceiver = this.friendUser.getUserId();
        String sql = "update chat set is_sender_delete = 1 "
                + "where user_id_sender = " + user.getUserId()
                + " and user_id_receiver = " + friendUser.getUserId();
        kon.setQuery(sql);
        kon.executeUpdate();
        sql = "update chat set is_receiver_delete = 1 "
                + "where user_id_receiver = " + user.getUserId()
                + " and user_id_sender = " + friendUser.getUserId();
        kon.setQuery(sql);
        kon.executeUpdate();
    }

}
