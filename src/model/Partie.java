/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.model;

import java.util.HashMap;
import java.util.Map;
import static src.model.QuartoCalculator.*;

/**
 *
 * @author timotheetroncy
 */
public class Partie {

    private final Map<Coord, Piece> plateauJeu;
    private final Map<Coord, Piece> plateauPiece;
    private Piece caseJoueur1;
    private Piece caseJoueur2;
    private final Joueur joueur1;
    private final Joueur joueur2;
    private final Parametre parametres;
        //TEST
    public static void main(String[] args) {
        Parametre p = new Parametre(true, false, true, true, true);
        Joueur j1 = new Joueur("Joueur1", false);
        Joueur j2 = new Joueur("Joueur2", false);
        Partie partie = new Partie(p, j1, j2);
        testQuarto(p);
    }
    private static void testQuarto(Parametre p){
        Map<Coord, Piece> plateauTest =  new HashMap<>();
        plateauTest.put(new Coord(1,0), new Piece(true,true,true,true));
        plateauTest.put(new Coord(1,1), new Piece(true,false,true,true));
        plateauTest.put(new Coord(1,2), new Piece(true,true,false,true));
        plateauTest.put(new Coord(2,1), new Piece(true,false,false,true));
        QuartoCalculator:thereIsQuarto(plateauTest, p, new Coord(1,1));
    }
        //ENDTEST

    public Partie(Parametre parametres, Joueur joueur1, Joueur joueur2) {
        this.plateauJeu = new HashMap<>();
        this.plateauPiece = new HashMap<>();
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.parametres = parametres;
        this.pieceFactory();
        //TEST
        System.out.println(this.plateauPiece.toString());
        //ENDTEST
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
        //Si un paramètre de jeu est actif alors, les pièces vont avoir la caractéristique associée variable:
        //ex: parametre hauteur == true -> il y a des pieces hautes et basses (return true ou false)
        //ex: parametre hauteur == false -> toutes les pièces sont hautes (return true)
        for (int i = 0; i < 16; i++) {
            //for a 1-1 to 4-4 plateau
            //laPiece = new Piece(booleanMatrix[i][0] || !parametres.formeActif(), booleanMatrix[i][1] || !parametres.hauteurActif(), booleanMatrix[i][2] || !parametres.couleurActif(), booleanMatrix[i][3] || !parametres.creuxActif(), new Coord(((i + 1) % 4) + 1, (i / 4) + 1));
            //Map from 0-0 to 1-7
            //laPiece = new Piece(booleanMatrix[i][0] || !parametres.formeActif(), booleanMatrix[i][1] || !parametres.hauteurActif(), booleanMatrix[i][2] || !parametres.couleurActif(), booleanMatrix[i][3] || !parametres.creuxActif(), new Coord((i % 2), (i / 2)));
            //Map from 0-0 to 1-7 (without coord in piece)
            laPiece = new Piece(booleanMatrix[i][0] || !parametres.formeActif(), booleanMatrix[i][1] || !parametres.hauteurActif(), booleanMatrix[i][2] || !parametres.couleurActif(), booleanMatrix[i][3] || !parametres.creuxActif());
            plateauPiece.put(new Coord((i % 2), (i / 2)), laPiece);
        }
    }
}
