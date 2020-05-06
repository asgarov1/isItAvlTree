package com.asgarov.avlbaum.tree;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int balanceFactor;
    private int value;
    private int height;

    private Node left;
    private Node right;


    /**
     * Constructor
     * @param value
     */
    public Node(int value) {
        this.value = value;
    }


    /**
     * Convernience method that takes left and right nodes and returns them as children
     * @return list of nodes containing left and right nodes
     */
    public List<Node> getChildren() {
        List<Node> children = new ArrayList<>();
        children.add(left);
        children.add(right);
        return children;
    }

    /**
     * Getters and Setters
     */

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
