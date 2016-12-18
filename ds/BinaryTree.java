package ds;

import java.io.*;
import java.util.*;

public class BinaryTree<Item> implements Serializable {

    protected Node<Item> root;

    protected static class Node<Item> implements Serializable {
        protected Item item;
        protected Node<Item> left;
        protected Node<Item> right;

        public Node(Item item) {
            this.item = item;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return "[" + item.toString() + "] ";
        }
    }

    public BinaryTree() {
        root = null;
    }

    protected BinaryTree(Node<Item> root) {
        this.root = root;
    }

    public BinaryTree(Item item, BinaryTree<Item> leftTree,
                      BinaryTree<Item> rightTree) {
        root = new Node<>(item);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    public BinaryTree<Item> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<>(root.left);
        }
        else {
            return null;
        }
    }

    public BinaryTree<Item> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<Item>(root.right);
        } else {
            return null;
        }
    }

    public Item getItem() {
        return root.item;
    }

    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, 1, sb);
        return sb.toString();
    }

    private void toString(Node<Item> node, int depth,
                          StringBuilder sb) {
        for (int i = 1; i < depth; ++i) {
            sb.append(" ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            toString(node.left, depth + 1, sb);
            toString(node.right, depth + 1, sb);
        }
    }

    public BinaryTree<String> readBinaryTree(Scanner scanner) {
        String item = scanner.nextLine().trim();
        if (item.toUpperCase().equals("NULL")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(scanner);
            BinaryTree<String> rightTree = readBinaryTree(scanner);
            return new BinaryTree<String>(item, leftTree, rightTree);
        }
    }
}
