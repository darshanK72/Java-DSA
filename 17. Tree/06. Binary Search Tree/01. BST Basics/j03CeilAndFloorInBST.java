/**
 * GeeksForGeeks: Ceil and Floor in Binary Search Tree
 * 
 * Problem Statement:
 *     Given a Binary Search Tree and a value X, find Ceil and Floor of X in BST.
 *     - Ceil Value: Smallest value in BST greater than or equal to X
 *     - Floor Value: Largest value in BST smaller than or equal to X
 * 
 * Input:
 *     - TreeNode root (root node of BST)
 *     - int target (value to find ceil and floor for)
 *     - Node values are integers
 * 
 * Output:
 *     - int[] containing [ceil, floor]
 *     - Return -1 if ceil or floor doesn't exist
 * 
 * Example:
 *     Input: BST = [8,4,12,2,6,10,14], target = 5
 *     Output: [6,4]
 * 
 *     Explanation:
 *          8
 *         / \
 *        4   12
 *       / \  / \
 *      2  6 10 14
 *     
 *     Ceil of 5 is 6 (smallest value > 5)
 *     Floor of 5 is 4 (largest value < 5)
 */

public class j03CeilAndFloorInBST {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }

    /**
     * Approach 1: Iterative Solution
     * 
     * Intuition:
     * - Traverse BST while updating ceil and floor values
     * - Use BST property to decide direction
     * 
     * Explanation:
     * - Initialize ceil as MAX_VALUE and floor as MIN_VALUE
     * - If current value < target: update floor, go right
     * - If current value > target: update ceil, go left
     * - If equal: both ceil and floor are current value
     * 
     * Time Complexity: O(h) where h is height of tree
     * Space Complexity: O(1) constant space
     */
    public static int[] findCeilAndFloorI(TreeNode root, int target) {
        int[] result = new int[2];
        result[0] = Integer.MAX_VALUE; // Ceil initialized to max value
        result[1] = Integer.MIN_VALUE; // Floor initialized to min value

        while (root != null) {
            if (root.val < target) {
                result[1] = Math.max(result[1], root.val); // Update floor
                root = root.right; // Go right
            } else if (root.val > target) {
                result[0] = Math.min(result[0], root.val); // Update ceil
                root = root.left; // Go left
            } else {
                // Found exact match, both ceil and floor are current node
                result[0] = root.val;
                result[1] = root.val;
                break;
            }
        }

        // Handle case where no ceil or floor found
        if (result[0] == Integer.MAX_VALUE)
            result[0] = -1; // No ceil found
        if (result[1] == Integer.MIN_VALUE)
            result[1] = -1; // No floor found

        return result;
    }

    /**
     * Approach 2: Recursive Solution
     * 
     * Intuition:
     * - Same logic as iterative but using recursion
     * - Pass result array through recursive calls
     * 
     * Explanation:
     * - Use helper method for recursive traversal
     * - Update ceil/floor in result array during traversal
     * - Base case: null node returns
     * 
     * Time Complexity: O(h) where h is height of tree
     * Space Complexity: O(h) for recursion stack
     */
    public static int[] findCeilAndFloorII(TreeNode root, int target) {
        int[] result = new int[2];
        result[0] = Integer.MAX_VALUE; // Ceil initialized to max value
        result[1] = Integer.MIN_VALUE; // Floor initialized to min value
        findCeilAndFloorHelper(root, target, result);

        // Handle case where no ceil or floor found
        if (result[0] == Integer.MAX_VALUE)
            result[0] = -1; // No ceil found
        if (result[1] == Integer.MIN_VALUE)
            result[1] = -1; // No floor found

        return result;
    }

    /**
     * Helper method for recursive approach
     */
    private static void findCeilAndFloorHelper(TreeNode node, int target, int[] result) {
        if (node == null)
            return; // Base case

        if (node.val < target) {
            result[1] = Math.max(result[1], node.val); // Update floor
            findCeilAndFloorHelper(node.right, target, result); // Go right
        } else if (node.val > target) {
            result[0] = Math.min(result[0], node.val); // Update ceil
            findCeilAndFloorHelper(node.left, target, result); // Go left
        } else {
            // Found exact match, both ceil and floor are current node
            result[0] = node.val;
            result[1] = node.val;
        }
    }

    /**
     * Approach 3: Separate Ceil and Floor Search
     * 
     * Intuition:
     * - Find ceil and floor separately using dedicated methods
     * - Combine results into single array
     * 
     * Explanation:
     * - Call separate methods for ceil and floor
     * - Handle null returns with -1 values
     * - Combine results into single array
     * 
     * Time Complexity: O(h) where h is height of tree
     * Space Complexity: O(1) constant space
     */
    public static int[] findCeilAndFloorIII(TreeNode root, int target) {
        int[] result = new int[2];
        result[0] = Integer.MAX_VALUE; // Ceil initialized to max value
        result[1] = Integer.MIN_VALUE; // Floor initialized to min value

        TreeNode floor = floorInBST(root, target);
        TreeNode ceil = ceilInBST(root, target);

        if (floor != null) {
            result[1] = floor.val; // Update floor
        } else {
            result[1] = -1; // No floor found
        }

        if (ceil != null) {
            result[0] = ceil.val; // Update ceil
        } else {
            result[0] = -1; // No ceil found
        }

        return result;
    }

    /**
     * Helper method to find ceil value
     * Returns smallest value >= key
     */
    public static TreeNode ceilInBST(TreeNode root, int key) {
        TreeNode ceil = null;
        while (root != null) {
            if (root.val < key) {
                root = root.right; // Go right
            } else if (root.val > key) {
                ceil = root; // Update ceil
                root = root.left; // Go left
            } else {
                return root; // Found exact match
            }
        }
        return ceil; // Return closest ceil found
    }

    /**
     * Helper method to find floor value
     * Returns largest value <= key
     */
    public static TreeNode floorInBST(TreeNode root, int key) {
        TreeNode floor = null;
        while (root != null) {
            if (root.val < key) {
                floor = root; // Update floor
                root = root.right; // Go right
            } else if (root.val > key) {
                root = root.left; // Go left
            } else {
                return root; // Found exact match
            }
        }
        return floor; // Return closest floor found
    }

    public static void main(String[] args) {
        // Test Case 1: Basic BST
        System.out.println("\nBasic BST Test:");
        TreeNode root1 = new TreeNode(8);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(12);
        root1.left.left = new TreeNode(2);
        root1.left.right = new TreeNode(6);
        root1.right.left = new TreeNode(10);
        root1.right.right = new TreeNode(14);
        
        int target = 5;
        int[] result1 = findCeilAndFloorI(root1, target);
        System.out.println("Target: " + target);
        System.out.println("Ceil: " + result1[0] + ", Floor: " + result1[1]);

        // Test Case 2: Target equals a node value
        target = 8;
        int[] result2 = findCeilAndFloorII(root1, target);
        System.out.println("\nTarget equals node value:");
        System.out.println("Target: " + target);
        System.out.println("Ceil: " + result2[0] + ", Floor: " + result2[1]);

        // Test Case 3: Target outside BST range
        target = 15;
        int[] result3 = findCeilAndFloorIII(root1, target);
        System.out.println("\nTarget outside range:");
        System.out.println("Target: " + target);
        System.out.println("Ceil: " + result3[0] + ", Floor: " + result3[1]);
    }
}