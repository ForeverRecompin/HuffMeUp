package ds;

import java.io.*;
import java.util.*;

import ds.BinaryTree;

public class HuffmanTree implements Serializable {

    private BinaryTree<HuffmanNode> huffmanTree;

    public static class HuffmanNode implements Serializable {
        private double weight;
        private char symbol;

        public HuffmanNode(double weight, char symbol) {
            this.weight = weight;
            this.symbol = symbol;
        }
    }

    public void buildTree(HuffmanNode[] symbols) {
        Queue<BinaryTree<HuffmanNode>> queue = new PriorityQueue<>(symbols.length,
                                                                   (lt, rt) -> Double.compare(lt.getItem().weight, rt.getItem().weight));
        for (HuffmanNode symbol : symbols) {
            BinaryTree<HuffmanNode> aTree = new BinaryTree<>(symbol, null, null);
            queue.offer(aTree);
        }

        while (queue.size() > 1) {
            BinaryTree<HuffmanNode> left = queue.poll();
            BinaryTree<HuffmanNode> right = queue.poll();
            double leftWeight = left.getItem().weight;
            double rightWeight = right.getItem().weight;
            HuffmanNode sumOfWeights = new HuffmanNode(leftWeight + rightWeight, '\u0000');
            BinaryTree newTree = new BinaryTree<>(sumOfWeights, left, right);
            queue.offer(newTree);
        }

        huffmanTree = queue.poll();
    }

    private void printCode(PrintWriter printWriter,
                           String code, BinaryTree<HuffmanNode> tree) {
        HuffmanNode node = tree.getItem();
        if (node.symbol != '\u0000') {
            if (node.symbol == ' ') {
                printWriter.println("space: " + code);
            } else {
                printWriter.println(node.symbol + ": " + code);
            }
        } else {
            printCode(printWriter, code + "0", tree.getLeftSubtree());
            printCode(printWriter, code + "1", tree.getRightSubtree());
        }
    }

    public String decode(String codedMessage) {
        StringBuilder result = new StringBuilder();
        BinaryTree<HuffmanNode> currentTree = huffmanTree;
        for (int i = 0; i < codedMessage.length(); ++i) {
            if (codedMessage.charAt(i) == 1) {
                currentTree = currentTree.getRightSubtree();
            } else {
                currentTree = currentTree.getLeftSubtree();
            }
            if (currentTree.isLeaf()) {
                HuffmanNode node = currentTree.getItem();
                result.append(node.symbol);
                currentTree = huffmanTree;
            }
        }
        return result.toString();
    }
}
