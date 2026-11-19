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

import java.util.LinkedList;
import java.util.Queue;

public class AVLTree {

    private Node root;

    /**
     * Method Name: height
     * Description: If the node is null, then it returns the
     * height as 0. If the node is not null, then it returns
     * the height assigned to that node.
     * @param node the node that is being checked
     * @return the height of the node
     */
    private int height(Node node) {
        // If the node is null, then the height is returned as 0
        if (node == null) {
            return 0;
        }
        // If the node is not null, then it will give the height assigned to it
        return node.height;
    }

    /**
     * Method Name: getBalance
     * Description: Compares the height of the left node
     * and right node to determine the balance of the specific node.
     * @param node the node that is being checked
     * @return the resulting balance of the specific node
     */
    private int getBalance(Node node) {
        return height(node.left) - height(node.right);
    }

    /**
     * Method Name: rightRotate
     * Description: Rotates the tree to the right to maintain
     * the AVL tree structure and updates the heights of the nodes.
     * @param oldRoot the original root of the subtree
     * @return newRoot the new root of the rotated subtree
     */
    private Node rightRotate(Node oldRoot) {
        // Assigns the left child to newRoot
        Node newRoot = oldRoot.left;

        // Temporarily stores the right child of newRoot
        Node temp = newRoot.right;

        // Moves oldRoot to the right of newRoot
        newRoot.right = oldRoot;

        // Moves the temporary node to the left of oldRoot
        oldRoot.left = temp;

        // Updates the height of both nodes after the rotation
        oldRoot.height = 1 + Math.max(height(oldRoot.left), height(oldRoot.right));
        newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));

        return newRoot;
    }

    /**
     * Method Name: leftRotate
     * Description: Rotates the tree to the left to maintain
     * the AVL tree structure and updates the heights of the nodes.
     * @param oldRoot the original root of the subtree
     * @return newRoot the new root of the rotated subtree
     */
    private Node leftRotate(Node oldRoot) {
        // Assigns the right child to newRoot
        Node newRoot = oldRoot.right;

        // Temporarily stores the left child of newRoot
        Node temp = newRoot.left;

        // Moves oldRoot to the left of newRoot
        newRoot.left = oldRoot;

        // Moves the temporary node to the right of oldRoot
        oldRoot.right = temp;

        // Updates the height of both nodes after the rotation
        oldRoot.height = 1 + Math.max(height(oldRoot.left), height(oldRoot.right));
        newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));

        return newRoot;
    }

    /**
     * Method Name: insert
     * Description: Uses insertNode to insert a word into the
     * AVL tree and updates the root after insertion.
     * @param word the word that is being inserted
     */
    public void insert(String word) {
        root = insertNode(root, word);
    }

    /**
     * Method Name: insertNode
     * Description: Inserts a new word into the correct position
     * in the tree. If the word already exists, its frequency
     * is increased. It also checks the balance and rotates
     * the tree if needed.
     * @param node the current node being checked
     * @param word the word that is being inserted
     * @return the updated node after insertion and balancing
     */
    private Node insertNode(Node node, String word) {
        // If the node is null, then a new node is created
        if (node == null) {
            return new Node(word);
        }

        // Compares the word being inserted with the current node
        int comparison = word.compareTo(node.word);

        // If the word comes before the current word, move left
        if (comparison < 0) {
            node.left = insertNode(node.left, word);
        }
        // If the word comes after the current word, move right
        else if (comparison > 0) {
            node.right = insertNode(node.right, word);
        }
        // If the word already exists, increase its frequency
        else {
            node.frequency++;
            return node;
        }

        // Updates the height of the current node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Checks the balance of the current node
        int balance = getBalance(node);

        // Checks if the tree is left leaning (out of balance)
        if (balance > 1) {
            // Check to see if a left-right rotation is needed
            if (getBalance(node.left) < 0) {
                node.left = leftRotate(node.left);
            }
            return rightRotate(node);
        }

        // Checks if the tree is right leaning (out of balance)
        if (balance < -1) {
            // Checks to see if a right-left rotation is needed
            if (getBalance(node.right) > 0) {
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        }

        return node;
    }

    /**
     * Method Name: search
     * Description: Searches the AVL tree for a specific word.
     * If the word is found, it returns the frequency.
     * If the word does not exist, it returns 0.
     * @param word the word that is being searched for
     * @return the frequency of the word or 0 if not found
     */
    public int search(String word) {
        // Starts searching from the root
        Node current = root;

        // Continues searching while the current node exists
        while (current != null) {

            // Compares the search word with the current node
            int comparison = word.compareTo(current.word);

            // If the words match, return the frequency
            if (comparison == 0) {
                return current.frequency;
            }
            // If the word comes before the current word, move left
            else if (comparison < 0) {
                current = current.left;
            }
            // If the word comes after the current word, move right
            else {
                current = current.right;
            }
        }

        // Returns 0 if the word could not be found
        return 0;
    }

    /**
     * Method Name: findMin
     * Description: Searches through the left children of a 
     * subtree to find the node with the smallest word.
     * @param node the starting node of the subtree
     * @return the node containing the smallest word
     */
    private Node findMin(Node node) {
        // Continues moving left until the smallest node is found
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Method Name: delete
     * Description: Uses deleteNode to remove a word from the
     * AVL tree and updates the root after deletion.
     * @param word the word that is being deleted
     */
    public void delete(String word) {
        root = deleteNode(root, word);
    }

    /**
     * Method Name: deleteNode
     * Description: Searches for a word and removes its node
     * from the tree. It checks how many children the node has
     * and rebalances the tree after deletion.
     * @param node the current node being checked
     * @param word the word that is being deleted
     * @return the updated node after deletion and balancing
     */
    private Node deleteNode(Node node, String word) {
        // If the node does not exist, return null
        if (node == null) {
            return null;
        }

        // Compares the word being deleted with the current node
        int comparison = word.compareTo(node.word);

        // If the word comes before the current word, move left
        if (comparison < 0) {
            node.left = deleteNode(node.left, word);
        }
        // If the word comes after the current word, move right
        else if (comparison > 0) {
            node.right = deleteNode(node.right, word);
        }
        else {
            // If the node has no left child, replace it with its right child
            if (node.left == null) {
                return node.right;
            }
            // If the node has no right child, replace it with its left child
            else if (node.right == null) {
                return node.left;
            }

            // If the node has two children, find the smallest node on the right
            Node temp = findMin(node.right);

            // Replaces the current node's word and frequency
            node.word = temp.word;
            node.frequency = temp.frequency;

            // Removes the original node that was copied
            node.right = deleteNode(node.right, temp.word);
        }

        // Updates the height after deletion
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Checks the balance of the current node
        int balance = getBalance(node);

        // Checks if a left-left rotation is needed
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }

        // Checks if a left-right rotation is needed
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Checks if a right-right rotation is needed
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }

        // Checks if a right-left rotation is needed
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /**
     * Method Name: inOrderNode
     * Description: Traverses the tree by visiting the left
     * child, current node, and then right child. This displays
     * the words in alphabetical order.
     * @param node the current node being visited
     */
    private void inOrderNode(Node node) {
        // If the node is null, stop the traversal
        if (node == null) {
            return;
        }

        // Visits the left subtree
        inOrderNode(node.left);

        // Displays the current word and its frequency
        System.out.println(node.word + ": " + node.frequency);

        // Visits the right subtree
        inOrderNode(node.right);
    }

    /**
     * Method Name: inOrder
     * Description: Starts the in-order traversal from the root
     * to display all words and their frequencies alphabetically.
     */
    public void inOrder() {
        System.out.println("=== In-Order Traversal (Alphabetical) ===");
        inOrderNode(root);
    }

    /**
     * Method Name: levelOrder
     * Description: Uses a queue to visit and display each node
     * level by level, starting from the root of the tree.
     */
    public void levelOrder() {
        System.out.println("=== Level-Order Traversal ===");

        // Checks if the tree is empty
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        // Creates a queue to store nodes during traversal
        Queue<Node> queue = new LinkedList<>();

        // Adds the root node to the queue
        queue.add(root);

        // Continues until there are no more nodes in the queue
        while (!queue.isEmpty()) {

            // Removes the next node from the front of the queue
            Node current = queue.remove();

            // Displays the current word and its frequency
            System.out.println(current.word + ": " + current.frequency);

            // Adds the left child to the queue if it exists
            if (current.left != null) {
                queue.add(current.left);
            }

            // Adds the right child to the queue if it exists
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }
}
