package model;

import java.io.Serializable;

/**
 * @author francoise.perrin
 *
 */
public class Coord implements Serializable, Comparable, Cloneable {

    public int x, y;

    /**
     * @param x
     * @param y
     */
    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "[x=" + x + ", y=" + y + "]";
    }

    /**
     * @param x
     * @param y
     * @return true si les coordonn�es sont valides (dans un plateau de 8*8)
     */
    public static boolean coordonnees_valides(int x, int y) {
        return ((x <= 7) && (x >= 0) && (y <= 7) && (y >= 0));
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Coord) {
            return ((Coord) o).numeroCase() - numeroCase();
        } else {
            throw new IllegalArgumentException("Le paramètre attendu est de type Coord");
        }
    }

    /*
     Une méthode retournant un entier entre 1 et 64 en fonction des coordonnées
     */
    public int numeroCase() {
        return this.y * 8 + this.x + 1;
    }

    @Override
    public Coord clone() {
        return new Coord(x, y);
    }

    @Override
    public int hashCode() {
        return numeroCase();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coord other = (Coord) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

}
