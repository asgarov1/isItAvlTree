package com.asgarov.avlbaum;

import com.asgarov.avlbaum.tree.Tree;
import com.asgarov.avlbaum.util.FileReader;
import com.asgarov.avlbaum.util.TreePrinter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Integer> numbers = FileReader.readFile(args[0]).stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Tree tree = new Tree();
        for (Integer number : numbers) {
            tree.insert(number);
        }


        tree.displayBalanceFactor(tree.getRoot());
        System.out.println("AVL: " + (tree.isAVL() ? "yes" : "no"));
        System.out.println("min: " + tree.findMin() + ", max: " + tree.findMax() + ", avg: " + tree.findAverage());

        TreePrinter.displayTree(tree.getRoot());
    }
}
