/**
 * GeeksForGeeks: K Largest Elements
 * 
 * Problem Statement:
 *     Given an array of N elements and a number K, find the K largest elements
 *     from the array. The output elements should be printed in decreasing order.
 * 
 * Input:
 *     - arr[]: Array of integers (1 <= N <= 10^5)
 *     - k: Number of largest elements to find (1 <= k <= N)
 * 
 * Output:
 *     - ArrayList<Integer>: K largest elements in decreasing order
 * 
 * Example:
 *     Input: arr[] = {12, 5, 787, 1, 23}, k = 2
 *     Output: [787, 23]
 * 
 *     Explanation:
 *     The 2 largest elements are 787 and 23, returned in decreasing order.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class j01KLargestElements {

    /**
     * Approach 1: Max Heap
     * 
     * Intuition:
     * - Use a max heap to store all elements
     * - Extract k largest elements by removing from heap
     * - Elements come out in decreasing order naturally
     * 
     * Explanation:
     * 1. Create max heap and add all elements
     * 2. Extract k elements from heap
     * 3. Return elements in decreasing order
     * 
     * Time Complexity: O(n log n) for building heap + O(k log n) for extraction
     * Space Complexity: O(n) for storing all elements in heap
     * 
     * @param arr    Input array of integers
     * @param k      Number of largest elements to find
     * @return       ArrayList containing k largest elements in decreasing order
     */
    public static ArrayList<Integer> kLargest(int[] arr, int k) {
        // Initialize max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        // Add all elements to max heap
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }

        // Extract k largest elements
        ArrayList<Integer> out = new ArrayList<>();
        while (k-- > 0) {
            out.add(pq.remove());
        }
        return out;
    }

    /**
     * Approach 2: Min Heap (More Efficient)
     * 
     * Intuition:
     * - Maintain a min heap of size k
     * - Keep only k largest elements in heap
     * - Extract and reverse to get decreasing order
     * 
     * Explanation:
     * 1. Create min heap of size k
     * 2. For each element:
     *    - If heap size < k, add element
     *    - If element > heap root, replace root
     * 3. Extract all elements and reverse
     * 
     * Time Complexity: O(n log k) where n is array length
     * Space Complexity: O(k) for the heap
     * 
     * @param arr    Input array of integers
     * @param k      Number of largest elements to find
     * @return       ArrayList containing k largest elements in decreasing order
     */
    public static ArrayList<Integer> kLargestEfficient(int[] arr, int k) {
        // Initialize min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // Process each element
        for (int i = 0; i < arr.length; i++) {
            if (pq.size() == k) {
                // Replace smallest element if current is larger
                if (pq.peek() < arr[i]) {
                    pq.remove();
                    pq.add(arr[i]);
                }
            } else {
                // Add element if heap not full
                pq.add(arr[i]);
            }
        }

        // Extract elements and reverse to get decreasing order
        ArrayList<Integer> out = new ArrayList<>();
        for (int i = 0; i < k; i++)
            out.add(pq.remove());
        Collections.reverse(out);
        return out;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] arr1 = {12, 5, 787, 1, 23};
        System.out.println("Input: [12, 5, 787, 1, 23], k = 2");
        System.out.println("Max Heap Output: " + kLargest(arr1, 2));
        System.out.println("Min Heap Output: " + kLargestEfficient(arr1, 2));

        // Test Case 2: Edge case - k equals array length
        System.out.println("\nEdge Case - k equals array length:");
        int[] arr2 = {1, 2, 3, 4, 5};
        System.out.println("Input: [1, 2, 3, 4, 5], k = 5");
        System.out.println("Max Heap Output: " + kLargest(arr2, 5));
        System.out.println("Min Heap Output: " + kLargestEfficient(arr2, 5));

        // Test Case 3: Edge case - k equals 1
        System.out.println("\nEdge Case - k equals 1:");
        int[] arr3 = {3, 2, 1};
        System.out.println("Input: [3, 2, 1], k = 1");
        System.out.println("Max Heap Output: " + kLargest(arr3, 1));
        System.out.println("Min Heap Output: " + kLargestEfficient(arr3, 1));

        // Test Case 4: Special case - duplicate elements
        System.out.println("\nSpecial Case - duplicate elements:");
        int[] arr4 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println("Input: [3, 2, 3, 1, 2, 4, 5, 5, 6], k = 3");
        System.out.println("Max Heap Output: " + kLargest(arr4, 3));
        System.out.println("Min Heap Output: " + kLargestEfficient(arr4, 3));
    }
}
