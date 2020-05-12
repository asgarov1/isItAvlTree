package com.asgarov.avlbaum;

import com.asgarov.avlbaum.tree.Tree;
import com.asgarov.avlbaum.util.FileReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = null;
        try {
            numbers = FileReader.readFile(args[0]).stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Tree tree = new Tree();
        for (Integer number : numbers) {
            tree.insert(number);
        }

        tree.displayBalanceFactor(tree.getRoot());
        System.out.println("AVL: " + (tree.isAVL(tree.getRoot()) ? "yes" : "no"));
        System.out.println("min: " + tree.findMin() + ", max: " + tree.findMax() + ", avg: " + tree.findAverage());
    }
}
