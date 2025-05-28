/**
 * LeetCode 897. Increasing Order Search Tree
 * 
 * Problem Statement:
 *     Given a binary search tree, rearrange the tree in in-order so that the 
 *     leftmost node in the tree is now the root of the tree, and every node 
 *     has no left child and only one right child.
 * 
 * Input:
 *     - root: Root node of BST
 *     - Number of nodes in range [0, 100]
 *     - Node values in range [0, 1000]
 * 
 * Output:
 *     - Root node of transformed tree where each node has only right child
 * 
 * Example:
 *     Input:        5
 *                 /   \
 *                3     6
 *               / \     \
 *              2   4     8
 *             /         / \
 *            1         7   9
 * 
 *     Output: 1->2->3->4->5->6->7->8->9
 */
import java.util.ArrayList;

public class j06ConstructSortedListFromBST {

    /**
     * Definition for binary tree node
     */
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
            left = right = null;
        }
    }

    /**
     * Approach 1: ArrayList Based Conversion
     * 
     * Intuition:
     * - Store nodes in ArrayList using inorder traversal
     * - Reconstruct tree using stored nodes
     * - Each node becomes right child of previous node
     * 
     * Explanation:
     * - Step 1: Create ArrayList to store nodes
     *          - ArrayList maintains nodes in sorted order
     *          - Allows easy access to nodes during reconstruction
     * 
     * - Step 2: Perform inorder traversal to fill ArrayList
     *          - Visit left subtree first (smaller values)
     *          - Add current node to list
     *          - Visit right subtree last (larger values)
     * 
     * - Step 3: Reconstruct tree from ArrayList
     *          - Process nodes in order from ArrayList
     *          - Set left child to null
     *          - Set right child to next node in list
     * 
     * Time Complexity: O(n) - Two passes through all nodes
     * Space Complexity: O(n) - ArrayList storage
     * 
     * @param root Root of input BST
     * @return Root of transformed right-skewed tree
     */
    public static TreeNode increasingBST(TreeNode root) {
        ArrayList<TreeNode> nodeList = new ArrayList<>();
        getNodeInList(root, nodeList);
        return convertToIncreasingTree(nodeList, 0);
    }

    /**
     * Helper Method: Inorder Traversal to ArrayList
     * 
     * Intuition:
     * - Perform inorder traversal (left, root, right)
     * - Add nodes to list in sorted order
     * 
     * Explanation:
     * - Step 1: Handle base case
     *          - Return if current node is null
     * 
     * - Step 2: Process left subtree
     *          - Recursively traverse left subtree first
     *          - Ensures smaller values are processed
     * 
     * - Step 3: Add current node
     *          - Add node to list after left subtree
     *          - Maintains sorted order
     * 
     * - Step 4: Process right subtree
     *          - Recursively traverse right subtree
     *          - Handles larger values last
     * 
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(h) - Recursion stack
     * 
     * @param root Current node being processed
     * @param list ArrayList to store nodes in order
     */
    public static void getNodeInList(TreeNode root, ArrayList<TreeNode> list) {
        if (root == null)
            return;
        getNodeInList(root.left, list);
        list.add(root);
        getNodeInList(root.right, list);
    }

    /**
     * Helper Method: Convert ArrayList to Right-Skewed Tree
     * 
     * Intuition:
     * - Process nodes in order from ArrayList
     * - Set left child to null and right child to next node
     * 
     * Time Complexity: O(n) - Process each node once
     * Space Complexity: O(h) - Recursion stack
     * 
     * @param list ArrayList containing nodes in order
     * @param index Current position in ArrayList
     * @return Root of current subtree
     */
    public static TreeNode convertToIncreasingTree(ArrayList<TreeNode> list, int index) {
        if (index == list.size())
            return null;
        TreeNode root = list.get(index);
        root.left = null;
        root.right = convertToIncreasingTree(list, index + 1);
        return root;
    }

    /**
     * Approach 2: Single Pass Efficient Solution
     * 
     * Intuition:
     * - Use dummy node to handle root case
     * - Maintain previous node reference during traversal
     * - Reconstruct tree during inorder traversal
     * 
     * Explanation:
     * - Step 1: Create dummy node
     *          - Acts as placeholder for head of list
     *          - Simplifies handling of first node
     * 
     * - Step 2: Maintain previous node reference
     *          - Use array to allow modification in recursive calls
     *          - Tracks last processed node for linking
     * 
     * - Step 3: Perform inorder traversal with reconstruction
     *          - Process left subtree first
     *          - Update current node's links
     *          - Process right subtree
     *          - Links are updated during traversal
     * 
     * - Step 4: Return transformed tree
     *          - Skip dummy node by returning its right child
     * 
     * Time Complexity: O(n) - Single pass through all nodes
     * Space Complexity: O(h) - Recursion stack
     * 
     * @param root Root of input BST
     * @return Root of transformed right-skewed tree
     */
    public static TreeNode increasingBSTEfficient(TreeNode root) {
        TreeNode dummy = new TreeNode(-1);
        TreeNode[] prev = new TreeNode[] { dummy };
        convertToIncreasingBST(root, prev);
        return dummy.right;
    }

    /**
     * Helper Method: In-place Tree Transformation
     * 
     * Intuition:
     * - Use array to hold previous node reference
     * - Update links during inorder traversal
     * 
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(h) - Recursion stack
     * 
     * @param root Current node being processed
     * @param prev Array containing previous node reference
     */
    public static void convertToIncreasingBST(TreeNode root, TreeNode[] prev) {
        if (root == null)
            return;
        // Process left subtree
        convertToIncreasingBST(root.left, prev);
        // Update current node
        prev[0].right = root;
        root.left = null;
        prev[0] = root;
        // Process right subtree
        convertToIncreasingBST(root.right, prev);
    }
}
