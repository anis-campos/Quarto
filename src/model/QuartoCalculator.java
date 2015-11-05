/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author timotheetroncy
 */
public class QuartoCalculator {

    //  x,y
    //
    //  0,0     0,1    0,2     0,3
    //
    //  1,0     1,1    1,2     1,3
    //    
    //  2,0     2,1    2,2     2,3
    //
    //  3,0     3,1    3,2     3,3
    private static ArrayList<Piece> vertical;
    private static ArrayList<Piece> horizontal;
    private static ArrayList<Piece> diagDesc;
    private static ArrayList<Piece> diagMont;
    private static ArrayList<Piece> carreHD;//carré haut droit
    private static ArrayList<Piece> carreHG;//carré haut gauche
    private static ArrayList<Piece> carreBD;//carré bas droit
    private static ArrayList<Piece> carreBG;//carré bas gauche

    //La coordonnée coordDernierePiece doit être une clé valide de la map plateau
    public static Boolean thereIsQuarto(PlateauJeu plateau, Parametre p, Coord coordDernierePiece) {
        emptyData();
        remplirVertical(plateau, coordDernierePiece.y);
        remplirHorizontal(plateau, coordDernierePiece.x);
        remplirDiagDesc(plateau, coordDernierePiece);
        remplirDiagMont(plateau, coordDernierePiece);
        if (p.quartoCarreActif()) {
            remplirCarreHD(plateau, coordDernierePiece);
            remplirCarreHG(plateau, coordDernierePiece);
            remplirCarreBD(plateau, coordDernierePiece);
            remplirCarreBG(plateau, coordDernierePiece);
        }
        return checkQuarto(p);
    }

    private static void emptyData() {
        vertical = new ArrayList();
        horizontal = new ArrayList();
        diagDesc = new ArrayList();
        diagMont = new ArrayList();
        carreHD = new ArrayList();//carré haut droit
        carreHG = new ArrayList();//carré haut gauche
        carreBD = new ArrayList();//carré bas droit
        carreBG = new ArrayList();//carré bas gauche
    }

    private static void remplirVertical(PlateauJeu plateau, int y) {
        Piece pieceCourante;
        for (int x = 0; x <= 3; x++) {
            pieceCourante = plateau.getPieceFromCoord(new Coord(x, y));
            if (pieceCourante != null) {
                vertical.add(pieceCourante);
            }
        }
    }

    private static void remplirHorizontal(PlateauJeu plateau, int x) {
        Piece pieceCourante;
        for (int y = 0; y <= 3; y++) {
            pieceCourante = plateau.getPieceFromCoord(new Coord(x, y));
            if (pieceCourante != null) {
                horizontal.add(pieceCourante);
            }
        }
    }

    private static void remplirDiagDesc(PlateauJeu plateau, Coord coord) {
        ArrayList<Coord> coordsValides = new ArrayList();
        Piece pieceCourante;
        coordsValides.add(new Coord(0, 0));
        coordsValides.add(new Coord(1, 1));
        coordsValides.add(new Coord(2, 2));
        coordsValides.add(new Coord(3, 3));
        if (coordsValides.contains(coord)) {
            for (Coord c : coordsValides) {
                pieceCourante = plateau.getPieceFromCoord(c);
                if (pieceCourante != null) {
                    diagDesc.add(pieceCourante);
                }
            }
        }
    }

    private static void remplirDiagMont(PlateauJeu plateau, Coord coord) {
        ArrayList<Coord> coordsValides = new ArrayList();
        Piece pieceCourante;
        coordsValides.add(new Coord(3, 0));
        coordsValides.add(new Coord(2, 1));
        coordsValides.add(new Coord(1, 2));
        coordsValides.add(new Coord(0, 3));
        if (coordsValides.contains(coord)) {
            for (Coord c : coordsValides) {
                pieceCourante = plateau.getPieceFromCoord(c);
                if (pieceCourante != null) {
                    diagMont.add(pieceCourante);
                }
            }
        }
    }

    private static void remplirCarreHD(PlateauJeu plateau, Coord coordDernierePiece) {
        int x, y;
        x = coordDernierePiece.x;
        y = coordDernierePiece.y;
        Piece pieceCourante;
        if (x != 0 && y != 3) {

            pieceCourante = plateau.getPieceFromCoord(coordDernierePiece);
            if (pieceCourante != null) {
                carreHD.add(pieceCourante);
            }
            pieceCourante = plateau.getPieceFromCoord(new Coord(x - 1, y));
            if (pieceCourante != null) {
                carreHD.add(pieceCourante);
            }
            pieceCourante = plateau.getPieceFromCoord(new Coord(x - 1, y + 1));
            if (pieceCourante != null) {
                carreHD.add(pieceCourante);
            }
            pieceCourante = plateau.getPieceFromCoord(new Coord(x, y + 1));
            if (pieceCourante != null) {
                carreHD.add(pieceCourante);
            }
        }
    }

