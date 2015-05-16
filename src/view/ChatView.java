/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author blegoh
 */
public class ChatView extends javax.swing.JFrame {

    /**
     * Creates new form Chat
     */
    private URL url;
    private Image image;
    private Toolkit format;

    public ChatView() {
        initComponents();
        chat.requestFocus();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nama = new javax.swing.JLabel();
        lastSeen = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        chat = new javax.swing.JTextArea();
        btnSend = new javax.swing.JButton();
        foto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nama.setText("Nama");
        getContentPane().add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 320, -1));

        lastSeen.setText("Last Seen");
        getContentPane().add(lastSeen, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 310, -1));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jScrollPane2.setViewportView(panel);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 380, 200));

        chat.setColumns(20);
        chat.setRows(5);
        jScrollPane1.setViewportView(chat);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 320, 90));

        btnSend.setBackground(new java.awt.Color(51, 156, 50));
        btnSend.setText("Send");
        getContentPane().add(btnSend, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 270, 60, 90));
        getContentPane().add(foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void injek() {
        //jScrollPane2.getHorizontalScrollBar().setValue(0);
        panel.revalidate();
        int height = (int) panel.getPreferredSize().getHeight();
        Rectangle rect = new Rectangle(0, height, 10, 10);
        panel.scrollRectToVisible(rect);
    }
    public void sundul() {
        //jScrollPane2.getHorizontalScrollBar().setValue(0);
        panel.revalidate();
        Rectangle rect = new Rectangle(0, 0, 10, 10);
        panel.scrollRectToVisible(rect);
    }
    

    public void addConversation(JLabel conversation[], boolean z[]) {
        this.conversation = conversation;
        int x = 20;
        for (int i = 0; i < conversation.length; i++) {
            int pjg = getLongestString(conversation[i].getText(), "`").length();
            //double d = conversation[i].getPreferredSize().getWidth();
            int a = (z[i]) ? 340 - (pjg * 8) : 20;
            System.out.println(pjg);
            this.conversation[i].setFont(new Font("Consolas", Font.PLAIN, 14));
            this.panel.add(this.conversation[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(a, x, -1, -1));
            x += 40;
        }
        System.out.println("============");
    }
    
    private String getLongestString(String string,String pisah){
        string = clean(string);
        String[] a = string.split(pisah);
        string = "";
        for (String a1 : a) {
            string = (string.length() < a1.trim().length()) ? a1.trim() : string;
        }
        return string;
    }
    
    private String clean(String s){
        s = s.replace("<html>", "").replace("</html>", "");
        s = s.replace("<p>", "").replace("</p>", "`");
        s = s.replace("<br />", "`");
        return s;
    }

    public void detailListener(MouseAdapter listener) {
        nama.addMouseListener(listener);
    }

    public void removeConversation() {
        try {
            this.panel.removeAll();
            jScrollPane2.setFocusable(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addFoto(String ft) {
        try {
            format = Toolkit.getDefaultToolkit();
            url = new URL("http://localhost/chat/" + ft);
            image = ImageIO.read(url).getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon ico = new ImageIcon(image);
            foto.setIcon(ico);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void addFotoListener(MouseAdapter listener) {
        foto.addMouseListener(listener);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JTextArea chat;
    private javax.swing.JLabel foto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lastSeen;
    private javax.swing.JLabel nama;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
    private JLabel conversation[];

    public void setNama(String nama) {
        this.nama.setText(nama);
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen.setText(lastSeen);
    }

    public void addSendListener(ActionListener listener) {
        btnSend.addActionListener(listener);
    }

    public String getChat() {
        String chat = "<html>";
        chat += this.chat.getText();
        chat += "</html>";
        return chat;
    }

    public void clearChat() {
        chat.setText("");
    }

}
