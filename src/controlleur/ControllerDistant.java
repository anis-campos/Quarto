package controlleur;

import java.util.List;
import java.util.Map;
import model.Coord;
import model.EtatGUI;
import model.NumeroJoueur;
import model.Partie;
import model.SortieGUI;

/**
 * @author francoise.perrin
 *
 * Ce controleur ne fait quasi rien � part transformer les coordonn�es venant de
 * la vue sous forme de Coord en 2 int
 *
 */
public class ControllerDistant extends AbstractController {

    public enum Type {

        CLIENT, SERVER
    }

    private final Type type;

    public ControllerDistant(Type type, Partie partie) {
        super(partie);
        this.type = type;
    }

 
    /*public ControllerDistant(ChessGame chessGame, Type type) {
     super(chessGame);
     this.type = type;
     }

     @Override
     public boolean move(Coord initCoord, Coord finalCoord) {

     Couleur current = chessGame.getColorCurrentPlayer();

     boolean ret = false;

     if ((current == Couleur.NOIR && type == Type.CLIENT) || (current == Couleur.BLANC && type == Type.SERVER)) {
     if (ret = chessGame.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y)) {
     setChanged();
     notifyObservers(new MoveNotification(initCoord, finalCoord));
     }
     } else {
     chessGame.notifyIllegal();
     }

     return ret;

     }

     public boolean moveRemote(Coord initCoord, Coord finalCoord) {

     Couleur current = chessGame.getColorCurrentPlayer();

     if ((current == Couleur.BLANC && type == Type.CLIENT) || (current == Couleur.NOIR && type == Type.SERVER)) {
     if (chessGame.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y)) {
     return true;
     }
     }
     return false;

     }

     @Override
     public boolean promote(Coord coord, String newType) {
     if (chessGame.getColorCurrentPlayer() == Couleur.NOIR && chessGame.promote(coord.x, coord.y, newType)) {
     setChanged();
     notifyObservers(new PromotedNotification(coord, newType));
     return true;
     } else {
     return false;
     }
     }*/
}
