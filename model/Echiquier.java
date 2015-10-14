/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Antoine
 */
public class Echiquier implements Cloneable {

    private final Jeu jeux[];
    private Jeu jeuCourant;

    private String message;

    private boolean echecEtMat;

    public Echiquier() {
        this(Couleur.BLANC);

    }

    public Echiquier(Couleur PremierJoueur) {
        jeux = new Jeu[2];
        for (Couleur coul : Couleur.values()) {
            jeux[coul.ordinal()] = new Jeu(coul);
        }
        echecEtMat = false;
        jeuCourant = jeux[PremierJoueur.ordinal()];
    }

    private Echiquier(Jeu jeux[], Jeu jeuCourant, String message, boolean echecEtMat) {
        this.jeux = jeux;
        this.jeuCourant = jeuCourant;
        this.message = message;
        this.echecEtMat = echecEtMat;

    }

    public String getMessage() {
        return message;
    }

    public boolean isEchecEtMat() {
        return echecEtMat;
    }

    @Override
    protected Echiquier clone() {

        Jeu jeuxClone[] = new Jeu[2];
        for (int i = 0; i < 2; i++) {
            jeuxClone[i] = this.jeux[i].clone();
        }

        Jeu jeuCourantClone = jeuCourant.getCouleur() == Couleur.BLANC ? jeuxClone[Couleur.BLANC.ordinal()] : jeuxClone[Couleur.NOIR.ordinal()];

        Echiquier clone = new Echiquier(jeuxClone, jeuCourantClone, message, echecEtMat);

        return clone;
    }

    private boolean MetRoiCourantEnDanger(int xInit, int yInit, int xFinal, int yFinal) {

        Echiquier MondeParallele = this.clone();
        Coord roi = MondeParallele.jeuCourant.getKingCoord();
        System.out.println("Coord Roi : " + roi);
        MondeParallele.move_clone(xInit, yInit, xFinal, yFinal);
        return MondeParallele.roiEnDanger(roi);
    }

    private boolean move_clone(int xInit, int yInit, int xFinal, int yFinal) {

        boolean rep = false;
        if (Coord.coordonnees_valides(xFinal, yFinal)
                && !(xInit == xFinal && yInit == yFinal)
                && jeuCourant.isPieceHere(xInit, yInit)
                && jeuCourant.isMoveOk(xInit, yInit, xFinal, yFinal)
                && !collisionInPath(xInit, yInit, xFinal, yFinal)
                && !jeuCourant.isPieceHere(xFinal, yFinal)) {

            if (!getAdversaire().isPieceHere(xFinal, yFinal)) {

                if (jeuCourant.getPieceType(xInit, yInit).equals("Pion") && (Math.abs(xFinal - xInit) == Math.abs(yInit - yFinal))) {
                    return false;
                }

                rep = jeuCourant.move(xInit, yInit, xFinal, yFinal);

                if (rep) {
                    return true;
                }

            } else {
                if (jeuCourant.getPieceType(xInit, yInit).equals("Pion") && !(Math.abs(xFinal - xInit) == Math.abs(yInit - yFinal))) {
                    return false;
                }
                getAdversaire().capture(xFinal, yFinal);
                rep = jeuCourant.move(xInit, yInit, xFinal, yFinal);

                if (rep) {
                    return true;
                }
            }

        }
        return rep;
    }

    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
        boolean rep = false;

