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

/**
 *
 * @author timotheetroncy
 */
public class Bot implements Observer {

    private final IControlleur controller;

    public Bot(IControlleur controller) {
        this.controller = controller;
    }

    @Override
    public void update(Observable o, Object arg) {
        Thread traitement = null;
        Notification notif = (Notification) arg;
        switch (notif.nouvelEtat) {//etat actuel
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
            case J2DoitChoisir:

                traitement = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1500);
                            controller.selectionPiece(pickRandomPiece());
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                traitement.start();
                break;
            case J2DoitPlacer:
                traitement = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1500);
                            controller.poserPiece(pickRandomCoord());
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                traitement.start();
                break;
            //case J2DernierTour:
            //    break;
            //case J1AAnnonceQuarto:
            //    break;
            //case J2AAnnonceQuarto:
            //    break;
            //case J1ATrouveUnQuarto:
            //    break;
            //case J2ATrouveUnQuarto:
            //    break;
            //case J2PeutConfirmerMatchNull:
            //    break;
            //case J1EtJ2OntAnnoncerMatchNull:
            //    break;
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
