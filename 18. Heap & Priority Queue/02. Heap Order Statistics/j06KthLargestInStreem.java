/**
 * LeetCode 703: Kth Largest Element in a Stream
 * 
 * Problem Statement:
 *     Design a class to find the kth largest element in a stream. Note that it
 *     is the kth largest element in the sorted order, not the kth distinct
 *     element. The class should support adding new elements and finding the
 *     kth largest element at any point.
 * 
 * Input:
 *     - k: Position of largest element to find
 *     - nums[]: Initial array of integers
 *     - val: New element to add to stream
 * 
 * Output:
 *     - int: The kth largest element after adding new value
 * 
 * Example:
 *     Input: k = 3, nums = [4, 5, 8, 2]
 *     add(3) -> returns 4
 *     add(5) -> returns 5
 *     add(10) -> returns 5
 *     add(9) -> returns 8
 *     add(4) -> returns 8
 * 
 *     Explanation:
 *     After adding each number, the kth largest element is returned.
 *     Initially: [4, 5, 8, 2] -> 4 is 3rd largest
 *     After adding 3: [4, 5, 8, 2, 3] -> 4 is 3rd largest
 *     After adding 5: [4, 5, 8, 2, 3, 5] -> 5 is 3rd largest
 *     And so on...
 */

import java.util.PriorityQueue;

public class j06KthLargestInStreem {
    
    /**
     * Class: KthLargestInStream
     * 
     * Maintains a min heap of size k to track k largest elements.
     * The root of the heap is always the kth largest element.
     */
    static class KthLargestInStream {
        private PriorityQueue<Integer> pq;  // Min heap to store k largest elements
        private int k;                      // Position of largest element to find

        /**
         * Constructor: Initializes the data structure with k and initial array
         * 
         * @param k     Position of largest element to find
         * @param nums  Initial array of integers
         */
        public KthLargestInStream(int k, int[] nums) {
            this.k = k;
            this.pq = new PriorityQueue<>();
            
            // Initialize heap with k largest elements from nums
            for (int i = 0; i < nums.length; i++) {
                if (pq.size() < k)
                    pq.add(nums[i]);
                else if (pq.peek() < nums[i]) {
                    pq.remove();
                    pq.add(nums[i]);
                }
            }
        }

        /**
         * Method: Adds a new element to the stream and returns kth largest
         * 
         * Intuition:
         * - Maintain min heap of size k
         * - If new element is larger than heap root, replace root
         * - Root of heap is always kth largest element
         * 
         * Time Complexity: O(log k) for heap operations
         * Space Complexity: O(k) for storing k elements in heap
         * 
         * @param val   New element to add to stream
         * @return      The kth largest element after adding val
         */
        public int add(int val) {
            // Add element if heap not full
            if (pq.size() < this.k)
                pq.add(val);
            // Replace smallest element if new value is larger
            else if (pq.peek() < val) {
                pq.remove();
                pq.add(val);
            }
            return pq.peek();
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic operations
        System.out.println("\nBasic Test Case:");
        KthLargestInStream kthLargest = new KthLargestInStream(3, new int[]{4, 5, 8, 2});
        System.out.println("Initial array: [4, 5, 8, 2], k = 3");
        System.out.println("After adding 3: " + kthLargest.add(3));
        System.out.println("After adding 5: " + kthLargest.add(5));
        System.out.println("After adding 10: " + kthLargest.add(10));
        System.out.println("After adding 9: " + kthLargest.add(9));
        System.out.println("After adding 4: " + kthLargest.add(4));

        // Test Case 2: Empty initial array
        System.out.println("\nEmpty Initial Array Test:");
        KthLargestInStream kthLargest2 = new KthLargestInStream(1, new int[]{});
        System.out.println("Initial array: [], k = 1");
        System.out.println("After adding 3: " + kthLargest2.add(3));
        System.out.println("After adding 2: " + kthLargest2.add(2));
        System.out.println("After adding 4: " + kthLargest2.add(4));

        // Test Case 3: k equals 1
        System.out.println("\nK equals 1 Test:");
        KthLargestInStream kthLargest3 = new KthLargestInStream(1, new int[]{2});
        System.out.println("Initial array: [2], k = 1");
        System.out.println("After adding 3: " + kthLargest3.add(3));
        System.out.println("After adding 4: " + kthLargest3.add(4));
        System.out.println("After adding 5: " + kthLargest3.add(5));

        // Test Case 4: Large numbers
        System.out.println("\nLarge Numbers Test:");
        KthLargestInStream kthLargest4 = new KthLargestInStream(2, new int[]{1000, 2000, 3000});
        System.out.println("Initial array: [1000, 2000, 3000], k = 2");
        System.out.println("After adding 4000: " + kthLargest4.add(4000));
        System.out.println("After adding 5000: " + kthLargest4.add(5000));
    }
}
