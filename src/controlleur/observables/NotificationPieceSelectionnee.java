/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur.observables;

import model.NumeroJoueur;
import model.EtatGUI;
import model.SortieGUI;

/**
 *
 * @author Anis
 */
public class NotificationPieceSelectionnee extends Notification {

    public final String NomPiece;

    public NotificationPieceSelectionnee(String NomPiece, NumeroJoueur joueurSource, EtatGUI nouvelEtat, EtatGUI etatPrecedent, SortieGUI sortie) {
        super(joueurSource, nouvelEtat, etatPrecedent, sortie);
        this.NomPiece = NomPiece;
    }

   
    
}
