/**
 * GeeksForGeeks. Verify Identical BSTs
 * 
 * Problem Statement:
 *     Given two arrays representing preorder traversals of two BSTs, check if
 *     they represent identical BSTs. Two BSTs are identical if they have the
 *     same structure and node values.
 * 
 * Input:
 *     - arr1: ArrayList<Integer> representing first BST's preorder traversal
 *     - arr2: ArrayList<Integer> representing second BST's preorder traversal
 *     - n: Size of the arrays (both arrays must be of same size)
 * 
 * Output:
 *     - boolean: true if BSTs are identical, false otherwise
 * 
 * Example:
 *     Input: 
 *     arr1 = [2, 1, 3]
 *     arr2 = [2, 3, 1]
 *     Output: false
 * 
 *     Explanation:
 *     First BST:     Second BST:
 *         2             2
 *        / \           / \
 *       1   3         3   1
 *     These BSTs have different structures, hence not identical.
 */

import java.util.ArrayList;

public class j06VerifyIdenticalBST {

    /**
     * Approach: Recursive Comparison
     * 
     * Intuition:
     * - First element in preorder is root
     * - Elements less than root go to left subtree
     * - Elements greater than root go to right subtree
     * - Compare roots and recursively compare subtrees
     * 
     * Explanation:
     * - Step 1: Check if both arrays are empty (base case)
     * - Step 2: Compare root values
     * - Step 3: Split arrays into left and right subtrees
     * - Step 4: Recursively compare left and right subtrees
     * 
     * Time Complexity: O(N²) where N is array size
     * Space Complexity: O(N) for recursion stack and array lists
     * 
     * @param arr1    First BST's preorder traversal
     * @param arr2    Second BST's preorder traversal
     * @param n       Size of the arrays
     * @return        true if BSTs are identical, false otherwise
     */
    public static boolean isIdenticalBST(ArrayList<Integer> arr1, ArrayList<Integer> arr2, int n) {
        // Initialize result array for pass-by-reference
        boolean[] ans = new boolean[1];
        ans[0] = true;
        canBeBST(arr1, arr2, ans);
        return ans[0];
    }

    /**
     * Helper Method: Recursive comparison of BSTs
     * 
     * Intuition:
     * - Compare root values
     * - Split arrays into left and right subtrees
     * - Recursively compare subtrees
     * 
     * Explanation:
     * - Step 1: Handle base cases (empty arrays)
     * - Step 2: Compare root values
     * - Step 3: Create left and right subtree arrays
     * - Step 4: Recursively compare subtrees
     * 
     * Time Complexity: O(N) per node
     * Space Complexity: O(N) for recursion stack and array lists
     * 
     * @param arr1    First BST's preorder traversal
     * @param arr2    Second BST's preorder traversal
     * @param ans     Boolean array to store result
     */
    public static void canBeBST(ArrayList<Integer> arr1, ArrayList<Integer> arr2, boolean[] ans) {
        // Base case: both arrays empty
        if (arr1.size() == 0 && arr2.size() == 0)
            return;

        // If one array is empty and other is not, BSTs are different
        if (arr1.size() == 0 || arr2.size() == 0) {
            ans[0] = false;
            return;
        }

        // Compare root values
        int root1 = arr1.get(0);
        int root2 = arr2.get(0);
        if (root1 != root2) {
            ans[0] = false;
            return;
        }

        // Create lists for left and right subtrees
        ArrayList<Integer> left1 = new ArrayList<>();
        ArrayList<Integer> right1 = new ArrayList<>();
        ArrayList<Integer> left2 = new ArrayList<>();
        ArrayList<Integer> right2 = new ArrayList<>();

        // Split first array into left and right subtrees
        for (int i = 1; i < arr1.size(); i++) {
            int ele = arr1.get(i);
            if (ele < root1)
                left1.add(ele);
            else
                right1.add(ele);
        }

        // Split second array into left and right subtrees
        for (int i = 1; i < arr2.size(); i++) {
            int ele = arr2.get(i);
            if (ele < root2)
                left2.add(ele);
            else
                right2.add(ele);
        }

        // Recursively compare left and right subtrees
        canBeBST(left1, left2, ans);
        canBeBST(right1, right2, ans);
    }

    public static void main(String[] args) {
        // Test Case 1: Identical BSTs
        System.out.println("\nBasic Test Case - Identical BSTs:");
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();
        arr1.add(2); arr1.add(1); arr1.add(3);
        arr2.add(2); arr2.add(1); arr2.add(3);
        System.out.println("Input: arr1 = [2,1,3], arr2 = [2,1,3]");
        System.out.println("Expected: true, Output: " + isIdenticalBST(arr1, arr2, 3));

        // Test Case 2: Different BSTs
        System.out.println("\nTest Case - Different BSTs:");
        ArrayList<Integer> arr3 = new ArrayList<>();
        ArrayList<Integer> arr4 = new ArrayList<>();
        arr3.add(2); arr3.add(1); arr3.add(3);
        arr4.add(2); arr4.add(3); arr4.add(1);
        System.out.println("Input: arr1 = [2,1,3], arr2 = [2,3,1]");
        System.out.println("Expected: false, Output: " + isIdenticalBST(arr3, arr4, 3));

        // Test Case 3: Empty arrays
        System.out.println("\nEdge Case - Empty Arrays:");
        ArrayList<Integer> arr5 = new ArrayList<>();
        ArrayList<Integer> arr6 = new ArrayList<>();
        System.out.println("Input: arr1 = [], arr2 = []");
        System.out.println("Expected: true, Output: " + isIdenticalBST(arr5, arr6, 0));

        // Test Case 4: Different sizes
        System.out.println("\nEdge Case - Different Sizes:");
        ArrayList<Integer> arr7 = new ArrayList<>();
        ArrayList<Integer> arr8 = new ArrayList<>();
        arr7.add(1);
        System.out.println("Input: arr1 = [1], arr2 = []");
        System.out.println("Expected: false, Output: " + isIdenticalBST(arr7, arr8, 1));
    }
}
