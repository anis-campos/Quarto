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

    public static void main(String[] args) {
        new Partie(new Parametre(true, false, true, true, true));
    }

    public Partie(Parametre parametres) {
        plateauJeu = new HashMap<>();
        plateauPiece = new HashMap<>();
        this.parametres = parametres;
        this.pieceFactory();
        System.out.println(plateauPiece.toString());
    }

    //Création des 16 pièces pour initialiser une partie
    //La pièceFactory prend en compte les paramètres de jeu
    private void pieceFactory() {
        Boolean booleanMatrix[][] = {
            {false, false, false, false},
            {false, false, false, true},
            {false, false, true, false},
            {false, false, true, true},
            {false, true, false, false},
            {false, true, false, true},
            {false, true, true, false},
            {false, true, true, true},
            {true, false, false, false},
            {true, false, false, true},
            {true, false, true, false},
            {true, false, true, true},
            {true, true, false, false},
            {true, true, false, true},
            {true, true, true, false},
            {true, true, true, true}
        };
        Piece laPiece;
        //Si un paramètre de jeu est actif alors, les pièces vont avoir les caractéristiques associées variables:
        //ex: parametre hauteur == true -> il y a des pieces hautes et basses (return true ou false)
        //ex: parametre hauteur == false -> toutes les pièces sont hautes (return true)
        for (int i = 0; i < 16; i++) {
            laPiece = new Piece(booleanMatrix[i][0] || !parametres.formeActif(), booleanMatrix[i][1] || !parametres.hauteurActif(), booleanMatrix[i][2] || !parametres.couleurActif(), booleanMatrix[i][3] || !parametres.creuxActif(), new Coord(((i + 1) % 4) + 1, (i / 4) + 1));
            plateauPiece.put(laPiece.coord, laPiece);
        }
    }
}
