package controller;

import java.util.Observer;
import model.Coord;
import model.Couleur;

public interface ChessGameControlers {

    boolean move(Coord initCoord, Coord finalCoord);

    boolean promote(Coord coord, String newType);

    String getMessage();

    public boolean isEchecEtMat();

    public Couleur getColorCurrentPlayer();

    public void addObserver(Observer observer);

    public Couleur getColorPiece(Coord initCoord);

}
