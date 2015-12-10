/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

import IA.Tree.Node;
import IA.Tree.NodeVisitor;

/**
 *
 * @author Sean Patrick Floyd
 * @source
 * http://stackoverflow.com/questions/4748684/java-tree-structure-with-multiple-children-sorted-at-each-level
 */
public class TestTree {

    public static void main(String[] args) {
        final Tree<Integer> tree = new Tree<>();
        final Node<Integer> rootNode = tree.getRootElement();
        rootNode.setValue(1);
        final Node<Integer> childNode = rootNode.addChild(2);
        final Node<Integer> newChildNode = rootNode.addChild(3);
        newChildNode.addChild(4);
        
    }
}
