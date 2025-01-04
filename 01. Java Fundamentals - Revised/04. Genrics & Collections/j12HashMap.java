/*
 * Topic: HashMap Implementation in Java
 * 
 * Key Characteristics:
 * - Implements Map interface
 * - Hash table based implementation
 * - No guaranteed order
 * - Allows one null key and multiple null values
 * - Not synchronized
 * - O(1) for basic operations
 * - Load factor default: 0.75
 * - Initial capacity default: 16
 * - Backed by array of Entry objects
 */

import java.util.*;

public class j12HashMap {
    public static void main(String[] args) {
        // Different ways to initialize HashMap
        System.out.println("=== Initialization Examples ===");
        HashMap<String, Integer> map1 = new HashMap<>();                    // Default capacity (16) and load factor (0.75)
        map1.put("One", 1);
        HashMap<String, Integer> map2 = new HashMap<>(20);                 // Custom initial capacity
        map2.put("Two", 2);
        HashMap<String, Integer> map3 = new HashMap<>(20, 0.8f);          // Custom capacity and load factor
        map3.put("Three", 3);
        HashMap<String, Integer> map4 = new HashMap<>(map1);              // From another map
        
        System.out.println("Map1 (default): " + map1);
        System.out.println("Map2 (sized): " + map2);
        System.out.println("Map3 (with load factor): " + map3);
        System.out.println("Map4 (from map): " + map4);

        // 1. Basic Operations
        System.out.println("\n=== Basic Operations ===");
        HashMap<String, String> map = new HashMap<>();
        
        // Adding entries
        map.put("Key1", "Value1");
        map.put("Key2", "Value2");
        map.put("Key3", "Value3");
        System.out.println("After adding entries: " + map);
        
        // Updating value
        map.put("Key1", "UpdatedValue1");
        System.out.println("After updating Key1: " + map);
        
        // Getting values
        System.out.println("Value for Key1: " + map.get("Key1"));
        System.out.println("Value for nonexistent key: " + map.get("Key4"));
        System.out.println("Default value for Key4: " + map.getOrDefault("Key4", "NotFound"));

        // 2. Null Key and Values
        System.out.println("\n=== Null Key and Values ===");
        map.put(null, "NullKey");
        map.put("NullValue", null);
        System.out.println("Map with null key and value: " + map);
        System.out.println("Value for null key: " + map.get(null));

        // 3. Checking Operations
        System.out.println("\n=== Checking Operations ===");
        System.out.println("Contains key 'Key1': " + map.containsKey("Key1"));
        System.out.println("Contains value 'Value2': " + map.containsValue("Value2"));
        System.out.println("Size: " + map.size());
        System.out.println("Is empty: " + map.isEmpty());

        // 4. Removal Operations
        System.out.println("\n=== Removal Operations ===");
        System.out.println("Remove Key1: " + map.remove("Key1"));
        System.out.println("Remove Key2 with specific value: " + 
            map.remove("Key2", "Value2"));
        System.out.println("After removals: " + map);

        // 5. Bulk Operations
        System.out.println("\n=== Bulk Operations ===");
        HashMap<String, String> otherMap = new HashMap<>();
        otherMap.put("Key4", "Value4");
        otherMap.put("Key5", "Value5");
        
        map.putAll(otherMap);
        System.out.println("After putAll: " + map);

        // 6. Collection Views
        System.out.println("\n=== Collection Views ===");
        System.out.println("Keys: " + map.keySet());
        System.out.println("Values: " + map.values());
        System.out.println("Entries: " + map.entrySet());

        // 7. Iteration Methods
        System.out.println("\n=== Iteration Methods ===");
        
        // Using entrySet
        System.out.println("Iterating entries:");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        
        // Using forEach
        System.out.println("\nUsing forEach:");
        map.forEach((key, value) -> System.out.println(key + " => " + value));

        // 8. Compute Operations
        System.out.println("\n=== Compute Operations ===");
        map.compute("Key3", (key, value) -> value + "_Computed");
        map.computeIfAbsent("Key6", key -> "Value6");
        map.computeIfPresent("Key4", (key, value) -> value + "_Updated");
        System.out.println("After compute operations: " + map);

        // 9. Performance Testing
        System.out.println("\n=== Performance Testing ===");
        HashMap<Integer, String> largeMap = new HashMap<>();
        
        // Adding entries
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            largeMap.put(i, "Value" + i);
        }
        long endTime = System.nanoTime();
        System.out.println("Time to add 100,000 entries: " + 
            (endTime - startTime) / 1_000_000 + " ms");
        
        // Getting entries
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            largeMap.get(i);
        }
        endTime = System.nanoTime();
        System.out.println("Time to get 100,000 entries: " + 
            (endTime - startTime) / 1_000_000 + " ms");

        // 10. Custom Objects as Keys
        System.out.println("\n=== Custom Objects as Keys ===");
        HashMap<Person, String> personMap = new HashMap<>();
        Person person1 = new Person("John", 25);
        Person person2 = new Person("Jane", 30);
        
        personMap.put(person1, "Employee");
        personMap.put(person2, "Manager");
        System.out.println("Person map: " + personMap);
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