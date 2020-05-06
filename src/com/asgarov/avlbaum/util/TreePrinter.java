package com.asgarov.avlbaum.util;

import com.asgarov.avlbaum.tree.Node;

import java.util.ArrayList;
import java.util.List;

public class TreePrinter {

    /**
     * Prints the tree
     * @param root
     */
    public static void displayTree(Node root) {
        StringBuilder sb = new StringBuilder();
        List<List<String>> lines = new ArrayList<>();
        List<Node> level = new ArrayList<>();
        List<Node> next = new ArrayList<>();

        level.add(root);
        int nn = 1;
        int widest = 0;

        while (nn != 0) {
            nn = 0;
            List<String> line = new ArrayList<>();
            for (Node n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    String aa = String.valueOf(n.getValue());
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.getLeft());
                    next.add(n.getRight());

                    if (n.getLeft() != null) nn++;
                    if (n.getRight() != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<Node> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;
            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '#' : '#';
                        } else {
                            if (line.get(j) != null) c = '#';
                        }
                    }
                    sb.append(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            sb.append(' ');
                        }
                    } else {
                        for (int k = 0; k < hpw; k++) {
                            sb.append(j % 2 == 0 ? " " : "#");
                        }
                        sb.append(j % 2 == 0 ? "#" : "#");
                        for (int k = 0; k < hpw; k++) {
                            sb.append(j % 2 == 0 ? "#" : " ");
                        }
                    }
                }
                sb.append('\n');
            }
            for (String f : line) {
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                for (int k = 0; k < gap1; k++) {
                    sb.append(' ');
                }
                sb.append(f);
                for (int k = 0; k < gap2; k++) {
                    sb.append(' ');
                }
            }
            sb.append('\n');

            perpiece /= 2;
        }
        System.out.println(sb);
    }
}
