/*
 * Topic: HashSet Implementation in Java
 * 
 * Key Characteristics:
 * - Implements Set interface
 * - Backed by HashMap
 * - No duplicate elements
 * - Allows null element
 * - No guaranteed order
 * - Not synchronized
 * - O(1) for add, remove, contains
 * - Load factor default: 0.75
 * - Initial capacity default: 16
 */

import java.util.*;

public class j07HashSet {
    public static void main(String[] args) {
        // Different ways to initialize HashSet
        System.out.println("=== Initialization Examples ===");
        HashSet<String> set1 = new HashSet<>();                    // Default initial capacity (16) and load factor (0.75)
        set1.add("Default HashSet");
        HashSet<String> set2 = new HashSet<>(20);                 // Custom initial capacity
        set2.add("Custom Capacity");
        HashSet<String> set3 = new HashSet<>(20, 0.8f);          // Custom capacity and load factor
        set3.add("Custom Load Factor");
        HashSet<String> set4 = new HashSet<>(Arrays.asList("A", "B", "C")); // From collection
        
        System.out.println("Set1 (default): " + set1);
        System.out.println("Set2 (sized): " + set2);
        System.out.println("Set3 (with load factor): " + set3);
        System.out.println("Set4 (from collection): " + set4);

        // 1. Basic Operations
        System.out.println("\n=== Basic Operations ===");
        HashSet<String> set = new HashSet<>();
        
        // Adding elements
        System.out.println("Add 'Apple': " + set.add("Apple"));       // Returns true
        System.out.println("Add 'Banana': " + set.add("Banana"));
        System.out.println("Add 'Cherry': " + set.add("Cherry"));
        System.out.println("Add duplicate 'Apple': " + set.add("Apple")); // Returns false
        System.out.println("Set contents: " + set);
        
        // Adding null
        set.add(null);
        System.out.println("After adding null: " + set);

        // 2. Querying the Set
        System.out.println("\n=== Query Operations ===");
        System.out.println("Size: " + set.size());
        System.out.println("Contains 'Apple'? " + set.contains("Apple"));
        System.out.println("Contains 'Mango'? " + set.contains("Mango"));
        System.out.println("Is empty? " + set.isEmpty());

        // 3. Removing Elements
        System.out.println("\n=== Removal Operations ===");
        System.out.println("Remove 'Apple': " + set.remove("Apple"));     // Returns true
        System.out.println("Remove 'Mango': " + set.remove("Mango"));     // Returns false
        System.out.println("After removals: " + set);

        // 4. Bulk Operations
        System.out.println("\n=== Bulk Operations ===");
        HashSet<String> otherSet = new HashSet<>(Arrays.asList("Banana", "Date", "Elderberry"));
        
        // Union (addAll)
        HashSet<String> union = new HashSet<>(set);
        union.addAll(otherSet);
        System.out.println("Union: " + union);
        
        // Intersection (retainAll)
        HashSet<String> intersection = new HashSet<>(set);
        intersection.retainAll(otherSet);
        System.out.println("Intersection: " + intersection);
        
        // Difference (removeAll)
        HashSet<String> difference = new HashSet<>(set);
        difference.removeAll(otherSet);
        System.out.println("Difference: " + difference);

        // 5. Iteration Methods
        System.out.println("\n=== Iteration Methods ===");
        HashSet<String> iterSet = new HashSet<>(Arrays.asList("Dog", "Cat", "Bird"));
        
        // Using enhanced for loop
        System.out.println("Enhanced for loop:");
        for (String element : iterSet) {
            System.out.println("- " + element);
        }
        
        // Using iterator
        System.out.println("\nIterator:");
        Iterator<String> iterator = iterSet.iterator();
        while (iterator.hasNext()) {
            System.out.println("- " + iterator.next());
        }
        
        // Using forEach
        System.out.println("\nforEach method:");
        iterSet.forEach(element -> System.out.println("- " + element));

        // 6. Performance Testing
        System.out.println("\n=== Performance Testing ===");
        HashSet<Integer> largeSet = new HashSet<>();
        
        // Adding elements
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            largeSet.add(i);
        }
        long endTime = System.nanoTime();
        System.out.println("Time to add 100,000 elements: " + 
            (endTime - startTime) / 1_000_000 + " ms");
        
        // Searching for elements
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            largeSet.contains(i);
        }
        endTime = System.nanoTime();
        System.out.println("Time to search 100,000 elements: " + 
            (endTime - startTime) / 1_000_000 + " ms");

        // 7. Common Pitfalls and Solutions
        System.out.println("\n=== Common Pitfalls and Solutions ===");
        
        // Custom objects need proper equals and hashCode
        HashSet<Person> personSet = new HashSet<>();
        Person person1 = new Person("John", 25);
        Person person2 = new Person("John", 25);
        
        personSet.add(person1);
        System.out.println("Add duplicate person: " + personSet.add(person2));
        System.out.println("PersonSet size: " + personSet.size());
    }
    
    // Inner class to demonstrate custom objects in HashSet
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
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }
} 