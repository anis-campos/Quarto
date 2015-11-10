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
import model.SortieGUI;

/**
 *
 * @author Flo
 */
public class NotificationQuartoAnnoncer extends Notification {
    
    private final ArrayList<ArrayList<Coord>> quartos;

    public NotificationQuartoAnnoncer(ArrayList<ArrayList<Coord>> quartos,NumeroJoueur joueurSource, EtatGUI nouvelEtat, EtatGUI etatPrecedent, SortieGUI sortie) {
        super(joueurSource, nouvelEtat, etatPrecedent, sortie);
        this.quartos = quartos;
    }

}
