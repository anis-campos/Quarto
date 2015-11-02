/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.model;

import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author timotheetroncy
 */
public class QuartoCalculator {

    //  x,y
    //
    //  0,0     0,1    0,2     0,3
    //
    //  1,0     1,1    1,2     1,3
    //    
    //  2,0     2,1    2,2     2,3
    //
    //  3,0     3,1    3,2     3,3
    private static ArrayList<Piece> vertical;
    private static ArrayList<Piece> horizontal;
    private static ArrayList<Piece> diagDesc;
    private static ArrayList<Piece> diagMont;
    private static ArrayList<Piece> carreHD;//carré haut droit
    private static ArrayList<Piece> carreHG;//carré haut gauche
    private static ArrayList<Piece> carreBD;//carré bas droit
    private static ArrayList<Piece> carreBG;//carré bas gauche

    //La coordonnée coordDernierePiece doit être une clé valide de la map plateau
    public static Boolean thereIsQuarto(Map<Coord, Piece> plateau, Parametre p, Coord coordDernierePiece) {
        emptyData();
        remplirVertical(plateau, coordDernierePiece.y);
        remplirHorizontal(plateau, coordDernierePiece.x);
        remplirDiagDesc(plateau, coordDernierePiece);
        remplirDiagMont(plateau, coordDernierePiece);
//        if (p.quartoCarreActif()) {
//
//            remplirCarreHD();
//
//            remplirCarreHG();
//
//            remplirCarreBD();
//
//            remplirCarreBG();
//        }
        checkQuarto(p);
        return true;
    }

    private static void emptyData() {
        vertical = new ArrayList();
        horizontal = new ArrayList();
        diagDesc = new ArrayList();
        diagMont = new ArrayList();
        carreHD = new ArrayList();//carré haut droit
        carreHG = new ArrayList();//carré haut gauche
        carreBD = new ArrayList();//carré bas droit
        carreBG = new ArrayList();//carré bas gauche
    }

    private static void remplirVertical(Map<Coord, Piece> plateau, int y) {
        Piece pieceCourante;
        for (int x = 0; x <= 3; x++) {
            pieceCourante = plateau.get(new Coord(x, y));
            if (pieceCourante != null) {
                vertical.add(pieceCourante);
            }
        }
    }

    private static void remplirHorizontal(Map<Coord, Piece> plateau, int x) {
        Piece pieceCourante;
        for (int y = 0; y <= 3; y++) {
            pieceCourante = plateau.get(new Coord(x, y));
            if (pieceCourante != null) {
                horizontal.add(pieceCourante);
            }
        }
    }

    private static void remplirDiagDesc(Map<Coord, Piece> plateau, Coord coord) {
        ArrayList<Coord> coordsValides = new ArrayList();
        Piece pieceCourante;
        coordsValides.add(new Coord(0, 0));
        coordsValides.add(new Coord(1, 1));
        coordsValides.add(new Coord(2, 2));
        coordsValides.add(new Coord(3, 3));
        if (coordsValides.contains(coord)) {
            for (Coord c : coordsValides) {
                pieceCourante = plateau.get(c);
                if (pieceCourante != null) {
                    diagDesc.add(pieceCourante);
                }
            }
        }
    }

    private static void remplirDiagMont(Map<Coord, Piece> plateau, Coord coord) {
        ArrayList<Coord> coordsValides = new ArrayList();
        Piece pieceCourante;
        coordsValides.add(new Coord(3, 0));
        coordsValides.add(new Coord(2, 1));
        coordsValides.add(new Coord(1, 2));
        coordsValides.add(new Coord(0, 3));
        if (coordsValides.contains(coord)) {
            for (Coord c : coordsValides) {
                pieceCourante = plateau.get(c);
                if (pieceCourante != null) {
                    diagMont.add(pieceCourante);
                }
            }
        }
    }

    private static void remplirCarreHD(Map<Coord, Piece> plateau, Parametre p, Coord coordDernierePiece) {

    }

    private static void remplirCarreHG(Map<Coord, Piece> plateau, Parametre p, Coord coordDernierePiece) {

    }

    private static void remplirCarreBD(Map<Coord, Piece> plateau, Parametre p, Coord coordDernierePiece) {

    }

    private static void remplirCarreBG(Map<Coord, Piece> plateau, Parametre p, Coord coordDernierePiece) {

    }

    private static Boolean checkQuarto(Parametre p) {
        Boolean thereIsQuarto = listContainsQuarto(vertical, p) || listContainsQuarto(horizontal, p) || listContainsQuarto(diagMont, p) || listContainsQuarto(diagDesc, p);
        //if (!p.quartoCarreActif()) {
            return thereIsQuarto;
        //} else {
            //return thereIsQuarto || .....p;
        //}
    }

    private static Boolean listContainsQuarto(ArrayList<Piece> list, Parametre p) {
        if (list.size() < 4) {
            return false;//il n'y a pas 4 pieces alignées
        } else {
            //Compter les instances d'un caractère particulier
            //Grand, Petit, Carre, rond, Clair,Fonce,Creux,Plein
            int[] compteurs = {0, 0, 0, 0, 0, 0, 0, 0};
            for (int i = 0; i <= 3; i++) {
                
                if (p.formeActif()) {
                    if(list.get(i).carre){
                        compteurs[2]+=1;
                    }else{
                        compteurs[3]+=1;
                    }
                }
                if (p.couleurActif()) {
                    if(list.get(i).fonce){
                        compteurs[5]+=1;
                    }else{
                        compteurs[4]+=1;
                    }
                }
                if (p.creuxActif()) {
                    if(list.get(i).plein){
                        compteurs[7]+=1;
                    }else{
                        compteurs[6]+=1;
                    }
                }
                if (p.hauteurActif()) {
                    if(list.get(i).grand){
                        compteurs[0]+=1;
                    }else{
                        compteurs[1]+=1;
                    }
                }
            }
            
            return isThere4InArray(compteurs);
        }

    }
    private static Boolean isThere4InArray(int[] compteurs){
        for (int i = 0; i <= 7; i++) {
            if(compteurs[i]==4){
                return true;
            }
        }
        return false;
    }
}
