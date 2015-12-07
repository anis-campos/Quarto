/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.remote.view;

import Database.Compte;
import Network.RMI.Exceptions.PartieDoublonException;
import Network.RMI.Interface.IJeu;
import Network.RMI.Interface.ISession;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Parametre;

/**
 *
 * @author Anis
 */
public class Menu extends javax.swing.JPanel {

    private final ISession session;
    private IJeu jeuEnCour;

    /**
     * Creates new form Menu
     *
     * @param session
     */
    public Menu(ISession session) {
        initComponents();
        this.session = session;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Deconnexion = new javax.swing.JButton();
        NewPartieAdversaire = new javax.swing.JButton();
        RejoindrePartie = new javax.swing.JButton();
        NewPartie = new javax.swing.JButton();

        Deconnexion.setText("Deconnexion");
        Deconnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeconnexionActionPerformed(evt);
            }
        });

        NewPartieAdversaire.setText("Partie negga vs escroc");
        NewPartieAdversaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewPartieAdversaireActionPerformed(evt);
            }
        });

        RejoindrePartie.setText("Rejoindre une partie");
        RejoindrePartie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RejoindrePartieActionPerformed(evt);
            }
        });

        NewPartie.setText("Nouvelle partie");
        NewPartie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewPartieActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(105, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(RejoindrePartie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NewPartieAdversaire, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Deconnexion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NewPartie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(NewPartie)
                .addGap(18, 18, 18)
                .addComponent(NewPartieAdversaire)
                .addGap(18, 18, 18)
                .addComponent(RejoindrePartie)
                .addGap(28, 28, 28)
                .addComponent(Deconnexion)
                .addContainerGap(63, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void DeconnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeconnexionActionPerformed
        try {
            ModeReseau.getInstance().logout();
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);

            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_DeconnexionActionPerformed

    private void NewPartieAdversaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewPartieAdversaireActionPerformed
        try {
            Compte adversaire = session.listeComptes().get(0);
            Parametre parametre = new Parametre(true, true, true, true, true, true, false, false, false);
            ModeReseau.getInstance().creerPartieAvecAdversaire(parametre, adversaire);
        } catch (RemoteException ex) {

            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PartieDoublonException ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_NewPartieAdversaireActionPerformed

    private void RejoindrePartieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RejoindrePartieActionPerformed
        try {
            ModeReseau.getInstance(null).afficherList();
        } catch (RemoteException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_RejoindrePartieActionPerformed

    private void NewPartieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewPartieActionPerformed
        ModeReseau.getInstance(null).afficherParametrage();
    }//GEN-LAST:event_NewPartieActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Deconnexion;
    private javax.swing.JButton NewPartie;
    private javax.swing.JButton NewPartieAdversaire;
    private javax.swing.JButton RejoindrePartie;
    // End of variables declaration//GEN-END:variables
}
