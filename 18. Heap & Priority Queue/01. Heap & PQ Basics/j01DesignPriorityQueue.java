/**
 * Custom Implementation: Priority Queue using Array
 * 
 * Problem Statement:
 *     Implement a Priority Queue data structure using an array as the underlying
 *     storage. The implementation should support basic operations like insertion,
 *     deletion, and peeking while maintaining the min-heap property.
 * 
 * Input:
 *     - capacity (int): Maximum size of the priority queue
 *     - arr (int[]): Initial array of elements to build the heap
 * 
 * Output:
 *     - A fully functional Priority Queue with min-heap property
 * 
 * Example:
 *     Input: capacity = 5, arr = [3,1,4,2,5]
 *     Output: Priority Queue with elements [1,2,4,3,5]
 * 
 *     Explanation:
 *     The elements are arranged in a min-heap structure where each parent node
 *     is smaller than its children. The root node (index 0) always contains
 *     the minimum element.
 */

import java.util.Arrays;

public class j01DesignPriorityQueue {

    static class PriorityQueue {
        int[] heap;
        int size;

        /**
         * Constructor: Initializes the Priority Queue
         * 
         * Intuition:
         * - Creates a fixed-size array to store heap elements
         * - Initializes the heap with first element and builds min-heap
         * 
         * Explanation:
         * - Step 1: Allocate array space based on capacity
         * - Step 2: Set initial size to input array length
         * - Step 3: Copy all elements to heap
         * - Step 4: Build min-heap from the array
         * 
         * Edge Cases:
         * - Empty input array: Creates empty heap
         * - Full capacity: No additional elements can be added
         * 
         * Connection to Solution:
         * - Sets up the foundation for all heap operations
         * - Ensures initial heap property is maintained
         * 
         * Time Complexity: O(n) where n is the length of input array
         * Space Complexity: O(capacity) for storing the heap
         * 
         * @param capacity    Maximum size of the priority queue
         * @param arr         Initial array of elements
         */
        PriorityQueue(int capacity, int[] arr) {
            // Initialize heap array with given capacity
            this.heap = new int[capacity];
            // Set initial size to input array length
            this.size = arr.length;
            // Copy all elements to heap
            for (int i = 0; i < arr.length; i++) {
                this.heap[i] = arr[i];
            }
            // Build min-heap from the array
            buildMinHeapFromLeafs();
        }

        /**
         * Build Heap: Builds a min-heap from the input array
         * 
         * Intuition:
         * - Start from the last non-leaf node
         * - Heapify each node in reverse level order
         * - This ensures all subtrees are heaps before processing parent
         * 
         * Explanation:
         * - Step 1: Find last non-leaf node (size/2 - 1)
         * - Step 2: Heapify each node in reverse level order
         * - Step 3: This builds heap from bottom-up
         * 
         * Edge Cases:
         * - Single element: No heapification needed
         * - Already sorted array: Requires maximum swaps
         * 
         * Connection to Solution:
         * - Called during initialization to establish initial heap structure
         * - Ensures all elements follow min-heap property
         * 
         * Time Complexity: O(n) where n is array length
         * Space Complexity: O(1) as we modify array in-place
         */
        public void buildMinHeapFromLeafs() {
            // Start from last non-leaf node and heapify all nodes in reverse level order
            for (int i = (size / 2) - 1; i >= 0; i--) {
                downHeapify(i);
            }
        }

        /**
         * Build Heap From Root: Builds a min-heap by heapifying each element from root
         * 
         * Intuition:
         * - Start from the second element (index 1)
         * - Use upHeapify to maintain heap property for each element
         * - This approach builds heap from top-down
         * 
         * Explanation:
         * - Step 1: Start from second element (index 1)
         * - Step 2: For each element, use upHeapify to place it in correct position
         * - Step 3: upHeapify ensures element bubbles up to maintain heap property
         * - Step 4: Continue until all elements are processed
         * 
         * Edge Cases:
         * - Single element: No heapification needed
         * - Already sorted array: Requires maximum swaps
         * - Reverse sorted array: Each element needs to bubble up to root
         * 
         * Connection to Solution:
         * - Alternative approach to buildMinHeap
         * - More intuitive but less efficient
         * - Useful for understanding heap property maintenance
         * 
         * Time Complexity: O(n log n) where n is heap size
         * Space Complexity: O(log n) due to upHeapify recursion
         */
        public void buildMinHeapFromRoot(){
            // Start from second element and heapify each element
            for(int i = 1; i < size; i++){
                // Maintain heap property by bubbling up
                upHeapify(i);
            }
        }

