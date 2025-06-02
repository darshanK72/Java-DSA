/**
 * Custom Implementation: Min Heap to Max Heap Conversion
 * 
 * Problem Statement:
 *     Convert a given min heap into a max heap. The conversion should maintain
 *     the complete binary tree structure while reversing the heap property
 *     (parent should be greater than children instead of smaller).
 * 
 * Input:
 *     - n (int): Size of the heap array
 *     - arr (int[]): Array representing a min heap
 * 
 * Output:
 *     - Array representing a max heap with the same elements
 * 
 * Example:
 *     Input: n = 5, arr = [1, 2, 4, 3, 5]
 *     Output: [5, 3, 4, 1, 2]
 * 
 *     Explanation:
 *     The elements are rearranged to satisfy max heap property where each
 *     parent node is greater than its children. The root node (index 0)
 *     contains the maximum element.
 */

public class j03MinHeapToMaxHeap {
    /**
     * Convert Min Heap to Max Heap: Converts min heap to max heap
     * 
     * Intuition:
     * - Start from last non-leaf node
     * - Apply max heapify to each node
     * - This builds max heap from bottom-up
     * 
     * Explanation:
     * - Step 1: Find last non-leaf node (n/2 - 1)
     * - Step 2: Apply max heapify to each node
     * - Step 3: Process nodes in reverse level order
     * 
     * Edge Cases:
     * - Single element: No conversion needed
     * - Empty array: Returns empty array
     * 
     * Connection to Solution:
     * - Main method that orchestrates the conversion
     * - Uses downHeapify to maintain max heap property
     * 
     * Time Complexity: O(n) where n is array length
     * Space Complexity: O(log n) due to recursion in downHeapify
     * 
     * @param n    Size of the heap array
     * @param arr  Array representing a min heap
     * @return     Array representing a max heap
     */
    public static int[] MinToMaxHeap(int n, int[] arr) {
        for (int i = (arr.length / 2) - 1; i >= 0; i--) {
            downHeapify(i, arr);
        }
        return arr;
    }

    /**
     * Down Heapify: Maintains max heap property by bubbling down
     * 
     * Intuition:
     * - Compare element with children
     * - Swap with larger child if needed
     * - Continue until leaf or correct position
     * 
     * Explanation:
     * - Step 1: Check if index valid
     * - Step 2: Find maximum among current and children
     * - Step 3: Swap if needed
     * - Step 4: Continue with child
     * 
     * Edge Cases:
     * - Leaf node: Returns immediately
     * - Only left child: Compares only with left
     * - Invalid index: Returns immediately
     * 
     * Connection to Solution:
     * - Core method for maintaining max heap property
     * - Used during conversion process
     * 
     * Time Complexity: O(log n) where n is heap size
     * Space Complexity: O(log n) due to recursion
     * 
     * @param index    Index of element to heapify
     * @param heap     Array representing the heap
     */
    public static void downHeapify(int index, int[] heap) {
        // Base case: reached end of heap
        if (index == heap.length)
            return;
        // Calculate left and right child indices
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        // Assume current index is maximum
        int min = index;
        // Check if left child exists and is larger
        if (left < heap.length && heap[left] > heap[min]) {
            min = left;
        }
        // Check if right child exists and is larger
        if (right < heap.length && heap[right] > heap[min]) {
            min = right;
        }
        // If no swap needed, heap property is maintained
        if (min == index)
            return;
        // Swap with larger child
        swap(heap, index, min);
        // Continue heapifying down the tree
        downHeapify(min, heap);
    }

    /**
     * Swap: Swaps two elements in heap
     * 
     * Intuition:
     * - Helper method for element exchange
     * - Used by heapify operations
     * 
     * Explanation:
     * - Step 1: Store first element
     * - Step 2: Replace first with second
     * - Step 3: Replace second with stored
     * 
     * Edge Cases:
     * - Same indices: No effect
     * - Invalid indices: ArrayIndexOutOfBoundsException
     * 
     * Connection to Solution:
     * - Used by downHeapify
     * - Essential for maintaining heap property
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param heap    Array representing the heap
     * @param l       First index
     * @param r       Second index
     */
    public static void swap(int[] heap, int l, int r) {
        // Store first element
        int temp = heap[l];
        // Replace first element with second
        heap[l] = heap[r];
        // Replace second element with stored first
        heap[r] = temp;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic Conversion
        System.out.println("\nTest Case 1: Basic Conversion");
        int[] arr1 = {1, 2, 4, 3, 5};
        System.out.println("Input (Min Heap): " + java.util.Arrays.toString(arr1));
        MinToMaxHeap(arr1.length, arr1);
        System.out.println("Output (Max Heap): " + java.util.Arrays.toString(arr1) + 
                         " (Expected: [5, 3, 4, 1, 2])");

        // Test Case 2: Single Element
        System.out.println("\nTest Case 2: Single Element");
        int[] arr2 = {1};
        System.out.println("Input (Min Heap): " + java.util.Arrays.toString(arr2));
        MinToMaxHeap(arr2.length, arr2);
        System.out.println("Output (Max Heap): " + java.util.Arrays.toString(arr2) + 
                         " (Expected: [1])");

        // Test Case 3: Empty Array
        System.out.println("\nTest Case 3: Empty Array");
        int[] arr3 = {};
        System.out.println("Input (Min Heap): " + java.util.Arrays.toString(arr3));
        MinToMaxHeap(arr3.length, arr3);
        System.out.println("Output (Max Heap): " + java.util.Arrays.toString(arr3) + 
                         " (Expected: [])");

        // Test Case 4: Already Max Heap
        System.out.println("\nTest Case 4: Already Max Heap");
        int[] arr4 = {5, 4, 3, 2, 1};
        System.out.println("Input (Min Heap): " + java.util.Arrays.toString(arr4));
        MinToMaxHeap(arr4.length, arr4);
        System.out.println("Output (Max Heap): " + java.util.Arrays.toString(arr4) + 
                         " (Expected: [5, 4, 3, 2, 1])");
    }
}
