/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur.observables;

import Database.Compte;
import model.EtatGUI;
import model.NumeroJoueur;

/**
 *
 * @author Anis
 */
public class NotificationPartieSupprimee extends Notification {

    public final Compte Joueur;

    public NotificationPartieSupprimee(Compte Joueur, NumeroJoueur joueurSource, EtatGUI nouvelEtat, EtatGUI etatPrecedent) {
        super(joueurSource, nouvelEtat, etatPrecedent);
        this.Joueur = Joueur;
    }

}
