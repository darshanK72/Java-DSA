/**
 * Cartesian Tree Constructor
 * 
 * Problem Statement:
 *     Construct a Cartesian Tree from inorder traversal of an array of integers.
 *     A Cartesian Tree is a binary tree that satisfies the following properties:
 *     1. It is a heap (max heap in this implementation)
 *     2. An inorder traversal of the tree yields the original array
 * 
 * Input:
 *     - A (int[]): Array of integers representing inorder traversal
 * 
 * Output:
 *     - TreeNode: Root of the constructed Cartesian Tree
 * 
 * Example:
 *     Input: [3, 2, 6, 1, 9]
 * 
 *     Output Tree:
 *           9
 *          / \
 *         6   1
 *        / \
 *       3   2
 * 
 *     Explanation:
 *     - Root is maximum element (9)
 *     - Left subtree contains elements before 9
 *     - Right subtree contains elements after 9
 *     - Each subtree is recursively constructed
 */

public class j09ConstructCartasianTree {
    /**
     * TreeNode Structure: Represents a node in the Cartesian Tree
     * 
     * Fields:
     * - val: Value stored in the node
     * - left: Reference to left child
     * - right: Reference to right child
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * Cartesian Tree Builder: Main method to construct the tree
     * 
     * Intuition:
     * - Find maximum element in array
     * - Make it root
     * - Recursively construct left and right subtrees
     * 
     * Explanation:
     * - Step 1: Call helper method with full array range
     * - Step 2: Return constructed tree root
     * 
     * Edge Cases:
     * - Empty array: Returns null
     * - Single element: Returns single node
     * 
     * Connection to Solution:
     * - Entry point for tree construction
     * - Delegates to recursive helper
     * 
     * Time Complexity: O(n²) where n is array length
     * Space Complexity: O(n) for recursion stack
     * 
     * @param A    Input array
     * @return     Root of constructed Cartesian Tree
     */
    public static TreeNode buildTree(int[] A) {
        return buildHeapFromInorderHeapTraversal(A, 0, A.length - 1);
    }

    /**
     * Recursive Tree Constructor: Builds Cartesian Tree from array segment
     * 
     * Intuition:
     * - Find maximum in current segment
     * - Create node for maximum
     * - Recursively build subtrees
     * 
     * Explanation:
     * - Step 1: Check if segment is valid
     * - Step 2: Find maximum element in segment
     * - Step 3: Create root node with maximum
     * - Step 4: Recursively build left subtree
     * - Step 5: Recursively build right subtree
     * 
     * Edge Cases:
     * - Invalid segment: Returns null
     * - Single element: Returns single node
     * 
     * Connection to Solution:
     * - Core recursive construction logic
     * - Maintains heap and inorder properties
     * 
     * Time Complexity: O(n²) where n is segment length
     * Space Complexity: O(n) for recursion stack
     * 
     * @param arr    Input array
     * @param s      Start index of segment
     * @param e      End index of segment
     * @return       Root of constructed subtree
     */
    public static TreeNode buildHeapFromInorderHeapTraversal(int[] arr, int s, int e) {
        if (s > e)
            return null;
        int index = -1;
        int max = Integer.MIN_VALUE;
        for (int i = s; i <= e; i++) {
            if (arr[i] > max) {
                max = arr[i];
                index = i;
            }
        }

        TreeNode root = new TreeNode(arr[index]);
        root.left = buildHeapFromInorderHeapTraversal(arr, s, index - 1);
        root.right = buildHeapFromInorderHeapTraversal(arr, index + 1, e);
        return root;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic Case
        System.out.println("\nTest Case 1: Basic Case");
        int[] arr1 = {3, 2, 6, 1, 9};
        TreeNode root1 = buildTree(arr1);
        printTree(root1);
        System.out.println("Input Array: " + java.util.Arrays.toString(arr1));
        System.out.println("Tree Structure:");
        System.out.println("      9");
        System.out.println("     / \\");
        System.out.println("    6   1");
        System.out.println("   / \\");
        System.out.println("  3   2");

        // Test Case 2: Single Element
        System.out.println("\nTest Case 2: Single Element");
        int[] arr2 = {5};
        TreeNode root2 = buildTree(arr2);
        printTree(root2);
        System.out.println("Input Array: " + java.util.Arrays.toString(arr2));
        System.out.println("Tree Structure:");
        System.out.println("  5");

        // Test Case 3: Empty Array
        System.out.println("\nTest Case 3: Empty Array");
        int[] arr3 = {};
        TreeNode root3 = buildTree(arr3);
        printTree(root3);
        System.out.println("Input Array: " + java.util.Arrays.toString(arr3));
        System.out.println("Tree Structure: null");

        // Test Case 4: Sorted Array
        System.out.println("\nTest Case 4: Sorted Array");
        int[] arr4 = {1, 2, 3, 4, 5};
        TreeNode root4 = buildTree(arr4);
        printTree(root4);
        System.out.println("Input Array: " + java.util.Arrays.toString(arr4));
        System.out.println("Tree Structure:");
        System.out.println("  5");
        System.out.println(" /");
        System.out.println("4");
        System.out.println("/");
        System.out.println("3");
        System.out.println("/");
        System.out.println("2");
        System.out.println("/");
        System.out.println("1");
    }

    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("null");
            return;
        }
        System.out.println(root.val);
        printTree(root.left);
        printTree(root.right);
    }
    
}
