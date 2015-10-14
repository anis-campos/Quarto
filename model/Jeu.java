/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;
import java.util.List;
import tools.ChessPieceFactory;

/**
 *
 * @author Antoine
 */
public class Jeu implements Cloneable {

    private final Couleur couleur;
    private final List<Pieces> listPieces;

    private static final int NB_PIECES = 16;

    public Jeu(Couleur couleur) {
        this.couleur = couleur;
        this.listPieces = ChessPieceFactory.newPieces(couleur);
    }
    
    private Jeu (Couleur coul, List<Pieces> liste){
        this.couleur = coul;
        this.listPieces = liste;
    }

    @Override
    protected Jeu clone()  {
        
        List<Pieces> listeClone = new LinkedList<>();
        
        for (Pieces piece : listPieces) {
            listeClone.add(((AbstractPiece)piece).clone());
        }
        
        Jeu clone = new Jeu(this.getCouleur(),listeClone);
        
        return clone;
        
    }

    
    
    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
        Pieces piece = findPiece(xInit, yInit);
        if (piece != null) {
            return piece.move(xFinal, yFinal);
        } else {
            return false;
        }
    }

    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
        Pieces piece = findPiece(xInit, yInit);
        if (piece != null) {
            if (piece instanceof Pions) {
                return piece.isMoveOk(xFinal, yFinal) || ((Pions) piece).isMoveDiagOk(xFinal, yFinal);
            } else {
                return piece.isMoveOk(xFinal, yFinal);
            }
        } else {
            return false;
        }
    }

    public boolean capture(int xCatch, int yCatch) {
        Pieces piece = findPiece(xCatch, yCatch);
        if (piece != null) {
            return piece.capture();
        } else {
            return false;
        }
    }
    
    public boolean isALittleRoque(int xInit, int yInit, int xFinal, int yFinal){
        Pieces roiPotentiel = findPiece(xInit, yInit);
        Pieces tourPotentiel = findPiece(xFinal + 1, yFinal);
        
        
        if(roiPotentiel != null && tourPotentiel != null){
            if(roiPotentiel instanceof Roi && tourPotentiel instanceof Tour){
                if(((AbstractPiece)roiPotentiel).depart && ((AbstractPiece)tourPotentiel).depart 
                        && tourPotentiel.getName().contains("To2"))
                    return true;
            }
        }
        
        return false;
    }
    
    public boolean isABigRoque(int xInit, int yInit, int xFinal, int yFinal){
        Pieces roiPotentiel = findPiece(xInit, yInit);
        Pieces tourPotentiel = findPiece(xFinal - 2, yFinal);
        
        if(roiPotentiel != null && tourPotentiel != null){
            if(roiPotentiel instanceof Roi && tourPotentiel instanceof Tour){
                if(((AbstractPiece)roiPotentiel).depart && ((AbstractPiece)tourPotentiel).depart 
                        && tourPotentiel.getName().contains("To1"))
                    return true;
            }
        }
        
        return false;
    }
    
    public boolean littleRoque(int xInit, int yInit, int xFinal, int yFinal){
        return move(xFinal + 1, yFinal, xInit + 1, yInit) && 
               move(xInit, yInit, xFinal, yFinal);
    }
    
    public boolean bigRoque(int xInit, int yInit, int xFinal, int yFinal){
        return move(xFinal - 2, yFinal, xInit - 1, yInit) && 
               move(xInit, yInit, xFinal, yFinal);
    }
    
    public Coord getKingCoord(){
        Coord rep =null ;
        for(Pieces piece: listPieces){
            if (piece instanceof Roi)
                rep = ((AbstractPiece)piece).coord;
        }
        return rep;
    }
    
    public boolean isPieceToMoveHere(int x, int y) {
        return isPieceHere(x, y);
    }

    public boolean isPieceToCatchHere(int x, int y) {
        return isPieceHere(x, y);
    }

    public boolean isPieceHere(int x, int y) {
        if (findPiece(x, y) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return couleur.name();
    }

    public Couleur getPieceCouleur(int x, int y) {
        Pieces piece = findPiece(x, y);
        if (piece == null) {
            return null;
        } else {
            return piece.getCouleur();
        }
    }

    public String getPieceName(int x, int y) {
        Pieces piece = findPiece(x, y);
        if (piece == null) {
            return "";
        } else {
            return piece.getName();
        }
    }

    public String getPieceType(int x, int y) {
        Pieces piece = findPiece(x, y);
        if (piece == null) {
            return "";
        } else {
            return piece.getClass().getSimpleName();
        }
    }

    public Couleur getCouleur() {
        return this.couleur;
    }

    private Pieces findPiece(int x, int y) {
        for(Pieces piece : listPieces){
            if(!piece.getCapture()&&(piece.getX()==x&&piece.getY()==y))
                return piece;
        }
        return null;
    }
    
    public boolean promote(int x, int y, String newType){
        Pieces pieceToPromote = findPiece(x, y);
        
        this.listPieces.remove(pieceToPromote);
        
        if(newType.equals("Cavalier"))
            this.listPieces.add(new Cavalier(pieceToPromote.getName(), couleur, new Coord(pieceToPromote.getX(), pieceToPromote.getY())));
        else if(newType.equals("Tour"))
            this.listPieces.add(new Tour(pieceToPromote.getName(), couleur, new Coord(pieceToPromote.getX(), pieceToPromote.getY())));
        else if(newType.equals("Reine"))
            this.listPieces.add(new Reine(pieceToPromote.getName(), couleur, new Coord(pieceToPromote.getX(), pieceToPromote.getY())));
        if(newType.equals("Fou"))
            this.listPieces.add(new Fou(pieceToPromote.getName(), couleur, new Coord(pieceToPromote.getX(), pieceToPromote.getY())));
                
        
        return true;
    }
    
    public static void main(String args[]) {
        Jeu jeuB = new Jeu(Couleur.BLANC);
        System.out.println(jeuB.findPiece(0, 7));

        Jeu jeuN = new Jeu(Couleur.NOIR);
        System.out.println(jeuN.findPiece(4, 0));
    }
}
