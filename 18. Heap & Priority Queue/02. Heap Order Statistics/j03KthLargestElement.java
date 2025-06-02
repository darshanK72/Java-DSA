/**
 * LeetCode 215: Kth Largest Element in an Array
 * 
 * Problem Statement:
 *     Given an integer array nums and an integer k, return the kth largest
 *     element in the array. Note that it is the kth largest element in the
 *     sorted order, not the kth distinct element.
 * 
 * Input:
 *     - nums[]: Array of integers (1 <= nums.length <= 10^4)
 *     - k: Position of largest element to find (1 <= k <= nums.length)
 * 
 * Output:
 *     - int: The kth largest element in the array
 * 
 * Example:
 *     Input: nums = [3,2,1,5,6,4], k = 2
 *     Output: 5
 * 
 *     Explanation:
 *     The sorted array would be [1,2,3,4,5,6]. The 2nd largest element is 5.
 */

import java.util.PriorityQueue;

public class j03KthLargestElement {

    /**
     * Approach 1: QuickSelect Algorithm
     * 
     * Intuition:
     * - Modified version of QuickSort that only sorts the part of array
     *   containing the kth largest element
     * - Uses partition to find pivot's final position
     * - Recursively searches only the relevant partition
     * 
     * Explanation:
     * 1. Choose a pivot and partition array around it
     * 2. If pivot is at position (n-k), we found our answer
     * 3. If pivot is at position > (n-k), search left partition
     * 4. If pivot is at position < (n-k), search right partition
     * 
     * Time Complexity: O(n) average case, O(n²) worst case
     * Space Complexity: O(1) as we modify array in-place
     * 
     * @param nums    Input array of integers
     * @param k       Position of largest element to find
     * @return        The kth largest element
     */
    public static int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    /**
     * Helper Method: QuickSelect
     * 
     * @param nums    Input array
     * @param l       Left boundary of current partition
     * @param r       Right boundary of current partition
     * @param k       Position of largest element to find
     * @return        The kth largest element in current partition
     */
    private static int quickSelect(int[] nums, int l, int r, int k) {
        if (l == r)
            return nums[l];
            
        // Find pivot's final position
        int pivotIndex = partition(nums, l, r);
        
        // Check if pivot is the kth largest element
        if (pivotIndex == nums.length - k)
            return nums[pivotIndex];
            
        // Search appropriate partition
        if (pivotIndex > nums.length - k)
            return quickSelect(nums, l, pivotIndex - 1, k);
        return quickSelect(nums, pivotIndex + 1, r, k);
    }

    /**
     * Helper Method: Partition
     * 
     * @param arr    Array to partition
     * @param l      Left boundary
     * @param r      Right boundary
     * @return       Final position of pivot
     */
    private static int partition(int[] arr, int l, int r) {
        // Choose rightmost element as pivot
        int pivot = arr[r];
        int j = l;
        
        // Move all elements smaller than pivot to left
        for (int i = l; i <= r; i++) {
            if (arr[i] < pivot) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
        }

        // Place pivot in its final position
        int temp = arr[r];
        arr[r] = arr[j];
        arr[j] = temp;

        return j;
    }

    /**
     * Approach 2: Min Heap
     * 
     * Intuition:
     * - Maintain a min heap of size k
     * - Keep only k largest elements in heap
     * - Root of heap will be kth largest element
     * 
     * Explanation:
     * 1. Create min heap of size k
     * 2. For each element:
     *    - If heap size < k, add element
     *    - If element > heap root, replace root
     * 3. Return root of heap
     * 
     * Time Complexity: O(n log k) where n is array length
     * Space Complexity: O(k) for the heap
     * 
     * @param nums    Input array of integers
     * @param k       Position of largest element to find
     * @return        The kth largest element
     */
    public static int findKthLargestEfficient(int[] nums, int k) {
        // Initialize min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // Process each element
        for (int i = 0; i < nums.length; i++) {
            if (pq.size() < k) {
                pq.add(nums[i]);
            } else if (nums[i] > pq.peek()) {
                pq.remove();
                pq.add(nums[i]);
            }
        }

        return pq.peek();
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] arr1 = {3, 2, 1, 5, 6, 4};
        System.out.println("Input: [3, 2, 1, 5, 6, 4], k = 2");
        System.out.println("QuickSelect Output: " + findKthLargest(arr1, 2));
        System.out.println("Min Heap Output: " + findKthLargestEfficient(arr1, 2));

        // Test Case 2: Edge case - k equals array length
        System.out.println("\nEdge Case - k equals array length:");
        int[] arr2 = {1, 2, 3};
        System.out.println("Input: [1, 2, 3], k = 3");
        System.out.println("QuickSelect Output: " + findKthLargest(arr2, 3));
        System.out.println("Min Heap Output: " + findKthLargestEfficient(arr2, 3));

        // Test Case 3: Edge case - k equals 1
        System.out.println("\nEdge Case - k equals 1:");
        int[] arr3 = {3, 2, 1};
        System.out.println("Input: [3, 2, 1], k = 1");
        System.out.println("QuickSelect Output: " + findKthLargest(arr3, 1));
        System.out.println("Min Heap Output: " + findKthLargestEfficient(arr3, 1));

        // Test Case 4: Special case - duplicate elements
        System.out.println("\nSpecial Case - duplicate elements:");
        int[] arr4 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println("Input: [3, 2, 3, 1, 2, 4, 5, 5, 6], k = 4");
        System.out.println("QuickSelect Output: " + findKthLargest(arr4, 4));
        System.out.println("Min Heap Output: " + findKthLargestEfficient(arr4, 4));
    }
}
