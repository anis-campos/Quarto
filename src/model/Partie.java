/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import IA.Bot;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.log4j.Logger;


/**
 *
 * @author timotheetroncy
 */
public class Partie implements Serializable {

    private final PlateauJeu plateauJeu;
    private final ArrayList<Piece> listPiece;
    private Piece caseJoueur1;
    private Piece caseJoueur2;
    private final Joueur joueur1;
    private final Joueur joueur2;
    private final Parametre parametres;
    private EtatGUI etatActuel;
    private Coord coordDernierePiecePlacee;
    private ArrayList<ArrayList<Coord>> quartos;
    private Bot bot;
    
    private static final Logger logger = Logger.getLogger(Partie.class);

    private Joueur joueurCourant;

    public Partie(Parametre parametres, Joueur joueur1, Joueur joueur2) {
        Piece.instanciationNb = 0;//il ne doit y avoir que 16 pièces dans une partie
        this.plateauJeu = new PlateauJeu();
        this.listPiece = new ArrayList<>();
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.parametres = parametres;

        this.quartos = new ArrayList<>();

        this.pieceFactory();

        joueurCourant = designe1Joueur();

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
                logger.error(ex);
            }

        }
    }

    public List<Map.Entry<Integer, String>> getListPieceNameDisponibles() {
        List<Map.Entry<Integer, String>> rep = new ArrayList<>();
        for (Piece piece : listPiece) {
            rep.add(new AbstractMap.SimpleEntry<>(piece.getId(), piece.getName()));
        }

        return rep;
    }

    public boolean poserPiece(Coord coord) {

        boolean addPiece = plateauJeu.addPiece(coord, getPieceJoueurCourant());
        if (addPiece) {
            coordDernierePiecePlacee = coord;
            setPieceJoueurCourant(null);
        }
        return addPiece;
    }

    public Coord getDerniereCoord() {
        return coordDernierePiecePlacee;
    }

    private Piece getPieceJoueurCourant() {
        if (joueurCourant == joueur1) {
            return caseJoueur1;
        } else {
            return caseJoueur2;
        }
    }

    private void setPieceJoueurCourant(Piece piece) {
        if (joueurCourant == joueur1) {
            caseJoueur1 = piece;
        } else {
            caseJoueur2 = piece;
        }
    }

    private void setPieceJoueurAdversaire(Piece piece) {
        if (joueurCourant == joueur1) {
            caseJoueur2 = piece;
        } else {
            caseJoueur1 = piece;
        }
    }

    public boolean selectionPiece(int idPiece) {
        Piece piece = popPieceAvailableById(idPiece);
        if (piece != null) {
            Piece pieceJoueurCourant = getPieceJoueurCourant();
            if (pieceJoueurCourant != null) {
                listPiece.add(pieceJoueurCourant);
                logger.info("Piece remise dans la liste : "+pieceJoueurCourant.getName());
                setPieceJoueurCourant(null);
            }
            logger.info("Piece enlevée de la liste : "+piece.getName());
            setPieceJoueurCourant(piece);

            return true;
        }

        return false;
    }

    public boolean donnerPieceAdversaire() {

        Piece pieceJoueurCourant = getPieceJoueurCourant();
        if(pieceJoueurCourant==null)
            return false;
        setPieceJoueurAdversaire(pieceJoueurCourant);
        logger.info("Piece donnée : "+pieceJoueurCourant.getName());
        setPieceJoueurCourant(null);
        return true;
    }

    public Piece popPieceAvailableById(int idPiece) {

        for (Iterator<Piece> it = listPiece.iterator(); it.hasNext();) {
            Piece piece = it.next();
            if (piece.getId() == idPiece) {
                it.remove();
                return piece;
            }
        }
        logger.warn("Piece n'est pas dispo : " + idPiece);
        return null;
    }

    public Piece popPieceAvailableByName(String pieceName) {

        for (Iterator<Piece> it = listPiece.iterator(); it.hasNext();) {
            Piece piece = it.next();
            if (piece.getName().equalsIgnoreCase(pieceName)) {
                it.remove();
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

    public PlateauJeu getPlateauJeu() {
        return plateauJeu;
    }

    public List<String> getListPieceNamePlacees() {
        List<String> rep = new ArrayList<>();
        for (Piece piece : plateauJeu.getClonedPieceList()) {
            rep.add(piece.getName());
        }
        return rep;
    }

    public boolean thereIsQuarto() {
        quartos = new ArrayList<>();
        return QuartoCalculator.thereIsQuarto(plateauJeu, parametres, coordDernierePiecePlacee, quartos);
    }

    public String getNameJoueurFromNumero(NumeroJoueur nj) {
        if (joueur1.getNumeroJoueur() == nj) {
            return joueur1.getName();
        } else {
            return joueur2.getName();
        }
    }

    public EtatGUI getEtatGUI() {
        return etatActuel;
    }

    public EtatGUI passerEtatSuivant(EntreeGUI entree) {
        EtatGUI etatSuivant = MatriceDeTransition.getInstance().getEtatSuivant(etatActuel, entree);
        if (etatSuivant != EtatGUI.EtatNonDefinit) {
            etatActuel = etatSuivant;
        }
        return etatSuivant;
    }

    public SortieGUI getSortieGUI() {
        return MatriceDeSortie.getInstance().getEtatSortie(etatActuel);
    }

    public boolean isValidationAutoEnabled() {
        return parametres.validationAutoActif();
    }

    private Joueur designe1Joueur() {
        if (parametres.joueurRandom()) {
            Random r = new Random();
            int valeurMax = 3;
            int valeurMin = 1;
            int valeur = valeurMin + r.nextInt(valeurMax - valeurMin);
            if (valeur == 1) {
                joueurCourant = joueur1;
                etatActuel = EtatGUI.J1DoitChoisir;
            } else {
                joueurCourant = joueur2;
                etatActuel = EtatGUI.J2DoitChoisir;
            }

        } else {
            joueurCourant = joueur1;
            etatActuel = EtatGUI.J1DoitChoisir;

        }
        return joueurCourant;
    }

    public void designeeJoueur(NumeroJoueur num) {
        if (num == NumeroJoueur.J1) {
            joueurCourant = joueur1;
            etatActuel = EtatGUI.J1DoitChoisir;
        } else {
            joueurCourant = joueur2;
            etatActuel = EtatGUI.J2DoitChoisir;
        }
    }

    public boolean annoncerQuarto() {
        return thereIsQuarto();
    }

    public ArrayList<ArrayList<Coord>> getQuartos() {
        return quartos;
    }

    public boolean isListPieceEmpty() {
        return listPiece.isEmpty();
    }

    public ArrayList<Coord> getAvailableCoords() {
        return this.plateauJeu.getAvailableCoords();
    }

    public Parametre getParametres() {
        return parametres;
    }

    public Coord getCoordDernierePiecePlacee() {
        return coordDernierePiecePlacee;
    }

    public boolean onePlayer() {
        return joueur2.isBot();
    }

}
