/**
 * GeeksForGeeks: Kth Smallest Element
 * 
 * Problem Statement:
 *     Given an array arr[] and an integer K where K is smaller than size of
 *     array, the task is to find the Kth smallest element in the given array.
 *     It is given that all array elements are distinct.
 * 
 * Input:
 *     - arr[]: Array of integers (1 <= N <= 10^5)
 *     - k: Position of smallest element to find (1 <= k <= N)
 * 
 * Output:
 *     - int: The kth smallest element in the array
 * 
 * Example:
 *     Input: arr[] = {7, 10, 4, 3, 20, 15}, k = 3
 *     Output: 7
 * 
 *     Explanation:
 *     The sorted array would be [3, 4, 7, 10, 15, 20]. The 3rd smallest
 *     element is 7.
 */

import java.util.Collections;
import java.util.PriorityQueue;

public class j04KthSmallestElement {

    /**
     * Approach: Max Heap
     * 
     * Intuition:
     * - Use a max heap to maintain k smallest elements
     * - Keep only k elements in heap at any time
     * - Root of heap will be kth smallest element
     * 
     * Explanation:
     * 1. Create max heap of size k
     * 2. For each element:
     *    - If heap size < k, add element
     *    - If element < heap root, replace root
     * 3. Return root of heap
     * 
     * Time Complexity: O(n log k) where n is array length
     *                  - O(log k) for each heap operation
     *                  - O(n) elements to process
     * Space Complexity: O(k) for storing k elements in heap
     * 
     * @param arr    Input array of integers
     * @param k      Position of smallest element to find
     * @return       The kth smallest element
     */
    public static int kthSmallest(int[] arr, int k) {
        // Initialize max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        // Process each element
        for (int i = 0; i < arr.length; i++) {
            if (pq.size() < k)
                pq.add(arr[i]);
            else if (pq.peek() > arr[i]) {
                pq.remove();
                pq.add(arr[i]);
            }
        }

        // Return kth smallest element
        return pq.remove();
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] arr1 = {7, 10, 4, 3, 20, 15};
        System.out.println("Input: [7, 10, 4, 3, 20, 15], k = 3");
        System.out.println("Output: " + kthSmallest(arr1, 3));

        // Test Case 2: Edge case - k equals 1
        System.out.println("\nEdge Case - k equals 1:");
        int[] arr2 = {5, 4, 3, 2, 1};
        System.out.println("Input: [5, 4, 3, 2, 1], k = 1");
        System.out.println("Output: " + kthSmallest(arr2, 1));

        // Test Case 3: Edge case - k equals array length
        System.out.println("\nEdge Case - k equals array length:");
        int[] arr3 = {1, 2, 3, 4, 5};
        System.out.println("Input: [1, 2, 3, 4, 5], k = 5");
        System.out.println("Output: " + kthSmallest(arr3, 5));

        // Test Case 4: Special case - sorted array
        System.out.println("\nSpecial Case - sorted array:");
        int[] arr4 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Input: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 4");
        System.out.println("Output: " + kthSmallest(arr4, 4));
    }
}
