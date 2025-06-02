/**
 * LeetCode 378: Kth Smallest Element in a Sorted Matrix
 * 
 * Problem Statement:
 *     Given an n x n matrix where each of the rows and columns is sorted in
 *     ascending order, return the kth smallest element in the matrix. Note that
 *     it is the kth smallest element in the sorted order, not the kth distinct
 *     element.
 * 
 * Input:
 *     - matrix[][]: n x n matrix where rows and columns are sorted
 *     - k: Position of smallest element to find (1 <= k <= n^2)
 * 
 * Output:
 *     - int: The kth smallest element in the matrix
 * 
 * Example:
 *     Input: matrix = [[1,5,9],
 *                     [10,11,13],
 *                     [12,13,15]], k = 8
 *     Output: 13
 * 
 *     Explanation:
 *     The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th
 *     smallest number is 13.
 */

import java.util.Collections;
import java.util.PriorityQueue;

public class j07KthSmallestInMatrix {

    /**
     * Approach 1: Max Heap
     * 
     * Intuition:
     * - Use a max heap to maintain k smallest elements
     * - Process all elements in matrix
     * - Keep only k smallest elements in heap
     * - Root of heap will be kth smallest element
     * 
     * Explanation:
     * 1. Create max heap of size k
     * 2. For each element in matrix:
     *    - If heap size < k, add element
     *    - If element < heap root, replace root
     * 3. Return root of heap
     * 
     * Time Complexity: O(n² log k) where n is matrix size
     *                  - O(n²) to process all elements
     *                  - O(log k) for each heap operation
     * Space Complexity: O(k) for storing k elements in heap
     * 
     * @param matrix    Input sorted matrix
     * @param k         Position of smallest element to find
     * @return          The kth smallest element
     */
    public static int kthSmallest(int[][] matrix, int k) {
        // Initialize max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        // Process all elements in matrix
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                // Add element if heap not full
                if(pq.size() < k) 
                    pq.add(matrix[i][j]);
                // Replace largest element if current is smaller
                else if(pq.peek() > matrix[i][j]) {
                    pq.remove();
                    pq.add(matrix[i][j]);
                }
            }
        }
        
        // Return kth smallest element
        return pq.remove();
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[][] matrix1 = {
            {1, 5, 9},
            {10, 11, 13},
            {12, 13, 15}
        };
        System.out.println("Input: matrix = [[1,5,9], [10,11,13], [12,13,15]], k = 8");
        System.out.println("Output: " + kthSmallest(matrix1, 8));

        // Test Case 2: k equals 1
        System.out.println("\nK equals 1 Test:");
        int[][] matrix2 = {
            {1, 2},
            {3, 4}
        };
        System.out.println("Input: matrix = [[1,2], [3,4]], k = 1");
        System.out.println("Output: " + kthSmallest(matrix2, 1));

        // Test Case 3: k equals matrix size
        System.out.println("\nK equals matrix size Test:");
        int[][] matrix3 = {
            {1, 2},
            {3, 4}
        };
        System.out.println("Input: matrix = [[1,2], [3,4]], k = 4");
        System.out.println("Output: " + kthSmallest(matrix3, 4));

        // Test Case 4: Single element matrix
        System.out.println("\nSingle Element Matrix Test:");
        int[][] matrix4 = {{5}};
        System.out.println("Input: matrix = [[5]], k = 1");
        System.out.println("Output: " + kthSmallest(matrix4, 1));
    }
}
