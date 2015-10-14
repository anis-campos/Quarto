/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static java.lang.Math.abs;

/**
 *
 * @author Antoine
 */
public class Fou extends AbstractPiece {

    public Fou(String name, Couleur couleur, Coord coord) {
        super(name, couleur, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
        return abs(xFinal - getX()) == abs(yFinal - getY());
    }

    @Override
    protected AbstractPiece clone() {

        Fou clone = new Fou(name, couleur, coord.clone());
        clone.capture = capture;
        return clone;

    }

}
