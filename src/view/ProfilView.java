package view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author blegoh
 */
public class ProfilView extends javax.swing.JFrame {
    private URL url;
    private Image image;
    private Toolkit format;

    public ProfilView() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        foto = new javax.swing.JLabel();
        nama = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        foto.setBackground(new java.awt.Color(79, 245, 23));
        foto.setForeground(new java.awt.Color(34, 230, 109));
        foto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foto.setText("Iki Foto");

        nama.setText("Nama");

        btnEdit.setBackground(new java.awt.Color(51, 156, 50));
        btnEdit.setText("Edit Profil");

        btnBack.setText("Kembali");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(foto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(foto, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nama)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnBack))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel foto;
    private javax.swing.JLabel nama;
    // End of variables declaration//GEN-END:variables
    
    public void setFoto(String f) throws MalformedURLException, IOException{
        url = new URL("http://localhost/chat/" + f);
        image = ImageIO.read(url).getScaledInstance(170, 170, Image.SCALE_SMOOTH);
        foto.setText("");
        ImageIcon ico = new ImageIcon(image);
        foto.setIcon(ico);
    }
    
    public void setNama(String nama){
        this.nama.setText(nama);
    }
    
    public void removeEdit(){
        btnEdit.setVisible(false);
    }
    
    public void addEditListener(ActionListener listener){
        btnEdit.addActionListener(listener);
    }
    
    public void addBackListener(ActionListener listener){
        btnBack.addActionListener(listener);
    }
}
