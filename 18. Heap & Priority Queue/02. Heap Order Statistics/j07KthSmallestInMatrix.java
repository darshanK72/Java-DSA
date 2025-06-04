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

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class j07KthSmallestInMatrix {

    /**
     * Approach 1: Sorting
     * 
     * Intuition:
     * - Since we need to find the kth smallest element, we can flatten the matrix
     *   into a single array and sort it
     * - The kth element in the sorted array will be our answer
     * 
     * Explanation:
     * 1. Create a new array of size n*n to store all matrix elements
     * 2. Flatten the matrix by copying all elements into the array
     * 3. Sort the array using Arrays.sort()
     * 4. Return the (k-1)th element (0-based indexing)
     * 
     * Time Complexity: O(n^2 log n^2) 
     *  - O(n^2) to flatten the matrix
     *  - O(n^2 log n^2) to sort the array
     * Space Complexity: O(n^2) 
     *  - O(n^2) to store the flattened matrix
     * 
     * @param matrix    Input sorted matrix of size n x n
     * @param k         Position of smallest element to find (1-based)
     * @return          The kth smallest element in the matrix
     */
    public static int kthSmallestUsingSorting(int[][] matrix, int k) {
        int[] out = new int[matrix.length * matrix[0].length];
        int l = 0;
        // Flatten the matrix into a single array
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                out[l++] = matrix[i][j];
            }
        }
        // Sort the array
        Arrays.sort(out);
        // Return the kth smallest element
        return out[k-1];
    }

    /**
     * Approach 2: Max Heap
     * 
     * Intuition:
     * - Use a max heap to maintain k smallest elements
     * - Process all elements in matrix
     * - Keep only k smallest elements in heap
     * - Root of heap will be kth smallest element
     * 
     * Explanation:
     * 1. Create max heap of size k using PriorityQueue with reverse order
     * 2. For each element in matrix:
     *    - If heap size < k, add element
     *    - If element < heap root, replace root with current element
     * 3. Return root of heap which will be kth smallest element
     * 
     * Time Complexity: O(n² log k) where n is matrix size
     *                  - O(n²) to process all elements
     *                  - O(log k) for each heap operation
     * Space Complexity: O(k) for storing k elements in heap
     * 
     * @param matrix    Input sorted matrix of size n x n
     * @param k         Position of smallest element to find (1-based)
     * @return          The kth smallest element in the matrix
     */
    public static int kthSmallestUsingMaxHeap(int[][] matrix, int k) {
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

    /**
     * Approach 3: Binary Search
     * 
     * Intuition:
     * - Use binary search to find the kth smallest element
     * - For each potential answer (mid), count how many elements are smaller
     * - Adjust search range based on count comparison with k
     * 
     * Explanation:
     * 1. Initialize search range from matrix[0][0] to matrix[n-1][n-1]
     * 2. While start <= end:
     *    - Calculate mid of current range
     *    - Count numbers smaller than or equal to mid in matrix
     *    - If count >= k, update end to mid-1 (answer might be smaller)
     *    - Else update start to mid+1 (answer must be larger)
     * 3. Return the final answer
     * 
     * Time Complexity: O(n^2 log n^2)
     *  - O(n^2) to count numbers smaller than mid
     *  - O(log n^2) for binary search iterations
     * Space Complexity: O(1) for storing variables
     * 
     * @param matrix    Input sorted matrix of size n x n
     * @param k         Position of smallest element to find (1-based)
     * @return          The kth smallest element in the matrix
     */
    public static int kthSmallestUsingBinarySearch(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int s = matrix[0][0];
        int e = matrix[m-1][n-1];
        int ans = -1;
        while(s <= e){
            int mid = s + (e - s)/2;
            int count = countNumbersSmallerThanMid(matrix,mid);
            if(count >= k){
                ans = mid;
                e = mid - 1;
            }else{
                s = mid + 1;
            }
        }
        return ans;
    }

    /**
     * Helper Method: Count Numbers Smaller Than Mid
     * 
     * Intuition:
     * - Count how many elements in the matrix are less than or equal to mid
     * - Use the sorted property of rows and columns for optimization
     * 
     * Explanation:
     * 1. For each row in the matrix:
     *    - If last element of row <= mid, all elements in row are counted
     *    - Otherwise, count elements <= mid in current row
     * 2. Return total count of elements <= mid
     * 
     * Time Complexity: O(n^2) in worst case
     *                  - O(n) when all elements are <= mid
     *                  - O(n^2) when we need to check each element
     * Space Complexity: O(1) for storing count
     * 
     * @param matrix    Input sorted matrix of size n x n
     * @param mid       The value to compare against
     * @return          Count of elements less than or equal to mid
     */
    public static int countNumbersSmallerThanMid(int[][] matrix, int mid) {
        int m = matrix.length;
        int n = matrix[0].length;
        int count = 0;
        for(int i = 0; i < m; i++){
            if(matrix[i][n-1] <= mid) count += n;
            else{
                for(int j = 0; j < n; j++){
                    if(matrix[i][j] <= mid) count++;
                }
            }
        }
        return count;
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
        System.out.println("Output: " + kthSmallestUsingSorting(matrix1, 8));

        // Test Case 2: k equals 1
        System.out.println("\nK equals 1 Test:");
        int[][] matrix2 = {
            {1, 2},
            {3, 4}
        };
        System.out.println("Input: matrix = [[1,2], [3,4]], k = 1");
        System.out.println("Output: " + kthSmallestUsingMaxHeap(matrix2, 1));

        // Test Case 3: k equals matrix size
        System.out.println("\nK equals matrix size Test:");
        int[][] matrix3 = {
            {1, 2},
            {3, 4}
        };
        System.out.println("Input: matrix = [[1,2], [3,4]], k = 4");
        System.out.println("Output: " + kthSmallestUsingBinarySearch(matrix3, 4));

        // Test Case 4: Single element matrix
        System.out.println("\nSingle Element Matrix Test:");
        int[][] matrix4 = {{5}};
        System.out.println("Input: matrix = [[5]], k = 1");
        System.out.println("Output: " + kthSmallestUsingBinarySearch(matrix4, 1));
    }
}
