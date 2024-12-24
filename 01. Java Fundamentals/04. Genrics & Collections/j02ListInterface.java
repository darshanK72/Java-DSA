/*
 * Topic: Java List Interface and Implementations
 * 
 * This program demonstrates:
 * 1. ArrayList vs LinkedList
 * 2. Common List operations
 * 3. Performance characteristics
 * 4. Iteration methods
 * 5. Utility methods
 * 6. Sorting and searching
 * 7. SubList operations
 */

import java.util.*;

public class j02ListInterface {
    public static void main(String[] args) {
        // ArrayList Demo
        System.out.println("=== ArrayList Demonstrations ===");
        ArrayList<String> arrayList = new ArrayList<>();
        
        // Basic Operations
        System.out.println("\n1. Basic ArrayList Operations:");
        arrayList.add("Java");                  // O(1) amortized
        arrayList.add("Python");
        arrayList.add(1, "JavaScript");         // O(n) - needs shifting
        System.out.println("After adding: " + arrayList);
        
        arrayList.remove("Python");             // O(n) - search and shift
        arrayList.remove(0);                // O(n) - needs shifting
        System.out.println("After removing: " + arrayList);
        
        // LinkedList Demo
        System.out.println("\n2. LinkedList Demonstrations:");
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("First");                // O(1)
        linkedList.addFirst("Start");           // O(1)
        linkedList.addLast("Last");             // O(1)
        System.out.println("LinkedList: " + linkedList);
        
        // Comparing ArrayList vs LinkedList
        System.out.println("\n3. ArrayList vs LinkedList Operations:");
        List<Integer> arrayList2 = new ArrayList<>();
        List<Integer> linkedList2 = new LinkedList<>();
        
        // Adding elements
        long startTime = System.nanoTime();
        for(int i = 0; i < 100000; i++) {
            arrayList2.add(0, i);  // Adding at beginning
        }
        System.out.println("ArrayList add at beginning time: " + 
            (System.nanoTime() - startTime) / 1000000 + "ms");
        
        startTime = System.nanoTime();
        for(int i = 0; i < 100000; i++) {
            linkedList2.add(0, i);  // Adding at beginning
        }
        System.out.println("LinkedList add at beginning time: " + 
            (System.nanoTime() - startTime) / 1000000 + "ms");
        
        // Different ways to iterate
        System.out.println("\n4. Iteration Methods:");
        List<String> list = Arrays.asList("A", "B", "C", "D");
        
        // Using for-each
        System.out.println("For-each loop:");
        for(String item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
        
        // Using Iterator
        System.out.println("Iterator:");
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        
        // Using ListIterator (can move both directions)
        System.out.println("ListIterator (reverse):");
        ListIterator<String> listIterator = list.listIterator(list.size());
        while(listIterator.hasPrevious()) {
            System.out.print(listIterator.previous() + " ");
        }
        System.out.println();
        
        // Utility operations
        System.out.println("\n5. Utility Operations:");
        List<Integer> numbers = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3));
        
        // Sorting
        Collections.sort(numbers);
        System.out.println("Sorted: " + numbers);
        
        // Binary Search (only on sorted list)
        int index = Collections.binarySearch(numbers, 5);
        System.out.println("Index of 5: " + index);
        
        // Frequency
        System.out.println("Frequency of 5: " + Collections.frequency(numbers, 5));
        
        // SubList operations
        System.out.println("\n6. SubList Operations:");
        List<Integer> subList = numbers.subList(2, 5);
        System.out.println("SubList (2-5): " + subList);
        
        // Bulk Operations
        System.out.println("\n7. Bulk Operations:");
        List<String> list1 = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> list2 = new ArrayList<>(Arrays.asList("B", "C", "D"));
        
        // Union
        List<String> union = new ArrayList<>(list1);
        union.addAll(list2);
        System.out.println("Union: " + union);
        
        // Intersection
        List<String> intersection = new ArrayList<>(list1);
        intersection.retainAll(list2);
        System.out.println("Intersection: " + intersection);
        
        // Difference
        List<String> difference = new ArrayList<>(list1);
        difference.removeAll(list2);
        System.out.println("Difference: " + difference);
    }
} 