/**
 * GeeksForGeeks: K Largest Elements in Order
 * 
 * Problem Statement:
 *     Given an array of N elements and a number K, find the K smallest elements
 *     from the array and return them in the same order as they appear in the
 *     original array.
 * 
 * Input:
 *     - arr[]: Array of integers (1 <= N <= 10^5)
 *     - k: Number of smallest elements to find (1 <= k <= N)
 * 
 * Output:
 *     - ArrayList<Integer>: K smallest elements in their original order
 * 
 * Example:
 *     Input: arr[] = {5, 2, 8, 1, 9}, k = 3
 *     Output: [2, 1, 5]
 * 
 *     Explanation:
 *     The 3 smallest elements are 1, 2, and 5. We return them in the order
 *     they appear in the original array: [2, 1, 5]
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class j02KSmallestElementsInOrder {

    /**
     * Approach: Max Heap with Frequency Map
     * 
     * Intuition:
     * - Use a max heap to maintain k smallest elements
     * - Use a frequency map to track occurrences of elements
     * - Process array twice: once to find k smallest elements, then to
     *   maintain original order
     * 
     * Explanation:
     * 1. First Pass: Use max heap to find k smallest elements
     *    - Keep only k elements in heap
     *    - Replace largest element if current element is smaller
     * 2. Second Pass: Use frequency map to maintain order
     *    - Store frequencies of k smallest elements
     *    - Process original array to maintain order
     * 
     * Time Complexity: O(N log k) where N is array length and k is number of
     *                  elements to find
     * Space Complexity: O(k) for heap and frequency map
     * 
     * @param arr    Input array of integers
     * @param k      Number of smallest elements to find
     * @return       ArrayList containing k smallest elements in original order
     */
    public static ArrayList<Integer> kSmallestElements(int[] arr, int k) {
        // Initialize max heap to maintain k smallest elements
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        // First pass: Find k smallest elements using max heap
        for (int i = 0; i < arr.length; i++) {
            if (pq.size() < k)
                pq.add(arr[i]);
            else if (pq.peek() > arr[i]) {
                pq.remove();
                pq.add(arr[i]);
            }
        }

        // Initialize result list and frequency map
        ArrayList<Integer> out = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // Store frequencies of k smallest elements
        while (pq.size() > 0) {
            map.put(pq.peek(), map.getOrDefault(pq.peek(), 0) + 1);
            pq.remove();
        }

        // Second pass: Maintain original order using frequency map
        int i = 0;
        while (k > 0 && i < arr.length) {
            if (map.containsKey(arr[i])) {
                out.add(arr[i]);
                map.put(arr[i], map.get(arr[i]) - 1);
                if (map.get(arr[i]) == 0)
                    map.remove(arr[i]);
                k--;
            }
            i++;
        }
        return out;
    }

    public static void main(String[] args) {

        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] arr1 = {5, 2, 8, 1, 9};
        System.out.println("Input: [5, 2, 8, 1, 9], k = 3");
        System.out.println("Output: " + kSmallestElements(arr1, 3));

        // Test Case 2: Edge case - k equals array length
        System.out.println("\nEdge Case - k equals array length:");
        int[] arr2 = {3, 1, 4, 2};
        System.out.println("Input: [3, 1, 4, 2], k = 4");
        System.out.println("Output: " + kSmallestElements(arr2, 4));

        // Test Case 3: Edge case - k equals 1
        System.out.println("\nEdge Case - k equals 1:");
        int[] arr3 = {7, 3, 5, 1, 9};
        System.out.println("Input: [7, 3, 5, 1, 9], k = 1");
        System.out.println("Output: " + kSmallestElements(arr3, 1));

        // Test Case 4: Special case - duplicate elements
        System.out.println("\nSpecial Case - duplicate elements:");
        int[] arr4 = {2, 2, 1, 1, 3, 3};
        System.out.println("Input: [2, 2, 1, 1, 3, 3], k = 3");
        System.out.println("Output: " + kSmallestElements(arr4, 3));
    }
}
