/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur.observables;

import model.Coord;

/**
 *
 * @author Anis
 */
public abstract class Notification {

    
    public final Coord coord;
    
    public final String nomPiece;

    public Notification(String nomPiece, Coord coord) {
        this.coord = coord;
        this.nomPiece = nomPiece;
    }
    
    
        
}
