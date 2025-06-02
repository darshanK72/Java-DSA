/**
 * Custom Implementation: Generic Priority Queue
 * 
 * Problem Statement:
 *     Implement a generic Priority Queue data structure that can work with any
 *     Comparable type. The implementation should support basic operations like
 *     insertion, deletion, and peeking while maintaining the min-heap property.
 * 
 * Input:
 *     - capacity (int): Maximum size of the priority queue
 *     - arr (T[]): Optional initial array of elements to build the heap
 * 
 * Output:
 *     - A fully functional Generic Priority Queue with min-heap property
 * 
 * Example:
 *     Input: capacity = 5, arr = [3, 1, 4, 2, 5]
 *     Output: Priority Queue with elements [1, 2, 4, 3, 5]
 * 
 *     Explanation:
 *     The elements are arranged in a min-heap structure where each parent node
 *     is smaller than its children. The root node (index 0) always contains
 *     the minimum element.
 */

import java.util.Arrays;

public class j02GenericPriorityQueue<T extends Comparable<T>> {
    
    static class PriorityQueue<T extends Comparable<T>> {
        private T[] heap;
        private int size;
        private final int capacity;

        /**
         * Constructor: Creates an empty Priority Queue
         * 
         * Intuition:
         * - Initialize empty heap with given capacity
         * - Use Comparable as base type for generic array
         * 
         * Explanation:
         * - Step 1: Set capacity and size
         * - Step 2: Create generic array using Comparable
         * - Step 3: Initialize size to 0
         * 
         * Edge Cases:
         * - Zero capacity: Creates empty queue
         * - Negative capacity: Not handled (assumed valid)
         * 
         * Connection to Solution:
         * - Sets up empty queue for dynamic element addition
         * - Ensures type safety with generic implementation
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(capacity)
         * 
         * @param capacity    Maximum size of the priority queue
         */
        @SuppressWarnings("unchecked")
        PriorityQueue(int capacity) {
            this.capacity = capacity;
            // Create generic array using Comparable as base type
            this.heap = (T[]) new Comparable[capacity];
            this.size = 0;
        }

        /**
         * Constructor: Creates Priority Queue from array
         * 
         * Intuition:
         * - Initialize heap with given array
         * - Build min-heap from the array
         * 
         * Explanation:
         * - Step 1: Set capacity and size
         * - Step 2: Create generic array
         * - Step 3: Copy elements to heap
         * - Step 4: Build min-heap
         * 
         * Edge Cases:
         * - Empty array: Creates empty queue
         * - Full capacity: No additional elements can be added
         * 
         * Connection to Solution:
         * - Sets up queue with initial elements
         * - Ensures heap property from start
         * 
         * Time Complexity: O(n) where n is array length
         * Space Complexity: O(capacity)
         * 
         * @param capacity    Maximum size of the priority queue
         * @param arr         Initial array of elements
         */
        @SuppressWarnings("unchecked")
        PriorityQueue(int capacity, T[] arr) {
            this.capacity = capacity;
            this.heap = (T[]) new Comparable[capacity];
            this.size = arr.length;
            
            // Copy elements to heap
            for (int i = 0; i < arr.length; i++) {
                this.heap[i] = arr[i];
            }
            
            // Build min-heap
            buildMinHeapFromLeafs();
        }

        /**
         * Method: Builds min-heap from leaf nodes up
         * 
         * Intuition:
         * - Start from last non-leaf node
         * - Heapify each node in reverse level order
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
         * - Called during initialization
         * - Ensures initial heap property
         * 
         * Time Complexity: O(n) where n is heap size
         * Space Complexity: O(log n) due to recursion
         */
        public void buildMinHeapFromLeafs() {
            for (int i = (size / 2) - 1; i >= 0; i--) {
                downHeapify(i);
            }
        }

        /**
         * Method: Builds min-heap from root down
         * 
         * Intuition:
         * - Start from second element
         * - Use upHeapify to maintain heap property
         * 
         * Explanation:
         * - Step 1: Start from second element
         * - Step 2: For each element, use upHeapify
         * - Step 3: Continue until all elements processed
         * 
         * Edge Cases:
         * - Single element: No heapification needed
         * - Already sorted array: Requires maximum swaps
         * 
         * Connection to Solution:
         * - Alternative approach to buildMinHeapFromLeafs
         * - More intuitive but less efficient
         * 
         * Time Complexity: O(n log n) where n is heap size
         * Space Complexity: O(log n) due to recursion
         */
        public void buildMinHeapFromRoot() {
            for (int i = 1; i < size; i++) {
                upHeapify(i);
            }
        }

