/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur.observables;

import model.Coord;
import model.EtatGUI;
import model.NumeroJoueur;

/**
 *
 * @author Anis
 */
public class NotificationPiecePlacee extends Notification{
    
    public final Coord casePlateau;

    public NotificationPiecePlacee(Coord casePlateau, NumeroJoueur joueurSource, EtatGUI nouvelEtat, EtatGUI etatPrecedent) {
        super(joueurSource, nouvelEtat, etatPrecedent);
        this.casePlateau = casePlateau;
    }

  
    
    
}
