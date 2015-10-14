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
public class MoveNotification extends Notification {

    public boolean isPionToPromote;

    MoveNotification(int xInit, int yInit, int xFinal, int yFinal, boolean isPionToPromote) {
        super(xInit, yInit, xFinal, yFinal);
        this.isPionToPromote = isPionToPromote;
    }

}
