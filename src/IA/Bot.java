/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

import controlleur.IControlleur;
import controlleur.observables.Notification;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coord;
import model.Partie;
import model.Piece;
import model.PlateauJeu;
import model.QuartoCalculator;

/**
 *
 * @author timotheetroncy
 */
public class Bot implements Observer {

    private final IControlleur controller;
    private final Partie partie;
    private final MiniMax miniMax;

    public Bot(IControlleur controller, Partie partie) {
        this.controller = controller;
        this.partie = partie;
        switch (controller.getBotLevel()) {
            case 2:
                this.miniMax = new MiniMax(2, this.partie.getParametres());
                break;
            default:
                this.miniMax = null;
                break;
        }

    }

    @Override
    public void update(Observable o, Object arg) {

        try {
            algosBot(arg);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void algosBot(Object arg) throws CloneNotSupportedException {

        Notification notif = (Notification) arg;
        switch (notif.nouvelEtat) {//etat actuel
            case J2DoitPlacer:
                Coord coord = null;
                switch (controller.getBotLevel()) {
                    case 0:
                        coord = pickRandomCoord();
                        break;
                    case 1:
                        //Si un quarto peut être fait à partir de la pièce stockée, celui-ci est fait, sinon random
                        coord = pickCoordToMakeQuartoFromPiece(partie.getClonedPlateauJeu(), partie.getClonedPieceJoueur2());
                        if (coord == null) {
                            coord = pickRandomCoord();
                        }
                        break;
                    case 2:
                        coord = miniMax.getNextMove().getKey();
                        if (coord == null) {
                            coord = pickRandomCoord();
                        }
                        break;
                    default:
                        throw new UnsupportedOperationException("Not supported Bot Level");
                }
                placer(coord);
                break;
            case J2DoitChoisir:
                int pieceNb = -1;
                switch (controller.getBotLevel()) {
                    case 0:
                        pieceNb = pickRandomPiece();
                        break;
                    case 1:
                        //Si une piece ne permet pas à l'adversaire de faire un quarto immédiat, c'est celle-ci qui lui est donnée, sinon random
                        Piece piece = pickPieceToAvoidQuarto(partie.getClonedPlateauJeu(), partie.getClonedListePiece());
                        if (piece == null) {
                            //il est impossible d'éviter une quarto de l'adversaire
                            pieceNb = pickRandomPiece();
                        } else {
                            pieceNb = piece.getId();
                        }
                        break;
                    case 2:
                        miniMax.buildTree(partie.getClonedPlateauJeu(), partie.getClonedListePiece(), partie.getCoordDernierePiecePlacee(), partie.getClonedDernierePiecePlacee());
                        break;
                    default:
                        throw new UnsupportedOperationException("Not supported Bot Level");
                }
                choisir(pieceNb);
                break;
            case J2DoitDonner:
                donner();
                break;
            case J2DernierTour:
                //annoncer quarto ou annoncer match null
                dernierTour();
                break;
            case J2PeutConfirmerMatchNull:
                //annoncer quarto ou confirmer match null
                dernierMot();
                break;
            default:
                break;
        }
    }

    private void choisir(final int pieceNb) {
        Thread traitement = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    if (partie.getCoordDernierePiecePlacee() != null && partie.thereIsQuarto()) {
                        controller.annoncerQuarto();//annonce quarto adverse
                    } else {
                        controller.selectionPiece(pieceNb);
                    }

                } catch (InterruptedException ex) {
                    Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        traitement.start();
    }

    private void donner() {
        Thread traitement = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    controller.donnerPieceAdversaire();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        traitement.start();
    }

    private void placer(final Coord coord) {
        Thread traitement = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    if (partie.getCoordDernierePiecePlacee() != null && partie.thereIsQuarto()) {
                        controller.annoncerQuarto();//annonce Quarto
                    } else {
                        controller.poserPiece(coord);
                    }

                } catch (InterruptedException ex) {
                    Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        traitement.start();
    }

    private void dernierMot() {
        Thread traitement = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    if (partie.getCoordDernierePiecePlacee() != null && partie.thereIsQuarto()) {
                        controller.annoncerQuarto();
                    } else {
                        controller.annoncerMatchNul();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        traitement.start();
    }

    private void dernierTour() {
        Thread traitement = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    if (partie.getCoordDernierePiecePlacee() != null && partie.thereIsQuarto()) {
                        controller.annoncerQuarto();
                    } else {
                        controller.annoncerMatchNul();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        traitement.start();
    }

    private int pickRandomPiece() {
        List<Map.Entry<Integer, String>> piecesDispos = controller.getListPieceDisponible();
        Random random = new Random();
        int index = random.nextInt(piecesDispos.size());
        return piecesDispos.get(index).getKey();
    }

    private Coord pickRandomCoord() {
        ArrayList<Coord> coordsDispos = controller.getAvailableCoords();
        Random random = new Random();
        int index = random.nextInt(coordsDispos.size());
        return coordsDispos.get(index);
    }

    //Return null if no quarto possible from piece
    private Coord pickCoordToMakeQuartoFromPiece(PlateauJeu plateauJeu, Piece pieceAPoser) {
        if (pieceAPoser == null) {
            return null;
        }
        Coord currentCoord;
        Piece currentPiece;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                currentCoord = new Coord(x, y);
                currentPiece = plateauJeu.getPieceFromCoord(currentCoord);
                if (currentPiece == null) {
                    //On peut poser une piece ici
                    plateauJeu.addPiece(currentCoord, pieceAPoser);
                    if (QuartoCalculator.thereIsQuarto(plateauJeu, partie.getParametres(), currentCoord)) {
                        return currentCoord;
                    }
                    plateauJeu.removePieceFromPiece(pieceAPoser);
                }
            }
        }
        return null;
    }

    //return null si aucune piece de la liste ne permet d'éviter un quarto
    private Piece pickPieceToAvoidQuarto(PlateauJeu clonedPlateauJeu, ArrayList<Piece> clonedListePiece) {
        for (Piece p : clonedListePiece) {
            Coord coord = pickCoordToMakeQuartoFromPiece(clonedPlateauJeu, p);
            if (coord == null) {
                return p;
            }
        }
        return null;
    }

}
