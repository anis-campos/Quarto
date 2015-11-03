/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static model.QuartoCalculator.thereIsQuarto;

/**
 *
 * @author timotheetroncy
 */
public class Partie {

    private final Map<Coord, Piece> plateauJeu;
    private final ArrayList<Piece> listPiece;
    private Piece caseJoueur1;
    private Piece caseJoueur2;
    private final Joueur joueur1;
    private final Joueur joueur2;
    private final Parametre parametres;

    private Joueur Courant;

    //TEST

    public static void main(String[] args) {
        Parametre p = new Parametre(true, true, true, true, false);
        Joueur j1 = new Joueur("Joueur1", false, NumeroJoueur.J1);
        Joueur j2 = new Joueur("Joueur2", false, NumeroJoueur.J2);
        Partie partie = new Partie(p, j1, j2);
        testQuarto(p);
    }

    private static void testQuarto(Parametre p) {
        Map<Coord, Piece> plateauTest = new HashMap<>();
        plateauTest.put(new Coord(0, 0), new Piece(true, false, true, true));
        plateauTest.put(new Coord(0, 1), new Piece(true, true, true, false));
        plateauTest.put(new Coord(0, 2), new Piece(true, false, false, true));
        plateauTest.put(new Coord(0, 3), new Piece(true, false, false, false));
        System.out.println(thereIsQuarto(plateauTest, p, new Coord(0, 1)).toString());
    }
    //ENDTEST

    public Partie(Parametre parametres, Joueur joueur1, Joueur joueur2) {
        this.plateauJeu = new PlateauJeu();
        this.listPiece = new ArrayList<>();
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        Courant = joueur1;
        this.parametres = parametres;
        this.pieceFactory();
    }

    public NumeroJoueur getJoueurCourant() {
        return Courant.getNumeroJoueur();
    }

    public void changerJoueurCourant() {
        if (Courant == joueur1) {
            Courant = joueur2;
        } else {
            Courant = joueur1;
        }
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
            laPiece = new Piece(booleanMatrix[i][0] || !parametres.formeActif(), booleanMatrix[i][1] || !parametres.hauteurActif(), booleanMatrix[i][2] || !parametres.couleurActif(), booleanMatrix[i][3] || !parametres.creuxActif());
            listPiece.add(laPiece);
        }
    }

    public List<String> getListPieceDisponible() {
        List<String> rep = new ArrayList<>();
        for (Piece piece : listPiece) {
            rep.add(piece.getName());
        }
        return rep;
    }

    public void donnerPiece(Piece piece) {

        if (this.Courant == joueur1) {
            caseJoueur2 =  piece;
        } else {
            caseJoueur1 = piece;
        }
        
        listPiece.remove(piece);
    }
    
   public Piece findPieceAvailable(String nomPiece){
       
       for( Piece piece :listPiece){
           if (piece.getName() == null ? nomPiece == null : piece.getName().equals(nomPiece))
               return piece;
       }
      return null;
   }

}