        /**
         * Up Heapify: Maintains heap property by bubbling up element
         * 
         * Intuition:
         * - Compares element with its parent
         * - Swaps if parent is larger to maintain min-heap property
         * - Recursively continues until root or correct position
         * 
         * Explanation:
         * - Step 1: Check if element is at root (base case)
         * - Step 2: Calculate parent index using heap formula
         * - Step 3: Compare with parent and swap if needed
         * - Step 4: Recursively continue with parent position
         * 
         * Edge Cases:
         * - Root element: Returns immediately
         * - Already in correct position: No swaps needed
         * 
         * Connection to Solution:
         * - Used after insertion to maintain heap property
         * - Ensures new elements are placed correctly
         * 
         * Time Complexity: O(log n) where n is heap size
         * Space Complexity: O(log n) due to recursion stack
         * 
         * @param index    Index of element to heapify
         */
        public void upHeapify(int index) {
            // Base case: reached root
            if (index == 0)
                return;
            // Calculate parent index using heap property
            int parent = (index - 1) / 2;
            // If parent is larger, swap to maintain min-heap property
            if (this.heap[parent] > this.heap[index]) {
                swap(index, parent);
            }
            // Continue heapifying up the tree
            upHeapify(parent);
        }

        /**
         * Down Heapify: Maintains heap property by bubbling down element
         * 
         * Intuition:
         * - Compares element with its children
         * - Swaps with smallest child if needed
         * - Recursively continues until leaf or correct position
         * 
         * Explanation:
         * - Step 1: Check if element is at end of heap (base case)
         * - Step 2: Calculate left and right child indices
         * - Step 3: Find minimum among current and children
         * - Step 4: Swap if needed and continue with child
         * 
         * Edge Cases:
         * - Leaf node: Returns immediately
         * - Only left child exists: Compares only with left
         * - No children: Returns immediately
         * 
         * Connection to Solution:
         * - Used after removal to maintain heap property
         * - Ensures root element is properly placed
         * 
         * Time Complexity: O(log n) where n is heap size
         * Space Complexity: O(log n) due to recursion stack
         * 
         * @param index    Index of element to heapify
         */
        public void downHeapify(int index) {
            // Base case: reached end of heap
            if (index == this.size)
                return;
            // Calculate left and right child indices
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            // Assume current index is minimum
            int min = index;
            // Check if left child exists and is smaller
            if (left < this.size && this.heap[left] < this.heap[min]) {
                min = left;
            }
            // Check if right child exists and is smaller
            if (right < this.size && this.heap[right] < this.heap[min]) {
                min = right;
            }
            // If no swap needed, heap property is maintained
            if(min == index) return;
            // Swap with smaller child
            swap(index, min);
            // Continue heapifying down the tree
            downHeapify(min);
        }

        /**
         * Peek: Returns the minimum element without removing it
         * 
         * Intuition:
         * - Returns root element (index 0) which is minimum in min-heap
         * - Returns -1 if heap is empty
         * 
         * Explanation:
         * - Step 1: Check if heap is empty
         * - Step 2: Return -1 if empty
         * - Step 3: Return root element (minimum)
         * 
         * Edge Cases:
         * - Empty heap: Returns -1
         * - Single element: Returns that element
         * 
         * Connection to Solution:
         * - Provides access to minimum element without modifying heap
         * - Used for checking next element to be removed
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return    Minimum element or -1 if empty
         */
        public int peek() {
            // Return -1 if heap is empty
            if (this.size == 0)
                return -1;
            // Return root element (minimum in min-heap)
            return this.heap[0];
        }

        /**
         * Add: Adds a new element to the priority queue
         * 
         * Intuition:
         * - Adds element at the end of heap
         * - Maintains heap property by up-heapifying
         * - Does nothing if heap is full
         * 
         * Explanation:
         * - Step 1: Check if heap is full
         * - Step 2: Add element at next available position
         * - Step 3: Increment size counter
         * - Step 4: Maintain heap property by up-heapifying
         * 
         * Edge Cases:
         * - Full heap: Operation fails silently
         * - First element: Becomes root
         * 
         * Connection to Solution:
         * - Primary method for adding new elements
         * - Ensures heap property is maintained after insertion
         * 
         * Time Complexity: O(log n) where n is heap size
         * Space Complexity: O(log n) due to upHeapify recursion
         * 
         * @param value    Element to be added
         */
        public void add(int value) {
            // Check if heap is full
            if (this.size == this.heap.length)
                return;
            // Add new element at the end
            this.heap[this.size++] = value;
            // Maintain heap property by bubbling up
            upHeapify(this.size - 1);
        }

