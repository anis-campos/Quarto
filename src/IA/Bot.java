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

/**
 *
 * @author timotheetroncy
 */
public class Bot implements Observer {

    private final IControlleur controller;
    private final Partie partie;

    public Bot(IControlleur controller, Partie partie) {
        this.controller = controller;
        this.partie = partie;
    }

    @Override
    public void update(Observable o, Object arg) {

        randomBot(arg);
//            case 1:
//                break;
//            case 2:
//                break;

    }

    private void randomBot(Object arg) {

        Notification notif = (Notification) arg;
        switch (notif.nouvelEtat) {//etat actuel
            case J2DoitPlacer:
                Coord targetCoord = null;
                switch (controller.getBotLevel()) {
                    case 0:
                        targetCoord = pickRandomCoord();
                        break;
                    case 1:
                        break;
                    default:
                        throw new UnsupportedOperationException("Not supported Bot Level");
                }
                placer(targetCoord);
                break;
            case J2DoitChoisir:
                int pieceNb = -1;
                switch (controller.getBotLevel()) {
                    case 0:
                        pieceNb = pickRandomPiece();
                        break;
                    case 1:
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

}
