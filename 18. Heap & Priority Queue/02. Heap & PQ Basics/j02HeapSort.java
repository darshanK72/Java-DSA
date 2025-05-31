/*-
 * Custom Implementation: Heap Sort Algorithm
 * 
 * Problem Statement:
 *     Implement Heap Sort algorithm to sort an array in ascending order using
 *     a min-heap data structure. The implementation should efficiently sort
 *     the array in-place using heap operations.
 * 
 * Input:
 *     - arr (int[]): Array of integers to be sorted
 * 
 * Output:
 *     - Sorted array in ascending order
 * 
 * Example:
 *     Input: [5, 3, 1, 4, 2]
 *     Output: [1, 2, 3, 4, 5]
 * 
 *     Explanation:
 *     The algorithm first builds a min-heap from the input array, then
 *     repeatedly extracts the minimum element to form the sorted array.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class j02HeapSort {

    /*-
     * Method: Main heap sort implementation
     * 
     * Intuition:
     * - Convert array to min-heap
     * - Extract minimum elements one by one
     * - Place them in sorted order
     * 
     * Explanation:
     * - Step 1: Convert input array to ArrayList for heap operations
     * - Step 2: Build min-heap from the array
     * - Step 3: Extract minimum elements and place in sorted order
     * - Step 4: Continue until heap is empty
     * 
     * Edge Cases:
     * - Empty array: No operation needed
     * - Single element: Already sorted
     * - Already sorted array: Still needs heapification
     * 
     * Connection to Solution:
     * - Uses min-heap property to efficiently find minimum elements
     * - Combines heap operations to achieve sorting
     * 
     * Time Complexity: O(n log n) where n is array length
     * Space Complexity: O(n) for ArrayList
     * 
     * @param arr    Array to be sorted
     */
    public static void heapSort(int arr[]) {
        // Convert array to ArrayList for heap operations
        ArrayList<Integer> heap = new ArrayList<>();
        for (int ele : arr)
            heap.add(ele);
        
        // Build min-heap from the array
        for (int i = (heap.size() / 2) - 1; i >= 0; i--) {
            downHeapify(i, heap);
        }
        
        // Extract elements in sorted order
        int i = 0;
        while (heap.size() > 0) {
            arr[i++] = remove(heap);
        }
    }

    /*-
     * Method: Maintains heap property by bubbling down element
     * 
     * Intuition:
     * - Compare element with its children
     * - Swap with smallest child if needed
     * - Continue until heap property is maintained
     * 
     * Explanation:
     * - Step 1: Check if index is valid
     * - Step 2: Find minimum among current and children
     * - Step 3: Swap if needed and continue heapifying
     * - Step 4: Recursively maintain heap property
     * 
     * Edge Cases:
     * - Leaf node: Returns immediately
     * - Only left child exists: Compares only with left
     * - No children: Returns immediately
     * 
     * Connection to Solution:
     * - Used in both heap building and element removal
     * - Maintains min-heap property throughout sorting
     * 
     * Time Complexity: O(log n) where n is heap size
     * Space Complexity: O(log n) due to recursion
     * 
     * @param index    Index of element to heapify
     * @param heap     ArrayList representing the heap
     */
    public static void downHeapify(int index, ArrayList<Integer> heap) {
        // Base case: reached end of heap
        if (index == heap.size())
            return;
        
        // Calculate child indices
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int min = index;
        
        // Find minimum among current and children
        if (left < heap.size() && heap.get(left) < heap.get(min)) {
            min = left;
        }
        if (right < heap.size() && heap.get(right) < heap.get(min)) {
            min = right;
        }
        
        // If no swap needed, heap property is maintained
        if (min == index)
            return;
            
        // Swap and continue heapifying
        swap(heap, index, min);
        downHeapify(min, heap);
    }

    /*-
     * Method: Swaps two elements in the heap
     * 
     * Intuition:
     * - Helper method to exchange elements at given indices
     * - Used by heapify operations
     * 
     * Explanation:
     * - Step 1: Store first element in temporary variable
     * - Step 2: Replace first element with second
     * - Step 3: Replace second element with stored first
     * 
     * Edge Cases:
     * - Same indices: No effect
     * - Invalid indices: IndexOutOfBoundsException
     * 
     * Connection to Solution:
     * - Used by downHeapify for maintaining heap property
     * - Essential for heap operations
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param heap    ArrayList representing the heap
     * @param l       First index
     * @param r       Second index
     */
    public static void swap(ArrayList<Integer> heap, int l, int r) {
        // Store first element
        int temp = heap.get(l);
        // Replace first element with second
        heap.set(l, heap.get(r));
        // Replace second element with stored first
        heap.set(r, temp);
    }

    /*-
     * Method: Removes and returns the minimum element
     * 
     * Intuition:
     * - Remove root element (minimum)
     * - Maintain heap property after removal
     * 
     * Explanation:
     * - Step 1: Check if heap is empty
     * - Step 2: Store root value
     * - Step 3: Swap root with last element
     * - Step 4: Remove last element
     * - Step 5: Maintain heap property
     * 
     * Edge Cases:
     * - Empty heap: Returns -1
     * - Single element: Returns that element
     * 
     * Connection to Solution:
     * - Used to extract elements in sorted order
     * - Maintains heap property after each removal
     * 
     * Time Complexity: O(log n) where n is heap size
     * Space Complexity: O(log n) due to downHeapify recursion
     * 
     * @param heap    ArrayList representing the heap
     * @return        Removed minimum element or -1 if empty
     */
    public static int remove(ArrayList<Integer> heap) {
        // Check if heap is empty
        if (heap.size() == 0)
            return -1;
            
        // Store root value
        int value = heap.get(0);
        
        // Swap root with last element
        swap(heap, 0, heap.size() - 1);
        
        // Remove last element
        heap.remove(heap.size() - 1);
        
        // Maintain heap property
        downHeapify(0, heap);
        
        return value;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic sorting
        System.out.println("\nTest Case 1: Basic Sorting");
        int[] arr1 = {5, 3, 1, 4, 2};
        System.out.println("Input: " + Arrays.toString(arr1));
        heapSort(arr1);
        System.out.println("Output: " + Arrays.toString(arr1) + 
                         " (Expected: [1, 2, 3, 4, 5])");

        // Test Case 2: Already sorted array
        System.out.println("\nTest Case 2: Already Sorted Array");
        int[] arr2 = {1, 2, 3, 4, 5};
        System.out.println("Input: " + Arrays.toString(arr2));
        heapSort(arr2);
        System.out.println("Output: " + Arrays.toString(arr2) + 
                         " (Expected: [1, 2, 3, 4, 5])");

        // Test Case 3: Reverse sorted array
        System.out.println("\nTest Case 3: Reverse Sorted Array");
        int[] arr3 = {5, 4, 3, 2, 1};
        System.out.println("Input: " + Arrays.toString(arr3));
        heapSort(arr3);
        System.out.println("Output: " + Arrays.toString(arr3) + 
                         " (Expected: [1, 2, 3, 4, 5])");

        // Test Case 4: Array with duplicates
        System.out.println("\nTest Case 4: Array with Duplicates");
        int[] arr4 = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        System.out.println("Input: " + Arrays.toString(arr4));
        heapSort(arr4);
        System.out.println("Output: " + Arrays.toString(arr4) + 
                         " (Expected: [1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 9])");

        // Test Case 5: Single element
        System.out.println("\nTest Case 5: Single Element");
        int[] arr5 = {1};
        System.out.println("Input: " + Arrays.toString(arr5));
        heapSort(arr5);
        System.out.println("Output: " + Arrays.toString(arr5) + 
                         " (Expected: [1])");

        // Test Case 6: Empty array
        System.out.println("\nTest Case 6: Empty Array");
        int[] arr6 = {};
        System.out.println("Input: " + Arrays.toString(arr6));
        heapSort(arr6);
        System.out.println("Output: " + Arrays.toString(arr6) + 
                         " (Expected: [])");

        // Test Case 7: Array with negative numbers
        System.out.println("\nTest Case 7: Array with Negative Numbers");
        int[] arr7 = {-5, 3, -1, 4, -2, 0};
        System.out.println("Input: " + Arrays.toString(arr7));
        heapSort(arr7);
        System.out.println("Output: " + Arrays.toString(arr7) + 
                         " (Expected: [-5, -2, -1, 0, 3, 4])");
    }
}
