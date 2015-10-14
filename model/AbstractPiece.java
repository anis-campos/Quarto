/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Antoine
 */
public abstract class AbstractPiece implements Pieces, Cloneable {

    protected String name;
    protected final Couleur couleur;
    protected Coord coord;
    protected boolean depart;
    
    protected boolean capture;

    public AbstractPiece(String name, Couleur couleur, Coord coord) {
        this.name = name;
        this.couleur = couleur;
        this.coord = coord;
        this.capture = false;
        this.depart = true;
    }

   
    @Override
    abstract protected AbstractPiece clone();
    

    @Override
    public boolean move(int xFinal, int yFinal) {
        this.coord.x = xFinal;
        this.coord.y = yFinal;
        this.depart = false;
        return true;
    }

    @Override
    public String toString() {
        return "[" + name + " " + couleur.name() + "]" + " " + coord.toString();
    }

    @Override
    public boolean capture() {
        capture = true;
        return capture;
    }

    @Override
    public Couleur getCouleur() {
        return this.couleur;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getX() {
        return this.coord.x;
    }

    @Override
    public int getY() {
        return this.coord.y;
    }
    
    public boolean getCapture(){
        return this.capture;
    }

    public List<Coord> availableCoords() {
        List<Coord> coords = new LinkedList<>();
        for (int colonne = 0; colonne < 8; colonne++) {
            for (int ligne = 0; ligne < 8; ligne++) {
                if (isMoveOk(colonne, ligne)) {
                    coords.add(new Coord(colonne, ligne));
                }
            }
        }
        return coords;
    }


    public static void main(String args[]) {
        List<AbstractPiece> list = new ArrayList<>();
        int x = 0, y = 0;
        list.add(new Tour("B_To1", Couleur.BLANC, new Coord(x++, y)));
        list.add(new Cavalier("B_Ca1", Couleur.BLANC, new Coord(x++, y)));
        list.add(new Fou("B_Fo1", Couleur.BLANC, new Coord(x++, y)));
        list.add(new Reine("B_Re1", Couleur.BLANC, new Coord(x++, y)));
        list.add(new Roi("B_Ro1", Couleur.BLANC, new Coord(x++, y)));
        list.add(new Pion("B_Pi1", Couleur.BLANC, new Coord(x++, y)));

        for (AbstractPiece piece : list) {
            System.out.println(piece);
        }

    }

}
