/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

import java.util.ArrayList;
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
            tree.visitNodes(new Tree.NodeVisitor<NodeData>() {

                @Override
                public boolean visit(final Tree.Node<NodeData> node) {
                    final StringBuilder sb = new StringBuilder();
                    Tree.Node<NodeData> curr = node;
                    do {
                        if (sb.length() > 0) {
                            sb.insert(0, " > ");
                        }
                        sb.insert(0, String.valueOf(curr.getValue().getGain()));
                        curr = curr.getParent();
                    } while (curr != null);
                    System.out.println(sb);
                    return true;
                }
            });
            //FIN DEBUG
        }

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
                    setChildren(childTreeNode);
                }
            }
        }
    }

    private ArrayList<Piece> getClonedArrayList(ArrayList<Piece> listePieceToClone) throws CloneNotSupportedException {
        ArrayList<Piece> clonedList = new ArrayList<>();
        for (Piece p : listePieceToClone) {
            clonedList.add(p.clone());
        }
        return clonedList;
    }

}
