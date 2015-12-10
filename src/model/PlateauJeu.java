/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author timotheetroncy
 */
public class PlateauJeu implements Serializable, Comparable {

    private final HashMap<Coord, Piece> plateauJeuCoordPiece;
    private final HashMap<Piece, Coord> plateauJeuPieceCoord;

    public PlateauJeu() {
        this.plateauJeuCoordPiece = new HashMap<>();
        this.plateauJeuPieceCoord = new HashMap<>();
    }

    public boolean addPiece(Coord coord, Piece piece) {
        if (!isPieceHere(coord)) {
            plateauJeuCoordPiece.put(coord, piece);
            plateauJeuPieceCoord.put(piece, coord);
            return true;
        }
        return false;
    }

    public boolean isPieceHere(Coord coord) {
        return plateauJeuCoordPiece.get(coord) != null;
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

    public ArrayList<Piece> getClonedPieceList() {
        //nécéssite de cloner les pièces pour préserver l'encapsulation (intégrité du modèle)
        ArrayList laListe = new ArrayList();
        for (Map.Entry<Piece, Coord> pc : plateauJeuPieceCoord.entrySet()) {
            try {
                laListe.add(pc.getKey().clone());
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(PlateauJeu.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    public ArrayList<Coord> getAvailableCoords() {
        ArrayList<Coord> coordsDispos = new ArrayList();
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                Coord current = new Coord(i, j);
                if (!isPieceHere(current)) {
                    coordsDispos.add(current);
                }
            }
        }
        return coordsDispos;
    }
    
    public List<Map.Entry<Coord, String>> getPiecesPlateauJeu(){
        List<Map.Entry<Coord, String>> liste = new ArrayList();
        for (Map.Entry<Piece, Coord> pc : plateauJeuPieceCoord.entrySet()) {
            liste.add(new AbstractMap.SimpleEntry<>(pc.getValue(), pc.getKey().getName()));
        }
        return liste;
    }

    @Override
    public String toString() {
        return "PlateauJeu{" + "plateauJeuCoordPiece=" + plateauJeuCoordPiece + ", plateauJeuPieceCoord=" + plateauJeuPieceCoord + '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        PlateauJeu clonedPL = new PlateauJeu();
        for (Map.Entry<Coord, Piece> pc : plateauJeuCoordPiece.entrySet()) {
            clonedPL.addPiece(pc.getKey().clone(),pc.getValue().clone());
        }
        return clonedPL;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for(Map.Entry<Coord, Piece> cp: plateauJeuCoordPiece.entrySet()){
            hash+=cp.hashCode();
        }
        
        return hash; //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public int compareTo(Object o) {
        if(this.hashCode()>((PlateauJeu)o).hashCode())return 1;
        if(this.hashCode()<((PlateauJeu)o).hashCode())return -1;
        return 0;
    }
    
    
}
