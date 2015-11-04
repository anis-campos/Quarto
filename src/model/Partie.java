/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author timotheetroncy
 */
public class Partie {

    private final PlateauJeu plateauJeu;
    private final ArrayList<Piece> listPiece;
    private Piece caseJoueur1;
    private Piece caseJoueur2;
    private final Joueur joueur1;
    private final Joueur joueur2;
    private final Parametre parametres;

    private Joueur joueurCourant;

    public Partie(Parametre parametres, Joueur joueur1, Joueur joueur2) {
        this.plateauJeu = new PlateauJeu();
        this.listPiece = new ArrayList<>();
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        joueurCourant = joueur1;
        this.parametres = parametres;
        this.pieceFactory();
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
            try {
                //for a 1-1 to 4-4 plateau
                //laPiece = new Piece(booleanMatrix[i][0] || !parametres.formeActif(), booleanMatrix[i][1] || !parametres.hauteurActif(), booleanMatrix[i][2] || !parametres.couleurActif(), booleanMatrix[i][3] || !parametres.creuxActif(), new Coord(((i + 1) % 4) + 1, (i / 4) + 1));
                //Map from 0-0 to 1-7
                laPiece = new Piece(booleanMatrix[i][0] || !parametres.formeActif(), booleanMatrix[i][1] || !parametres.hauteurActif(), booleanMatrix[i][2] || !parametres.couleurActif(), booleanMatrix[i][3] || !parametres.creuxActif());
                listPiece.add(laPiece);
            } catch (Exception ex) {
                Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    public List<String> getListPieceNameDisponibles() {
        List<String> rep = new ArrayList<>();
        for (Piece piece : listPiece) {
            rep.add(piece.getName());
        }
        return rep;
    }

    public boolean selectionPiece(String nomPiece) {

        Piece piece = findPieceAvailable(nomPiece);
        if (piece != null) {
            if (this.joueurCourant == joueur1) {
                caseJoueur2 = piece;
            } else {
                caseJoueur1 = piece;
            }
            listPiece.remove(piece);
            return true;
        }
        
        return false;
    }
    
    public boolean donnerPieceAdversaire(){
        if (this.joueurCourant == joueur1) {
                caseJoueur2 = caseJoueur1;
            } else {
                caseJoueur1 = caseJoueur2;
            }
        return true;
    }

    public Piece findPieceAvailable(String nomPiece) {

        for (Piece piece : listPiece) {
            if (piece.getName() == null ? nomPiece == null : piece.getName().equals(nomPiece)) {
                return piece;
            }
        }
        return null;
    }

    public NumeroJoueur getNumeroJoueurCourant() {
        return joueurCourant.getNumeroJoueur();
    }

    public void changerJoueurCourant() {
        if (joueurCourant == joueur1) {
            joueurCourant = joueur2;
        } else {
            joueurCourant = joueur1;
        }
    }

    public NumeroJoueur getJoueurCourant() {
        return joueurCourant.getNumeroJoueur();
    }

    public PlateauJeu getPlateauJeu() {
        return plateauJeu;
    }

}
