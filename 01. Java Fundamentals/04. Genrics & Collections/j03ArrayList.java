/*
 * Topic: ArrayList Implementation in Java
 * 
 * Key Characteristics:
 * - Dynamic resizing array implementation
 * - Random access - O(1)
 * - Good for: frequent access and iteration
 * - Bad for: frequent insertion/deletion in middle
 * - Default initial capacity: 10
 * - Growth factor: 1.5x when full
 */

import java.util.*;

public class j03ArrayList {
    public static void main(String[] args) {
        // Different ways to initialize ArrayList (examples)
        System.out.println("=== Initialization Examples ===");
        ArrayList<String> list1 = new ArrayList<>();                   // Default capacity (10)
        list1.add("Default");
        ArrayList<String> list2 = new ArrayList<>(20);                // Specified capacity
        list2.add("Custom Capacity");
        ArrayList<String> list3 = new ArrayList<>(Arrays.asList("A", "B", "C")); // From collection
        System.out.println("List1 (default): " + list1);
        System.out.println("List2 (sized): " + list2);
        System.out.println("List3 (from array): " + list3);

        // 1. Basic Operations
        System.out.println("=== Basic Operations ===");
        ArrayList<String> list = new ArrayList<>();
        
        // Adding elements - O(1) amortized
        list.add("First");                          // Adds at end
        list.add(0, "Start");                       // Adds at index - O(n)
        list.addAll(Arrays.asList("A", "B", "C"));  // Adds collection - O(n)
        System.out.println("After adding: " + list);

        // Accessing elements - O(1)
        System.out.println("Element at index 1: " + list.get(1));     // Get element at index
        list.set(1, "Updated");                     // Set element at index
        System.out.println("After updating: " + list);

        // Removing elements
        list.remove("A");                           // Remove by object - O(n)
        list.remove(0);                             // Remove by index - O(n)
        list.removeIf(s -> s.startsWith("B"));      // Remove conditionally - O(n)
        System.out.println("After removing: " + list);

        // 2. Search Operations
        System.out.println("\n=== Search Operations ===");
        System.out.println("Contains 'C': " + list.contains("C"));            // O(n)
        System.out.println("Index of 'C': " + list.indexOf("C"));            // O(n)
        System.out.println("Last index of 'C': " + list.lastIndexOf("C"));   // O(n)

        // 3. Bulk Operations
        System.out.println("\n=== Bulk Operations ===");
        ArrayList<String> list4 = new ArrayList<>(Arrays.asList("X", "Y", "Z"));
        list.addAll(list4);                         // Add all - O(n)
        System.out.println("After addAll: " + list);
        
        list.removeAll(list4);                      // Remove all - O(n)
        System.out.println("After removeAll: " + list);

        // 4. Range Operations
        System.out.println("\n=== Range Operations ===");
        list.addAll(Arrays.asList("D", "E", "F", "G", "H"));
        List<String> subList = list.subList(1, 4);  // View of original list
        System.out.println("SubList (1-4): " + subList);

        // 5. Array Operations
        System.out.println("\n=== Array Operations ===");
        String[] array = list.toArray(new String[0]);
        System.out.println("Converted to array: " + Arrays.toString(array));

        // 6. Size and Capacity
        System.out.println("\n=== Size Operations ===");
        System.out.println("Size: " + list.size());
        System.out.println("Is empty: " + list.isEmpty());
        list.trimToSize();                          // Trims capacity to size

        // 7. Clear and Clone
        System.out.println("\n=== Clear and Clone ===");
        @SuppressWarnings("unchecked")  // Suppress the type safety warning since we know it's safe
        ArrayList<String> clonedList = (ArrayList<String>) list.clone();
        System.out.println("Cloned list: " + clonedList);
        list.clear();                               // Removes all elements
        System.out.println("After clear: " + list);

        // 8. Iteration Methods
        System.out.println("\n=== Iteration Methods ===");
        clonedList.forEach(System.out::print);      // Method reference
        System.out.println();

        // Using iterator
        Iterator<String> iterator = clonedList.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if (item.equals("E")) {
                iterator.remove();                   // Safe way to remove during iteration
            }
        }
        System.out.println("After iterator removal: " + clonedList);

        // 9. Sorting
        System.out.println("\n=== Sorting ===");
        Collections.sort(clonedList);               // Natural ordering
        System.out.println("Natural sort: " + clonedList);
        Collections.sort(clonedList, Collections.reverseOrder()); // Custom ordering
        System.out.println("Reverse sort: " + clonedList);
    }
} 