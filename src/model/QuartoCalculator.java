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
    public static Boolean thereIsQuarto(PlateauJeu plateau, Parametre p, Coord coordDernierePiece, ArrayList<ArrayList<Coord>> listeDeCoordRetour) {
        emptyData();
        remplirVertical(plateau, coordDernierePiece.y);
        remplirHorizontal(plateau, coordDernierePiece.x);
        remplirDiagDesc(plateau, coordDernierePiece);
        remplirDiagMont(plateau, coordDernierePiece);
        if (p.quartoCarreActif()) {
            remplirCarreHD(plateau, coordDernierePiece, p);
            remplirCarreHG(plateau, coordDernierePiece, p);
            remplirCarreBD(plateau, coordDernierePiece, p);
            remplirCarreBG(plateau, coordDernierePiece, p);
        }
        return checkQuarto(p, plateau, listeDeCoordRetour);
    }
    public static Boolean thereIsQuarto(PlateauJeu plateau, Parametre p, Coord coordDernierePiece){
        return thereIsQuarto(plateau, p, coordDernierePiece, new ArrayList<ArrayList<Coord>>());
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

    private static void remplirCarreHD(PlateauJeu plateau, Coord coordDernierePiece, Parametre p) {
        int x, y;
        String squareName = "HD";
        x = coordDernierePiece.x;
        y = coordDernierePiece.y;
        addPieceFromImageCoords(plateau, p, x, y, squareName);
        addPieceFromImageCoords(plateau, p, x - 1, y, squareName);
        addPieceFromImageCoords(plateau, p, x - 1, y + 1, squareName);
        addPieceFromImageCoords(plateau, p, x, y + 1, squareName);

    }

    private static void remplirCarreHG(PlateauJeu plateau, Coord coordDernierePiece, Parametre p) {
        int x, y;
        String squareName = "HG";
        x = coordDernierePiece.x;
        y = coordDernierePiece.y;

        addPieceFromImageCoords(plateau, p, x, y, squareName);
        addPieceFromImageCoords(plateau, p, x - 1, y, squareName);
        addPieceFromImageCoords(plateau, p, x - 1, y - 1, squareName);
        addPieceFromImageCoords(plateau, p, x, y - 1, squareName);

    }

    private static void remplirCarreBD(PlateauJeu plateau, Coord coordDernierePiece, Parametre p) {
        int x, y;
        String squareName = "BD";
        x = coordDernierePiece.x;
        y = coordDernierePiece.y;

        addPieceFromImageCoords(plateau, p, x, y, squareName);
        addPieceFromImageCoords(plateau, p, x + 1, y, squareName);
        addPieceFromImageCoords(plateau, p, x, y + 1, squareName);
        addPieceFromImageCoords(plateau, p, x + 1, y + 1, squareName);

    }

    private static void remplirCarreBG(PlateauJeu plateau, Coord coordDernierePiece, Parametre p) {
        int x, y;
        String squareName = "BG";
        x = coordDernierePiece.x;
        y = coordDernierePiece.y;

        addPieceFromImageCoords(plateau, p, x, y, squareName);
        addPieceFromImageCoords(plateau, p, x, y - 1, squareName);
        addPieceFromImageCoords(plateau, p, x + 1, y - 1, squareName);
        addPieceFromImageCoords(plateau, p, x + 1, y, squareName);
    }

    private static void addPieceFromImageCoords(PlateauJeu plateau, Parametre p, int imageX, int imageY, String squareName) {
        if (imageX == -1 || imageY == -1 || imageX == 4 || imageY == 4) {
            if (p.torusActif()) {
                if (imageX == -1) {
                    imageX = 3;
                }
                if (imageY == -1) {
                    imageY = 3;
                }
                if (imageX == 4) {
                    imageX = 0;
                }
                if (imageY == 4) {
                    imageY = 0;
                }
                Piece pieceCourante = plateau.getPieceFromCoord(new Coord(imageX, imageY));
                addPieceToSquare(plateau, squareName, pieceCourante);
            }
        } else {
            Piece pieceCourante = plateau.getPieceFromCoord(new Coord(imageX, imageY));
            addPieceToSquare(plateau, squareName, pieceCourante);
        }

    }

    private static void addPieceToSquare(PlateauJeu plateau, String squareName, Piece pieceCourante) {
        if (pieceCourante != null) {
            switch (squareName) {
                case "BG":
                    carreBG.add(pieceCourante);
                    break;
                case "HG":
                    carreHG.add(pieceCourante);
                    break;
                case "BD":
                    carreBD.add(pieceCourante);
                    break;
                case "HD":
                    carreHD.add(pieceCourante);
                    break;
            }
        }
    }

    private static Boolean checkQuarto(Parametre p, PlateauJeu plateau, ArrayList<ArrayList<Coord>> listeDeCoordRetour) {
        if (listContainsQuarto(vertical, p)) {
            fillArrayOfQuartoCoords(vertical, plateau, listeDeCoordRetour);
        }
        if (listContainsQuarto(horizontal, p)) {
            fillArrayOfQuartoCoords(horizontal, plateau, listeDeCoordRetour);
        }
        if (listContainsQuarto(diagMont, p)) {
            fillArrayOfQuartoCoords(diagMont, plateau, listeDeCoordRetour);
        }
        if (listContainsQuarto(diagDesc, p)) {
            fillArrayOfQuartoCoords(diagDesc, plateau, listeDeCoordRetour);
        }
        //ajouter les Quartos par défaut
        if (!p.quartoCarreActif()) {
            return !listeDeCoordRetour.isEmpty();
        } else {
            if (listContainsQuarto(carreHD, p)) {
                fillArrayOfQuartoCoords(carreHD, plateau, listeDeCoordRetour);
            }
            if (listContainsQuarto(carreHG, p)) {
                fillArrayOfQuartoCoords(carreHG, plateau, listeDeCoordRetour);
            }
            if (listContainsQuarto(carreBD, p)) {
                fillArrayOfQuartoCoords(carreBD, plateau, listeDeCoordRetour);
            }
            if (listContainsQuarto(carreBG, p)) {
                fillArrayOfQuartoCoords(carreBG, plateau, listeDeCoordRetour);
            }
            //ajouter les Quartos carrés
            return !listeDeCoordRetour.isEmpty();
        }
    }

    private static void fillArrayOfQuartoCoords(ArrayList<Piece> listPieces, PlateauJeu plateau, ArrayList<ArrayList<Coord>> listeDeCoordRetour) {
        ArrayList<Coord> coordList = new ArrayList();
        for (Piece pieceCourante : listPieces) {
            coordList.add(plateau.getCoordFromPiece(pieceCourante).clone());
        }
        listeDeCoordRetour.add(coordList);
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
