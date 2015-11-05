/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur.observables;

import model.NumeroJoueur;
import view.EtatGUI;

/**
 *
 * @author Anis
 */
public class NotificationPieceSelectionnee extends Notification {

    public final String NomPiece;

    public NotificationPieceSelectionnee(String NomPiece, NumeroJoueur joueurSource, EtatGUI nouvelEtat, EtatGUI etatPrecedent) {
        super(joueurSource, nouvelEtat, etatPrecedent);
        this.NomPiece = NomPiece;
    }
   
    
}
