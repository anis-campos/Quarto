/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur.observables;

import java.util.ArrayList;
import model.Coord;
import model.EtatGUI;
import model.NumeroJoueur;

/**
 *
 * @author Flo
 */
public class NotificationQuartoDetecte extends Notification {

    private final ArrayList<ArrayList<Coord>> quartos;

    public NotificationQuartoDetecte(ArrayList<ArrayList<Coord>> quartos, NumeroJoueur joueurSource, EtatGUI nouvelEtat, EtatGUI etatPrecedent) {
        super(joueurSource, nouvelEtat, etatPrecedent);

        this.quartos = quartos;
    }
    
    public ArrayList<ArrayList<Coord>> getQuartos(){
        return quartos;
    }

}
