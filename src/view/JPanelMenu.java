/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controlleur.ControllerLocal;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import launcher.localLauncher.PartieBuilder;
import model.Joueur;
import model.NumeroJoueur;
import model.Parametre;
import model.Partie;
import model.Piece;

/**
 *
 * @author timotheetroncy
 */
public class JPanelMenu extends javax.swing.JPanel {

    /**
     * Creates new form JPanelMenu
     */
    public JPanelMenu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonParametrer = new javax.swing.JButton();
        jButtonCommencer = new javax.swing.JButton();
        jButtonContinuer = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setName("menu"); // NOI18N
        setOpaque(false);
        setSize(new java.awt.Dimension(396, 461));

        jButtonParametrer.setText("Paramétrer Partie");
        jButtonParametrer.setName("jButtonParametrer"); // NOI18N
        jButtonParametrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonParametrerActionPerformed(evt);
            }
        });

        jButtonCommencer.setText("Commencer nouvelle partie");
        jButtonCommencer.setName("jButtonCommencer"); // NOI18N
        jButtonCommencer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCommencerActionPerformed(evt);
            }
        });

        jButtonContinuer.setText("Continuer Partie");
        jButtonContinuer.setEnabled(false);
        jButtonContinuer.setName("jButtonContinuer"); // NOI18N
        jButtonContinuer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonContinuerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCommencer, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonParametrer, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonContinuer, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addComponent(jButtonParametrer, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButtonCommencer, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButtonContinuer, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonParametrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonParametrerActionPerformed
        CardLayout cl = (CardLayout) this.getParent().getLayout();
        cl.show(this.getParent(), "parametres");
    }//GEN-LAST:event_jButtonParametrerActionPerformed

    private void jButtonCommencerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCommencerActionPerformed
        Parametre p = new Parametre(true, true, true, true, true,true);
        Joueur j1 = new Joueur("Joueur 1", false, NumeroJoueur.J1);
        Joueur j2 = new Joueur("Joueur 2", false, NumeroJoueur.J2);
        PartieBuilder.buildPartie(p, j1, j2, this);
    }//GEN-LAST:event_jButtonCommencerActionPerformed

    private void jButtonContinuerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonContinuerActionPerformed
        CardLayout cl = (CardLayout) this.getParent().getLayout();
        cl.show(this.getParent(), "jeu");
    }//GEN-LAST:event_jButtonContinuerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCommencer;
    private javax.swing.JButton jButtonContinuer;
    private javax.swing.JButton jButtonParametrer;
    // End of variables declaration//GEN-END:variables
}
