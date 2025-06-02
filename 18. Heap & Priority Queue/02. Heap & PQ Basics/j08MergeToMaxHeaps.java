/**
 * Max Heaps Merger
 * 
 * Problem Statement:
 *     Merge two max heaps into a single max heap. The merged heap should
 *     maintain the max heap property where each parent node is greater than
 *     or equal to its children.
 * 
 * Input:
 *     - a (int[]): First max heap array
 *     - b (int[]): Second max heap array
 *     - n (int): Size of first heap
 *     - m (int): Size of second heap
 * 
 * Output:
 *     - int[]: Merged max heap array of size (n + m)
 * 
 * Example:
 *     Input: 
 *     a = [10, 5, 3]  (max heap)
 *     b = [8, 4, 2]   (max heap)
 *     n = 3, m = 3
 * 
 *     Output: [10, 8, 5, 4, 3, 2]
 * 
 *     Explanation:
 *     The heaps are merged by repeatedly extracting the maximum element
 *     from either heap and placing it in the result array. The result
 *     maintains the max heap property.
 */

public class j08MergeToMaxHeaps {
    /**
     * Max Heaps Merger: Merges two max heaps into one
     * 
     * Intuition:
     * - Extract maximum elements from both heaps
     * - Place them in result array in descending order
     * - This naturally maintains max heap property
     * 
     * Explanation:
     * - Step 1: Create result array of size n + m
     * - Step 2: While either heap has elements:
     *   - Compare maximum elements from both heaps
     *   - Extract and place larger element in result
     * - Step 3: Return merged array
     * 
     * Edge Cases:
     * - Empty heaps: Returns empty array
     * - One empty heap: Returns other heap
     * 
     * Connection to Solution:
     * - Main merging logic
     * - Uses peek and remove operations
     * 
     * Time Complexity: O((n + m) * log(n + m)) where n,m are heap sizes
     * Space Complexity: O(n + m) for result array
     * 
     * @param a    First max heap array
     * @param b    Second max heap array
     * @param n    Size of first heap
     * @param m    Size of second heap
     * @return     Merged max heap array
     */
    public int[] mergeHeaps(int[] a, int[] b, int n, int m) {
        int[] out = new int[n + m];
        int k = 0;
        while (peek(a, n) != -1 || peek(b, m) != -1) {
            if (peek(a, n) > peek(b, m)) {
                out[k++] = remove(a, n--);
            } else {
                out[k++] = remove(b, m--);
            }
        }
        return out;
    }

    /**
     * Element Remover: Removes and returns maximum element from heap
     * 
     * Intuition:
     * - Remove root (maximum element)
     * - Maintain heap property
     * - Return removed element
     * 
     * Explanation:
     * - Step 1: Check if heap is empty
     * - Step 2: Swap root with last element
     * - Step 3: Remove last element
     * - Step 4: Heapify down from root
     * 
     * Edge Cases:
     * - Empty heap: Returns -1
     * - Single element: Returns that element
     * 
     * Connection to Solution:
     * - Used during merging process
     * - Maintains heap property
     * 
     * Time Complexity: O(log n) where n is heap size
     * Space Complexity: O(log n) due to recursion
     * 
     * @param heap    Heap array
     * @param size    Current size of heap
     * @return        Maximum element or -1 if empty
     */
    public int remove(int[] heap, int size) {
        if (size <= 0)
            return -1;
        swap(heap, 0, size - 1);
        int value = heap[size - 1];
        heap[size - 1] = -1;
        downHeapify(0, heap);
        return value;
    }

    /**
     * Maximum Element Peeker: Returns maximum element without removing
     * 
     * Intuition:
     * - Return root element (maximum)
     * - Don't modify heap
     * 
     * Explanation:
     * - Step 1: Check if heap is empty
     * - Step 2: Return root element
     * 
     * Edge Cases:
     * - Empty heap: Returns -1
     * - Single element: Returns that element
     * 
     * Connection to Solution:
     * - Used during merging process
     * - Helps compare maximum elements
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param heap    Heap array
     * @param size    Current size of heap
     * @return        Maximum element or -1 if empty
     */
    public int peek(int[] heap, int size) {
        if (size == 0)
            return -1;
        else
            return heap[0];
    }

    /**
     * Down Heapifier: Maintains max heap property by bubbling down
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
     * - Used after element removal
     * - Maintains heap property
     * 
     * Time Complexity: O(log n) where n is heap size
     * Space Complexity: O(log n) due to recursion
     * 
     * @param index    Index of element to heapify
     * @param heap     Heap array
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
     * Element Swapper: Swaps two elements in heap
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
     * @param heap    Heap array
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
        j08MergeToMaxHeaps merger = new j08MergeToMaxHeaps();

        // Test Case 1: Equal Size Heaps
        System.out.println("\nTest Case 1: Equal Size Heaps");
        int[] heap1 = {10, 5, 3};
        int[] heap2 = {8, 4, 2};
        int[] result1 = merger.mergeHeaps(heap1, heap2, 3, 3);
        System.out.println("Heap 1: " + java.util.Arrays.toString(heap1));
        System.out.println("Heap 2: " + java.util.Arrays.toString(heap2));
        System.out.println("Merged: " + java.util.Arrays.toString(result1) + 
                         " (Expected: [10, 8, 5, 4, 3, 2])");

        // Test Case 2: Different Size Heaps
        System.out.println("\nTest Case 2: Different Size Heaps");
        int[] heap3 = {15, 10, 5};
        int[] heap4 = {12, 8};
        int[] result2 = merger.mergeHeaps(heap3, heap4, 3, 2);
        System.out.println("Heap 1: " + java.util.Arrays.toString(heap3));
        System.out.println("Heap 2: " + java.util.Arrays.toString(heap4));
        System.out.println("Merged: " + java.util.Arrays.toString(result2) + 
                         " (Expected: [15, 12, 10, 8, 5])");

        // Test Case 3: One Empty Heap
        System.out.println("\nTest Case 3: One Empty Heap");
        int[] heap5 = {7, 4, 2};
        int[] heap6 = {};
        int[] result3 = merger.mergeHeaps(heap5, heap6, 3, 0);
        System.out.println("Heap 1: " + java.util.Arrays.toString(heap5));
        System.out.println("Heap 2: " + java.util.Arrays.toString(heap6));
        System.out.println("Merged: " + java.util.Arrays.toString(result3) + 
                         " (Expected: [7, 4, 2])");

        // Test Case 4: Both Empty Heaps
        System.out.println("\nTest Case 4: Both Empty Heaps");
        int[] heap7 = {};
        int[] heap8 = {};
        int[] result4 = merger.mergeHeaps(heap7, heap8, 0, 0);
        System.out.println("Heap 1: " + java.util.Arrays.toString(heap7));
        System.out.println("Heap 2: " + java.util.Arrays.toString(heap8));
        System.out.println("Merged: " + java.util.Arrays.toString(result4) + 
                         " (Expected: [])");
    }
}
