package model.observable;

import java.util.Observable;
import model.Coord;
import model.Couleur;

import model.Echiquier;

/**
 * @author francoise.perrin
 *
 * Cette classe rend un Echiquier Observable et en simplifie l'interface ( DP
 * Proxy, Facade, Observer)
 *
 */
public class ChessGame extends Observable {

    private final Echiquier echiquier;

    /**
     * Cr�e une instance de la classe Echiquier
     */
    public ChessGame() {
        super();
        this.echiquier = new Echiquier();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String st = "";
        st += "\n" + echiquier.getMessage() + "\n";
        st += echiquier.toString();
        return st;
    }

    /**
     * Permet de d�placer une pi�ce connaissant ses coordonn�es initiales vers
     * ses coordonn�es finales si le d�placement est "l�gal". Si d�placement OK,
     * permet l'alternance des joueurs.
     *
     * @param xInit
     * @param yInit
     * @param xFinal
     * @param yFinal
     * @return OK si d�placement OK si OK, permet l'alternance des joueurs
     */
    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {

        boolean isMoveOk = false;
        boolean pionPromoted = false;

        Notification notif;

        if (echiquier.isEchecEtMat()) {
            notif = new EchecEtMatNotification();
        } else if (this.echiquier.isABigRoque(xInit, yInit, xFinal, yFinal)) {
            isMoveOk = this.echiquier.bigRoque(xInit, yInit, xFinal, yFinal);
            notif = new RoqueNotification(xInit, yInit, xFinal, yFinal, RoqueNotification.TypeRoque.BIG);

        } else if (this.echiquier.isALittleRoque(xInit, yInit, xFinal, yFinal)) {
            isMoveOk = this.echiquier.littleRoque(xInit, yInit, xFinal, yFinal);
            notif = new RoqueNotification(xInit, yInit, xFinal, yFinal, RoqueNotification.TypeRoque.LITTLE);
        } else {
            if (isMoveOk = echiquier.move(xInit, yInit, xFinal, yFinal)) {
                if (pionPromoted = echiquier.isPionToPromote(xFinal, yFinal)) {
                    pionPromoted = echiquier.isPionToPromote(xFinal, yFinal);
                } else {
                    echiquier.switchJoueur();
                }
                notif = new MoveNotification(xInit, yInit, xFinal, yFinal, pionPromoted);
            } else {
                notif = new IllegalMoveNotification();
            }
        }

        this.setChanged();
        this.notifyObservers(notif);
        return isMoveOk;
    }

    public boolean promote(int x, int y, String newType) {
        boolean isPromoteOk = false;
        Couleur couleur = null;
        if (!echiquier.isEchecEtMat()) {
            isPromoteOk = echiquier.promote(x, y, newType);
        }
        if (isPromoteOk) {
            couleur = getColorCurrentPlayer();
            echiquier.switchJoueur();
        }
        this.setChanged();

        this.notifyObservers(new PromotedNotification(x, y, newType));
        return isPromoteOk;
    }

    public boolean isEchecEtMat() {
        return echiquier.isEchecEtMat();
    }

    public String getMessage() {
        return echiquier.getMessage();
    }

    public Couleur getColorCurrentPlayer() {
        return echiquier.getColorCurrentPlayer();
    }

    public void notifyIllegal() {
        this.setChanged();
        this.notifyObservers(new IllegalMoveNotification());
    }

    public Couleur getColorPiece(Coord initCoord) {

       return  this.echiquier.getColorPiece(initCoord);

        
    }
}
