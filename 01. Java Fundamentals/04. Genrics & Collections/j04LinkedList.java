/*
 * Topic: LinkedList Implementation in Java
 * 
 * Key Characteristics:
 * - Doubly-linked list implementation
 * - No random access
 * - Good for: frequent insertion/deletion
 * - Bad for: random access and searching
 * - Implements both List and Deque interfaces
 * - Can be used as a List, Stack, or Queue
 */

import java.util.*;

public class j04LinkedList {
    public static void main(String[] args) {
        // Different ways to initialize LinkedList
        System.out.println("=== Initialization Examples ===");
        LinkedList<String> list1 = new LinkedList<>();                    // Empty list
        list1.add("Empty List Demo");
        LinkedList<String> list2 = new LinkedList<>(Arrays.asList("A", "B")); // From collection
        System.out.println("List1 (empty init): " + list1);
        System.out.println("List2 (from collection): " + list2);

        // 1. Basic List Operations
        System.out.println("=== Basic List Operations ===");
        LinkedList<String> list = new LinkedList<>();
        
        // Adding elements - O(1) at ends, O(n) at middle
        list.add("First");                          // Adds at end
        list.add(0, "Start");                       // Adds at index
        list.addAll(Arrays.asList("A", "B", "C"));  // Adds collection
        System.out.println("After adding: " + list);

        // Accessing elements - O(n)
        System.out.println("Element at index 1: " + list.get(1));     // Get element at index
        list.set(1, "Updated");                     // Set element at index
        System.out.println("After updating: " + list);

        // Removing elements
        list.remove("A");                           // Remove by object - O(n)
        list.remove(0);                             // Remove by index - O(n)
        list.removeIf(s -> s.startsWith("B"));      // Remove conditionally
        System.out.println("After removing: " + list);

        // 2. Deque Operations (Double-ended queue)
        System.out.println("\n=== Deque Operations ===");
        
        // Adding elements at ends - O(1)
        list.addFirst("First");                     // Add at beginning
        list.addLast("Last");                       // Add at end
        list.push("Pushed");                        // Same as addFirst
        list.offer("Offered");                      // Same as addLast
        System.out.println("After deque additions: " + list);

        // Retrieving elements from ends - O(1)
        System.out.println("First element: " + list.getFirst());
        System.out.println("Last element: " + list.getLast());
        System.out.println("Peek first: " + list.peek());        // Same as getFirst
        System.out.println("Peek last: " + list.peekLast());

        // Removing elements from ends - O(1)
        System.out.println("Removed first: " + list.removeFirst());
        System.out.println("Removed last: " + list.removeLast());
        System.out.println("Polled: " + list.poll());           // Same as removeFirst
        System.out.println("After deque removals: " + list);

        // 3. Search Operations - O(n)
        System.out.println("\n=== Search Operations ===");
        list.addAll(Arrays.asList("X", "Y", "Z", "X"));
        System.out.println("Contains 'X': " + list.contains("X"));
        System.out.println("First index of 'X': " + list.indexOf("X"));
        System.out.println("Last index of 'X': " + list.lastIndexOf("X"));

        // 4. Stack Operations
        System.out.println("\n=== Stack Operations ===");
        LinkedList<String> stack = new LinkedList<>();
        stack.push("First");                        // Add to front
        stack.push("Second");
        stack.push("Third");
        System.out.println("Stack: " + stack);
        System.out.println("Popped: " + stack.pop()); // Remove from front
        System.out.println("After pop: " + stack);

        // 5. Queue Operations
        System.out.println("\n=== Queue Operations ===");
        LinkedList<String> queue = new LinkedList<>();
        queue.offer("First");                       // Add to end
        queue.offer("Second");
        queue.offer("Third");
        System.out.println("Queue: " + queue);
        System.out.println("Polled: " + queue.poll()); // Remove from front
        System.out.println("After poll: " + queue);

        // 6. Iteration Methods
        System.out.println("\n=== Iteration Methods ===");
        
        // Forward iteration
        System.out.println("Forward iteration:");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        // Backward iteration
        System.out.println("Backward iteration:");
        Iterator<String> descendingIterator = list.descendingIterator();
        while (descendingIterator.hasNext()) {
            System.out.print(descendingIterator.next() + " ");
        }
        System.out.println();

        // 7. Bulk Operations
        System.out.println("\n=== Bulk Operations ===");
        LinkedList<String> list3 = new LinkedList<>(Arrays.asList("A", "B", "C"));
        list.addAll(list3);                         // Add all
        System.out.println("After addAll: " + list);
        list.removeAll(list3);                      // Remove all
        System.out.println("After removeAll: " + list);

        // 8. Clear and Clone
        System.out.println("\n=== Clear and Clone ===");
        @SuppressWarnings("unchecked")  // Suppress the type safety warning since we know it's safe
        LinkedList<String> clonedList = (LinkedList<String>) list.clone();
        System.out.println("Cloned list: " + clonedList);
        list.clear();                               // Removes all elements
        System.out.println("After clear: " + list);
    }
} 