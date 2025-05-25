/**
 * LeetCode 106. Construct Binary Tree from Inorder and Postorder Traversal
 * 
 * Problem Statement:
 *     Given two integer arrays inorder and postorder where inorder is the inorder 
 *     traversal of a binary tree and postorder is the postorder traversal of the 
 *     same tree, construct and return the binary tree.
 * 
 * Input:
 *     - int[] inorder (array containing inorder traversal, values unique)
 *     - int[] postorder (array containing postorder traversal, values unique)
 * 
 * Output:
 *     - TreeNode (root of constructed binary tree)
 * 
 * Example:
 *     Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 *     Output: [3,9,20,null,null,15,7]
 * 
 *     Explanation:
 *          3
 *         / \
 *        9  20
 *           / \
 *          15  7
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class j01ConstructFromPostorderInorder {
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * Constructs binary tree from inorder and postorder traversals
     * 
     * Intuition:
     * - Last element in postorder is root
     * - Elements left of root in inorder form left subtree
     * - Elements right of root in inorder form right subtree
     * - Use HashMap to quickly find root position in inorder
     * 
     * Explanation:
     * - Create HashMap to store inorder value to index mapping
     * - Call recursive helper with initial bounds
     * - Helper method constructs tree recursively using array bounds
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(n) for HashMap storage
     * 
     * @param inorder    Array containing inorder traversal
     * @param postorder  Array containing postorder traversal
     * @return          Root node of constructed binary tree
     */
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        // Create map to store inorder value to index mapping
        HashMap<Integer, Integer> map = new HashMap<>();
        int length = postorder.length;
        
        // Populate map with inorder indices for O(1) lookup
        for (int i = 0; i < length; i++) {
            map.put(inorder[i], i);
        }
        
        // Start recursive construction with full array bounds
        return buildTreePostIn(postorder, inorder, map, 0, length - 1, length - 1);
    }

    /**
     * Helper method for recursive tree construction
     * 
     * Intuition:
     * - Use array bounds to identify subtree ranges
     * - Postorder index determines current root
     * - Map lookup finds split point in inorder array
     * 
     * Explanation:
     * - Base case: left > right means empty subtree
     * - Create root from current postorder value
     * - Find root position in inorder using map
     * - Recursively build left and right subtrees
     * 
     * Time Complexity: O(n) for visiting each node once
     * Space Complexity: O(h) for recursion stack
     * 
     * @param postorder  Postorder traversal array
     * @param inorder   Inorder traversal array
     * @param map       HashMap for inorder value lookup
     * @param left      Left bound of current subtree in inorder
     * @param right     Right bound of current subtree in inorder
     * @param index     Current index in postorder array
     * @return         Root of current subtree
     */
    private static TreeNode buildTreePostIn(int[] postorder, int[] inorder, 
            HashMap<Integer, Integer> map, int left, int right, int index) {
        // Base case: empty subtree
        if (left > right)
            return null;
            
        // Get current root value from postorder
        int value = postorder[index];
        TreeNode root = new TreeNode(value);
        
        // Leaf node case
        if (left == right)
            return root;
            
        // Find root position in inorder
        int mid = map.get(value);
        
        // Build right subtree (process first as we're using postorder)
        root.right = buildTreePostIn(postorder, inorder, map, 
            mid + 1, right, index - 1);
            
        // Build left subtree (index adjustment accounts for right subtree size)
        root.left = buildTreePostIn(postorder, inorder, map, 
            left, mid - 1, index - 1 - (right - mid));
            
        return root;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic tree
        System.out.println("\nBasic Test Case:");
        int[] inorder1 = {9, 3, 15, 20, 7};
        int[] postorder1 = {9, 15, 7, 20, 3};
        TreeNode result1 = buildTree(inorder1, postorder1);
        printTree(result1);
        
        // Test Case 2: Single node
        System.out.println("\nSingle Node Case:");
        int[] inorder2 = {1};
        int[] postorder2 = {1};
        TreeNode result2 = buildTree(inorder2, postorder2);
        printTree(result2);
        
        // Test Case 3: Left-skewed tree
        System.out.println("\nLeft-skewed Tree Case:");
        int[] inorder3 = {3, 2, 1};
        int[] postorder3 = {3, 2, 1};
        TreeNode result3 = buildTree(inorder3, postorder3);
        printTree(result3);
        
        // Test Case 4: Right-skewed tree
        System.out.println("\nRight-skewed Tree Case:");
        int[] inorder4 = {1, 2, 3};
        int[] postorder4 = {3, 2, 1};
        TreeNode result4 = buildTree(inorder4, postorder4);
        // Print results
        printTree(result4);  // Expected: 1->2->3
    }

    // Helper method to print tree structure
    private static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
        
        // Level order traversal to print tree
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            System.out.println();
        }
    }
    
}
