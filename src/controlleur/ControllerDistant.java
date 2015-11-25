package controlleur;

import model.Coord;
import model.NumeroJoueur;
import model.Partie;

/**
 * @author francoise.perrin
 *
 * Ce controleur ne fait quasi rien � part transformer les coordonn�es venant de
 * la vue sous forme de Coord en 2 int
 *
 */
public class ControllerDistant extends ControllerLocal {

    @Override
    public boolean poserPiece(Coord coord) {

        if (isGoodClient()) {

            return super.poserPiece(coord);
        }
        return false;
    }

    @Override
    public boolean donnerPieceAdversaire() {
        if (isGoodClient()) {
            return super.donnerPieceAdversaire();
        }
        return false;
    }

    @Override
    public boolean selectionPiece(int idPiece) {
        if (isGoodClient()) {
            return super.selectionPiece(idPiece);
        }
        return false;
    }

    @Override
    public boolean annoncerQuarto() {
        if (isGoodClient()) {
            return super.annoncerQuarto();
        }
        return false;
    }

    @Override
    public boolean annoncerMatchNul() {
        if (isGoodClient()) {
            return super.annoncerMatchNul();
        }
        return false;
    }

    public enum Type {

        CLIENT, SERVER
    }

    private final Type type;

    public ControllerDistant(Type type, Partie partie) {
        super(partie);
        this.type = type;
    }

    private boolean isGoodClient() {
        NumeroJoueur j = partie.getNumeroJoueurCourant();
        //return true;
        return (type == Type.CLIENT && j == NumeroJoueur.J2) || (type == Type.SERVER && j == NumeroJoueur.J1);
    }

}
