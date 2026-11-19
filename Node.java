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
 * File Name: AVLTree.java
 * Description: This is for the AVLTree functions.
 * It uses the objects created in Node.java and creates
 * a AVL tree using insert, deleting
 */

public class Node {

    String word;
    int frequency;
    int height;
    Node left, right;

    /**
     * Constructor Name: Node
     * Description: Creates a new node with the given word.
     * The frequency starts at 1 and the height starts at 1.
     * @param word the word being stored in the node
     */
    Node(String word) {
        this.word = word;
        frequency = 1;
        height = 1;   // A leaf has height 1, null has height 0
    }
}
