/**
 * GeeksForGeeks. Verify Preorder Traversal of BST
 * 
 * Problem Statement:
 *     Given an array of integers, check if it can represent a valid preorder
 *     traversal of a Binary Search Tree (BST). In a valid BST preorder traversal,
 *     all elements in the left subtree of a node must be less than the node's
 *     value, and all elements in the right subtree must be greater than the
 *     node's value.
 * 
 * Input:
 *     - arr[]: Integer array representing preorder traversal
 *     - N: Size of the array
 * 
 * Output:
 *     - int: 1 if array can represent BST preorder, 0 otherwise
 * 
 * Example:
 *     Input: arr[] = {40, 30, 35, 80, 100}
 *     Output: 1
 * 
 *     Explanation:
 *     The array represents a valid BST preorder traversal:
 *           40
 *          /  \
 *         30   80
 *          \    \
 *           35   100
 */

import java.util.Stack;

public class j05VerifyPreorderTraversalOfBST {

    /**
     * Approach 1: Recursive Validation
     * 
     * Intuition:
     * - First element is root
     * - All elements less than root must be in left subtree
     * - All elements greater than root must be in right subtree
     * - Recursively validate left and right subtrees
     * 
     * Explanation:
     * - Step 1: First element is root
     * - Step 2: Find first element greater than root (right subtree start)
     * - Step 3: Verify all elements after that are greater than root
     * - Step 4: Recursively validate left and right subtrees
     * 
     * Time Complexity: O(N²) in worst case (skewed tree)
     * Space Complexity: O(N) for recursion stack
     * 
     * @param arr    Array representing preorder traversal
     * @param N      Size of the array
     * @return       1 if valid BST preorder, 0 otherwise
     */
    public static int canRepresentBST(int arr[], int N) {
        // Initialize result array for pass-by-reference
        boolean[] ans = new boolean[1];
        ans[0] = true;
        canBeBST(arr, 0, N - 1, ans);
        return ans[0] ? 1 : 0;
    }

    /**
     * Helper Method: Recursive validation of BST preorder
     * 
     * Intuition:
     * - Validate BST property for current subtree
     * - Split array into left and right subtrees
     * - Recursively validate subtrees
     * 
     * Explanation:
     * - Step 1: Base case for empty range
     * - Step 2: Find boundary between left and right subtrees
     * - Step 3: Verify all elements in right subtree are greater than root
     * - Step 4: Recursively validate left and right subtrees
     * 
     * Time Complexity: O(N) per node
     * Space Complexity: O(N) for recursion stack
     * 
     * @param arr    Array representing preorder traversal
     * @param s      Start index of current subtree
     * @param e      End index of current subtree
     * @param ans    Boolean array to store result
     */
    public static void canBeBST(int[] arr, int s, int e, boolean[] ans) {
        // Base case: empty range
        if (s > e)
            return;

        // Root is first element in preorder
        int root = arr[s];
        
        // Find first element greater than root (right subtree start)
        int i = s + 1;
        while (i <= e) {
            if (arr[i] < root)
                i++;
            else
                break;
        }

        // Verify all elements in right subtree are greater than root
        int j = i;
        while (j <= e) {
            if (arr[j] > root)
                j++;
            else
                break;
        }

        // If any element in right subtree is less than root, invalid BST
        if (j <= e) {
            ans[0] = false;
            return;
        }

        // Recursively validate left and right subtrees
        canBeBST(arr, s + 1, i - 1, ans);
        canBeBST(arr, i, j - 1, ans);
    }

    /**
     * Approach 2: Stack-based Validation (Efficient)
     * 
     * Intuition:
     * - Use stack to keep track of ancestors
     * - When we see a greater element, pop elements until we find
     *   the last popped element as root
     * - All elements after this must be greater than root
     * 
     * Explanation:
     * - Step 1: Initialize stack and root as minimum value
     * - Step 2: For each element, pop elements smaller than current
     * - Step 3: Last popped element becomes root
     * - Step 4: Verify current element is greater than root
     * 
     * Time Complexity: O(N) where N is array size
     * Space Complexity: O(N) for stack
     * 
     * @param arr    Array representing preorder traversal
     * @param N      Size of the array
     * @return       1 if valid BST preorder, 0 otherwise
     */
    public static int canRepresentBSTEfficient(int arr[], int N) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MIN_VALUE;
        
        for (int i = 0; i < N; i++) {
            // Pop elements smaller than current element
            while(!stack.isEmpty() && stack.peek() < arr[i]){
                root = stack.pop();
            }
            // If current element is smaller than root, invalid BST
            if(arr[i] < root) return 0;
            stack.push(arr[i]);
        }
        return 1;
    }

    public static void main(String[] args) {
        // Test Case 1: Valid BST preorder
        System.out.println("\nBasic Test Case - Valid BST:");
        int[] arr1 = {40, 30, 35, 80, 100};
        System.out.println("Input: [40, 30, 35, 80, 100]");
        System.out.println("Expected: 1, Output: " + canRepresentBST(arr1, arr1.length));
        System.out.println("Efficient Output: " + canRepresentBSTEfficient(arr1, arr1.length));

        // Test Case 2: Invalid BST preorder
        System.out.println("\nTest Case - Invalid BST:");
        int[] arr2 = {40, 30, 35, 20, 80, 100};
        System.out.println("Input: [40, 30, 35, 20, 80, 100]");
        System.out.println("Expected: 0, Output: " + canRepresentBST(arr2, arr2.length));
        System.out.println("Efficient Output: " + canRepresentBSTEfficient(arr2, arr2.length));

        // Test Case 3: Empty array
        System.out.println("\nEdge Case - Empty Array:");
        int[] arr3 = {};
        System.out.println("Input: []");
        System.out.println("Expected: 1, Output: " + canRepresentBST(arr3, arr3.length));
        System.out.println("Efficient Output: " + canRepresentBSTEfficient(arr3, arr3.length));

        // Test Case 4: Single element
        System.out.println("\nEdge Case - Single Element:");
        int[] arr4 = {1};
        System.out.println("Input: [1]");
        System.out.println("Expected: 1, Output: " + canRepresentBST(arr4, arr4.length));
        System.out.println("Efficient Output: " + canRepresentBSTEfficient(arr4, arr4.length));
    }
}
