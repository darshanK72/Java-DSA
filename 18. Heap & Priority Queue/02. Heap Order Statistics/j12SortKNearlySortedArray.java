/**
 * GeeksForGeeks: Sort a K Sorted Array
 * 
 * Problem Statement:
 *     Given an array of n elements, where each element is at most k away from
 *     its target position, devise an algorithm that sorts in O(n log k) time.
 *     For example, let us consider k is 2, an element at index 7 in the sorted
 *     array, can be at indexes 5, 6, 7, 8, 9 in the given array.
 * 
 * Input:
 *     - arr[]: Array of integers where each element is at most k positions away
 *              from its sorted position
 *     - k: Maximum distance of any element from its sorted position
 * 
 * Output:
 *     - void: The input array is sorted in-place
 * 
 * Example:
 *     Input: arr = [6, 5, 3, 2, 8, 10, 9], k = 3
 *     Output: [2, 3, 5, 6, 8, 9, 10]
 * 
 *     Explanation:
 *     Each element is at most 3 positions away from its sorted position.
 *     For example, 2 is 3 positions away from its sorted position (index 0).
 */

import java.util.PriorityQueue;

public class j12SortKNearlySortedArray {

    /**
     * Approach: Min Heap with Sliding Window
     * 
     * Intuition:
     * - Use min heap to maintain a window of k+1 elements
     * - The minimum element in this window will be the next element
     *   in the sorted array
     * - Slide the window forward as we process elements
     * 
     * Explanation:
     * 1. Initialize min heap
     * 2. For each element in array:
     *    - Add current element to heap
     *    - If heap size > k, extract minimum and place in result
     * 3. Process remaining elements in heap
     * 
     * Time Complexity: O(n log k) where n is array length
     *                  - O(log k) for each heap operation
     *                  - O(n) elements to process
     * Space Complexity: O(k) for storing k+1 elements in heap
     * 
     * @param arr    Input array where each element is at most k positions
     *               away from its sorted position
     * @param k      Maximum distance of any element from its sorted position
     */
    public static void nearlySorted(int[] arr, int k) {
        // Initialize min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int j = 0;

        // Process each element
        for(int i = 0; i < arr.length; i++) {
            // Add current element to heap
            pq.add(arr[i]);
            
            // If heap size exceeds k, extract minimum
            if(pq.size() > k) {
                arr[j++] = pq.remove(); 
            }
        }
        
        // Process remaining elements in heap
        while(!pq.isEmpty()) {
            arr[j++] = pq.remove();
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] arr1 = {6, 5, 3, 2, 8, 10, 9};
        int k1 = 3;
        System.out.println("Input: arr = " + java.util.Arrays.toString(arr1) + ", k = " + k1);
        nearlySorted(arr1, k1);
        System.out.println("Output: " + java.util.Arrays.toString(arr1));

        // Test Case 2: k = 0 (already sorted)
        System.out.println("\nK equals 0 Test:");
        int[] arr2 = {1, 2, 3, 4, 5};
        int k2 = 0;
        System.out.println("Input: arr = " + java.util.Arrays.toString(arr2) + ", k = " + k2);
        nearlySorted(arr2, k2);
        System.out.println("Output: " + java.util.Arrays.toString(arr2));

        // Test Case 3: k = n-1 (completely unsorted)
        System.out.println("\nK equals n-1 Test:");
        int[] arr3 = {5, 4, 3, 2, 1};
        int k3 = 4;
        System.out.println("Input: arr = " + java.util.Arrays.toString(arr3) + ", k = " + k3);
        nearlySorted(arr3, k3);
        System.out.println("Output: " + java.util.Arrays.toString(arr3));

        // Test Case 4: Single element
        System.out.println("\nSingle Element Test:");
        int[] arr4 = {1};
        int k4 = 0;
        System.out.println("Input: arr = " + java.util.Arrays.toString(arr4) + ", k = " + k4);
        nearlySorted(arr4, k4);
        System.out.println("Output: " + java.util.Arrays.toString(arr4));
    }
}