        /**
         * Remove: Removes and returns the minimum element
         * 
         * Intuition:
         * - Swaps root with last element
         * - Removes last element
         * - Maintains heap property by down-heapifying
         * 
         * Explanation:
         * - Step 1: Check if heap is empty
         * - Step 2: Swap root with last element
         * - Step 3: Store and remove last element
         * - Step 4: Mark removed position as invalid
         * - Step 5: Decrease size counter
         * - Step 6: Maintain heap property by down-heapifying
         * 
         * Edge Cases:
         * - Empty heap: Returns -1
         * - Single element: Returns that element
         * - Last element: No down-heapify needed
         * 
         * Connection to Solution:
         * - Primary method for removing minimum element
         * - Ensures heap property is maintained after removal
         * 
         * Time Complexity: O(log n) where n is heap size
         * Space Complexity: O(log n) due to downHeapify recursion
         * 
         * @return    Removed minimum element or -1 if empty
         */
        public int remove() {
            // Return -1 if heap is empty
            if (this.size == 0)
                return -1;
            // Swap root with last element
            swap(0, size - 1);
            // Store value to return
            int value = this.heap[this.size - 1];
            // Mark last position as invalid
            this.heap[this.size - 1] = Integer.MIN_VALUE;
            // Decrease heap size
            this.size--;
            // Maintain heap property by bubbling down
            downHeapify(0);
            return value;
        }

        /**
         * Swap: Swaps two elements in the heap
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
         * - Invalid indices: ArrayIndexOutOfBoundsException
         * 
         * Connection to Solution:
         * - Used by both upHeapify and downHeapify
         * - Essential for maintaining heap property
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param l    First index
         * @param r    Second index
         */
        public void swap(int l, int r) {
            // Store first element
            int temp = this.heap[l];
            // Replace first element with second
            this.heap[l] = this.heap[r];
            // Replace second element with stored first
            this.heap[r] = temp;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic operations
        System.out.println("\nTest Case 1: Basic Operations");
        int[] arr1 = {3, 1, 4, 2, 5};
        PriorityQueue pq1 = new PriorityQueue(5, arr1);
        System.out.println("Initial heap: " + Arrays.toString(pq1.heap) + 
                         " (Expected: [1, 2, 4, 3, 5])");
        System.out.println("Peek: " + pq1.peek() + " (Expected: 1)");
        System.out.println("Remove: " + pq1.remove() + " (Expected: 1)");
        System.out.println("After removal: " + Arrays.toString(pq1.heap) + 
                         " (Expected: [2, 3, 4, 5, MIN_VALUE])");

        // Test Case 2: Edge cases
        System.out.println("\nTest Case 2: Edge Cases");
        int[] arr2 = {1};
        PriorityQueue pq2 = new PriorityQueue(1, arr2);
        System.out.println("Single element heap: " + Arrays.toString(pq2.heap) + 
                         " (Expected: [1])");
        System.out.println("Remove from single element: " + pq2.remove() + 
                         " (Expected: 1)");
        System.out.println("Peek empty heap: " + pq2.peek() + 
                         " (Expected: -1)");

        // Test Case 3: Full capacity
        System.out.println("\nTest Case 3: Full Capacity");
        int[] arr3 = {1, 2, 3};
        PriorityQueue pq3 = new PriorityQueue(3, arr3);
        System.out.println("Initial heap: " + Arrays.toString(pq3.heap) + 
                         " (Expected: [1, 2, 3])");
        pq3.add(4); // Should not add as heap is full
        System.out.println("After attempting to add to full heap: " + 
                         Arrays.toString(pq3.heap) + " (Expected: [1, 2, 3])");

        // Test Case 4: Multiple operations
        System.out.println("\nTest Case 4: Multiple Operations");
        int[] arr4 = {5, 3, 1, 4, 2};
        PriorityQueue pq4 = new PriorityQueue(10, arr4);
        System.out.println("Initial heap: " + Arrays.toString(pq4.heap) + 
                         " (Expected: [1, 2, 5, 4, 3])");
        pq4.add(0);
        System.out.println("After adding 0: " + Arrays.toString(pq4.heap) + 
                         " (Expected: [0, 2, 1, 4, 3, 5])");
        System.out.println("Remove: " + pq4.remove() + " (Expected: 0)");
        System.out.println("After removal: " + Arrays.toString(pq4.heap) + 
                         " (Expected: [1, 2, 5, 4, 3, MIN_VALUE])");
        System.out.println("Peek: " + pq4.peek() + " (Expected: 1)");

        // Test Case 5: Already sorted array
        System.out.println("\nTest Case 5: Already Sorted Array");
        int[] arr5 = {1, 2, 3, 4, 5};
        PriorityQueue pq5 = new PriorityQueue(5, arr5);
        System.out.println("Sorted array heap: " + Arrays.toString(pq5.heap) + 
                         " (Expected: [1, 2, 3, 4, 5])");
        System.out.println("Remove all elements (Expected: 1 2 3 4 5):");
        while (pq5.size > 0) {
            System.out.print(pq5.remove() + " ");
        }
        System.out.println();
    }
}