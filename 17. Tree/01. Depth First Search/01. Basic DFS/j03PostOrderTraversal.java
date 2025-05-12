/**
 * Problem Statement:
 *     LeetCode 145. Binary Tree Postorder Traversal
 * 
 *     Given the root of a binary tree, return the postorder traversal of its nodes'
 *     values. In postorder traversal, we traverse the left subtree first, then
 *     traverse the right subtree, and finally visit the root node.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Number of nodes in range [0, 100]
 *     - Node values in range [-100, 100]
 * 
 * Output:
 *     - List of node values in postorder traversal order
 * 
 * Example:
 *     Input: root = [1,2,3,4,5]
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *     Output: [4,5,2,3,1]
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class j03PostOrderTraversal {

    /**
     * TreeNode class represents a node in binary tree
     * Each node contains a value and references to left and right children
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic tree
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        System.out.println("Test Case 1 (Basic Tree):");
        System.out.println("Expected: [4,5,2,3,1]");
        System.out.println("Result: " + postorderTraversal(root1));

        // Test Case 2: Left-skewed tree
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        System.out.println("\nTest Case 2 (Left-skewed Tree):");
        System.out.println("Expected: [3,2,1]");
        System.out.println("Result: " + postorderTraversal(root2));

        // Test Case 3: Single node
        TreeNode root3 = new TreeNode(1);
        System.out.println("\nTest Case 3 (Single Node):");
        System.out.println("Expected: [1]");
        System.out.println("Result: " + postorderTraversal(root3));

        // Test Case 4: Empty tree
        System.out.println("\nTest Case 4 (Empty Tree):");
        System.out.println("Expected: []");
        System.out.println("Result: " + postorderTraversal(null));
    }

    /**
     * Approach: Recursive Postorder Traversal
     * 
     * Intuition:
     * - Follow Left->Right->Root pattern recursively
     * - Traverse left subtree first
     * - Then traverse right subtree
     * - Finally process current node
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * - n is number of nodes in tree
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * - Worst case O(n) for skewed tree
     * - Best case O(log n) for balanced tree
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    /**
     * Helper method to perform recursive postorder traversal
     * Traverses left subtree, right subtree, then processes current node
     */
    private static void postorder(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        postorder(root.left, list); // Process left subtree
        postorder(root.right, list); // Process right subtree
        list.add(root.val); // Process root
    }

    /**
    * Approach 1: Iterative Postorder Traversal using Two Stacks
    * 
    * Intuition:
    * - Use two stacks to reverse the order of processing
    * - First stack for traversal, second stack to store in reverse order
    * - Process: Root->Right->Left (in first stack)
    * - When popping from second stack: Left->Right->Root (postorder)
    * 
    * Algorithm:
    * 1. Push root to first stack
    * 2. While first stack not empty:
    *    - Pop node from first stack and push to second stack
    *    - Push left child to first stack (if exists)
    *    - Push right child to first stack (if exists)
    * 3. Pop all nodes from second stack to get postorder
    * 
    * Time: O(n) - visit each node once
    * Space: O(n) - two stacks can store all nodes
    */
    public static List<Integer> postorderTraversalIterativeTwoStacks(TreeNode root) {
        List<Integer> list = new ArrayList<>(); // Result list
        if (root == null)
            return list; // Handle empty tree

        Stack<TreeNode> stack1 = new Stack<>(); // For traversal
        Stack<TreeNode> stack2 = new Stack<>(); // For reverse order

        stack1.push(root); // Start with root
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop(); // Get next node
            stack2.push(node); // Save for later

            if (node.left != null) { // Push left first
                stack1.push(node.left); // (will be processed later)
            }

            if (node.right != null) { // Push right second
                stack1.push(node.right); // (will be processed later)
            }
        }

        // Pop from second stack to get postorder
        while (!stack2.isEmpty()) {
            list.add(stack2.pop().val);
        }
        return list;
    }

    /**
     * Approach 2: Iterative Postorder Traversal using One Stack
     * 
     * Intuition:
     * - Use single stack and track last visited node
     * - Go left as far as possible first
     * - Process a node only after both children are processed
     * - Use temp pointer to track current node
     * 
     * Algorithm:
     * 1. Go left while possible, pushing nodes to stack
     * 2. At null, peek at stack top:
     *    - If right child null/visited, process current
     *    - Else, move to right child
     * 3. Track processed nodes to handle backtracking
     * 
     * Time: O(n) - visit each node once
     * Space: O(h) - stack stores height of tree
     */
    public static List<Integer> postorderTraversalIterativeOneStack(TreeNode root) {
        List<Integer> list = new ArrayList<>(); // Result list
        if (root == null)
            return list; // Handle empty tree

        Stack<TreeNode> stack = new Stack<>(); // Process nodes
        TreeNode temp = root; // Current node pointer

        while (!stack.isEmpty() || temp != null) {
            if (temp != null) { // Go left as far as possible
                stack.push(temp); // Save current node
                temp = temp.left; // Move left
            } else {
                TreeNode node = stack.peek(); // Look at stack top
                if (node.right == null) { // If no right child
                    stack.pop(); // Process current node
                    list.add(node.val);
                    // Process all parent nodes where current was right child
                    while (!stack.isEmpty() && node == stack.peek().right) {
                        node = stack.pop();
                        list.add(node.val);
                    }
                } else {
                    temp = node.right; // Move to right child
                }
            }
        }
        return list;
    }
}