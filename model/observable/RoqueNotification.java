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
public class RoqueNotification extends MoveNotification {

    public int xTourInit, xTourFinal, yTourInit, yTourFinal;

    public TypeRoque type;

    public RoqueNotification(int xInit, int yInit, int xFinal, int yFinal, TypeRoque typeRoque) {
        super(xInit, yInit, xFinal, yFinal, false);

        this.yTourInit = yInit;
        this.yTourFinal = yFinal;
        
        
        switch (type=typeRoque) {
            case BIG:
                this.xTourInit = 0;
                this.xTourFinal = 3;
                break;
            case LITTLE:
                this.xTourInit = 7;
                this.xTourFinal = 5;
                break;
            default :
                this.xTourInit = xInit;
                this.xTourFinal = xFinal;
                break;
        }

    }

    public enum TypeRoque {

        BIG, LITTLE
    }

    @Override
    public String toString() {
        String tmp = type == TypeRoque.BIG ? "Grand Roque :":"Petit Roque :";
        return tmp+" Roi ("+xInit+","+yInit+")->("+xFinal+","+yFinal+") et Tour ("+xTourInit+","+yTourInit+")->("+xTourFinal+","+yTourFinal+")";
    }

    
}
