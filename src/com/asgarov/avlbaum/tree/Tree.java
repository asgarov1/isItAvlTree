package com.asgarov.avlbaum.tree;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private Node root;

    /**
     * This function checks whether the value already exists via contains() method
     * and then calls the recursive insert function
     *
     * @param value to be inserted into new node
     * @return boolean that states whether the insert was successful or not (in case such value already exists)
     */
    public boolean insert(int value) {
        if (contains(root, value)) {
            return false;
        }
        root = insert(root, value);

        return true;
    }

    /**
     * Function to check whether value exists somewhere under this node
     *
     * @param node - starting node from which method recursively will check if any of the children contain the value
     * @param value to be searched for
     * @return boolean true or false
     */
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

    /**
     * Recursive function that traverses the tree based on the comparison of the value to be inserted and the current
     * node value -> if current value is higher it recursively goes left, otherwise right. When found null sets the new
     * Node with the new value.
     *
     * @param node - starting node from which the insert function will traverse down
     * @param value to be inserted
     * @return new Node with the value
     */
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

    /**
     * displays the message containing the balance value of the node and if |AVL Value| >= 2 (AVL violation!)
     * e.g. bal(30) = 0
     * e.g. bal(5) = -2 (AVL violation!)
     *
     * @param node for which the information will be displayed
     */
    private void displayMessage(Node node) {
        System.out.print("bal(" + node.getValue() + ") = " + node.getBalanceFactor());
        System.out.print((Math.abs(node.getBalanceFactor()) >= 2 ? " (AVL violation!)\n" : "\n"));
    }

    /**
     * using the findAllNodes() function it runs through the stream to determine whether any node has |balanceFactor| >= 2
     * if yes returns false, otherwise true
     *
     * @return boolean that states whether the tree is AVL or not
     */
    public boolean isAVL() {
        return findAllNodes().stream().noneMatch(node -> Math.abs(node.getBalanceFactor()) >= 2);
    }

    /**
     * updates the height values and balance factor of each node
     *
     * @param node to be updated
     */
    private void updateNode(Node node) {
        int leftNodeHeight = (node.getLeft() == null) ? -1 : node.getLeft().getHeight();
        int rightNodeHeight = (node.getRight() == null) ? -1 : node.getRight().getHeight();

        node.setHeight(1 + Math.max(leftNodeHeight, rightNodeHeight));

        node.setBalanceFactor(rightNodeHeight - leftNodeHeight);
    }

    /**
     * traverses the tree to the bottom left value to find min value
     *
     * @return min value
     */
    public int findMin() {
        Node node = root;
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getValue();
    }

    /**
     * traverses the tree to the bottom right value to find max value
     *
     * @return max value
     */
    public int findMax() {
        Node node = root;
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node.getValue();
    }

    /**
     * uses findAllNodes() function to stream all Nodes, after which maps nodes to their corresponding integer values
     * based on which it calculates the average and returns it as double
     * if no values/nodes were present RuntimeException is thrown
     *
     * @return average as double
     */
    public double findAverage() {
        return findAllNodes()
                .stream()
                .mapToInt(Node::getValue)
                .average()
                .orElseThrow(RuntimeException::new);
    }

    /**
     * Because addAllNodes is a recursive method I have this method which created the ArrayList of Nodes and later returns it
     *
     * @return list containing all the nodes
     */
    public List<Node> findAllNodes() {
        List<Node> allNodes = new ArrayList<>();
        addAllNodes(root, allNodes);
        return allNodes;
    }

    /**
     * recursive method that traverses the tree and keeps adding the nodes to the list which is a second parameter
     * @param node starting node
     * @param listOfNodes list of nodes to which each node will be added
     */
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

    /**
     * Traverses the tree to the rightest value and then display them recursively from right to left
     * @param node - starting node
     */
    public void displayBalanceFactor(Node node) {
        if (node != null) {
            displayBalanceFactor(node.getRight());
            displayBalanceFactor(node.getLeft());
            displayMessage(node);
        }
    }

    /**
     * Getter for the root
     * @return root
     */
    public Node getRoot() {
        return root;
    }
}
