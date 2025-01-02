/*
 * Topic: PriorityQueue Implementation in Java
 * 
 * Key Characteristics:
 * - Implements Queue interface
 * - Heap data structure (min-heap by default)
 * - Elements processed based on priority
 * - No null elements allowed
 * - Not thread-safe
 * - O(log n) for insert and delete
 * - O(1) for peek/element
 * - Default initial capacity: 11
 * - Can use custom Comparator
 */

import java.util.*;

public class j10PriorityQueue {
    public static void main(String[] args) {
        // Different ways to initialize PriorityQueue
        System.out.println("=== Initialization Examples ===");
        PriorityQueue<Integer> queue1 = new PriorityQueue<>();           // Natural ordering
        queue1.add(3);
        PriorityQueue<Integer> queue2 = new PriorityQueue<>(20);        // Custom initial capacity
        queue2.add(2);
        PriorityQueue<Integer> queue3 = new PriorityQueue<>(Comparator.reverseOrder()); // Custom comparator
        queue3.add(1);
        PriorityQueue<Integer> queue4 = new PriorityQueue<>(Arrays.asList(3, 1, 4, 1, 5)); // From collection
        
        System.out.println("Queue1 (natural order): " + queue1);
        System.out.println("Queue2 (with capacity): " + queue2);
        System.out.println("Queue3 (reverse order): " + queue3);
        System.out.println("Queue4 (from collection): " + queue4);

        // 1. Basic Queue Operations
        System.out.println("\n=== Basic Queue Operations ===");
        PriorityQueue<Integer> numbers = new PriorityQueue<>();
        
        // Adding elements
        numbers.offer(5);  // Same as add()
        numbers.offer(2);
        numbers.offer(8);
        numbers.offer(1);
        numbers.offer(9);
        System.out.println("After adding elements: " + numbers);
        
        // Peek operations
        System.out.println("Peek (doesn't remove): " + numbers.peek());
        System.out.println("After peek: " + numbers);
        
        // Poll operations
        System.out.println("Poll (removes): " + numbers.poll());
        System.out.println("After poll: " + numbers);

        // 2. Priority Based on Custom Comparator
        System.out.println("\n=== Custom Priority Example ===");
        PriorityQueue<String> stringQueue = new PriorityQueue<>((s1, s2) -> s2.length() - s1.length());
        
        stringQueue.offer("Dog");
        stringQueue.offer("Cat");
        stringQueue.offer("Elephant");
        stringQueue.offer("Bird");
        
        System.out.println("Elements by length (descending):");
        while (!stringQueue.isEmpty()) {
            System.out.println(stringQueue.poll());
        }

        // 3. Custom Objects with Natural Ordering
        System.out.println("\n=== Custom Objects Example ===");
        PriorityQueue<Task> taskQueue = new PriorityQueue<>();
        
        taskQueue.offer(new Task("Write report", 3));
        taskQueue.offer(new Task("Fix bug", 1));
        taskQueue.offer(new Task("Review code", 2));
        
        System.out.println("Tasks by priority:");
        while (!taskQueue.isEmpty()) {
            System.out.println(taskQueue.poll());
        }

        // 4. Bulk Operations
        System.out.println("\n=== Bulk Operations ===");
        PriorityQueue<Integer> bulkQueue = new PriorityQueue<>();
        bulkQueue.addAll(Arrays.asList(5, 2, 8, 1, 9));
        System.out.println("After bulk add: " + bulkQueue);
        
        // Remove specific elements
        bulkQueue.removeIf(n -> n % 2 == 0);  // Remove even numbers
        System.out.println("After removing even numbers: " + bulkQueue);

        // 5. Error Handling
        System.out.println("\n=== Error Handling ===");
        PriorityQueue<Integer> emptyQueue = new PriorityQueue<>();
        
        try {
            emptyQueue.element(); // Throws exception if empty
        } catch (NoSuchElementException e) {
            System.out.println("element() on empty queue throws exception");
        }
        
        System.out.println("peek() on empty queue returns: " + emptyQueue.peek());

        // 6. Performance Testing
        System.out.println("\n=== Performance Testing ===");
        PriorityQueue<Integer> largeQueue = new PriorityQueue<>();
        
        // Adding elements
        long startTime = System.nanoTime();
        for (int i = 100000; i >= 0; i--) {  // Add in reverse order
            largeQueue.offer(i);
        }
        long endTime = System.nanoTime();
        System.out.println("Time to add 100,001 elements: " + 
            (endTime - startTime) / 1_000_000 + " ms");
        
        // Removing elements
        startTime = System.nanoTime();
        while (!largeQueue.isEmpty()) {
            largeQueue.poll();
        }
        endTime = System.nanoTime();
        System.out.println("Time to remove 100,001 elements: " + 
            (endTime - startTime) / 1_000_000 + " ms");
    }
    
    // Inner class implementing Comparable
    static class Task implements Comparable<Task> {
        String description;
        int priority;  // Lower number = higher priority
        
        Task(String description, int priority) {
            this.description = description;
            this.priority = priority;
        }
        
        @Override
        public int compareTo(Task other) {
            return Integer.compare(this.priority, other.priority);
        }
        
        @Override
        public String toString() {
            return String.format("Task{priority=%d, description='%s'}", priority, description);
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Task task = (Task) o;
            return priority == task.priority && Objects.equals(description, task.description);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(description, priority);
        }
    }
} 