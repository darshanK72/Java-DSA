/*
 * Topic: Vector Implementation in Java
 * 
 * Key Characteristics:
 * - Legacy synchronized List implementation
 * - Thread-safe (all methods synchronized)
 * - Dynamic array implementation
 * - Default initial capacity: 10
 * - Growth factor: 2x when full (unlike ArrayList's 1.5x)
 * - Slower than ArrayList due to synchronization
 * - Legacy class, ArrayList preferred unless synchronization needed
 */

import java.util.*;

public class j05Vector {
    public static void main(String[] args) {
        // Different ways to initialize Vector
        System.out.println("=== Initialization Examples ===");
        Vector<String> vector1 = new Vector<>();                    // Default capacity (10)
        vector1.add("Default Vector");
        Vector<String> vector2 = new Vector<>(20);                 // Custom initial capacity
        vector2.add("Sized Vector");
        Vector<String> vector3 = new Vector<>(Arrays.asList("A", "B", "C")); // From collection
        System.out.println("Vector1 (default): " + vector1);
        System.out.println("Vector2 (sized): " + vector2);
        System.out.println("Vector3 (from collection): " + vector3);

        // 1. Basic Operations
        System.out.println("\n=== Basic Operations ===");
        Vector<String> vector = new Vector<>();
        
        // Adding elements
        vector.add("First");                        // Adds at end
        vector.add(0, "Start");                     // Adds at index
        vector.addAll(Arrays.asList("A", "B", "C")); // Adds collection
        System.out.println("After adding: " + vector);

        // Accessing elements
        System.out.println("Element at index 1: " + vector.get(1));
        vector.set(1, "Updated");                   // Set element at index
        System.out.println("After updating: " + vector);

        // Vector-specific methods
        System.out.println("First element: " + vector.firstElement());
        System.out.println("Last element: " + vector.lastElement());
        System.out.println("Capacity: " + vector.capacity());
        
        // 2. Removing Elements
        System.out.println("\n=== Removal Operations ===");
        vector.remove("A");                         // Remove by object
        vector.remove(0);                           // Remove by index
        vector.removeIf(s -> s.startsWith("B"));    // Remove conditionally
        System.out.println("After removing: " + vector);

        // 3. Search Operations
        System.out.println("\n=== Search Operations ===");
        vector.addAll(Arrays.asList("X", "Y", "Z", "X"));
        System.out.println("Contains 'X': " + vector.contains("X"));
        System.out.println("Index of 'X': " + vector.indexOf("X"));
        System.out.println("Last index of 'X': " + vector.lastIndexOf("X"));

        // 4. Capacity Management
        System.out.println("\n=== Capacity Management ===");
        System.out.println("Current size: " + vector.size());
        System.out.println("Current capacity: " + vector.capacity());
        vector.trimToSize();                        // Trim capacity to size
        System.out.println("Capacity after trim: " + vector.capacity());
        vector.ensureCapacity(20);                  // Ensure minimum capacity
        System.out.println("Capacity after ensure: " + vector.capacity());

        // 5. Enumeration (Legacy)
        System.out.println("\n=== Enumeration (Legacy) ===");
        Enumeration<String> enumeration = vector.elements();
        System.out.print("Elements via enumeration: ");
        while (enumeration.hasMoreElements()) {
            System.out.print(enumeration.nextElement() + " ");
        }
        System.out.println();

        // 6. Bulk Operations
        System.out.println("\n=== Bulk Operations ===");
        Vector<String> vector4 = new Vector<>(Arrays.asList("P", "Q", "R"));
        vector.addAll(vector4);                     // Add all
        System.out.println("After addAll: " + vector);
        vector.removeAll(vector4);                  // Remove all
        System.out.println("After removeAll: " + vector);

        // 7. Clear and Clone
        System.out.println("\n=== Clear and Clone ===");
        @SuppressWarnings("unchecked")
        Vector<String> clonedVector = (Vector<String>) vector.clone();
        System.out.println("Cloned vector: " + clonedVector);
        vector.clear();                             // Removes all elements
        System.out.println("After clear: " + vector);

        // 8. Synchronized Nature Demo
        System.out.println("\n=== Synchronization Demo ===");
        Vector<Integer> syncVector = new Vector<>();
        // Example of thread-safe operation (no explicit synchronization needed)
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                syncVector.add(i);
                System.out.println("Added: " + i);
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        });
        
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                if (!syncVector.isEmpty()) {
                    System.out.println("Removed: " + syncVector.remove(0));
                }
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final vector state: " + syncVector);
    }
} 