/**
 * Level Order Array Max Heap Validator
 * 
 * Problem Statement:
 *     Given an array representing a level order traversal of a binary tree,
 *     determine if it represents a valid max heap. A max heap must satisfy
 *     the property that each parent node is greater than or equal to its
 *     children.
 * 
 * Input:
 *     - arr (long[]): Array representing level order traversal
 *     - n (long): Size of the array
 * 
 * Output:
 *     - boolean: True if array represents a valid max heap, false otherwise
 * 
 * Example:
 *     Input: arr = [10, 5, 3, 2, 4]
 *     Output: true
 * 
 *     Explanation:
 *     The array represents a valid max heap because:
 *     - 10 > 5 and 10 > 3 (root > children)
 *     - 5 > 2 and 5 > 4 (left subtree)
 *     - 3 has no children (right subtree)
 */

public class j05IsLevelOrderArrayHeap {
    /**
     * Recursive Heap Validator: Validates if array represents a max heap
     * 
     * Intuition:
     * - Start from root (index 0)
     * - Recursively check each node's children
     * - Verify max heap property at each level
     * 
     * Explanation:
     * - Step 1: Check if current index is valid
     * - Step 2: Calculate left and right child indices
     * - Step 3: Verify parent > children
     * - Step 4: Recursively check subtrees
     * 
     * Edge Cases:
     * - Empty array: Returns true
     * - Single element: Returns true
     * - Invalid indices: Returns true (beyond array bounds)
     * 
     * Connection to Solution:
     * - Entry point for heap validation
     * - Delegates to recursive helper
     * 
     * Time Complexity: O(n) where n is array length
     * Space Complexity: O(log n) due to recursion
     * 
     * @param arr    Array representing level order traversal
     * @param n      Size of the array
     * @return       True if array represents a valid max heap
     */
    public static boolean countSub(long arr[], long n) {
        return isMaxHeap(arr, 0);
    }

    /**
     * Recursive Max Heap Checker: Validates max heap property recursively
     * 
     * Intuition:
     * - Check current node against children
     * - Recursively validate left and right subtrees
     * - Return false if any violation found
     * 
     * Explanation:
     * - Step 1: Base case - beyond array bounds
     * - Step 2: Calculate child indices
     * - Step 3: Check left child if exists
     * - Step 4: Check right child if exists
     * - Step 5: Recursively check subtrees
     * 
     * Edge Cases:
     * - Leaf nodes: Returns true
     * - Nodes with only left child: Checks only left
     * - Invalid indices: Returns true
     * 
     * Connection to Solution:
     * - Core recursive validation logic
     * - Maintains heap property check
     * 
     * Time Complexity: O(n) where n is array length
     * Space Complexity: O(log n) due to recursion
     * 
     * @param arr     Array representing level order traversal
     * @param index   Current node index to validate
     * @return        True if subtree rooted at index is a valid max heap
     */
    public static boolean isMaxHeap(long[] arr, int index) {
        if (index >= arr.length)
            return true;
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        if (left < arr.length && arr[left] > arr[index])
            return false;
        if (right < arr.length && arr[right] > arr[index])
            return false;
        return isMaxHeap(arr, left) && isMaxHeap(arr, right);
    }

    public static void main(String[] args) {
        // Test Case 1: Valid Max Heap
        System.out.println("\nTest Case 1: Valid Max Heap");
        long[] arr1 = {10, 5, 3, 2, 4};
        System.out.println("Input: " + java.util.Arrays.toString(arr1));
        System.out.println("Is Max Heap: " + countSub(arr1, arr1.length) + 
                         " (Expected: true)");

        // Test Case 2: Invalid Max Heap
        System.out.println("\nTest Case 2: Invalid Max Heap");
        long[] arr2 = {1, 2, 3, 4, 5};
        System.out.println("Input: " + java.util.Arrays.toString(arr2));
        System.out.println("Is Max Heap: " + countSub(arr2, arr2.length) + 
                         " (Expected: false)");

        // Test Case 3: Single Element
        System.out.println("\nTest Case 3: Single Element");
        long[] arr3 = {1};
        System.out.println("Input: " + java.util.Arrays.toString(arr3));
        System.out.println("Is Max Heap: " + countSub(arr3, arr3.length) + 
                         " (Expected: true)");

        // Test Case 4: Empty Array
        System.out.println("\nTest Case 4: Empty Array");
        long[] arr4 = {};
        System.out.println("Input: " + java.util.Arrays.toString(arr4));
        System.out.println("Is Max Heap: " + countSub(arr4, arr4.length) + 
                         " (Expected: true)");

        // Test Case 5: Complete Binary Tree
        System.out.println("\nTest Case 5: Complete Binary Tree");
        long[] arr5 = {7, 6, 5, 4, 3, 2, 1};
        System.out.println("Input: " + java.util.Arrays.toString(arr5));
        System.out.println("Is Max Heap: " + countSub(arr5, arr5.length) + 
                         " (Expected: true)");
    }
}
