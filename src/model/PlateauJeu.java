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
        this.plateauJeuCoordPiece = new HashMap<>();
        this.plateauJeuPieceCoord = new HashMap<>();
    }

    public void addPiece(Coord coord, Piece piece) {
        plateauJeuCoordPiece.put(coord, piece);
        plateauJeuPieceCoord.put(piece, coord);
    }

    public Boolean removePieceFromCoord(Coord coord) {
        Piece pieceCourante = plateauJeuCoordPiece.get(coord);
        if (pieceCourante != null) {
            plateauJeuCoordPiece.remove(coord);
            plateauJeuPieceCoord.remove(pieceCourante);
            return true;
        } else {
            return false;
        }
    }
    
    //attention à l'id (clone de piece ok ! new piece NOK)
    public Boolean removePieceFromPiece(Piece piece) {
        Coord coord = plateauJeuPieceCoord.get(piece);
        if (coord != null) {
            plateauJeuPieceCoord.remove(piece);
            plateauJeuCoordPiece.remove(coord);
            return true;
        } else {
            return false;
        }
    }

    public Piece getPieceFromCoord(Coord coord) {
        return plateauJeuCoordPiece.get(coord);
    }

    //attention à l'id (clone de piece ok ! new piece NOK)
    public Coord getCoordFromPiece(Piece piece) {
        return plateauJeuPieceCoord.get(piece);
    }

    public ArrayList<Piece> getClonedPieceList() throws Exception {
        //nécéssite de cloner les pièces pour préserver l'encapsulation (intégrité du modèle)
        ArrayList laListe = new ArrayList();
        for (Map.Entry<Piece, Coord> pc : plateauJeuPieceCoord.entrySet()) {
            laListe.add(pc.getKey().clone());
        }
        return laListe;
    }

    public ArrayList<Coord> getClonedCoordList() throws Exception {
        //nécéssite de cloner les coordonnées pour préserver l'encapsulation (intégrité du modèle)
        ArrayList laListe = new ArrayList();
        for (Map.Entry<Piece, Coord> pc : plateauJeuPieceCoord.entrySet()) {
            laListe.add(pc.getValue().clone());
        }
        return laListe;
    }

    public void removeAll() {
        plateauJeuCoordPiece.clear();
        plateauJeuPieceCoord.clear();
    }

    @Override
    public String toString() {
        return "PlateauJeu{" + "plateauJeuCoordPiece=" + plateauJeuCoordPiece + ", plateauJeuPieceCoord=" + plateauJeuPieceCoord + '}';
    }

}
