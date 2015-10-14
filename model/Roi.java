 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static java.lang.Math.abs;

/**
 *
 * @author Anis
 */
public class Roi extends AbstractPiece {

    public Roi(String name, Couleur couleur, Coord coord) {
        super(name, couleur, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {

        return abs(getX() - xFinal) <= 1 && abs(yFinal - getY()) <= 1;
    }

    @Override
    protected AbstractPiece clone() {

        
        Roi clone = new Roi(name, couleur, coord.clone());
        clone.capture = capture;
        return clone;
        
    }

    
    
}
