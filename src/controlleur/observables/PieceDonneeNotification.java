/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur.observables;

import model.Coord;
import model.NumeroJoueur;

/**
 *
 * @author Anis
 */
public class PieceDonneeNotification extends Notification {

    public final NumeroJoueur source;


    public PieceDonneeNotification(String nomPiece, Coord coord, NumeroJoueur source) {
        super(nomPiece, coord);
        this.source = source;
        
    }

}
