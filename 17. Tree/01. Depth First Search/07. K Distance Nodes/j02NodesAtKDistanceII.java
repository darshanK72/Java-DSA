/**
 * Problem Statement:
 *     LeetCode 863. All Nodes Distance K in Binary Tree
 * 
 *     Given the root of a binary tree, a target node, and an integer value k, return an array
 *     of values of all nodes that have a distance k from the target node. The distance between
 *     two nodes is the number of edges that connects the two nodes.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Target node reference
 *     - Distance K (non-negative integer)
 * 
 * Output:
 *     - List of values of nodes at distance K from target
 * 
 * Example:
 *     Input: 
 *           3
 *          / \
 *         5   1
 *        / \   \
 *       6   2   8
 *          / \
 *         7   4
 *     target = 5, k = 2
 *     
 *     Output: [7, 4, 1]
 *     Explanation: 
 *     - Node 7: 5 -> 2 -> 7
 *     - Node 4: 5 -> 2 -> 4
 *     - Node 1: 5 -> 3 -> 1
 */

import java.util.ArrayList;
import java.util.List;

public class j02NodesAtKDistanceII {

    /**
     * TreeNode class represents a node in binary tree
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Main method to find nodes at distance K from target
     * 
     * Approach:
     * - Use DFS to find target and maintain path distance
     * - When target found, search in both directions:
     *   1. Downward from target
     *   2. Upward and then downward through other paths
     * 
     * Time Complexity: O(n)
     * - Visit each node at most twice
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * 
     * @param root Root node of binary tree
     * @param target Target node to measure distance from
     * @param k Required distance
     * @return List of node values at distance k from target
     */
    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        findNodesAtKDist(root, target, k, result);
        return result;
    }

    /**
     * Helper method to find nodes using DFS
     * 
     * Algorithm:
     * 1. If node is null, return 0
     * 2. If node is target:
     *    - Find nodes k distance downward
     *    - Return 1 (distance from target)
     * 3. Search left and right subtrees
     * 4. If target found in subtree:
     *    - Search other subtree at appropriate distance
     *    - Check if current node is at distance k
     *    - Return distance + 1
     * 
     * @param root Current node being processed
     * @param target Target node to find
     * @param k Remaining distance to search
     * @param result List to store found node values
     * @return Distance from current node to target, -1 if not found
     */
    public static int findNodesAtKDist(TreeNode root, TreeNode target, int k, 
            ArrayList<Integer> result) {
        if (root == null)
            return 0;
        if (root == target) {
            findNodesAtKFromRoot(root, k, result);
            return 1;
        }
        int left = findNodesAtKDist(root.left, target, k, result);
        int right = findNodesAtKDist(root.right, target, k, result);

        if (left > 0) {
            findNodesAtKFromRoot(root.right, k - left - 1, result);
            if (k == left)
                result.add(root.val);
            return 1 + left;
        }

        if (right > 0) {
            findNodesAtKFromRoot(root.left, k - right - 1, result);
            if (k == right)
                result.add(root.val);
            return 1 + right;
        }

        return -1;
    }

    /**
     * Helper method to find nodes at distance K from root
     * 
     * @param root Starting node for search
     * @param k Distance to search
     * @param result List to store found node values
     */
    public static void findNodesAtKFromRoot(TreeNode root, int k, 
            ArrayList<Integer> result) {
        if (root == null)
            return;
        if (k == 0) {
            result.add(root.val);
        }
        findNodesAtKFromRoot(root.left, k - 1, result);
        findNodesAtKFromRoot(root.right, k - 1, result);
    }

    public static void main(String[] args) {
        // Test Case 1: Multiple paths to K distance
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(1);
        root1.left.left = new TreeNode(6);
        root1.left.right = new TreeNode(2);
        root1.right.right = new TreeNode(8);
        root1.left.right.left = new TreeNode(7);
        root1.left.right.right = new TreeNode(4);
        
        System.out.println("Test Case 1 (K=2 from node 5): " + 
            distanceK(root1, root1.left, 2));  // Expected: [7, 4, 1]

        // Test Case 2: Target is leaf node
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        
        System.out.println("Test Case 2 (K=1 from node 2): " + 
            distanceK(root2, root2.left, 1));  // Expected: [1]

        // Test Case 3: K greater than possible distance
        System.out.println("Test Case 3 (K=5): " + 
            distanceK(root1, root1.left, 5));  // Expected: []
    }
}
