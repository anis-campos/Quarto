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
    private final Tree<NodeData> tree;
    private Tree.Node<NodeData> rootNode;
    private Map.Entry<Coord, Piece> nextMove;

    public MiniMax(int maxDepth, Parametre p) {
        tree = new Tree<>();
        this.maxDepth = maxDepth;
        this.p = p;
    }

    public void buildTree(PlateauJeu plateauJeu, ArrayList<Piece> listPiece, Coord coordDernierePiecePosee, Piece dernierePiecePosee) throws CloneNotSupportedException {
        if (coordDernierePiecePosee != null) {
            rootNode = tree.getRootElement();
            rootNode.setValue(new NodeData(plateauJeu, listPiece, p, maxDepth, true, coordDernierePiecePosee, dernierePiecePosee));
            setChildren(rootNode);

            //DEBUG
//            tree.visitNodes(new Tree.NodeVisitor<NodeData>() {
//
//                @Override
//                public boolean visit(final Tree.Node<NodeData> node) {
//                    final StringBuilder sb = new StringBuilder();
//                    Tree.Node<NodeData> curr = node;
//                    do {
//                        if (sb.length() > 0) {
//                            sb.insert(0, " > ");
//                        }
//                        sb.insert(0, String.valueOf(curr.getValue().getGain()));
//                        curr = curr.getParent();
//                    } while (curr != null);
//                    //System.out.println(sb);
//                    return true;
//                }
//            });
            //FIN DEBUG
        }

    }

    public void setMove() {
        Node<NodeData> greaterNode = getGreaterNode();
        if (greaterNode != null) {
            nextMove = new AbstractMap.SimpleEntry<>(greaterNode.getValue().getCoordDernierePiecePosee(), greaterNode.getValue().getDernierePiecePosee());
        } else {
            nextMove = null;
        }
    }

    private Node<NodeData> getGreaterNode() {
        ArrayList<Node<NodeData>> childList = rootNode.getChildren();
        Node<NodeData> greaterNode = null;
        for (Node<NodeData> node : childList) {
            if (greaterNode == null) {
                greaterNode = node;
            }
            if (node.getValue().getGain() > greaterNode.getValue().getGain()) {
                greaterNode = node;
            }
            if (greaterNode.getValue().getGain() == 100) {
                return greaterNode;
            }
        }
        return greaterNode;
    }

    public void flushTree() {

    }

    private void setChildren(Tree.Node<NodeData> parentNode) throws CloneNotSupportedException {
        int depth = parentNode.getValue().getDepth() - 1;
        if (depth >= 0) {
            for (Piece piece : parentNode.getValue().getListPiece()) {
                for (Coord coord : parentNode.getValue().getPlateauJeu().getAvailableCoords()) {

                    PlateauJeu clonedPJ = (PlateauJeu) parentNode.getValue().getPlateauJeu().clone();
                    ArrayList<Piece> clonedListPiece = getClonedArrayList(parentNode.getValue().getListPiece());

                    clonedListPiece.remove(piece);//DEBUG
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

    private ArrayList<Piece> getClonedArrayList(ArrayList<Piece> listePieceToClone) throws CloneNotSupportedException {
        ArrayList<Piece> clonedList = new ArrayList<>();
        for (Piece piece : listePieceToClone) {
            clonedList.add(piece.clone());
        }
        return clonedList;
    }

    private void propagerGainFeuille(Tree.Node<NodeData> childTreeNode) {
        if (!childTreeNode.getParent().equals(rootNode)) {
            int gainFils = childTreeNode.getValue().getGain();
            int gainParent = childTreeNode.getParent().getValue().getGain();

            boolean filsAdversaire = childTreeNode.getValue().getAdversaire();

            if (filsAdversaire) {
                if (gainParent > gainFils) {
                    childTreeNode.getParent().getValue().setGain(gainFils);
                    propagerGainFeuille(childTreeNode.getParent());
                }
            } else {
                if (gainParent < gainFils) {
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
