/*
 * Topic: TreeSet Implementation in Java
 * 
 * Key Characteristics:
 * - Implements NavigableSet interface
 * - Red-Black Tree implementation
 * - Elements stored in sorted order
 * - No duplicate elements
 * - Null not allowed
 * - Not synchronized
 * - O(log n) for most operations
 * - Can use custom Comparator
 */

import java.util.*;

public class j09TreeSet {
    public static void main(String[] args) {
        // Different ways to initialize TreeSet
        System.out.println("=== Initialization Examples ===");
        TreeSet<String> set1 = new TreeSet<>();                    // Natural ordering
        set1.add("First");
        TreeSet<String> set2 = new TreeSet<>(Comparator.reverseOrder()); // Custom comparator
        set2.add("Reverse Order");
        TreeSet<String> set3 = new TreeSet<>(Arrays.asList("C", "A", "B")); // From collection
        
        System.out.println("Set1 (natural order): " + set1);
        System.out.println("Set2 (reverse order): " + set2);
        System.out.println("Set3 (from collection, sorted): " + set3);

        // 1. Basic Operations with Natural Ordering
        System.out.println("\n=== Basic Operations ===");
        TreeSet<Integer> numbers = new TreeSet<>();
        
        // Adding elements
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);
        numbers.add(1);
        numbers.add(9);
        System.out.println("Numbers in sorted order: " + numbers);
        
        // Attempting to add duplicate
        System.out.println("Add duplicate 5: " + numbers.add(5));  // Returns false

        // 2. Navigation Operations
        System.out.println("\n=== Navigation Operations ===");
        System.out.println("First element: " + numbers.first());
        System.out.println("Last element: " + numbers.last());
        System.out.println("Lower than 5: " + numbers.lower(5));   // Strictly less
        System.out.println("Higher than 5: " + numbers.higher(5)); // Strictly more
        System.out.println("Floor of 4: " + numbers.floor(4));     // Less than or equal
        System.out.println("Ceiling of 4: " + numbers.ceiling(4)); // Greater than or equal

        // 3. Range View Operations
        System.out.println("\n=== Range View Operations ===");
        System.out.println("Elements <= 5: " + numbers.headSet(5, true));
        System.out.println("Elements >= 5: " + numbers.tailSet(5, true));
        System.out.println("Elements 2-8: " + numbers.subSet(2, true, 8, true));
        
        // Descending order
        System.out.println("Descending order: " + numbers.descendingSet());

        // 4. Polling Operations
        System.out.println("\n=== Polling Operations ===");
        TreeSet<Integer> pollSet = new TreeSet<>(numbers);
        System.out.println("Poll first: " + pollSet.pollFirst()); // Retrieves and removes first
        System.out.println("Poll last: " + pollSet.pollLast());   // Retrieves and removes last
        System.out.println("After polling: " + pollSet);

        // 5. Custom Comparator Example
        System.out.println("\n=== Custom Comparator Example ===");
        TreeSet<String> lengthSet = new TreeSet<>((s1, s2) -> {
            if (s1.length() != s2.length()) {
                return s1.length() - s2.length();
            }
            return s1.compareTo(s2);
        });
        
        lengthSet.add("Dog");
        lengthSet.add("Cat");
        lengthSet.add("Elephant");
        lengthSet.add("Bird");
        System.out.println("Sorted by length: " + lengthSet);

        // 6. Custom Objects with Natural Ordering
        System.out.println("\n=== Custom Objects (Natural Ordering) ===");
        TreeSet<Person> personSet = new TreeSet<>();
        personSet.add(new Person("John", 25));
        personSet.add(new Person("Alice", 30));
        personSet.add(new Person("Bob", 20));
        System.out.println("Persons sorted by age: " + personSet);

        // 7. Performance Testing
        System.out.println("\n=== Performance Testing ===");
        TreeSet<Integer> largeSet = new TreeSet<>();
        
        // Adding elements
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            largeSet.add(i);
        }
        long endTime = System.nanoTime();
        System.out.println("Time to add 100,000 elements: " + 
            (endTime - startTime) / 1_000_000 + " ms");
        
        // Searching elements
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            largeSet.contains(i);
        }
        endTime = System.nanoTime();
        System.out.println("Time to search 1,000 elements: " + 
            (endTime - startTime) / 1_000_000 + " ms");
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
    }
} 