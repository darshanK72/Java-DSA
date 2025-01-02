/*
 * Topic: Collections Utility Class in Java
 * 
 * Key Features:
 * - Static utility methods for Collection objects
 * - Sorting and searching
 * - Synchronization wrappers
 * - Unmodifiable wrappers
 * - Singleton collections
 * - Collection algorithms
 * - Type-safe wrappers
 * - Empty collections
 */

import java.util.*;

public class j16CollectionsUtility {
    public static void main(String[] args) {
        // 1. Basic List Operations
        System.out.println("=== Basic List Operations ===");
        List<String> list = new ArrayList<>(Arrays.asList("banana", "apple", "cherry"));
        System.out.println("Original list: " + list);
        
        // Sorting
        Collections.sort(list);
        System.out.println("Natural sort: " + list);
        
        Collections.reverse(list);
        System.out.println("Reversed: " + list);
        
        Collections.shuffle(list);
        System.out.println("Shuffled: " + list);

        // 2. Search Operations
        System.out.println("\n=== Search Operations ===");
        Collections.sort(list);  // Must be sorted for binarySearch
        int index = Collections.binarySearch(list, "cherry");
        System.out.println("Index of 'cherry': " + index);
        
        System.out.println("Minimum: " + Collections.min(list));
        System.out.println("Maximum: " + Collections.max(list));
        
        // Frequency and disjoint
        System.out.println("Frequency of 'apple': " + 
            Collections.frequency(list, "apple"));

        // 3. Unmodifiable Views
        System.out.println("\n=== Unmodifiable Views ===");
        List<String> unmodifiableList = Collections.unmodifiableList(list);
        try {
            unmodifiableList.add("date");  // Will throw UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify unmodifiable list");
        }

        // 4. Synchronized Wrappers
        System.out.println("\n=== Synchronized Wrappers ===");
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
        Set<String> syncSet = Collections.synchronizedSet(new HashSet<>());
        Map<String, String> syncMap = Collections.synchronizedMap(new HashMap<>());

        // Thread-safe operations
        syncList.add("Thread Safe List");
        syncSet.add("Thread Safe Set");
        syncMap.put("key", "Thread Safe Map");
        
        System.out.println("Sync List: " + syncList);
        System.out.println("Sync Set: " + syncSet);
        System.out.println("Sync Map: " + syncMap);

        // 5. Singleton Collections
        System.out.println("\n=== Singleton Collections ===");
        Set<String> singletonSet = Collections.singleton("Only One");
        List<String> singletonList = Collections.singletonList("Only One");
        Map<String, String> singletonMap = Collections.singletonMap("key", "value");
        
        System.out.println("Singleton Set: " + singletonSet);
        System.out.println("Singleton List: " + singletonList);
        System.out.println("Singleton Map: " + singletonMap);

        // 6. Fill, Copy, and Replace Operations
        System.out.println("\n=== Fill, Copy, and Replace Operations ===");
        List<String> fillList = new ArrayList<>(Arrays.asList("one", "two", "three"));
        Collections.fill(fillList, "filled");
        System.out.println("After fill: " + fillList);
        
        List<String> source = Arrays.asList("A", "B", "C");
        List<String> dest = Arrays.asList("D", "E", "F");
        Collections.copy(dest, source);
        System.out.println("After copy: " + dest);
        
        List<String> replaceList = new ArrayList<>(Arrays.asList("one", "two", "one"));
        Collections.replaceAll(replaceList, "one", "replaced");
        System.out.println("After replace: " + replaceList);

        // 7. Empty Collections
        System.out.println("\n=== Empty Collections ===");
        List<Object> emptyList = Collections.emptyList();
        Set<Object> emptySet = Collections.emptySet();
        Map<Object, Object> emptyMap = Collections.emptyMap();
        
        System.out.println("Empty List size: " + emptyList.size());
        System.out.println("Empty Set size: " + emptySet.size());
        System.out.println("Empty Map size: " + emptyMap.size());

        // 8. Collection Algorithms
        System.out.println("\n=== Collection Algorithms ===");
        List<String> rotateList = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        Collections.rotate(rotateList, 2);
        System.out.println("After rotate(2): " + rotateList);
        
        List<Integer> numbers = new ArrayList<>(Arrays.asList(3, 7, 2, 9, 1));
        System.out.println("Before swap: " + numbers);
        Collections.swap(numbers, 0, 4);
        System.out.println("After swap(0,4): " + numbers);

        // 9. Custom Objects with Comparator
        System.out.println("\n=== Custom Objects with Comparator ===");
        List<Person> people = new ArrayList<>(Arrays.asList(
            new Person("John", 25),
            new Person("Alice", 30),
            new Person("Bob", 20)
        ));
        
        // Sort by age (natural ordering)
        Collections.sort(people);
        System.out.println("Sorted by age: " + people);
        
        // Sort by name (custom comparator)
        Collections.sort(people, Comparator.comparing(p -> p.name));
        System.out.println("Sorted by name: " + people);

        // 10. Thread-Safe Collection Example
        System.out.println("\n=== Thread-Safe Collection Example ===");
        List<Integer> threadSafeList = Collections.synchronizedList(new ArrayList<>());
        
        // Multiple threads adding elements
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                threadSafeList.add(i);
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 3; i < 6; i++) {
                threadSafeList.add(i);
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        });
        
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Thread-safe list result: " + threadSafeList);
    }
    
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