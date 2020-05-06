package com.asgarov.avlbaum.tree;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private Node root;
    private int nodeCount;

    public boolean insert(int value) {
        if (contains(root, value)) {
            return false;
        }
        root = insert(root, value);

        nodeCount++;
        return true;
    }

    private boolean contains(Node node, int value) {
        if (node == null) {
            return false;
        }

        int compare = Integer.compare(value, node.getValue());
        if (compare < 0) {
            return contains(node.getLeft(), value);
        } else if (compare > 0) {
            return contains(node.getRight(), value);
        }

        return true;
    }

    private Node findNode(Node node, int value) {
        int compare = Integer.compare(value, node.getValue());

        if (compare < 0) {
            return findNode(node.getLeft(), value);
        } else if (compare > 0) {
            return findNode(node.getRight(), value);
        }

        return node;
    }

    private Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        int compare = Integer.compare(value, node.getValue());
        if (compare < 0) {
            node.setLeft(insert(node.getLeft(), value));
        } else if (compare > 0) {
            node.setRight(insert(node.getRight(), value));
        }

        //Update balance factor and height values
        updateNode(node);

        return node;
    }

    private void displayMessage(Node node) {
        System.out.print("bal(" + node.getValue() + ") = " + node.getBalanceFactor());
        if (node.getBalanceFactor() >= 2) {
            System.out.println(" (AVL violation!)");
        } else {
            System.out.println();
        }
    }

    public boolean isAVL() {
        return findAllNodes().stream().noneMatch(node -> node.getBalanceFactor() >= 2 || node.getBalanceFactor() <= -2);
    }

    private void updateNode(Node node) {
        int leftNodeHeight = (node.getLeft() == null) ? -1 : node.getLeft().getHeight();
        int rightNodeHeight = (node.getRight() == null) ? -1 : node.getRight().getHeight();

        node.setHeight(1 + Math.max(leftNodeHeight, rightNodeHeight));

        node.setBalanceFactor(rightNodeHeight - leftNodeHeight);
    }

    public int findMin() {
        Node node = root;
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getValue();
    }

    public int findMax() {
        Node node = root;
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node.getValue();
    }

    public double findAverage() {
        return findAllNodes().stream().mapToInt(Node::getValue).average().orElseThrow(RuntimeException::new);
    }

    public List<Node> findAllNodes() {
        List<Node> allNodes = new ArrayList<>();
        addAllNodes(root, allNodes);
        return allNodes;
    }

    public void displayBalanceFactor(Node node) {
        if (node != null) {
            displayBalanceFactor(node.getRight());
            displayBalanceFactor(node.getLeft());
            displayMessage(node);
        }
    }

    private void addAllNodes(Node node, List<Node> listOfNodes) {
        if (node != null) {
            listOfNodes.add(node);
            List<Node> children = node.getChildren();
            if (children != null) {
                for (Node child : children) {
                    addAllNodes(child, listOfNodes);
                }
            }
        }
    }

    public Node getRoot() {
        return root;
    }

    public int getNodeCount() {
        return nodeCount;
    }
}
