/**
 * GeeksForGeeks: Inorder Predecessor and Successor in BST
 * 
 * Problem Statement:
 *     Given a binary search tree and a key value, find the inorder predecessor 
 *     and successor of the given key. If key is not found, return the closest 
 *     possible values.
 * 
 * Input:
 *     - Node root (root node of BST)
 *     - int key (value to find predecessor and successor for)
 *     - Node values are integers
 * 
 * Output:
 *     - ArrayList<Node> containing [predecessor, successor]
 *     - Return Node with value -1 if predecessor/successor doesn't exist
 * 
 * Example:
 *     Input: BST = [20,8,22,4,12,null,25], key = 8
 *     Output: [4,12]
 * 
 *     Explanation:
 *          20
 *         /  \
 *        8    22
 *       / \     \
 *      4   12    25
 *     
 *     Predecessor of 8 is 4
 *     Successor of 8 is 12
 */

import java.util.ArrayList;

public class j01InorderPredecessorSuccessor {

    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    /**
     * Finds inorder predecessor and successor
     * 
     * Intuition:
     * - Use BST property to efficiently find both values
     * - Search separately for predecessor and successor
     * - Return results in ArrayList
     * 
     * Time Complexity: O(h) where h is height of tree
     * Space Complexity: O(1) constant space
     * 
     * @param root    Root node of BST
     * @param key     Value to find predecessor and successor for
     * @return       ArrayList containing [predecessor, successor]
     */
    public static ArrayList<Node> findPreSuc(Node root, int key) {
        ArrayList<Node> out = new ArrayList<>();
        out.add(inorderPredecessor(root, key));
        out.add(inorderSuccessor(root, key));
        return out;
    }

    /**
     * Finds inorder predecessor of given key
     * 
     * Intuition:
     * - Track last node smaller than key
     * - Move right when current value is too small
     * - Move left when current value is too large
     * 
     * Explanation:
     * - Initialize predecessor as -1
     * - Update predecessor when finding smaller value
     * - Navigate BST based on current value comparison
     * 
     * Time Complexity: O(h) where h is height of tree
     * Space Complexity: O(1) constant space
     * 
     * @param root    Root node of BST
     * @param key     Value to find predecessor for
     * @return       Predecessor node or node with value -1 if not found
     */
    public static Node inorderPredecessor(Node root, int key) {
        Node pred = new Node(-1);
        while (root != null) {
            if (root.data < key) {
                pred = root;
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return pred;
    }

    /**
     * Finds inorder successor of given key
     * 
     * Intuition:
     * - Track first node larger than key
     * - Move right when current value is too small
     * - Move left when current value is too large
     * 
     * Explanation:
     * - Initialize successor as -1
     * - Update successor when finding larger value
     * - Navigate BST based on current value comparison
     * 
     * Time Complexity: O(h) where h is height of tree
     * Space Complexity: O(1) constant space
     * 
     * @param root    Root node of BST
     * @param key     Value to find successor for
     * @return       Successor node or node with value -1 if not found
     */
    public static Node inorderSuccessor(Node root, int key) {
        Node succ = new Node(-1);
        while (root != null) {
            if (root.data <= key) {
                root = root.right;
            } else {
                succ = root;
                root = root.left;
            }
        }
        return succ;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic BST
        System.out.println("\nBasic BST Test:");
        Node root1 = new Node(20);
        root1.left = new Node(8);
        root1.right = new Node(22);
        root1.left.left = new Node(4);
        root1.left.right = new Node(12);
        root1.right.right = new Node(25);
        
        int key = 8;
        ArrayList<Node> result1 = findPreSuc(root1, key);
        System.out.println("Key: " + key);
        System.out.println("Predecessor: " + result1.get(0).data);
        System.out.println("Successor: " + result1.get(1).data);

        // Test Case 2: Value not in tree
        System.out.println("\nValue Not in Tree Test:");
        key = 10;
        ArrayList<Node> result2 = findPreSuc(root1, key);
        System.out.println("Key: " + key);
        System.out.println("Predecessor: " + result2.get(0).data);
        System.out.println("Successor: " + result2.get(1).data);

        // Test Case 3: Minimum value
        System.out.println("\nMinimum Value Test:");
        key = 4;
        ArrayList<Node> result3 = findPreSuc(root1, key);
        System.out.println("Key: " + key);
        System.out.println("Predecessor: " + result3.get(0).data);
        System.out.println("Successor: " + result3.get(1).data);
    }
}
