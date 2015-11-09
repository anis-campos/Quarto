/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import launcher.localLauncher.PartieBuilder;
import model.Joueur;
import model.NumeroJoueur;
import model.Parametre;

/**
 *
 * @author timotheetroncy
 */
public class JPanelParametres extends javax.swing.JPanel {

    /**
     * Creates new form JPanelParametres
     */
    public JPanelParametres() {
        this.minCharNumber = 3;
        this.maxCharNumber = 10;
        initComponents();
        this.QuartoAutoValidation.setVisible(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CommencerPartie = new javax.swing.JButton();
        RetourMenu = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        Taille = new javax.swing.JCheckBox();
        Creux = new javax.swing.JCheckBox();
        Couleur = new javax.swing.JCheckBox();
        Forme = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        QuartoCarre = new javax.swing.JCheckBox();
        QuartoAutoValidation = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        joueur1 = new javax.swing.JTextField();
        joueur2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JoueurRandom = new javax.swing.JCheckBox();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setName("parametres"); // NOI18N
        setOpaque(false);

        CommencerPartie.setText("Commencer la partie");
        CommencerPartie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CommencerPartieActionPerformed(evt);
            }
        });

        RetourMenu.setText("Retour au menu");
        RetourMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RetourMenuActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Paramètres du jeu de pièce"));
        jPanel4.setOpaque(false);

        Taille.setSelected(true);
        Taille.setText("Taille");
        Taille.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TailleActionPerformed(evt);
            }
        });

        Creux.setSelected(true);
        Creux.setText("Creux");
        Creux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreuxActionPerformed(evt);
            }
        });

        Couleur.setSelected(true);
        Couleur.setText("Couleur");
        Couleur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CouleurActionPerformed(evt);
            }
        });

        Forme.setSelected(true);
        Forme.setText("Forme");
        Forme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FormeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Taille)
                    .addComponent(Forme)
                    .addComponent(Couleur)
                    .addComponent(Creux))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(Taille)
                .addGap(18, 18, 18)
                .addComponent(Forme)
                .addGap(18, 18, 18)
                .addComponent(Couleur)
                .addGap(18, 18, 18)
                .addComponent(Creux)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Paramètrage du Quarto"));
        jPanel5.setOpaque(false);

        QuartoCarre.setSelected(true);
        QuartoCarre.setText("Activation Quarto par assemblage carré groupé");
        QuartoCarre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuartoCarreActionPerformed(evt);
            }
        });

        QuartoAutoValidation.setSelected(true);
        QuartoAutoValidation.setText("Activation de la validation automatique du Quarto");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(QuartoAutoValidation)
                    .addComponent(QuartoCarre))
                .addGap(72, 72, 72))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(QuartoCarre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(QuartoAutoValidation)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Joueurs"));
        jPanel1.setOpaque(false);

        joueur1.setText("Joueur 1\n");
        joueur1.setName("Joueur1\n"); // NOI18N
        this.joueur1.setTransferHandler(null);
        joueur1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                joueur1PropertyChange(evt);
            }
        });
        joueur1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                J1KeyTyped(evt);
            }
        });

        joueur2.setText("Joueur 2");
        joueur2.setName("Joueur2"); // NOI18N
        this.joueur2.setTransferHandler(null);
        joueur2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                J2KeyTyped(evt);
            }
        });

        jLabel1.setText("Joueur 1");

        jLabel2.setText("Joueur 2");

        JoueurRandom.setSelected(true);
        JoueurRandom.setText("Désignation aléatoire du premier joueur");
        JoueurRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JoueurRandomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(joueur1, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                            .addComponent(joueur2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JoueurRandom)
                        .addGap(89, 89, 89))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(joueur1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(joueur2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(JoueurRandom)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(RetourMenu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CommencerPartie))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CommencerPartie, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RetourMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TailleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TailleActionPerformed
        if (!this.Couleur.isSelected() && !this.Creux.isSelected() && !this.Forme.isSelected()) {
            this.Taille.setSelected(true);
        }
    }//GEN-LAST:event_TailleActionPerformed

    private void CouleurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CouleurActionPerformed
        if (!this.Taille.isSelected() && !this.Creux.isSelected() && !this.Forme.isSelected()) {
            this.Couleur.setSelected(true);
        }
    }//GEN-LAST:event_CouleurActionPerformed

    private void QuartoCarreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuartoCarreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QuartoCarreActionPerformed

    private void RetourMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RetourMenuActionPerformed
        CardLayout cl = (CardLayout) this.getParent().getLayout();
        cl.show(this.getParent(), "menu");
    }//GEN-LAST:event_RetourMenuActionPerformed

    private void CommencerPartieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CommencerPartieActionPerformed
        PartieBuilder.buildPartie(getParametres(), getJoueur1(), getJoueur2(), this);
    }//GEN-LAST:event_CommencerPartieActionPerformed

    private void FormeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FormeActionPerformed
        if (!this.Couleur.isSelected() && !this.Creux.isSelected() && !this.Taille.isSelected()) {
            this.Forme.setSelected(true);
        }
    }//GEN-LAST:event_FormeActionPerformed

    private void CreuxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreuxActionPerformed
        if (!this.Couleur.isSelected() && !this.Taille.isSelected() && !this.Forme.isSelected()) {
            this.Creux.setSelected(true);
        }
    }//GEN-LAST:event_CreuxActionPerformed

    private void JoueurRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JoueurRandomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JoueurRandomActionPerformed

    private void J1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_J1KeyTyped
        if (this.joueur1.getText().length() >= this.maxCharNumber){
            evt.consume();
        }
    }//GEN-LAST:event_J1KeyTyped

    private void J2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_J2KeyTyped
        if (this.joueur2.getText().length() >= this.maxCharNumber){
            evt.consume();
        }
    }//GEN-LAST:event_J2KeyTyped

    private void joueur1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_joueur1PropertyChange

    }//GEN-LAST:event_joueur1PropertyChange

    private Parametre getParametres() {
        return new Parametre(
                this.Forme.isSelected(), 
                this.Taille.isSelected(), 
                this.Couleur.isSelected(), 
                this.Creux.isSelected(), 
                this.QuartoCarre.isSelected(),
                this.QuartoAutoValidation.isSelected(),
                this.JoueurRandom.isSelected()
        );
    }

    private Joueur getJoueur1() {
        return new Joueur(this.joueur1.getText(), false, NumeroJoueur.J1);
    }

    private Joueur getJoueur2() {
        return new Joueur(this.joueur2.getText(), false, NumeroJoueur.J2);
    }
    private final int maxCharNumber;
    private final int minCharNumber;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CommencerPartie;
    private javax.swing.JCheckBox Couleur;
    private javax.swing.JCheckBox Creux;
    private javax.swing.JCheckBox Forme;
    private javax.swing.JCheckBox JoueurRandom;
    private javax.swing.JCheckBox QuartoAutoValidation;
    private javax.swing.JCheckBox QuartoCarre;
    private javax.swing.JButton RetourMenu;
    private javax.swing.JCheckBox Taille;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField joueur1;
    private javax.swing.JTextField joueur2;
    // End of variables declaration//GEN-END:variables
}
