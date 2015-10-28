/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelquarto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author timotheetroncy
 */
public class Partie {

    private final Map<Coord, Piece> plateauJeu;
    private final Map<Coord, Piece> plateauPiece;
    private Piece caseJoueur1;
    private Piece caseJoueur2;
    private Joueur joueur1;
    private Joueur joueur2;
    private Parametre parametres;

    public static void main(String[] args){
        new Partie(new Parametre(true,true,true,true));
    }
    
    public Partie(Parametre parametres) {
        plateauJeu = new HashMap<>();
        plateauPiece = new HashMap<>();
        this.parametres = parametres;
        this.partieBuilder();
    }

    private void partieBuilder() {
        Piece laPiece;
        laPiece = new Piece(false, false, false, false, new Coord(1, 1));
        plateauPiece.put(laPiece.coord, laPiece);
        laPiece = new Piece(false, false, false, true, new Coord(1, 2));
        plateauPiece.put(laPiece.coord, laPiece);
        laPiece = new Piece(false, false, true, false, new Coord(1, 3));
        plateauPiece.put(laPiece.coord, laPiece);
        laPiece = new Piece(false, false, true, true, new Coord(1, 4));
        plateauPiece.put(laPiece.coord, laPiece);
        laPiece = new Piece(false, true, false, false, new Coord(2, 1));
        plateauPiece.put(laPiece.coord, laPiece);
        laPiece = new Piece(false, true, false, true, new Coord(2, 2));
        plateauPiece.put(laPiece.coord, laPiece);
        laPiece = new Piece(false, true, true, false, new Coord(2, 3));
        plateauPiece.put(laPiece.coord, laPiece);
        laPiece = new Piece(false, true, true, true, new Coord(2, 4));
        plateauPiece.put(laPiece.coord, laPiece);
        laPiece = new Piece(true, false, false, false, new Coord(3, 1));
        plateauPiece.put(laPiece.coord, laPiece);
        laPiece = new Piece(true, false, false, true, new Coord(3, 2));
        plateauPiece.put(laPiece.coord, laPiece);
        laPiece = new Piece(true, false, true, false, new Coord(3, 3));
        plateauPiece.put(laPiece.coord, laPiece);
        laPiece = new Piece(true, false, true, true, new Coord(3, 4));
        plateauPiece.put(laPiece.coord, laPiece);
        laPiece = new Piece(true, true, false, false, new Coord(4, 1));
        plateauPiece.put(laPiece.coord, laPiece);
        laPiece = new Piece(true, true, false, true, new Coord(4, 2));
        plateauPiece.put(laPiece.coord, laPiece);
        laPiece = new Piece(true, true, true, false, new Coord(4, 3));
        plateauPiece.put(laPiece.coord, laPiece);
        laPiece = new Piece(true, true, true, true, new Coord(4, 4));
        plateauPiece.put(laPiece.coord, laPiece);

        for (Piece pieceCourante: plateauPiece.values()) {
            if (!parametres.getCouleur()) {
                pieceCourante.setFonce(Boolean.TRUE);
            }
            if (!parametres.getCreux()) {
                pieceCourante.setPlein(Boolean.TRUE);
            }
            if (!parametres.getForme()) {
                pieceCourante.setCarre(Boolean.TRUE);
            }
            if (!parametres.getHauteur()) {
                pieceCourante.setGrand(Boolean.TRUE);
            }
        }
        System.out.println(plateauPiece.toString());
    }

}
