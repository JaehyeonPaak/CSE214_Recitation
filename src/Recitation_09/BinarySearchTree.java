package Recitation_09;

import java.util.ArrayList;

public class BinarySearchTree<E extends Comparable<E>> {
    private static class Node<E extends Comparable<E>> {
        public E e;
        public Node<E> left, right;
        public Node(E e, Node<E> left, Node<E> right) {
            this.e = e; this.left = left; this.right = right;
        }
    }

    private Node<E> root;

    public BinarySearchTree() {}

    //TODO: implement find method
    public Node<E> find(E e) {
    }
    private Node<E> find(Node<E> node, E e) {
    }

    //TODO: implement add method
    public void add(E e) {
    }
    private void add(Node<E> node, E e) {
    }

    //TODO: implement visitInorder method
    public Iterable<E> visitInorder() {
        java.util.List<E> snapshot = new ArrayList<E>(5);
    }
    private void visitInorder(Node<E> node, java.util.List<E> snapshot) {
    }

    public static void find(Integer[] arr) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for(Integer e : arr)
            tree.add(e);
        for(int i = 0; i < 10; i++)
            System.out.print((tree.find(i) != null) + ", ");
        System.out.println("");
    }

    //TODO: implement sort by BinarySearchTree
    public static void sort(Integer[] arr) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        //TODO: 1. add elements of arr to tree

        //TODO: 2. print the inorder traversal result

        System.out.println("");
    }
    public static void main(String[] args) {
        Integer[] arr = {5, 3, 6, 2, 8, 1, 9, 4, 7};
        find(arr);
        sort(arr);
    }
}