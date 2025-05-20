/**
 * Problem Statement:
 *     LeetCode 2385. Amount of Time for Binary Tree to Be Infected
 * 
 *     Given the root of a binary tree and an integer start. The infection starts 
 *     from a node with value start. Each minute, infection spreads to the directly 
 *     connected nodes that are not infected. Return the number of minutes needed 
 *     for the whole tree to be infected.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Starting node value (integer)
 *     - Tree can have any number of nodes
 * 
 * Output:
 *     - Time (in minutes) for entire tree to be infected
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         5   2
 *        /   / \
 *       3   4   6
 *     start = 3
 *     
 *     Output: 4
 *     Explanation: 
 *     Minute 0: Node 3 is infected
 *     Minute 1: Node 5 is infected
 *     Minute 2: Node 1 is infected
 *     Minute 3: Node 2 is infected
 *     Minute 4: Nodes 4 and 6 are infected
 */

public class j03BurningTree {

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
     * Main method to calculate infection time
     * 
     * Approach:
     * - Use DFS to find starting node and track distances
     * - Calculate maximum time needed to reach all nodes
     * - Time equals maximum distance from start node
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * 
     * @param root Root node of binary tree
     * @param start Value of starting node
     * @return Time needed to infect whole tree
     */
    public int amountOfTime(TreeNode root, int start) {
        int[] max = new int[1];
        findNodesAtKDist(root, start, max);
        return max[0];
    }

    /**
     * Helper method to find starting node and calculate distances
     * 
     * Algorithm:
     * 1. If node is null, return 0
     * 2. If current node is start:
     *    - Calculate distances for all descendants
     *    - Return 1 (distance from parent)
     * 3. Search left and right subtrees
     * 4. If start found in subtree:
     *    - Calculate distances for other subtree
     *    - Update maximum time if needed
     *    - Return distance to parent
     * 
     * @param root Current node being processed
     * @param start Value of starting node
     * @param max Array to store maximum time needed
     * @return Distance from current node to start node, -1 if not found
     */
    public int findNodesAtKDist(TreeNode root, int start, int[] max) {
        if (root == null)
            return 0;
        if (root.val == start) {
            findNodesAtKFromRoot(root, 0, max);
            return 1;
        }
        int left = findNodesAtKDist(root.left, start, max);
        int right = findNodesAtKDist(root.right, start, max);

        if (left > 0) {
            findNodesAtKFromRoot(root.right, left + 1, max);
            max[0] = Math.max(max[0], left);
            return 1 + left;
        }

        if (right > 0) {
            findNodesAtKFromRoot(root.left, right + 1, max);
            max[0] = Math.max(max[0], right);
            return 1 + right;
        }

        return -1;
    }

    /**
     * Helper method to calculate distances from a root node
     * 
     * @param root Starting node for distance calculation
     * @param dist Current distance from start
     * @param max Array to store maximum time needed
     */
    public void findNodesAtKFromRoot(TreeNode root, int dist, int[] max) {
        if (root == null)
            return;
        max[0] = Math.max(max[0], dist);
        findNodesAtKFromRoot(root.left, dist + 1, max);
        findNodesAtKFromRoot(root.right, dist + 1, max);
    }

    public static void main(String[] args) {
        j03BurningTree solution = new j03BurningTree();

        // Test Case 1: Basic tree
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(6);
        
        System.out.println("Test Case 1 (Start=3): " + 
            solution.amountOfTime(root1, 3));  // Expected: 4

        // Test Case 2: Linear tree
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        
        System.out.println("Test Case 2 (Start=1): " + 
            solution.amountOfTime(root2, 1));  // Expected: 2

        // Test Case 3: Start at leaf
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);
        
        System.out.println("Test Case 3 (Start=3): " + 
            solution.amountOfTime(root3, 3));  // Expected: 2
    }
}