        /**
         * Method: Maintains heap property by bubbling up
         * 
         * Intuition:
         * - Compare element with parent
         * - Swap if parent is larger
         * - Continue until root or correct position
         * 
         * Explanation:
         * - Step 1: Check if at root
         * - Step 2: Compare with parent
         * - Step 3: Swap if needed
         * - Step 4: Continue with parent
         * 
         * Edge Cases:
         * - Root element: Returns immediately
         * - Already in correct position: No swaps
         * 
         * Connection to Solution:
         * - Used after insertion
         * - Maintains heap property
         * 
         * Time Complexity: O(log n) where n is heap size
         * Space Complexity: O(log n) due to recursion
         * 
         * @param index    Index of element to heapify
         */
        public void upHeapify(int index) {
            if (index == 0) return;
            
            int parent = (index - 1) / 2;
            if (heap[parent].compareTo(heap[index]) > 0) {
                swap(index, parent);
                upHeapify(parent);
            }
        }

        /**
         * Method: Maintains heap property by bubbling down
         * 
         * Intuition:
         * - Compare element with children
         * - Swap with smallest child if needed
         * - Continue until leaf or correct position
         * 
         * Explanation:
         * - Step 1: Check if index valid
         * - Step 2: Find minimum among current and children
         * - Step 3: Swap if needed
         * - Step 4: Continue with child
         * 
         * Edge Cases:
         * - Leaf node: Returns immediately
         * - Only left child: Compares only with left
         * 
         * Connection to Solution:
         * - Used after removal
         * - Maintains heap property
         * 
         * Time Complexity: O(log n) where n is heap size
         * Space Complexity: O(log n) due to recursion
         * 
         * @param index    Index of element to heapify
         */
        public void downHeapify(int index) {
            if (index >= size) return;
            
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int min = index;
            
            if (left < size && heap[left].compareTo(heap[min]) < 0) {
                min = left;
            }
            
            if (right < size && heap[right].compareTo(heap[min]) < 0) {
                min = right;
            }
            
            if (min != index) {
                swap(index, min);
                downHeapify(min);
            }
        }

        /**
         * Method: Returns minimum element without removing
         * 
         * Intuition:
         * - Return root element (minimum)
         * - Return null if empty
         * 
         * Explanation:
         * - Step 1: Check if empty
         * - Step 2: Return root element
         * 
         * Edge Cases:
         * - Empty queue: Returns null
         * - Single element: Returns that element
         * 
         * Connection to Solution:
         * - Provides access to minimum element
         * - Doesn't modify heap
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return    Minimum element or null if empty
         */
        public T peek() {
            if (size == 0) return null;
            return heap[0];
        }

        /**
         * Method: Adds new element to queue
         * 
         * Intuition:
         * - Add element at end
         * - Maintain heap property
         * 
         * Explanation:
         * - Step 1: Check if full
         * - Step 2: Add element at end
         * - Step 3: Heapify up
         * - Step 4: Increment size
         * 
         * Edge Cases:
         * - Full queue: Operation fails silently
         * - First element: Becomes root
         * 
         * Connection to Solution:
         * - Primary method for adding elements
         * - Maintains heap property
         * 
         * Time Complexity: O(log n) where n is heap size
         * Space Complexity: O(log n) due to upHeapify
         * 
         * @param value    Element to be added
         */
        public void add(T value) {
            if (size == capacity) return;
            
            heap[size] = value;
            upHeapify(size);
            size++;
        }

        /**
         * Method: Removes and returns minimum element
         * 
         * Intuition:
         * - Remove root element
         * - Maintain heap property
         * 
         * Explanation:
         * - Step 1: Check if empty
         * - Step 2: Store root value
         * - Step 3: Swap with last element
         * - Step 4: Remove last element
         * - Step 5: Heapify down
         * 
         * Edge Cases:
         * - Empty queue: Returns null
         * - Single element: Returns that element
         * 
         * Connection to Solution:
         * - Primary method for removing elements
         * - Maintains heap property
         * 
         * Time Complexity: O(log n) where n is heap size
         * Space Complexity: O(log n) due to downHeapify
         * 
         * @return    Removed minimum element or null if empty
         */
        public T remove() {
            if (size == 0) return null;
            
            T value = heap[0];
            swap(0, size - 1);
            heap[size - 1] = null;
            size--;
            
            if (size > 0) {
                downHeapify(0);
            }
            
            return value;
        }

