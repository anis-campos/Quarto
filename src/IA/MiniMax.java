/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

import IA.Tree.Node;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import model.Coord;
import model.Parametre;
import model.Piece;
import model.PlateauJeu;

/**
 *
 * @author timotheetroncy
 */
public class MiniMax {

    private final int maxDepth;
    private final Parametre p;
    private Tree<NodeData> tree;
    private Tree.Node<NodeData> rootNode;
    private Map.Entry<Coord, Piece> nextMove;
    private Piece pieceJ2;
    private Boolean doitPlacer;

    public MiniMax(int maxDepth, Parametre p) {
        this.maxDepth = maxDepth;
        this.p = p;
    }

    public void buildTree(PlateauJeu plateauJeu, ArrayList<Piece> listPiece, Coord coordDernierePiecePosee, Piece dernierePiecePosee, Piece pieceJ2) throws CloneNotSupportedException {
        //Si PieceJ2 != null alors doitPlacer == true alors adversaire == true sur rootNode
        //Si PieceJ2 == null alors doitPlacer == false alors adversaire == false sur RootNode
        tree = new Tree<>();
        rootNode = null;
        this.pieceJ2 = pieceJ2;
        this.doitPlacer = (this.pieceJ2 != null);

        if (coordDernierePiecePosee != null) {
            rootNode = tree.getRootElement();
            rootNode.setValue(new NodeData(plateauJeu, listPiece, p, maxDepth, doitPlacer, coordDernierePiecePosee, dernierePiecePosee));
            setChildren(rootNode);
        }
    }

    public Boolean setNextMove() {
        if (rootNode == null) {
            nextMove = null;
            return false;
        } else {
            Node<NodeData> greaterNode = getBestNode();
            if (greaterNode != null) {
                nextMove = new AbstractMap.SimpleEntry<>(greaterNode.getValue().getCoordDernierePiecePosee(), greaterNode.getValue().getDernierePiecePosee());
                return true;
            } else {
                nextMove = null;
                return false;
            }
        }
    }

    private Node<NodeData> getBestNode() {
        ArrayList<Node<NodeData>> childList = rootNode.getChildren();
        ArrayList<Node<NodeData>> negChildList = new ArrayList<>();
        ArrayList<Node<NodeData>> nodesToRemove = new ArrayList<>();
        
        for (Node<NodeData> node : childList) {
            if (node.getValue().getGain() < 0) {
                negChildList.add(node);
            }
        }
        
        for (Node<NodeData> node : childList) {
            for(Node<NodeData> negNode : negChildList) {
                if(node.getValue().getDernierePiecePosee().equals(negNode.getValue().getDernierePiecePosee())){
                    nodesToRemove.add(node);
                    //ce n'est pas un noeud à utiliser (pièce qui fait gagner l'adversaire)
                }
            }
        }
        for(Node<NodeData> node: nodesToRemove){
            childList.remove(node);
        }
        
        
        Node<NodeData> greaterNode = null;
        for (Node<NodeData> node : childList) {
            System.out.print(node.getValue().getGain() + " ");
            if (greaterNode == null) {
                greaterNode = node;
            }
            if (node.getValue().getGain() > greaterNode.getValue().getGain()) {
                greaterNode = node;
            }
        }
        System.out.print("\n");
        return greaterNode;
    }

    private void setChildren(Tree.Node<NodeData> parentNode) throws CloneNotSupportedException {
        int depth = parentNode.getValue().getDepth() - 1;
        if (depth >= 0) {
            //si parentNode est le rootNode et qu'on est dans l'état doitPlacer on commence l'algo avec la pieceJ2 uniquement
            if (depth == (maxDepth - 1) && this.doitPlacer) {

                for (Coord coord : parentNode.getValue().getPlateauJeu().getAvailableCoords()) {

                    PlateauJeu clonedPJ = (PlateauJeu) parentNode.getValue().getPlateauJeu().clone();
                    ArrayList<Piece> clonedListPiece = getClonedArrayList(parentNode.getValue().getListPiece());

                    //clonedListPiece.remove(this.pieceJ2);
                    clonedPJ.addPiece(coord, this.pieceJ2);

                    NodeData childNode = new NodeData(clonedPJ, clonedListPiece, p, depth, !parentNode.getValue().getAdversaire(), coord, this.pieceJ2);
                    Tree.Node<NodeData> childTreeNode = parentNode.addChild(childNode);
                    if (depth == 0 || parentNode.getValue().getGain() != 0) {
                        // C'est une feuille, propager resultat vers le haut 
                        if (parentNode.getValue().getGain() != 0) {
                            propagerGainFeuille(childTreeNode);
                        }
                    } else {
                        setChildren(childTreeNode);
                    }
                }
            } else {
                for (Piece piece : parentNode.getValue().getListPiece()) {
                    for (Coord coord : parentNode.getValue().getPlateauJeu().getAvailableCoords()) {

                        PlateauJeu clonedPJ = (PlateauJeu) parentNode.getValue().getPlateauJeu().clone();
                        ArrayList<Piece> clonedListPiece = getClonedArrayList(parentNode.getValue().getListPiece());

                        removePieceFromArray(clonedListPiece, piece);
                        clonedPJ.addPiece(coord, piece);

                        NodeData childNode = new NodeData(clonedPJ, clonedListPiece, p, depth, !parentNode.getValue().getAdversaire(), coord, piece);
                        Tree.Node<NodeData> childTreeNode = parentNode.addChild(childNode);
                        if (depth == 0 || parentNode.getValue().getGain() != 0) {
                            // C"est une feuille, propager resultat vers le haut 
                            if (parentNode.getValue().getGain() != 0) {
                                propagerGainFeuille(childTreeNode);
                            }
                        } else {
                            setChildren(childTreeNode);
                        }
                    }
                }
            }

        }
    }

    private void removePieceFromArray(ArrayList<Piece> listPiece, Piece pToRemove) {
        Piece removeThis = null;
        for (Piece pc : listPiece) {
            if (pc.equals(pToRemove)) {
                removeThis = pc;
            }
        }
        listPiece.remove(removeThis);
    }

    private ArrayList<Piece> getClonedArrayList(ArrayList<Piece> listePieceToClone) throws CloneNotSupportedException {
        ArrayList<Piece> clonedList = new ArrayList<>();
        for (Piece piece : listePieceToClone) {
            clonedList.add(piece.clone());
        }
        return clonedList;
    }

    private void propagerGainFeuille(Tree.Node<NodeData> childTreeNode) {
        if (!childTreeNode.equals(rootNode)) {
            int gainFils = childTreeNode.getValue().getGain();
            int gainParent = childTreeNode.getParent().getValue().getGain();

            boolean filsAdversaire = childTreeNode.getValue().getAdversaire();

            if (filsAdversaire) {
                if (gainParent < gainFils) {
                    System.out.print("parent < enfant\n");
                    childTreeNode.getParent().getValue().setGain(gainFils);
                    propagerGainFeuille(childTreeNode.getParent());
                }
            } else {
                if (gainParent > gainFils) {
                    System.out.print("parent > enfant\n");
                    childTreeNode.getParent().getValue().setGain(gainFils);
                    propagerGainFeuille(childTreeNode.getParent());
                }

            }
        }
    }

    public Entry<Coord, Piece> getNextMove() {
        return nextMove;
    }

}
