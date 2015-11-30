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

    public final int idPiece;

    public NotificationPieceSelectionnee(int idPiece, NumeroJoueur joueurSource, EtatGUI nouvelEtat, EtatGUI etatPrecedent) {
        super(joueurSource, nouvelEtat, etatPrecedent);
        this.idPiece = idPiece;
    }

    @Override
    public String toString() {
        return super.toString() + "\b\b, IdPiece:"+idPiece+"]"; //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
