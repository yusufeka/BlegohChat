/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author blegoh
 */
public class HomeView extends javax.swing.JFrame {

    private URL url;
    private Image image;
    private Toolkit format;

    public HomeView() {
        initComponents();
        btnChat = new ArrayList<>();
        panel = new ArrayList<>();
        format = Toolkit.getDefaultToolkit();
        Image img = format.getImage(this.getClass().getResource("/ico/gear39.png")).getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon ico = new ImageIcon(img);
        btnSetting.setIcon(ico);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        btnNewChat = new javax.swing.JButton();
        btnSetting = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        mbok = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(java.awt.Color.green);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 290, 30));

        btnNewChat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/add68(4).png"))); // NOI18N
        btnNewChat.setBorderPainted(false);
        btnNewChat.setContentAreaFilled(false);
        jPanel1.add(btnNewChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, -1, -1));

        btnSetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/gear39.png"))); // NOI18N
        btnSetting.setBorderPainted(false);
        btnSetting.setContentAreaFilled(false);
        btnSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingActionPerformed(evt);
            }
        });
        jPanel1.add(btnSetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 40));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mbok.setBorder(null);
        mbok.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jScrollPane1.setViewportView(mbok);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 400, 250));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSettingActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNewChat;
    private javax.swing.JButton btnSetting;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel mbok;
    // End of variables declaration//GEN-END:variables
    private ArrayList<JButton> btnChat;
    private ArrayList<JPanel> panel;
    private int a = 10;

    public void addBtnChat(String nama, String foto, String lastChat) throws MalformedURLException, IOException {
        JButton chat = new JButton();
        chat.setContentAreaFilled(false);
        btnChat.add(chat);
        JLabel namaItem = new JLabel(nama);
        JLabel fotoItem = new JLabel();
        url = new URL("http://localhost/chat/" + foto);
        image = ImageIO.read(url).getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon ico = new ImageIcon(image);
        fotoItem.setIcon(ico);
        JLabel lastChatItem = new JLabel(lastChat); //jgn di masukkan arraylist dulu
        JPanel panelItem = new JPanel(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelItem.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelItem.add(fotoItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 60));
        panelItem.add(namaItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 310, 30));
        panelItem.add(lastChatItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 310, 30));
        panelItem.add(chat, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 0, 380, 60));
        panel.add(panelItem);
        mbok.add(panelItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, a, 380, 60));
        mbok.revalidate();
        this.a += 60;
    }

    public void addChatListener(int i, ActionListener listener) {
        btnChat.get(i).addActionListener(listener);
    }

    public void addNewChatListener(ActionListener listener) {
        btnNewChat.addActionListener(listener);
    }

    public void addSettingListener(ActionListener listener) {
        btnSetting.addActionListener(listener);
    }
}
