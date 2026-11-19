/*
 * I certify that the Java code I am submitting is all my own work.
 * None of the code is copied from any source or any person.
 * I did not use AI to generate the code for my program.
 * Signed: Keane Brier
 * Date: 11/19/2026
 */


/**
 * @author Keane Brier
 * Date: 11/19/2026
 * Class: CSS405
 * Assignment: Assignment 4 - AVL Tree
 * File Name: Main.java
 * Description: This is the Main of the program.
 * It gives the file path to WordFrequencyAnalyzer class
 * and uses functions to analyze, do certain traversal (inorder and levelOrder)
 * and gives the result to the user.
 *
 *
 * References: 
 * ZyBooks CSS 405: Data Structure and Algorithms
 * https://learn.zybooks.com/zybook/CSPCSS405DuerreFall2026
 * Geeksforgeeks
 * https://www.geeksforgeeks.org/dsa/deletion-in-an-avl-tree/
 * https://www.geeksforgeeks.org/dsa/insertion-in-an-avl-tree/
 * Programiz
 * https://www.programiz.com/dsa/avl-tree
 * Oracle
 * https://docs.oracle.com/en/java/javase/26/docs/api/java.base/java/lang/String.html
 * Youtube
 * https://www.youtube.com/watch?v=W_MFWaTKN0c
 * https://www.youtube.com/watch?v=ygZMI2YIcvk
 * ChatGPT (For resources, .txt generation and ideas)
 * https://chatgpt.com
 */



public class Main {

    /**
     * Method Name: main
     * Description: Runs the word frequency analyzer using
     * three different text files and displays the results.
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        // List of text files to test
        String[] files = {
            "testfiles/small.txt",
            "testfiles/medium.txt",
            "testfiles/edge.txt"
        };

        // Runs the program with each text file by utilizing a for loop
        for (String filePath : files) {

            System.out.println("\n==================================");
            System.out.println("Testing file: " + filePath);
            System.out.println("==================================");

            // Creates the analyzer and reads the text file
            WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzer();
            analyzer.readFile(filePath);

            // Gets the AVL tree
            AVLTree tree = analyzer.getTree();

            // Displays the words alphabetically
            tree.inOrder();

            // Displays the tree level by level
            tree.levelOrder();

            // Demonstrates searching and deleting a word
            System.out.println("=== Search and Delete Demo ===");
            System.out.println("Count of 'hello': " + tree.search("hello"));

            tree.delete("hello");

            System.out.println("After delete: " + tree.search("hello"));
        }
    }
}