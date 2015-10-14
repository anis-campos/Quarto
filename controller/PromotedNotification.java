/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import model.Coord;
import model.Couleur;

/**
 *
 * @author Anis.DASILVACAMPOS
 */
public class PromotedNotification implements Serializable{

    public String newType;
    public Couleur couleur;
    public Coord CoordPion;

    public PromotedNotification(Coord CoordPion, String newType) {
        this.CoordPion = CoordPion.clone();
        this.newType = newType;
    }

}
