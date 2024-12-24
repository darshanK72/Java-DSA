/*
 * Topic: ArrayDeque Implementation in Java
 * 
 * Key Characteristics:
 * - Implements Deque interface
 * - Resizable array implementation
 * - No capacity restrictions
 * - Not thread-safe
 * - Null elements not allowed
 * - O(1) for operations at both ends
 * - More efficient than Stack and LinkedList
 * - Can be used as Stack, Queue, or Deque
 * - Default initial capacity: 16
 */

import java.util.*;

public class j11ArrayDeque {
    public static void main(String[] args) {
        // Different ways to initialize ArrayDeque
        System.out.println("=== Initialization Examples ===");
        ArrayDeque<String> deque1 = new ArrayDeque<>();               // Default capacity (16)
        deque1.add("Default");
        ArrayDeque<String> deque2 = new ArrayDeque<>(20);            // Custom initial capacity
        deque2.add("Custom Capacity");
        ArrayDeque<String> deque3 = new ArrayDeque<>(Arrays.asList("A", "B", "C")); // From collection
        
        System.out.println("Deque1 (default): " + deque1);
        System.out.println("Deque2 (sized): " + deque2);
        System.out.println("Deque3 (from collection): " + deque3);

        // 1. Using as a Stack (LIFO)
        System.out.println("\n=== Stack Operations ===");
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        // Push operations
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("After pushing 1,2,3: " + stack);
        
        // Pop operations
        System.out.println("Pop: " + stack.pop());
        System.out.println("After pop: " + stack);
        
        // Peek operation
        System.out.println("Peek: " + stack.peek());
        System.out.println("After peek: " + stack);

        // 2. Using as a Queue (FIFO)
        System.out.println("\n=== Queue Operations ===");
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        
        // Offer operations (add to end)
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println("After offering 1,2,3: " + queue);
        
        // Poll operations (remove from front)
        System.out.println("Poll: " + queue.poll());
        System.out.println("After poll: " + queue);
        
        // Peek operation
        System.out.println("Peek: " + queue.peek());
        System.out.println("After peek: " + queue);

        // 3. Deque Operations (Double-ended queue)
        System.out.println("\n=== Deque Operations ===");
        ArrayDeque<String> deque = new ArrayDeque<>();
        
        // Adding elements
        deque.addFirst("First");
        deque.addLast("Last");
        System.out.println("After adding First and Last: " + deque);
        
        // Adding more elements
        deque.offerFirst("New First");
        deque.offerLast("New Last");
        System.out.println("After offering at both ends: " + deque);
        
        // Removing elements
        System.out.println("Remove first: " + deque.removeFirst());
        System.out.println("Remove last: " + deque.removeLast());
        System.out.println("After removing both ends: " + deque);
        
        // Examining elements
        System.out.println("Get first: " + deque.getFirst());
        System.out.println("Get last: " + deque.getLast());

        // 4. Bulk Operations
        System.out.println("\n=== Bulk Operations ===");
        ArrayDeque<String> bulkDeque = new ArrayDeque<>();
        bulkDeque.addAll(Arrays.asList("One", "Two", "Three"));
        System.out.println("After bulk add: " + bulkDeque);
        
        // Clear operation
        bulkDeque.clear();
        System.out.println("After clear: " + bulkDeque);

        // 5. Iteration Methods
        System.out.println("\n=== Iteration Methods ===");
        ArrayDeque<String> iterDeque = new ArrayDeque<>(Arrays.asList("A", "B", "C", "D"));
        
        // Forward iteration
        System.out.println("Forward iteration:");
        Iterator<String> iterator = iterDeque.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        
        // Reverse iteration
        System.out.println("Reverse iteration:");
        Iterator<String> descendingIterator = iterDeque.descendingIterator();
        while (descendingIterator.hasNext()) {
            System.out.print(descendingIterator.next() + " ");
        }
        System.out.println();

        // 6. Performance Testing
        System.out.println("\n=== Performance Testing ===");
        ArrayDeque<Integer> largeDeque = new ArrayDeque<>();
        
        // Adding elements at front
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            largeDeque.addFirst(i);
        }
        long endTime = System.nanoTime();
        System.out.println("Time to add 100,000 elements at front: " + 
            (endTime - startTime) / 1_000_000 + " ms");
        
        // Adding elements at back
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            largeDeque.addLast(i);
        }
        endTime = System.nanoTime();
        System.out.println("Time to add 100,000 elements at back: " + 
            (endTime - startTime) / 1_000_000 + " ms");
        
        // Removing elements
        startTime = System.nanoTime();
        while (!largeDeque.isEmpty()) {
            largeDeque.pollFirst();
        }
        endTime = System.nanoTime();
        System.out.println("Time to remove 200,000 elements: " + 
            (endTime - startTime) / 1_000_000 + " ms");

        // 7. Error Handling
        System.out.println("\n=== Error Handling ===");
        ArrayDeque<String> emptyDeque = new ArrayDeque<>();
        
        try {
            emptyDeque.getFirst(); // Throws exception if empty
        } catch (NoSuchElementException e) {
            System.out.println("getFirst() on empty deque throws exception");
        }
        
        System.out.println("peekFirst() on empty deque returns: " + emptyDeque.peekFirst());
    }
} 