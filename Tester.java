import java.io.*;
import java.util.*;

import ds.BinaryTree;

public class Tester {

    public static class TestOrchestrator {
        void test(Scanner scanner, PrintWriter printWriter) {
            printWriter.println("Alrighty then. We're testing!");
            BinaryTree<String> mVPBinaryTree = new BinaryTree<>();
            mVPBinaryTree = mVPBinaryTree.readBinaryTree(scanner);
            printWriter.println(mVPBinaryTree.toString());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("input.txt"));
        PrintWriter printWriter = new PrintWriter(System.out);
        new TestOrchestrator().test(scanner, printWriter);
        printWriter.close();
    }
}
