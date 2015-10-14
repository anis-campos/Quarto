package model;

/**
 *
 * @author Antoine
 */
public interface Pieces {
    boolean capture();
    Couleur getCouleur();
    String getName();
    int getX();
    int getY();
    boolean getCapture();
    boolean  isMoveOk(int xFinal, int yFinal);
    boolean move(int xFinal, int yFinal);


    
}