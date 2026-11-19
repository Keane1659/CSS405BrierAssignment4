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
 * File Name: WordFrequencyAnalayzer.java
 * Description: It reads from a txt file seperates the words.
 * It cleans the word to have standard letters only, and afterwards
 * inserts into the tree.
 * It also keeps track of the words that have been inserted.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordFrequencyAnalyzer {

    private AVLTree tree;

    /**
     * Constructor Name: WordFrequencyAnalyzer
     * Description: Creates a new AVL tree to store words
     * and their frequencies.
     */
    public WordFrequencyAnalyzer() {
        tree = new AVLTree();
    }

    /**
     * Method Name: getTree
     * Description: Returns the AVL tree so Main can access
     * it to display, search, or delete words.
     * @return the AVL tree
     */
    public AVLTree getTree() {
        return tree;
    }

    /**
     * Method Name: cleanWord
     * Description: Converts a word to lowercase and removes
     * everything except letters from a to z.
     * @param word the original word being cleaned
     * @return the cleaned word
     */
    private String cleanWord(String word) {
        return word.toLowerCase().replaceAll("[^a-z]", "");
    }

    /**
     * Method Name: readFile
     * Description: Reads the text file, cleans each word,
     * and inserts the words into the AVL tree.
     * If the file cannot be found, it returns false.
     * @param path the location of the text file
     * @return true if the file was read, false if not found
     */

    public boolean readFile(String path) {

        try {
            // Opens the text file
            Scanner scanner = new Scanner(new File(path));

            // Continues reading while there are words in the file
            while (scanner.hasNext()) {

                // Reads and cleans the next word
                String word = cleanWord(scanner.next());

                // Inserts the word if it is not empty
                if (!word.isEmpty()) {
                    tree.insert(word);
                }
            }

            // Closes the file after reading
            scanner.close();

            return true;

        } catch (FileNotFoundException e) {
            // Displays a message if the file cannot be found
            System.out.println("File not found: " + path);
            return false;
        }
    }
}