        /**
         * Method: Swaps two elements in heap
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
         * - Used by heapify operations
         * - Essential for maintaining heap property
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param i    First index
         * @param j    Second index
         */
        private void swap(int i, int j) {
            T temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        /**
         * Method: Returns current size of queue
         * 
         * Intuition:
         * - Simple getter for size
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return    Current size of queue
         */
        public int size() {
            return size;
        }

        /**
         * Method: Checks if queue is empty
         * 
         * Intuition:
         * - Simple check for empty state
         * 
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return    True if empty, false otherwise
         */
        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * Method: String representation of queue
         * 
         * Intuition:
         * - Convert heap to string
         * - Only include valid elements
         * 
         * Time Complexity: O(n) where n is size
         * Space Complexity: O(n) for string
         * 
         * @return    String representation of queue
         */
        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOf(heap, size));
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Integer Priority Queue
        System.out.println("\nTest Case 1: Integer Priority Queue");
        Integer[] intArr = {3, 1, 4, 2, 5};
        PriorityQueue<Integer> intPQ = new PriorityQueue<>(5, intArr);
        System.out.println("Initial heap: " + intPQ + 
                         " (Expected: [1, 2, 4, 3, 5])");
        System.out.println("Peek: " + intPQ.peek() + " (Expected: 1)");
        System.out.println("Remove: " + intPQ.remove() + " (Expected: 1)");
        System.out.println("After removal: " + intPQ + 
                         " (Expected: [2, 3, 4, 5])");

        // Test Case 2: String Priority Queue
        System.out.println("\nTest Case 2: String Priority Queue");
        String[] strArr = {"banana", "apple", "cherry", "date"};
        PriorityQueue<String> strPQ = new PriorityQueue<>(4, strArr);
        System.out.println("Initial heap: " + strPQ + 
                         " (Expected: [apple, banana, cherry, date])");
        System.out.println("Peek: " + strPQ.peek() + " (Expected: apple)");
        System.out.println("Remove: " + strPQ.remove() + " (Expected: apple)");
        System.out.println("After removal: " + strPQ + 
                         " (Expected: [banana, date, cherry])");

        // Test Case 3: Double Priority Queue
        System.out.println("\nTest Case 3: Double Priority Queue");
        Double[] doubleArr = {3.14, 1.41, 2.71, 1.73};
        PriorityQueue<Double> doublePQ = new PriorityQueue<>(4, doubleArr);
        System.out.println("Initial heap: " + doublePQ + 
                         " (Expected: [1.41, 1.73, 2.71, 3.14])");
        System.out.println("Peek: " + doublePQ.peek() + " (Expected: 1.41)");
        System.out.println("Remove: " + doublePQ.remove() + " (Expected: 1.41)");
        System.out.println("After removal: " + doublePQ + 
                         " (Expected: [1.73, 3.14, 2.71])");

        // Test Case 4: Empty Queue Operations
        System.out.println("\nTest Case 4: Empty Queue Operations");
        PriorityQueue<Integer> emptyPQ = new PriorityQueue<>(5);
        System.out.println("Peek empty: " + emptyPQ.peek() + " (Expected: null)");
        System.out.println("Remove empty: " + emptyPQ.remove() + " (Expected: null)");
        System.out.println("Size: " + emptyPQ.size() + " (Expected: 0)");
        System.out.println("Is empty: " + emptyPQ.isEmpty() + " (Expected: true)");

        // Test Case 5: Full Queue Operations
        System.out.println("\nTest Case 5: Full Queue Operations");
        PriorityQueue<Integer> fullPQ = new PriorityQueue<>(3, new Integer[]{1, 2, 3});
        System.out.println("Initial heap: " + fullPQ + " (Expected: [1, 2, 3])");
        fullPQ.add(4); // Should not add as queue is full
        System.out.println("After attempting to add to full queue: " + fullPQ + 
                         " (Expected: [1, 2, 3])");
    }
}
