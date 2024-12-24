/*
 * Topic: LinkedHashSet Implementation in Java
 * 
 * Key Characteristics:
 * - Extends HashSet
 * - Maintains insertion order
 * - Backed by LinkedHashMap
 * - No duplicate elements
 * - Allows null element
 * - Not synchronized
 * - O(1) for basic operations
 * - Slightly slower than HashSet
 * - Uses more memory than HashSet
 */

import java.util.*;

public class j08LinkedHashSet {
    public static void main(String[] args) {
        // Different ways to initialize LinkedHashSet
        System.out.println("=== Initialization Examples ===");
        LinkedHashSet<String> set1 = new LinkedHashSet<>();           // Default capacity (16) and load factor (0.75)
        set1.add("First Element");
        LinkedHashSet<String> set2 = new LinkedHashSet<>(20);        // Custom initial capacity
        set2.add("Custom Capacity");
        LinkedHashSet<String> set3 = new LinkedHashSet<>(20, 0.8f);  // Custom capacity and load factor
        set3.add("Custom Load Factor");
        LinkedHashSet<String> set4 = new LinkedHashSet<>(Arrays.asList("A", "B", "C")); // From collection
        
        System.out.println("Set1 (default): " + set1);
        System.out.println("Set2 (sized): " + set2);
        System.out.println("Set3 (with load factor): " + set3);
        System.out.println("Set4 (from collection): " + set4);

        // 1. Demonstrating Order Preservation
        System.out.println("\n=== Order Preservation Demo ===");
        LinkedHashSet<String> orderedSet = new LinkedHashSet<>();
        orderedSet.add("First");
        orderedSet.add("Second");
        orderedSet.add("Third");
        orderedSet.add("Fourth");
        System.out.println("Insertion order maintained: " + orderedSet);
        
        // Remove and add to show order changes
        orderedSet.remove("Second");
        orderedSet.add("Second");  // Will be added at the end
        System.out.println("After remove and re-add: " + orderedSet);

        // 2. Basic Operations
        System.out.println("\n=== Basic Operations ===");
        LinkedHashSet<String> set = new LinkedHashSet<>();
        
        // Adding elements
        System.out.println("Add 'Apple': " + set.add("Apple"));
        System.out.println("Add 'Banana': " + set.add("Banana"));
        System.out.println("Add duplicate 'Apple': " + set.add("Apple")); // Returns false
        System.out.println("Set contents: " + set);
        
        // Adding null
        set.add(null);
        System.out.println("After adding null: " + set);

        // 3. Query Operations
        System.out.println("\n=== Query Operations ===");
        System.out.println("Size: " + set.size());
        System.out.println("Contains 'Apple'? " + set.contains("Apple"));
        System.out.println("Contains 'Mango'? " + set.contains("Mango"));
        System.out.println("Is empty? " + set.isEmpty());

        // 4. Bulk Operations
        System.out.println("\n=== Bulk Operations ===");
        LinkedHashSet<String> otherSet = new LinkedHashSet<>(Arrays.asList("Cherry", "Date", "Banana"));
        
        // Union (addAll)
        LinkedHashSet<String> union = new LinkedHashSet<>(set);
        union.addAll(otherSet);
        System.out.println("Union (maintains order): " + union);
        
        // Intersection (retainAll)
        LinkedHashSet<String> intersection = new LinkedHashSet<>(set);
        intersection.retainAll(otherSet);
        System.out.println("Intersection: " + intersection);
        
        // Difference (removeAll)
        LinkedHashSet<String> difference = new LinkedHashSet<>(set);
        difference.removeAll(otherSet);
        System.out.println("Difference: " + difference);

        // 5. Iteration Methods
        System.out.println("\n=== Iteration Methods ===");
        LinkedHashSet<String> iterSet = new LinkedHashSet<>(Arrays.asList("Dog", "Cat", "Bird"));
        
        // Using enhanced for loop
        System.out.println("Enhanced for loop (maintains order):");
        for (String element : iterSet) {
            System.out.println("- " + element);
        }
        
        // Using iterator
        System.out.println("\nIterator (maintains order):");
        Iterator<String> iterator = iterSet.iterator();
        while (iterator.hasNext()) {
            System.out.println("- " + iterator.next());
        }

        // 6. Performance Comparison with HashSet
        System.out.println("\n=== Performance Comparison ===");
        HashSet<Integer> hashSet = new HashSet<>();
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        
        // Adding elements
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            hashSet.add(i);
        }
        long hashSetTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            linkedHashSet.add(i);
        }
        long linkedHashSetTime = System.nanoTime() - startTime;
        
        System.out.println("Time to add 100,000 elements:");
        System.out.println("HashSet: " + hashSetTime / 1_000_000 + " ms");
        System.out.println("LinkedHashSet: " + linkedHashSetTime / 1_000_000 + " ms");

        // 7. Custom Objects Demo
        System.out.println("\n=== Custom Objects Demo ===");
        LinkedHashSet<Person> personSet = new LinkedHashSet<>();
        Person person1 = new Person("John", 25);
        Person person2 = new Person("Jane", 30);
        Person person3 = new Person("John", 25); // Same as person1
        
        personSet.add(person1);
        personSet.add(person2);
        System.out.println("Add duplicate person: " + personSet.add(person3));
        System.out.println("PersonSet (maintains order): " + personSet);
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
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }
} 