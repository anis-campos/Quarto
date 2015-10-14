/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.observable;

/**
 *
 * @author Anis.DASILVACAMPOS
 */
public abstract class Notification {

    //description du mouvement normale d'une pièce
    public int xInit, yInit, xFinal, yFinal;

    public Notification(int xInit, int yInit, int xFinal, int yFinal) {
        this.xInit = xInit;
        this.yInit = yInit;
        this.xFinal = xFinal;
        this.yFinal = yFinal;
    }

}
