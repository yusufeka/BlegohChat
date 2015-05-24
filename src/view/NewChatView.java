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
public class NewChatView extends javax.swing.JFrame {

    private URL url;
    private Image image;
    private Toolkit format;
    
    public NewChatView() {
        initComponents();
        btnKontak = new ArrayList<>();
        panel = new ArrayList<>();
        format = Toolkit.getDefaultToolkit();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        mbok = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(java.awt.Color.green);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 10, 320, -1));

        btnBack.setBackground(new java.awt.Color(51, 156, 50));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/back64.png"))); // NOI18N
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 59, -1));

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/social-network14(4).png"))); // NOI18N
        btnAdd.setBorderPainted(false);
        btnAdd.setContentAreaFilled(false);
        jPanel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 51, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 40));

        mbok.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jScrollPane1.setViewportView(mbok);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 420, 250));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mbok;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
    private ArrayList<JButton> btnKontak;
    private ArrayList<JPanel> panel;
    private int a = 10;
    
    public void addKontak(String nama, String foto, String status) throws MalformedURLException, IOException{
        JButton btn = new JButton();
        btn.setContentAreaFilled(false);
        btnKontak.add(btn);
        url = new URL("http://localhost/chat/" + foto);
        image = ImageIO.read(url).getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon ico = new ImageIcon(image);
        JLabel ft = new JLabel(ico);
        JLabel nm = new JLabel(nama);
        JLabel st = new JLabel(status);
        JPanel p = new JPanel(new org.netbeans.lib.awtextra.AbsoluteLayout());
        p.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        p.add(ft, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 60));
        p.add(nm, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 310, 30));
        p.add(st, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 310, 30));
        p.add(btn,new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 0, 380, 60));
        panel.add(p);
        mbok.add(p, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, a, 380, 60));
        a+=60;
    }
    
//    public void addBtnKontak(JLabel nama[],JLabel foto[],JLabel status[]) throws IOException{
//        this.nama = nama;
//        btnKontak = new JButton[nama.length];
//        this.foto = foto;
//        this.status = status;
//        panel = new JPanel[nama.length];
//        this.add();
//    }
//    
//    public void add() throws MalformedURLException, IOException{
//        int a = 10;
//        for (int i = 0; i < btnKontak.length; i++) {
//            btnKontak[i] = new JButton();
//            btnKontak[i].setContentAreaFilled(false);
//            panel[i] = new JPanel(new org.netbeans.lib.awtextra.AbsoluteLayout());
//            panel[i].setBorder(javax.swing.BorderFactory.createEtchedBorder());
//            panel[i].add(foto[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 60));
//            panel[i].add(nama[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 310, 30));
//            panel[i].add(status[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 310, 30));
//            panel[i].add(btnKontak[i],new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 0, 380, 60));
//            mbok.add(panel[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(14, a, 380, 60));
//            url = new URL("http://localhost/chat/" + foto[i].getText());
//            image = ImageIO.read(url).getScaledInstance(50, 50, Image.SCALE_SMOOTH);
//            foto[i].setText("");
//            ImageIcon ico = new ImageIcon(image);
//            foto[i].setIcon(ico);
//            a+=60;
//        }
//        this.pack();
//    }
    
    public void addChatListener(int i, ActionListener listener) {
        btnKontak.get(i).addActionListener(listener);
    }
    
    public void addBackListener(ActionListener listener){
        btnBack.addActionListener(listener);
    }
    
    public void addAddListener(ActionListener listener){
        btnAdd.addActionListener(listener);
    }
}
