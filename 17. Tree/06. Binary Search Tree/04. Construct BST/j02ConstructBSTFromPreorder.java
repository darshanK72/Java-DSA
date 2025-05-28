/**
 * LeetCode 1008. Construct Binary Search Tree from Preorder Traversal
 * 
 * Problem Statement:
 *     Given an array of integers preorder, which represents the preorder traversal
 *     of a BST (binary search tree), construct the tree and return its root.
 * 
 * Input:
 *     - preorder: Integer array representing preorder traversal of BST
 * 
 * Output:
 *     - Root node of constructed BST
 * 
 * Example:
 *     Input: [8,5,1,7,10,12]
 *     Output: 
 *              8
 *            /   \
 *           5    10
 *          / \     \
 *         1   7    12
 *     
 *     Explanation:
 *     - First element 8 is root
 *     - All elements < 8 form left subtree [5,1,7]
 *     - All elements > 8 form right subtree [10,12]
 *     - Process repeats recursively
 */

import java.util.LinkedList;
import java.util.Queue;

public class j02ConstructBSTFromPreorder {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int item) {
            val = item;
            left = right = null;
        }
    }

    /**
     * Explanation:
     * - Convert preorder array to BST:
     *   1. First element is root
     *   2. Find first element > root (separates left and right subtrees)
     *   3. Recursively build left and right subtrees
     * 
     * Time Complexity: O(N²)
     * - For each node, may need to scan entire remaining array
     * 
     * Space Complexity: O(N)
     * - Recursion stack in worst case (skewed tree)
     * 
     * @param preorder Array containing preorder traversal
     * @return         Root node of constructed BST
     */
    public static TreeNode bstFromPreorder(int[] preorder) {
        return convertPreorderToBST(preorder, 0, preorder.length - 1);
    }

    /**
     * Helper Method: Find Index of First Larger Value
     * 
     * Explanation:
     * - Linear scan to find first element > key
     * - Returns -1 if no larger element found
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     * 
     * @param preorder Array to search in
     * @param s        Start index
     * @param e        End index
     * @param key      Value to compare against
     * @return         Index of first larger value or -1
     */
    public static int findBiggerValueIndex(int[] preorder, int s, int e, int key) {
        for (int i = s; i <= e; i++) {
            if (preorder[i] > key)
                return i;
        }
        return -1;
    }

    /**
     * Helper Method: Convert Preorder Segment to BST
     * 
     * Explanation:
     * - Converts array segment [s,e] to BST:
     *   1. Create root from first element
     *   2. Find index of first larger element
     *   3. If no larger element:
     *      - All remaining elements form left subtree
     *   4. If larger element found at index idx:
     *      - Elements [s+1, idx-1] form left subtree
     *      - Elements [idx, e] form right subtree
     * 
     * @param preorder Array containing preorder traversal
     * @param s        Start index of current segment
     * @param e        End index of current segment
     * @return         Root node of BST for current segment
     */
    public static TreeNode convertPreorderToBST(int[] preorder, int s, int e) {
        if (s > e)
            return null;
            
        // Create root from first element
        TreeNode root = new TreeNode(preorder[s]);
        
        // Find first larger element to separate subtrees
        int idx = findBiggerValueIndex(preorder, s + 1, e, preorder[s]);
        
        if (idx == -1) {
            // No larger element - all elements form left subtree
            root.left = convertPreorderToBST(preorder, s + 1, e);
        } else {
            // Split into left and right subtrees
            root.left = convertPreorderToBST(preorder, s + 1, idx - 1);
            root.right = convertPreorderToBST(preorder, idx, e);
        }
        return root;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        int[] preorder1 = {8, 5, 1, 7, 10, 12};
        System.out.println("\nBasic Test Case:");
        printLevelOrder(bstFromPreorder(preorder1));

        // Test Case 2: Edge case - single element
        int[] preorder2 = {1};
        System.out.println("\nSingle Element Test Case:");
        printLevelOrder(bstFromPreorder(preorder2));

        // Test Case 3: Increasing sequence
        int[] preorder3 = {1, 2, 3, 4, 5};
        System.out.println("\nIncreasing Sequence Test Case:");
        printLevelOrder(bstFromPreorder(preorder3));

        // Test Case 4: Decreasing sequence
        int[] preorder4 = {5, 4, 3, 2, 1};
        System.out.println("\nDecreasing Sequence Test Case:");
        printLevelOrder(bstFromPreorder(preorder4));
    }

    // Helper method to print BST level by level for verification
    private static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
       
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                System.out.print(node.val + " ");
                queue.add(node.left);
                queue.add(node.right);
            } else {
                System.out.print("null ");
            }
        }
    }
}
