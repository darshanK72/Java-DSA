/**
 * Problem Statement:
 *     LeetCode 993. Cousins in Binary Tree
 * 
 *     Given the root of a binary tree with unique values and two values x and y,
 *     return true if and only if x and y are cousins. Two nodes are cousins if
 *     they have the same depth with different parents.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Two values x and y to check
 * 
 * Output:
 *     - true if x and y are cousins, false otherwise
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *        /     \
 *       4       5
 *     x = 4, y = 5
 *     
 *     Output: true
 *     Explanation: 
 *     - Nodes 4 and 5 are at same level
 *     - They have different parents (2 and 3)
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class j04CousionsInBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class Pair {
        TreeNode node;
        TreeNode parent;

        Pair(TreeNode node, TreeNode parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Valid cousins
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.right.right = new TreeNode(5);
        
        System.out.println("Test Case 1:");
        System.out.println("HashMap Approach: " + 
            isCousins(root1, 4, 5));  // Expected: true
        System.out.println("Efficient Approach: " + 
            isCousinsEfficient(root1, 4, 5));  // Expected: true

        // Test Case 2: Same parent (siblings)
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        
        System.out.println("\nTest Case 2:");
        System.out.println("HashMap Approach: " + 
            isCousins(root2, 2, 3));  // Expected: false
        System.out.println("Efficient Approach: " + 
            isCousinsEfficient(root2, 2, 3));  // Expected: false
    }

    /**
     * Approach 1: BFS with HashMap and Pair Class
     * 
     * Intuition:
     * - Use BFS to process level by level
     * - Store node-parent pairs in HashMap for each level
     * - Two nodes are cousins if:
     *   1. They are at same level (found in same HashMap)
     *   2. They have different parents
     * 
     * Time: O(n) - visit each node once
     * Space: O(w) - w is max width of tree
     * 
     * @param root Root node of binary tree
     * @param x First value to check
     * @param y Second value to check
     * @return true if x and y are cousins
     */
    public static boolean isCousins(TreeNode root, int x, int y) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root,null));
        while(!queue.isEmpty()){
            HashMap<Integer,Pair> map = new HashMap<>();
            int s = queue.size();
            for(int i = 0; i < s; i++){
                Pair pair = queue.poll();
                if(pair.node.left != null){
                    queue.add(new Pair(pair.node.left,pair.node));
                }
                if(pair.node.right != null){
                    queue.add(new Pair(pair.node.right,pair.node));
                }
                map.put(pair.node.val,pair);
            }
            if(map.containsKey(x) && map.containsKey(y)){
                Pair p1 = map.get(x);
                Pair p2 = map.get(y);
                if(p1.parent == p2.parent) return false;
                return true;
            }else if(map.containsKey(x) || map.containsKey(y)){
                return false;
            }
        }
        return false;
    }

    /**
     * Approach 2: BFS with Direct Value Check
     * 
     * Intuition:
     * - Use BFS without extra storage
     * - Track presence of x and y in current level
     * - Check siblings condition directly
     * - More space efficient but slightly less readable
     * 
     * Time: O(n) - visit each node once
     * Space: O(w) - only queue storage needed
     * 
     * @param root Root node of binary tree
     * @param x First value to check
     * @param y Second value to check
     * @return true if x and y are cousins
     */
    public static boolean isCousinsEfficient(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int s = queue.size();
            boolean hasX = false;
            boolean hasY = false;
            for(int i = 0; i < s; i++){
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
                if(node.val == x) hasX = true;
                if(node.val == y) hasY = true;

                if(node.left != null && node.right != null){
                    if((node.left.val == x && node.right.val == y) || (node.left.val == y && node.right.val == x)) return false;
                }
            }
            if(hasX && hasY) return true;
        }
        return false;
    }
}
