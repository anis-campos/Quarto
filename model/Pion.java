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
public class Pion extends AbstractPiece implements Pions {

    boolean depart;

    public Pion(String name, Couleur couleur, Coord coord) {
        super(name, couleur, coord);
        this.depart = true;
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
       
        if (!(xFinal == this.getX())) {
            return false;
        }

        int diffY = yFinal - getY();

        switch (this.couleur) {
            case BLANC:
                return diffY<0&& diffY >= (depart ? -2 : -1);

            case NOIR:
                return diffY>0&&diffY <= (depart ? 2 : 1);
            default:
                return false;
        }

    }

    @Override
    public boolean isMoveDiagOk(int xFinal, int yFinal) {

        if (abs(getX() - xFinal) != 1) {
            return false;
        }
        switch (this.couleur) {
            case BLANC:
                return (getY() - yFinal == 1);

            case NOIR:
                return (yFinal - getY() == 1);
            default:
                return false;
        }
    }

    @Override
    protected AbstractPiece clone() {

        Pion clone = new Pion(name, couleur, coord.clone());
        clone.capture = capture;
        return clone;
    }

}
