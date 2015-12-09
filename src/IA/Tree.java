/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Sean Patrick Floyd
 * @source http://stackoverflow.com/questions/4748684/java-tree-structure-with-multiple-children-sorted-at-each-level
 * @param <T>
 */
public class Tree<T> {

    private final Node<T> rootElement;

    public void visitNodes(final NodeVisitor<T> visitor){
        doVisit(rootElement, visitor);
    }

    private static <T> boolean doVisit(final Node<T> node,
        final NodeVisitor<T> visitor){
        boolean result = visitor.visit(node);
        if(result){
            for(final Node<T> subNode : node.children){
                if(!doVisit(subNode, visitor)){
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    public interface NodeVisitor<T> {

        boolean visit(Node<T> node);
    }

    public Node<T> getRootElement(){
        return rootElement;
    }

    private static final class NodeComparator<T> implements Comparator<Node<T>>{

        private final Comparator<T> wrapped;

        @Override
        public int compare(final Node<T> o1, final Node<T> o2){
            return wrapped.compare(o1.value, o2.value);
        }

        public NodeComparator(final Comparator<T> wrappedComparator){
            this.wrapped = wrappedComparator;
        }

    }

    public static class Node<T> {

        private final SortedSet<Node<T>> children;

        private final Node<T> parent;

        private T value;

        private final Comparator<?> comparator;

        @SuppressWarnings("unchecked")
        Node(final T value, final Node<T> parent, final Comparator<?> comparator){
            this.value = value;
            this.parent = parent;
            this.comparator = comparator;
            children =
                new TreeSet<>(new NodeComparator<>((Comparator<T>) comparator));
        }

        public ArrayList<Node<T>> getChildren(){
            return new ArrayList<>(children);
        }

        public Node<T> getParent(){
            return parent;
        }

        public T getValue(){
            return value;
        }

        public void setValue(final T value){
            this.value = value;
        }

        public Node<T> addChild(final T value){
            final Node<T> node = new Node<>(value, this, comparator);
            return children.add(node) ? node : null;
        }

    }

    @SuppressWarnings("rawtypes")
    private static final Comparator NATURAL_ORDER = new Comparator(){

        @SuppressWarnings("unchecked")
        @Override
        public int compare(final Object o1, final Object o2){
            return ((Comparable) o1).compareTo(o2);
        }
    };

    private final Comparator<?> comparator;

    public Tree(){
        this(null, null);
    }

    public Tree(final Comparator<? super T> comparator){
        this(comparator, null);
    }

    public Tree(final Comparator<? super T> comparator, final T rootValue){
        this.comparator = comparator == null ? NATURAL_ORDER : comparator;
        this.rootElement = new Node<>(rootValue, null, this.comparator);
    }

    public Tree(final T rootValue){
        this(null, rootValue);
    }

}
