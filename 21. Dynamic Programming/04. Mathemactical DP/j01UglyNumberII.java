/**
 * LeetCode 264. Ugly Number II
 * 
 * Problem Statement:
 *     An ugly number is a positive integer whose prime factors are limited to 2,
 *     3, and 5. Given an integer n, return the nth ugly number.
 * 
 * Input:
 *     - n (1 <= n <= 1690) - the position of the ugly number to find
 * 
 * Output:
 *     - Integer representing the nth ugly number
 * 
 * Example:
 *     Input: n = 10
 *     Output: 12
 * 
 *     Explanation:
 *     The sequence of ugly numbers is: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, ...
 *     The 10th ugly number is 12.
 * 
 * Note:
 *     1 is typically considered an ugly number.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class j01UglyNumberII {
    
    /**
     * Approach 1: Priority Queue with HashSet (Min Heap)
     * 
     * Intuition:
     * - Use a min heap to always get the smallest ugly number available
     * - Start with 1 and generate new ugly numbers by multiplying with 2, 3, 5
     * - Use a HashSet to avoid duplicates and track processed numbers
     * - Extract the nth smallest number from the heap
     * 
     * Explanation:
     * - Initialize min heap with 1 (first ugly number)
     * - While we haven't found n ugly numbers:
     *   - Extract minimum from heap
     *   - Add it to result set if not already processed
     *   - Generate new ugly numbers by multiplying with 2, 3, 5
     *   - Add new numbers to heap if not already present
     * - Return the nth ugly number
     * 
     * Time Complexity: O(n log n) - Each number processed once, heap operations
     * Space Complexity: O(n) - Heap and HashSet to store ugly numbers
     * 
     * @param n    Position of ugly number to find (1 <= n <= 1690)
     * @return     The nth ugly number
     */
    public static int nthUglyNumber(int n) {
        // Initialize min heap to always get smallest ugly number
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(1);  // Start with first ugly number
        
        // Track processed numbers to avoid duplicates
        Set<Integer> processed = new HashSet<>();
        int currentUgly = 1;
        
        // Generate ugly numbers until we find the nth one
        while (processed.size() < n) {
            // Extract smallest ugly number from heap
            currentUgly = pq.poll();
            
            // Add to processed set if not already seen
            if (!processed.contains(currentUgly)) {
                processed.add(currentUgly);
                
                // Generate new ugly numbers by multiplying with 2, 3, 5
                if (!processed.contains(currentUgly * 2)) {
                    pq.add(currentUgly * 2);
                }
                if (!processed.contains(currentUgly * 3)) {
                    pq.add(currentUgly * 3);
                }
                if (!processed.contains(currentUgly * 5)) {
                    pq.add(currentUgly * 5);
                }
            }
        }
        
        return currentUgly;
    }

    /**
     * Approach 2: Three Pointers (Dynamic Programming)
     * 
     * Intuition:
     * - Use three pointers to track the next ugly number that can be generated
     *   by multiplying with 2, 3, and 5 respectively
     * - Maintain a sorted list of ugly numbers
     * - Always choose the minimum of the three possible next ugly numbers
     * - This ensures we generate ugly numbers in sorted order
     * 
     * Explanation:
     * - Initialize three pointers (ptr1, ptr2, ptr3) pointing to index 0
     * - Start with ugly numbers list containing [1]
     * - For each iteration:
     *   - Calculate next ugly numbers: a = list[ptr1] * 2, b = list[ptr2] * 3, c = list[ptr3] * 5
     *   - Choose minimum of a, b, c as next ugly number
     *   - Add it to the list
     *   - Increment pointers that generated the chosen number
     * - Return the nth ugly number
     * 
     * Time Complexity: O(n) - Single pass to generate n ugly numbers
     * Space Complexity: O(n) - List to store ugly numbers
     * 
     * @param n    Position of ugly number to find (1 <= n <= 1690)
     * @return     The nth ugly number
     */
    public static int nthUglyNumber2(int n) {
        // Initialize three pointers for 2, 3, 5 multipliers
        int ptr2 = 0;  // Pointer for multiplying by 2
        int ptr3 = 0;  // Pointer for multiplying by 3
        int ptr5 = 0;  // Pointer for multiplying by 5
        
        // List to store ugly numbers in sorted order
        ArrayList<Integer> uglyNumbers = new ArrayList<>();
        uglyNumbers.add(1);  // First ugly number
        
        // Generate ugly numbers until we have n numbers
        while (uglyNumbers.size() < n) {
            // Calculate next possible ugly numbers
            int next2 = uglyNumbers.get(ptr2) * 2;  // Next ugly number from ptr2
            int next3 = uglyNumbers.get(ptr3) * 3;  // Next ugly number from ptr3
            int next5 = uglyNumbers.get(ptr5) * 5;  // Next ugly number from ptr5
            
            // Choose the minimum as next ugly number
            int nextUgly = Math.min(next2, Math.min(next3, next5));
            uglyNumbers.add(nextUgly);
            
            // Increment pointers that generated the chosen number
            if (nextUgly == next2) ptr2++;  // Move ptr2 if 2 was used
            if (nextUgly == next3) ptr3++;  // Move ptr3 if 3 was used
            if (nextUgly == next5) ptr5++;  // Move ptr5 if 5 was used
        }
        
        return uglyNumbers.get(n - 1);
    }
    
    /**
     * Approach 3: Space-Optimized Three Pointers
     * 
     * Intuition:
     * - Similar to Approach 2 but use an array instead of ArrayList
     * - Pre-allocate array of size n to avoid dynamic resizing
     * - More memory efficient and slightly faster
     * 
     * Explanation:
     * - Initialize array of size n and three pointers
     * - Fill array with ugly numbers using same logic as Approach 2
     * - Return the nth element directly from array
     * 
     * Time Complexity: O(n) - Single pass to generate n ugly numbers
     * Space Complexity: O(n) - Array to store ugly numbers
     * 
     * @param n    Position of ugly number to find (1 <= n <= 1690)
     * @return     The nth ugly number
     */
    public static int nthUglyNumberOptimized(int n) {
        // Pre-allocate array for better performance
        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;  // First ugly number
        
        // Initialize three pointers
        int ptr2 = 0, ptr3 = 0, ptr5 = 0;
        
        // Generate ugly numbers
        for (int i = 1; i < n; i++) {
            // Calculate next possible ugly numbers
            int next2 = uglyNumbers[ptr2] * 2;
            int next3 = uglyNumbers[ptr3] * 3;
            int next5 = uglyNumbers[ptr5] * 5;
            
            // Choose minimum and add to array
            uglyNumbers[i] = Math.min(next2, Math.min(next3, next5));
            
            // Increment pointers that generated the chosen number
            if (uglyNumbers[i] == next2) ptr2++;
            if (uglyNumbers[i] == next3) ptr3++;
            if (uglyNumbers[i] == next5) ptr5++;
        }
        
        return uglyNumbers[n - 1];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: n=1, Expected: 1, Output: " + nthUglyNumber2(1));
        System.out.println("Input: n=2, Expected: 2, Output: " + nthUglyNumber2(2));
        System.out.println("Input: n=3, Expected: 3, Output: " + nthUglyNumber2(3));
        System.out.println("Input: n=4, Expected: 4, Output: " + nthUglyNumber2(4));
        System.out.println("Input: n=5, Expected: 5, Output: " + nthUglyNumber2(5));
        System.out.println("Input: n=6, Expected: 6, Output: " + nthUglyNumber2(6));
        System.out.println("Input: n=7, Expected: 8, Output: " + nthUglyNumber2(7));
        System.out.println("Input: n=10, Expected: 12, Output: " + nthUglyNumber2(10));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: n=1, Expected: 1, Output: " + nthUglyNumber2(1));
        System.out.println("Input: n=2, Expected: 2, Output: " + nthUglyNumber2(2));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: n=15, Expected: 24, Output: " + nthUglyNumber2(15));
        System.out.println("Input: n=20, Expected: 36, Output: " + nthUglyNumber2(20));

        // Test Case 4: Compare all approaches
        System.out.println("\nComparing All Approaches:");
        int n = 25;
        System.out.println("Input: n=" + n);
        System.out.println("Priority Queue: " + nthUglyNumber(n));
        System.out.println("Three Pointers: " + nthUglyNumber2(n));
        System.out.println("Optimized: " + nthUglyNumberOptimized(n));

        // Test Case 5: Large input
        System.out.println("\nLarge Input Test:");
        n = 50;
        System.out.println("Input: n=" + n);
        System.out.println("Three Pointers: " + nthUglyNumber2(n));
        System.out.println("Optimized: " + nthUglyNumberOptimized(n));
        
        // Test Case 6: First few ugly numbers
        System.out.println("\nFirst 20 Ugly Numbers:");
        for (int i = 1; i <= 20; i++) {
            System.out.print(nthUglyNumber2(i) + " ");
        }
        System.out.println();
    }
}
