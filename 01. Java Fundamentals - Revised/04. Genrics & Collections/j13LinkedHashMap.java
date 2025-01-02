/*
 * Topic: LinkedHashMap Implementation in Java
 * 
 * Key Characteristics:
 * - Extends HashMap
 * - Maintains insertion order (by default)
 * - Can maintain access order (LRU cache)
 * - Allows one null key and multiple null values
 * - Not synchronized
 * - O(1) for basic operations
 * - Uses doubly-linked list + hash table
 * - Slightly slower than HashMap
 * - Uses more memory than HashMap
 */

import java.util.*;

public class j13LinkedHashMap {
    public static void main(String[] args) {
        // Different ways to initialize LinkedHashMap
        System.out.println("=== Initialization Examples ===");
        LinkedHashMap<String, Integer> map1 = new LinkedHashMap<>();           // Default - insertion order
        map1.put("One", 1);
        LinkedHashMap<String, Integer> map2 = new LinkedHashMap<>(20);        // Custom initial capacity
        map2.put("Two", 2);
        LinkedHashMap<String, Integer> map3 = new LinkedHashMap<>(20, 0.8f);  // Custom load factor
        map3.put("Three", 3);
        LinkedHashMap<String, Integer> map4 = new LinkedHashMap<>(map1);      // From another map
        
        System.out.println("Map1 (default): " + map1);
        System.out.println("Map2 (sized): " + map2);
        System.out.println("Map3 (with load factor): " + map3);
        System.out.println("Map4 (from map): " + map4);

        // 1. Demonstrating Insertion Order
        System.out.println("\n=== Insertion Order Demo ===");
        LinkedHashMap<String, String> insertionMap = new LinkedHashMap<>();
        insertionMap.put("First", "1st");
        insertionMap.put("Second", "2nd");
        insertionMap.put("Third", "3rd");
        insertionMap.put("Fourth", "4th");
        System.out.println("Original insertion order: " + insertionMap);
        
        // Update existing entry
        insertionMap.put("Second", "2nd-updated");
        System.out.println("After update (order preserved): " + insertionMap);
        
        // Remove and add
        insertionMap.remove("Third");
        insertionMap.put("Third", "3rd-new");
        System.out.println("After remove and re-add: " + insertionMap);

        // 2. Access Order (LRU Cache Implementation)
        System.out.println("\n=== Access Order Demo (LRU Cache) ===");
        LinkedHashMap<String, String> accessMap = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > 3; // Keep only 3 entries (LRU cache)
            }
        };
        
        accessMap.put("A", "1");
        accessMap.put("B", "2");
        accessMap.put("C", "3");
        System.out.println("Initial state: " + accessMap);
        
        // Access entries
        accessMap.get("A");  // This moves A to the end
        System.out.println("After accessing A: " + accessMap);
        
        // Add new entry (triggers removal of eldest)
        accessMap.put("D", "4");
        System.out.println("After adding D (B removed as eldest): " + accessMap);

        // 3. Basic Operations
        System.out.println("\n=== Basic Operations ===");
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        
        // Adding entries
        map.put("Key1", "Value1");
        map.put("Key2", "Value2");
        map.put("Key3", "Value3");
        System.out.println("After adding entries: " + map);
        
        // Getting values
        System.out.println("Value for Key1: " + map.get("Key1"));
        System.out.println("Default value: " + map.getOrDefault("Key4", "NotFound"));

        // 4. Null Handling
        System.out.println("\n=== Null Handling ===");
        map.put(null, "NullKey");
        map.put("NullValue", null);
        System.out.println("Map with null key and value: " + map);

        // 5. Iteration Methods
        System.out.println("\n=== Iteration Methods ===");
        
        // Using entrySet (maintains order)
        System.out.println("Iterating entries:");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        
        // Using forEach (maintains order)
        System.out.println("\nUsing forEach:");
        map.forEach((key, value) -> System.out.println(key + " => " + value));

        // 6. Performance Comparison with HashMap
        System.out.println("\n=== Performance Comparison ===");
        HashMap<Integer, String> hashMap = new HashMap<>();
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        
        // Adding entries
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            hashMap.put(i, "Value" + i);
        }
        long hashMapTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            linkedHashMap.put(i, "Value" + i);
        }
        long linkedHashMapTime = System.nanoTime() - startTime;
        
        System.out.println("Time to add 100,000 entries:");
        System.out.println("HashMap: " + hashMapTime / 1_000_000 + " ms");
        System.out.println("LinkedHashMap: " + linkedHashMapTime / 1_000_000 + " ms");

        // 7. Custom Objects Demo
        System.out.println("\n=== Custom Objects Demo ===");
        LinkedHashMap<Person, String> personMap = new LinkedHashMap<>();
        Person person1 = new Person("John", 25);
        Person person2 = new Person("Jane", 30);
        
        personMap.put(person1, "Employee");
        personMap.put(person2, "Manager");
        System.out.println("Person map (maintains order): " + personMap);
    }
    
    // Inner class for custom objects
    static class Person {
        String name;
        int age;
        
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
        
        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }
} 