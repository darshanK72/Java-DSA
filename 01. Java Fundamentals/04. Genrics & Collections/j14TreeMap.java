/*
 * Topic: TreeMap Implementation in Java
 * 
 * Key Characteristics:
 * - Implements NavigableMap interface
 * - Red-Black Tree implementation
 * - Keys stored in sorted order
 * - No null keys allowed (null values allowed)
 * - Not synchronized
 * - O(log n) for most operations
 * - Can use custom Comparator
 * - Suitable for sorted key-value pairs
 */

import java.util.*;

public class j14TreeMap {
    public static void main(String[] args) {
        // Different ways to initialize TreeMap
        System.out.println("=== Initialization Examples ===");
        TreeMap<String, Integer> map1 = new TreeMap<>();                    // Natural ordering
        map1.put("One", 1);
        TreeMap<String, Integer> map2 = new TreeMap<>(Comparator.reverseOrder()); // Custom comparator
        map2.put("Two", 2);
        TreeMap<String, Integer> map3 = new TreeMap<>(map1);              // From another map
        
        System.out.println("Map1 (natural order): " + map1);
        System.out.println("Map2 (reverse order): " + map2);
        System.out.println("Map3 (from map): " + map3);

        // 1. Basic Operations with Natural Ordering
        System.out.println("\n=== Basic Operations ===");
        TreeMap<Integer, String> numbers = new TreeMap<>();
        
        // Adding entries
        numbers.put(5, "Five");
        numbers.put(2, "Two");
        numbers.put(8, "Eight");
        numbers.put(1, "One");
        numbers.put(9, "Nine");
        System.out.println("Numbers in sorted order: " + numbers);
        
        // Getting values
        System.out.println("Value for key 5: " + numbers.get(5));
        System.out.println("Default value: " + numbers.getOrDefault(10, "Not Found"));

        // 2. Navigation Operations
        System.out.println("\n=== Navigation Operations ===");
        System.out.println("First key: " + numbers.firstKey());
        System.out.println("Last key: " + numbers.lastKey());
        System.out.println("Lower key than 5: " + numbers.lowerKey(5));   // Strictly less
        System.out.println("Higher key than 5: " + numbers.higherKey(5)); // Strictly more
        System.out.println("Floor key of 4: " + numbers.floorKey(4));     // Less than or equal
        System.out.println("Ceiling key of 4: " + numbers.ceilingKey(4)); // Greater than or equal

        // 3. Range View Operations
        System.out.println("\n=== Range View Operations ===");
        System.out.println("SubMap (2 to 8): " + numbers.subMap(2, true, 8, true));
        System.out.println("HeadMap (< 5): " + numbers.headMap(5));
        System.out.println("TailMap (>= 5): " + numbers.tailMap(5));
        
        // Descending order
        System.out.println("Descending Map: " + numbers.descendingMap());

        // 4. Entry Navigation
        System.out.println("\n=== Entry Navigation ===");
        Map.Entry<Integer, String> firstEntry = numbers.firstEntry();
        Map.Entry<Integer, String> lastEntry = numbers.lastEntry();
        System.out.println("First entry: " + firstEntry.getKey() + " -> " + firstEntry.getValue());
        System.out.println("Last entry: " + lastEntry.getKey() + " -> " + lastEntry.getValue());
        
        // Poll operations
        System.out.println("Poll first entry: " + numbers.pollFirstEntry());
        System.out.println("Poll last entry: " + numbers.pollLastEntry());
        System.out.println("After polling: " + numbers);

        // 5. Custom Comparator Example
        System.out.println("\n=== Custom Comparator Example ===");
        TreeMap<String, Integer> lengthMap = new TreeMap<>((s1, s2) -> {
            if (s1.length() != s2.length()) {
                return s1.length() - s2.length();
            }
            return s1.compareTo(s2);
        });
        
        lengthMap.put("Dog", 1);
        lengthMap.put("Cat", 2);
        lengthMap.put("Elephant", 3);
        lengthMap.put("Bird", 4);
        System.out.println("Sorted by length: " + lengthMap);

        // 6. Bulk Operations
        System.out.println("\n=== Bulk Operations ===");
        TreeMap<Integer, String> bulkMap = new TreeMap<>();
        bulkMap.putAll(Map.of(1, "One", 2, "Two", 3, "Three"));
        System.out.println("After putAll: " + bulkMap);

        // 7. Custom Objects with Natural Ordering
        System.out.println("\n=== Custom Objects Example ===");
        TreeMap<Person, String> personMap = new TreeMap<>();
        personMap.put(new Person("John", 25), "Employee");
        personMap.put(new Person("Alice", 30), "Manager");
        personMap.put(new Person("Bob", 20), "Intern");
        System.out.println("Persons sorted by age: " + personMap);

        // 8. Performance Testing
        System.out.println("\n=== Performance Testing ===");
        TreeMap<Integer, String> largeMap = new TreeMap<>();
        
        // Adding entries
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            largeMap.put(i, "Value" + i);
        }
        long endTime = System.nanoTime();
        System.out.println("Time to add 100,000 entries: " + 
            (endTime - startTime) / 1_000_000 + " ms");
        
        // Searching entries
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            largeMap.get(i);
        }
        endTime = System.nanoTime();
        System.out.println("Time to search 1,000 entries: " + 
            (endTime - startTime) / 1_000_000 + " ms");

        // 9. Error Handling
        System.out.println("\n=== Error Handling ===");
        TreeMap<String, String> errorMap = new TreeMap<>();
        
        try {
            errorMap.put(null, "Value"); // Will throw NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Cannot put null key in TreeMap");
        }
    }
    
    // Inner class implementing Comparable
    static class Person implements Comparable<Person> {
        String name;
        int age;
        
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        @Override
        public int compareTo(Person other) {
            return Integer.compare(this.age, other.age);
        }
        
        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }
} 