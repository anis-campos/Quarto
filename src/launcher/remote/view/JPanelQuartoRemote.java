/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.remote.view;

import Network.RMI.Interface.IJeu;
import controlleur.IControlleur;
import controlleur.observables.*;
import java.awt.Dimension;
import java.util.Observable;
import javax.swing.JOptionPane;
import view.JPanelQuarto;

/**
 *
 * @author Anis
 */
public class JPanelQuartoRemote extends JPanelQuarto {

    private IJeu JeuDistant;
    private boolean fini;
    
    public JPanelQuartoRemote(IControlleur controleur, Dimension dimensionCase, IJeu jeuDistant) {
        super(controleur, dimensionCase);
        
        this.JeuDistant = jeuDistant;
        fini = false;
        
        super.cacherAfficherMenu();
        
        super.Titre.setVisible(false);
        
        /*
        bAfficherMenu.addActionListener(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fini = true;
                    JeuDistant.quiterPartie();
                } catch (RemoteException ex) {
                    Logger.getLogger(JPanelQuartoRemote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });*/
    }

    @Override
    public void update(Observable o, Object arg) {
        if(fini) return;
        
        super.update(o, arg);

        
        //GESTION DES NOTIF RESEAU
        Notification notif = (Notification) arg;

        if (notif instanceof NotificationErreurReseau) {
            JOptionPane.showMessageDialog(this, "Exception :\n" + ((NotificationErreurReseau) notif).exception, "Erreur Fatale !", JOptionPane.ERROR_MESSAGE);
        }

        if (notif instanceof NotificationJoueurAQuitte) {
            JOptionPane.showMessageDialog(this, "Le joueur c'est deconnecté : "+((NotificationJoueurAQuitte)notif).Joueur, "Info", JOptionPane.ERROR_MESSAGE);
        }
        
        if(notif instanceof NotificationPartieSupprimee){
            JOptionPane.showMessageDialog(this, "Le joueur a supprimé la partie : "+((NotificationPartieSupprimee)notif).Joueur, "Info", JOptionPane.ERROR_MESSAGE);
            this.bAfficherMenu.doClick();
        }

    }

}
