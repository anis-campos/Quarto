package controller;

import java.util.Observable;
import java.util.Observer;
import model.Coord;
import model.Couleur;
import model.observable.ChessGame;

/**
 * @author francoise.perrin
 *
 * Méthodes communes des controleurs dont le travail essentiel est de faire
 * communiquer la vue et le modèle pour gérer le déplacement des pièces
 *
 */
public abstract class AbstractChessGameControler extends Observable implements ChessGameControlers {

    protected ChessGame chessGame;

    public AbstractChessGameControler(ChessGame chessGame) {
        super();
        this.chessGame = chessGame;
    }

    @Override
    public abstract boolean move(Coord initCoord, Coord finalCoord);

    @Override
    public abstract boolean promote(Coord coord, String newType);

    @Override
    public boolean isEchecEtMat() {
        return chessGame.isEchecEtMat();
    }

    @Override
    public String getMessage() {
        return chessGame.getMessage();
    }

    @Override
    public String toString() {
        return chessGame.toString();
    }

    @Override
    public Couleur getColorCurrentPlayer() {
        return chessGame.getColorCurrentPlayer();
    }

    @Override
    public void addObserver(Observer observer){
        super.addObserver(observer);
    }

    @Override
    public Couleur getColorPiece(Coord initCoord) {

        return this.chessGame.getColorPiece(initCoord);
        
    }
    
    

}
