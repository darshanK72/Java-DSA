/**
 * Problem: Iterative DFS Traversals of Binary Tree
 * 
 * Description:
 *     Implement all three DFS traversals (preorder, inorder, postorder) iteratively
 *     using a single stack. The approach uses a state-based iteration to determine
 *     which operation to perform next.
 * 
 * Input:
 *     - Root node of a binary tree
 * 
 * Output:
 *     - ArrayList containing three lists: [preorder, inorder, postorder]
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *     
 *     Output: 
 *     [
 *       [1,2,4,5,3],    // preorder
 *       [4,2,5,1,3],    // inorder
 *       [4,5,2,3,1]     // postorder
 *     ]
 */

import java.util.ArrayList;
import java.util.Stack;

public class j07IterativeDFSTraversal {

    /**
     * TreeNode class representing each node in the binary tree
     */
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Pair class to track node and its state
     * 
     * Intuition:
     * - State 1: Need to process preorder and go left
     * - State 2: Need to process inorder and go right
     * - State 3: Need to process postorder and pop node
     * 
     * Example:
     * new Pair(node, 1) -> Initial state for preorder
     * new Pair(node, 2) -> After preorder, ready for inorder
     * new Pair(node, 3) -> After inorder, ready for postorder
     */
    static class Pair {
        TreeNode node;    // Current tree node
        int state;        // Processing state (1,2,3)

        Pair(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    /**
     * Approach: Single Stack with State Tracking
     * 
     * Intuition:
     * - Use stack to simulate recursion
     * - Each node goes through 3 states corresponding to 3 traversals
     * - State changes guide the traversal process
     * 
     * Example visualization of states:
     *           1(1->2->3)
     *          /          \
     *     (1->2->3)2      3(1->2->3)
     *        /     \
     *   (1->2->3)4  5(1->2->3)
     * 
     * Explanation:
     * 1. State 1 (Preorder):
     *    - Add node to preorder list
     *    - Push left child if exists
     *    - Increment state
     * 
     * 2. State 2 (Inorder):
     *    - Add node to inorder list
     *    - Push right child if exists
     *    - Increment state
     * 
     * 3. State 3 (Postorder):
     *    - Add node to postorder list
     *    - Pop node from stack
     * 
     * Time Complexity: O(n) - visit each node three times
     * Space Complexity: O(h) - h is height of tree
     * 
     * @param root Root node of binary tree
     * @return ArrayList of three lists containing traversal orders
     */
    public static ArrayList<ArrayList<Integer>> iterativeDFS(TreeNode root) {
        ArrayList<Integer> preorder = new ArrayList<>();        // Store preorder traversal
        ArrayList<Integer> inorder = new ArrayList<>();         // Store inorder traversal
        ArrayList<Integer> postorder = new ArrayList<>();       // Store postorder traversal
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if(root == null) {                                     // Handle empty tree
            result.add(preorder);
            result.add(inorder);
            result.add(postorder);
            return result;
        }

        Stack<Pair> stack = new Stack<>();                     // Stack for iteration
        stack.push(new Pair(root, 1));                        // Start with root, state 1

        while(!stack.isEmpty()) {
            Pair pair = stack.peek();                          // Get current node pair

            if(pair.state == 1) {                              // Preorder state
                preorder.add(pair.node.val);                   // Add to preorder
                pair.state++;                                  // Move to next state
                if(pair.node.left != null) {                   // Process left subtree
                    stack.push(new Pair(pair.node.left, 1));
                }
            } 
            else if(pair.state == 2) {                         // Inorder state
                inorder.add(pair.node.val);                    // Add to inorder
                pair.state++;                                  // Move to next state
                if(pair.node.right != null) {                  // Process right subtree
                    stack.push(new Pair(pair.node.right, 1));
                }
            } 
            else {                                             // Postorder state
                postorder.add(pair.node.val);                  // Add to postorder
                stack.pop();                                   // Remove processed node
            }
        }

        result.add(preorder);                                 // Combine results
        result.add(inorder);
        result.add(postorder);
        return result;
    }

    /**
     * Main method to test the implementation
     */
    public static void main(String[] args) {
        // Test Case 1: Regular binary tree
        /**
         *           1
         *          / \
         *         2   3
         *        / \
         *       4   5
         */
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        
        System.out.println("Test Case 1 - Regular binary tree");
        System.out.println("Expected:");
        System.out.println("Preorder:  [1, 2, 4, 5, 3]");
        System.out.println("Inorder:   [4, 2, 5, 1, 3]");
        System.out.println("Postorder: [4, 5, 2, 3, 1]");
        System.out.println("Output: " + iterativeDFS(root1));

        // Test Case 2: Empty tree
        System.out.println("\nTest Case 2 - Empty tree");
        System.out.println("Expected: [[], [], []]");
        System.out.println("Output: " + iterativeDFS(null));

        // Test Case 3: Single node tree
        TreeNode root3 = new TreeNode(1);
        System.out.println("\nTest Case 3 - Single node tree");
        System.out.println("Expected: [[1], [1], [1]]");
        System.out.println("Output: " + iterativeDFS(root3));
    }
}
