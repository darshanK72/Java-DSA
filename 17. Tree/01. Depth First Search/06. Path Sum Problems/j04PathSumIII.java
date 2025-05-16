/**
 * Problem Statement:
 *     LeetCode 437. Path Sum III
 * 
 *     Given the root of a binary tree and an integer targetSum, return the number 
 *     of paths where the sum of the values along the path equals targetSum. 
 *     The path does not need to start or end at the root or a leaf, but it must 
 *     go downwards (i.e., traveling only from parent nodes to child nodes).
 * 
 * Input:
 *     - Root node of binary tree
 *     - Target sum (integer)
 *     - Node values can be any integer
 *     - Tree can have up to 10^4 nodes
 * 
 * Output:
 *     - Integer representing number of valid paths
 * 
 * Example:
 *     Input: 
 *           10
 *          /  \
 *         5   -3
 *        / \    \
 *       3   2   11
 *      / \   \
 *     3  -2   1
 *     targetSum = 8
 *     
 *     Output: 3
 *     Explanation: 
 *     The paths that sum to 8 are:
 *     1. 5 -> 3 (direct path)
 *     2. 5 -> 2 -> 1 (direct path)
 *     3. -3 -> 11 (direct path)
 */

import java.util.HashMap;

public class j04PathSumIII {

    /**
     * TreeNode class represents a node in binary tree
     * Contains value and references to left and right children
     */
    public static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        // Test cases implementation
        // ...existing test cases...
    }

    /**
     * Approach 1: Recursive DFS with Double Recursion (Brute Force)
     * 
     * Intuition:
     * - For each node in tree, we need to try it as starting point of path
     * - For each starting point, we need to try all possible paths going down
     * - Using double recursion:
     *   1. Outer recursion: tries each node as starting point
     *   2. Inner recursion: finds all paths from current start node
     * - Need long for currSum to handle integer overflow cases
     * 
     * Algorithm:
     * 1. Base case: if root is null, return 0
     * 2. For current root:
     *    - Find paths starting from this node
     *    - Recursively process left child as new start
     *    - Recursively process right child as new start
     * 3. Sum up all paths found
     * 
     * Time Complexity: O(n²)
     * - For skewed tree, at each node we might process all its descendants
     * - For balanced tree, still processes each subtree multiple times
     * 
     * Space Complexity: O(h)
     * - h is height of tree
     * - Maximum recursion stack depth
     * 
     * @param root Root node of binary tree
     * @param targetSum Target sum to find
     * @return Number of paths summing to target
     */
    public static int pathSum(TreeNode root, int targetSum) {
        if (root == null)
            return 0;
        return getPathSum(root, targetSum, 0) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
    }

    /**
     * Helper method to find paths starting from given node
     * 
     * @param root Starting node for path
     * @param targetSum Target sum to find
     * @param currSum Running sum of current path
     * @return Number of valid paths from this node
     */
    private static int getPathSum(TreeNode root, int targetSum, long currSum) {
        if (root == null)
            return 0;
        currSum += root.val;
        int ans = 0;
        if (currSum == (long) targetSum) {
            ans++;
        }

        int left = getPathSum(root.left, targetSum, currSum);
        int right = getPathSum(root.right, targetSum, currSum);
        return ans + left + right;
    }

    /**
     * Approach 2: Prefix Sum with HashMap (Optimized)
     * 
     * Intuition:
     * - Use concept of prefix sums to optimize path finding
     * - At each node, current path sum = sum of all node values from root
     * - If we have seen a prefix sum of (currSum - targetSum) before,
     *   it means there exists a path ending at current node with sum = targetSum
     * - Use HashMap to store prefix sums and their frequencies
     * - Need to do backtracking to handle parallel paths
     * 
     * Algorithm:
     * 1. Use HashMap to store (prefixSum -> frequency)
     * 2. At each node:
     *    - Update current sum by adding node value
     *    - Check if (currSum - targetSum) exists in map
     *    - Add current sum to map
     *    - Recursively process children
     *    - Remove current sum from map (backtrack)
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * - HashMap operations are O(1)
     * 
     * Space Complexity: O(h)
     * - h is height of tree
     * - HashMap stores at most h entries (one per level)
     * 
     * @param root Root node of binary tree
     * @param targetSum Target sum to find
     * @return Number of paths summing to target
     */
    public static int pathSumHashMap(TreeNode root, int targetSum) {
        HashMap<Long, Integer> prefixSums = new HashMap<>();
        prefixSums.put(0L, 1);  // Handle case when entire path sums to target
        return findPathsWithMap(root, targetSum, 0L, prefixSums);
    }

    /**
     * Helper method for prefix sum approach
     * 
     * @param root Current node being processed
     * @param targetSum Target sum to find
     * @param currSum Running sum from root to current node
     * @param prefixSums Map storing prefix sums and their frequencies
     * @return Number of valid paths through current node
     */
    private static int findPathsWithMap(TreeNode root, int targetSum, 
            long currSum, HashMap<Long, Integer> prefixSums) {
        if (root == null)
            return 0;

        currSum += root.val;

        int ans = prefixSums.getOrDefault(currSum - targetSum, 0);

        prefixSums.put(currSum, prefixSums.getOrDefault(currSum, 0) + 1);

        int left = findPathsWithMap(root.left, targetSum, currSum, prefixSums);
        int right = findPathsWithMap(root.right, targetSum, currSum, prefixSums);

        prefixSums.put(currSum, prefixSums.get(currSum) - 1);
        if (prefixSums.get(currSum) == 0)
            prefixSums.remove(currSum);

        return left + right + ans;
    }
}