    private static void remplirCarreHG(PlateauJeu plateau, Coord coordDernierePiece) {
        int x, y;
        x = coordDernierePiece.x;
        y = coordDernierePiece.y;
        Piece pieceCourante;
        if (x != 0 && y != 0) {
            pieceCourante = plateau.getPieceFromCoord(coordDernierePiece);
            if (pieceCourante != null) {
                carreHG.add(pieceCourante);
            }
            pieceCourante = plateau.getPieceFromCoord(new Coord(x - 1, y));
            if (pieceCourante != null) {
                carreHG.add(pieceCourante);
            }
            pieceCourante = plateau.getPieceFromCoord(new Coord(x - 1, y - 1));
            if (pieceCourante != null) {
                carreHG.add(pieceCourante);
            }
            pieceCourante = plateau.getPieceFromCoord(new Coord(x, y - 1));
            if (pieceCourante != null) {
                carreHG.add(pieceCourante);
            }
        }
    }

    private static void remplirCarreBD(PlateauJeu plateau, Coord coordDernierePiece) {
        int x, y;
        x = coordDernierePiece.x;
        y = coordDernierePiece.y;
        Piece pieceCourante;
        if (x != 3 && y != 3) {
            pieceCourante = plateau.getPieceFromCoord(coordDernierePiece);
            if (pieceCourante != null) {
                carreBD.add(pieceCourante);
            }
            pieceCourante = plateau.getPieceFromCoord(new Coord(x + 1, y));
            if (pieceCourante != null) {
                carreBD.add(pieceCourante);
            }
            pieceCourante = plateau.getPieceFromCoord(new Coord(x, y + 1));
            if (pieceCourante != null) {
                carreBD.add(pieceCourante);
            }
            pieceCourante = plateau.getPieceFromCoord(new Coord(x + 1, y + 1));
            if (pieceCourante != null) {
                carreBD.add(pieceCourante);
            }
        }
    }

    private static void remplirCarreBG(PlateauJeu plateau, Coord coordDernierePiece) {
        int x, y;
        x = coordDernierePiece.x;
        y = coordDernierePiece.y;
        Piece pieceCourante;
        if (x != 3 && y != 0) {
            pieceCourante = plateau.getPieceFromCoord(coordDernierePiece);
            if (pieceCourante != null) {
                carreBG.add(pieceCourante);
            }
            pieceCourante = plateau.getPieceFromCoord(new Coord(x, y - 1));
            if (pieceCourante != null) {
                carreBG.add(pieceCourante);
            }
            pieceCourante = plateau.getPieceFromCoord(new Coord(x + 1, y - 1));
            if (pieceCourante != null) {
                carreBG.add(pieceCourante);
            }
            pieceCourante = plateau.getPieceFromCoord(new Coord(x + 1, y));
            if (pieceCourante != null) {
                carreBG.add(pieceCourante);
            }
        }
    }

    private static Boolean checkQuarto(Parametre p) {
        Boolean thereIsQuarto = listContainsQuarto(vertical, p) || listContainsQuarto(horizontal, p) || listContainsQuarto(diagMont, p) || listContainsQuarto(diagDesc, p);
        if (!p.quartoCarreActif()) {
            return thereIsQuarto;
        } else {
            return thereIsQuarto || listContainsQuarto(carreHD, p) || listContainsQuarto(carreHG, p) || listContainsQuarto(carreBD, p) || listContainsQuarto(carreBG, p);
        }
    }

    private static Boolean listContainsQuarto(ArrayList<Piece> list, Parametre p) {
        if (list.size() != 4) {
            return false;//il n'y a pas 4 pieces dans la liste
        } else {
            //Compter les instances d'un caractère particulier
            //Grand, Petit, Carre, rond, Clair,Fonce,Creux,Plein
            int[] compteurs = {0, 0, 0, 0, 0, 0, 0, 0};
            for (int i = 0; i <= 3; i++) {

                if (p.formeActif()) {
                    if (list.get(i).carre) {
                        compteurs[2] += 1;
                    } else {
                        compteurs[3] += 1;
                    }
                }
                if (p.couleurActif()) {
                    if (list.get(i).fonce) {
                        compteurs[5] += 1;
                    } else {
                        compteurs[4] += 1;
                    }
                }
                if (p.creuxActif()) {
                    if (list.get(i).plein) {
                        compteurs[7] += 1;
                    } else {
                        compteurs[6] += 1;
                    }
                }
                if (p.hauteurActif()) {
                    if (list.get(i).grand) {
                        compteurs[0] += 1;
                    } else {
                        compteurs[1] += 1;
                    }
                }
            }

            return isThere4InArray(compteurs);
        }

    }

    private static Boolean isThere4InArray(int[] compteurs) {
        for (int i = 0; i <= 7; i++) {
            if (compteurs[i] == 4) {
                return true;
            }
        }
        return false;
    }
 
}
