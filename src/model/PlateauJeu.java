/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author timotheetroncy
 */
public class PlateauJeu {

    private final HashMap<Coord, Piece> plateauJeuCoordPiece;
    private final HashMap<Piece, Coord> plateauJeuPieceCoord;

    public PlateauJeu() {
        this.plateauJeuCoordPiece = new HashMap<Coord, Piece>();
        this.plateauJeuPieceCoord = new HashMap<Piece, Coord>();
    }

    public void addPiece(Coord coord, Piece piece) {
        plateauJeuCoordPiece.put(coord, piece);
        plateauJeuPieceCoord.put(piece, coord);
    }

    public void removePieceFromCoord(Coord coord) {
        Piece pieceCourante = plateauJeuCoordPiece.get(coord);
        plateauJeuCoordPiece.remove(coord);
        plateauJeuPieceCoord.remove(pieceCourante);
    }

    public void removePieceFromPiece(Piece piece) {
        Coord coord = plateauJeuPieceCoord.get(piece);
        plateauJeuPieceCoord.remove(piece);
        plateauJeuCoordPiece.remove(coord);
    }

    public Piece getPieceFromCoord(Coord coord) {
        return plateauJeuCoordPiece.get(coord);
    }

    public Coord getCoordFromPiece(Piece piece) {
        return plateauJeuPieceCoord.get(piece);
    }

    public ArrayList<Piece> getPieceList() throws Exception {
        //nécéssite de cloner les pièces pour préserver l'encapsulation (intégrité du modèle)
        ArrayList laListe = new ArrayList();
        for (Map.Entry<Piece, Coord> pc : plateauJeuPieceCoord.entrySet()) {
            laListe.add(pc.getKey().clone());
        }
        return laListe;
    }

    public ArrayList<Coord> getCoordList() throws Exception {
        //nécéssite de cloner les coordonnées pour préserver l'encapsulation (intégrité du modèle)
        ArrayList laListe = new ArrayList();
        for (Map.Entry<Piece, Coord> pc : plateauJeuPieceCoord.entrySet()) {
            laListe.add(pc.getValue().clone());
        }
        return laListe;
    }

    @Override
    public String toString() {
        return "PlateauJeu{" + "plateauJeuCoordPiece=" + plateauJeuCoordPiece + ", plateauJeuPieceCoord=" + plateauJeuPieceCoord + '}';
    }

}
