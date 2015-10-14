package model;

import static java.lang.Math.abs;

/**
 *
 * @author Anis
 */
public class Cavalier extends AbstractPiece {

    public Cavalier(String name, Couleur couleur, Coord coord) {
        super(name, couleur, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
        return (abs(xFinal - getX()) == 2 && abs(yFinal - getY()) == 1)
                || (abs(xFinal - getX()) == 1 && abs(yFinal - getY()) == 2);

    }

    @Override
    protected AbstractPiece clone() {

        Cavalier clone = new Cavalier(name, couleur, coord.clone());
        clone.capture = capture;
        return clone;
        
    }

}
