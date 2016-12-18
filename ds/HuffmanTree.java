package ds;

import java.io.*;
import java.util.*;

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
                                                             (lt, rt) -> Double.compareTo
                                                             (lt.getData().weight, rt.getData().weight));
        for (HuffmanNode symbol : symbols) {
            BinaryTree<HuffmanNode> aTree = new BinaryTree<>(symbols, null, null);
            queue.offer(aTree);
        }

        while (queue.size() > 1) {
            BinaryTree<HuffmanNode> left = queue.poll();
            BinaryTree<HuffmanNode> right = queue.poll();
            double leftWeight = left.getData().weight;
            double rightWeight = right.getData().weight;
            HuffmanNode sumOfWeights = new HuffmanNode(leftWeight + rightWeight, '\u0000');
            BinaryTree newTree = new BinaryTree<>(sumOfWeights, left, right);
            queue.offer(newTree);
        }

        huffmanTree = queue.poll();
    }
}
