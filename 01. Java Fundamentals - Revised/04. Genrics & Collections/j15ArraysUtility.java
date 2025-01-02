/*
 * Topic: Arrays Utility Class in Java
 * 
 * Key Features:
 * - Static utility methods for arrays
 * - Sorting algorithms
 * - Searching algorithms
 * - Array manipulation
 * - Array comparison
 * - Parallel operations
 * - Array to List conversion
 * - Fill and copy operations
 */

import java.util.*;

public class j15ArraysUtility {
    public static void main(String[] args) {
        // 1. Basic Array Operations
        System.out.println("=== Basic Array Operations ===");
        int[] numbers = {5, 2, 8, 1, 9, 3, 7, 4, 6};
        System.out.println("Original array: " + Arrays.toString(numbers));
        
        // Sorting
        Arrays.sort(numbers);
        System.out.println("Sorted array: " + Arrays.toString(numbers));
        
        // Binary Search (array must be sorted)
        int index = Arrays.binarySearch(numbers, 7);
        System.out.println("Index of 7: " + index);

        // 2. Fill Operations
        System.out.println("\n=== Fill Operations ===");
        int[] filledArray = new int[5];
        Arrays.fill(filledArray, 10);
        System.out.println("Filled with 10: " + Arrays.toString(filledArray));
        
        // Range fill
        int[] rangeArray = new int[10];
        Arrays.fill(rangeArray, 2, 6, 42);
        System.out.println("Range filled: " + Arrays.toString(rangeArray));

        // 3. Copy Operations
        System.out.println("\n=== Copy Operations ===");
        int[] copied = Arrays.copyOf(numbers, numbers.length);
        System.out.println("Complete copy: " + Arrays.toString(copied));
        
        int[] partial = Arrays.copyOfRange(numbers, 2, 5);
        System.out.println("Partial copy (2-5): " + Arrays.toString(partial));

        // 4. Comparison Operations
        System.out.println("\n=== Comparison Operations ===");
        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 2, 3};
        int[] array3 = {1, 2, 4};
        
        System.out.println("array1 equals array2: " + Arrays.equals(array1, array2));
        System.out.println("array1 equals array3: " + Arrays.equals(array1, array3));
        
        // Compare (lexicographically)
        System.out.println("array1 compare to array3: " + Arrays.compare(array1, array3));

        // 5. Multi-dimensional Arrays
        System.out.println("\n=== Multi-dimensional Arrays ===");
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        System.out.println("2D array: " + Arrays.deepToString(matrix));
        
        int[][] matrix2 = {{1, 2, 3}, {4, 5, 6}};
        System.out.println("Deep equals: " + Arrays.deepEquals(matrix, matrix2));

        // 6. Array to List Conversion
        System.out.println("\n=== Array to List Conversion ===");
        String[] stringArray = {"Apple", "Banana", "Cherry"};
        List<String> list = Arrays.asList(stringArray);
        System.out.println("As list: " + list);

        // 7. Parallel Operations
        System.out.println("\n=== Parallel Operations ===");
        int[] largeArray = new int[1000000];
        Arrays.parallelSetAll(largeArray, i -> i);
        
        long startTime = System.nanoTime();
        Arrays.parallelSort(largeArray);
        long endTime = System.nanoTime();
        System.out.println("Parallel sort time: " + (endTime - startTime) / 1_000_000 + " ms");

        // 8. Custom Object Arrays
        System.out.println("\n=== Custom Object Arrays ===");
        Person[] people = {
            new Person("John", 25),
            new Person("Alice", 30),
            new Person("Bob", 20)
        };
        
        // Sort by natural ordering (age)
        Arrays.sort(people);
        System.out.println("Sorted by age: " + Arrays.toString(people));
        
        // Sort by custom comparator (name)
        Arrays.sort(people, Comparator.comparing(p -> p.name));
        System.out.println("Sorted by name: " + Arrays.toString(people));

        // 9. Stream Operations
        System.out.println("\n=== Stream Operations ===");
        int[] numbers2 = {1, 2, 3, 4, 5};
        
        // Convert to stream
        int sum = Arrays.stream(numbers2).sum();
        double avg = Arrays.stream(numbers2).average().orElse(0.0);
        System.out.println("Sum: " + sum + ", Average: " + avg);

        // 10. Mismatch Operations
        System.out.println("\n=== Mismatch Operations ===");
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {1, 2, 3, 5, 5};
        int mismatchIndex = Arrays.mismatch(arr1, arr2);
        System.out.println("First mismatch at index: " + mismatchIndex);
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