        if (roiEnDanger(jeuCourant.getKingCoord())) {
            if (testEchecEtMat()) {
                echecEtMat = true;
                message = "Jeu Terminé";
                return false;
            }
        }
        if (Coord.coordonnees_valides(xFinal, yFinal)
                && !(xInit == xFinal && yInit == yFinal)
                && jeuCourant.isPieceHere(xInit, yInit)
                && jeuCourant.isMoveOk(xInit, yInit, xFinal, yFinal)
                && !collisionInPath(xInit, yInit, xFinal, yFinal)
                && !jeuCourant.isPieceHere(xFinal, yFinal)) {

            if (MetRoiCourantEnDanger(xInit, yInit, xFinal, yFinal)) {
                message = "Ce mouvement met votre roi en danger !!!";
                return false;
            }

            if (!getAdversaire().isPieceHere(xFinal, yFinal)) {

                if (jeuCourant.getPieceType(xInit, yInit).equals("Pion") && (Math.abs(xFinal - xInit) == Math.abs(yInit - yFinal))) {
                    message = "Le pion se deplace en avant !";
                    return false;
                }

                rep = jeuCourant.move(xInit, yInit, xFinal, yFinal);

            } else {
                if (jeuCourant.getPieceType(xInit, yInit).equals("Pion") && !(Math.abs(xFinal - xInit) == Math.abs(yInit - yFinal))) {
                    message = "Le pion ne mange qu'en diagonale avant !";
                    return false;
                }
                getAdversaire().capture(xFinal, yFinal);
                rep = jeuCourant.move(xInit, yInit, xFinal, yFinal);
            }

        } else {
            message = "Il y a une/plusieurs erreur : ";
            if (!Coord.coordonnees_valides(xFinal, yFinal)) {
                message += "\n\t-> Coordonnées hors échiquier";
            }
            if (!jeuCourant.isPieceHere(xInit, yInit)) {
                message += "\n\t-> Ce n'est pas une pièce de votre équipe";
            } else if (xInit == xFinal && yInit == yFinal) {
                message += "\n\t-> Déplacement sur la même case";
            } else if (!jeuCourant.isMoveOk(xInit, yInit, xFinal, yFinal)) {
                message += "\n\t-> Déplacement interdit pour cette pièce : " + jeuCourant.getPieceType(xInit, yInit);
            }
        }
        return rep;
    }

    public void switchJoueur() {
        jeuCourant = getAdversaire();
    }

    private Jeu getAdversaire() {
        return getAdversaire(jeuCourant);
    }

    private Jeu getAdversaire(Jeu jeu) {
        return jeu.getCouleur() == Couleur.BLANC ? jeux[Couleur.NOIR.ordinal()] : jeux[Couleur.BLANC.ordinal()];
    }

    public Couleur getColorCurrentPlayer() {
        return this.jeuCourant.getCouleur();
    }

    @Override
    public String toString() {
        String ret = "Y \\ X", temp;

        for (int i = 0; i < 8; i++) {
            ret += "\t  " + i;
        }

        for (int y = 0; y < 8; y++) {
            ret += "\n" + y;
            for (int x = 0; x < 8; x++) {
                ret += "\t";
                for (Jeu jeu : jeux) {

                }
                ret += getCase(x, y);
            }
        }

        return ret;
    }

    private String getCase(int x, int y) {

        String rep = "_____";

        int i = 0;
        while (i < jeux.length && "_____".equals(rep)) {
            rep = jeux[i].isPieceHere(x, y)
                    ? jeux[i].getPieceName(x, y) : rep;
            i++;
        }

        return rep;
    }

    private boolean isPieceHereAllGames(int x, int y) {
        boolean rep = false;
        for (Jeu jeu : jeux) {
            if (jeu.isPieceHere(x, y)) {
                return true;
            }
        }
        return rep;
    }

    private boolean collisionInPath(int xInit, int yInit, int xFinal, int yFinal) {

        int x = xInit;
        int y = yInit;
        String typePiece = jeuCourant.getPieceType(x, y);
        if (!"Cavalier".equals(typePiece)) {

            x += (int) Math.signum(xFinal - x);
            y += (int) Math.signum(yFinal - y);
            while (!(x == xFinal && y == yFinal)) {

                if (isPieceHereAllGames(x, y)) {
                    message = "Il y a une pièce sur la trajectoire";
                    return true;
                }

                x += (int) Math.signum(xFinal - x);
                y += (int) Math.signum(yFinal - y);

            }
        }

        //Pas de colision
        return false;

    }

    private boolean roiEnDanger(Coord roi) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (getAdversaire().isPieceHere(x, y)) {
                    if (getAdversaire().isMoveOk(x, y, roi.x, roi.y) && !collisionInPath(x, y, roi.x, roi.y)
                            && (!jeuCourant.getPieceType(x, y).equals("Pion")
                            || jeuCourant.getPieceType(x, y).equals("Pion") && (Math.abs(x - roi.x) == Math.abs(y - roi.y)))) {
                        message = "La pièce " + getAdversaire().getPieceName(x, y) + " " + new Coord(x, y) + " menace le roi";
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean testEchecEtMat() {

        Coord roi = jeuCourant.getKingCoord();
        //Tester les deplacemnt du roi
        int X[] = {-1, 0, 1};
        int Y[] = {-1, 0, 1};
        for (int x : X) {
            for (int y : Y) {
                if (jeuCourant.isMoveOk(roi.x, roi.y, x, y) && !this.roiEnDanger(new Coord(roi.x + x, roi.y + y))) {
                    return false;
                }
            }

        }

        //Tester tous les autres pièces 
        for (int xInit = 0; xInit < 8; xInit++) {
            for (int yInit = 0; yInit < 8; yInit++) {
                if (jeuCourant.isPieceHere(xInit, yInit)) {
                    for (int xFinal = 0; xFinal < 8; xFinal++) {
                        for (int yFinal = 0; yFinal < 8; yFinal++) {
                            if (jeuCourant.isMoveOk(xInit, yInit, xFinal, yFinal)
                                    && !collisionInPath(xInit, yInit, xFinal, yFinal)) {
                                if (!this.MetRoiCourantEnDanger(xInit, yInit, xFinal, yFinal)) {
                                    System.out.println((xInit + 1) + " " + (yInit + 1) + " " + (xFinal + 1) + " " + (yFinal + 1));
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }

        //Tous les déplacement on été testés, le roi est foutu !!!!
        return true;
    }

    public boolean isPionToPromote(int x, int y) {
        if (!("Pion".equals(jeuCourant.getPieceType(x, y)))) {
            return false;
        } else {
            return jeuCourant.getCouleur() == Couleur.BLANC ? y == 0 : y == 7;
        }
    }

    public boolean promote(int x, int y, String newType) {
        return this.jeuCourant.promote(x, y, newType);
    }

    public boolean isALittleRoque(int xInit, int yInit, int xFinal, int yFinal) {
        if (!jeuCourant.isALittleRoque(xInit, yInit, xFinal, yFinal)) {
            return false;
        }
        return !collisionInPath(xInit, yInit, xFinal, yFinal);
    }

    public boolean isABigRoque(int xInit, int yInit, int xFinal, int yFinal) {

        if (!jeuCourant.isABigRoque(xInit, yInit, xFinal, yFinal)) {
            return false;
        }
        return !collisionInPath(xInit, yInit, xFinal, yFinal);
    }

    public boolean littleRoque(int xInit, int yInit, int xFinal, int yFinal) {
        boolean ret = this.jeuCourant.littleRoque(xInit, yInit, xFinal, yFinal);
        if (ret) {
            message = "Petit roque !";
        } else {
            message = "Erreur dans le petit roque.";
        }
        return ret;
    }

    public boolean bigRoque(int xInit, int yInit, int xFinal, int yFinal) {
        boolean ret = this.jeuCourant.bigRoque(xInit, yInit, xFinal, yFinal);
        if (ret) {
            message = "Grand roque !";
        } else {
            message = "Erreur dans le grand roque.";
        }
        return ret;
    }

    public Couleur getColorPiece(Coord initCoord) {

        Couleur rep;

        rep = this.jeux[Couleur.BLANC.ordinal()].isPieceHere(initCoord.x, initCoord.y) ? Couleur.BLANC : Couleur.NOIR;

        return rep;

    }

}
