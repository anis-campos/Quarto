/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur.observables;

import model.EtatGUI;
import model.NumeroJoueur;
import model.SortieGUI;

/**
 *
 * @author Anis
 */
public class NotificationMatchNullAnnonce extends Notification{

    public NotificationMatchNullAnnonce(NumeroJoueur joueurSource, EtatGUI nouvelEtat, EtatGUI etatPrecedent) {
        super(joueurSource, nouvelEtat, etatPrecedent);
    }
    
}
