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

    }

    private void randomBot(Object arg) {
        Thread traitement = null;
        Notification notif = (Notification) arg;
        switch (notif.nouvelEtat) {//etat actuel
            case J2DoitPlacer:
                traitement = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1500);
                            if (partie.getCoordDernierePiecePlacee() != null && partie.thereIsQuarto()) {
                                controller.annoncerQuarto();
                            } else {
                                controller.poserPiece(pickRandomCoord());
                            }

                        } catch (InterruptedException ex) {
                            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

                traitement.start();
                break;
            case J2DoitChoisir:

                traitement = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1500);
                            if (partie.getCoordDernierePiecePlacee() != null && partie.thereIsQuarto() ) {
                                controller.annoncerQuarto();
                            } else {
                                controller.selectionPiece(pickRandomPiece());
                            }

                        } catch (InterruptedException ex) {
                            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                traitement.start();
                break;
            case J2DoitDonner:
                traitement = new Thread(new Runnable() {

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
                break;

            case J2DernierTour:
                //annoncer quarto ou annoncer match null
                break;
            case J2PeutConfirmerMatchNull:
                //annoncer quarto ou confirmer match null
                break;
            default:
                break;
        }
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
