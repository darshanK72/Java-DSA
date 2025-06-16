/**
 * LeetCode 1054. Distant Barcodes
 * 
 * Problem Statement:
 *     In a warehouse, there is a row of barcodes, where the ith barcode is barcodes[i]. Rearrange
 *     the barcodes so that no two adjacent barcodes are equal. You may return any answer, and it
 *     is guaranteed an answer exists.
 * 
 * Input:
 *     - barcodes (int[]): Array of barcode values
 * 
 * Output:
 *     - int[]: Rearranged array where no two adjacent elements are equal
 * 
 * Example:
 *     Input: barcodes = [1,1,1,2,2,2]
 *     Output: [2,1,2,1,2,1]
 * 
 *     Explanation:
 *     - No two adjacent elements are equal
 *     - One possible valid arrangement is [2,1,2,1,2,1]
 *     - Other valid arrangements are also possible
 */

import java.util.Arrays;
import java.util.PriorityQueue;

public class j05DistantBarcodes {

    /**
     * Helper class to store number and its frequency
     */
    static class Pair {
        int num;
        int count;

        Pair(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    /**
     * Approach: Greedy with Max Heap
     * 
     * Intuition:
     * - To avoid adjacent same numbers, we need to alternate between different numbers
     * - Use max heap to always pick the number with highest remaining frequency
     * - Keep track of previously used number to avoid using it again
     * - Put back the previous number if it still has remaining frequency
     * 
     * Explanation:
     * - Step 1: Count frequency of each number
     * - Step 2: Create max heap based on frequency
     * - Step 3: Alternate between numbers:
     *   - Take number with highest frequency
     *   - Decrease its frequency
     *   - Put back previous number if it has remaining frequency
     * - Step 4: Return rearranged array
     * 
     * Time Complexity: O(n log n) where n is size of array
     *                  - O(n log n) for sorting
     *                  - O(n log k) for heap operations where k is unique numbers
     * Space Complexity: O(n) for storing frequency and result
     * 
     * @param barcodes    Array of barcode values
     * @return           Rearranged array with no adjacent equal elements
     */
    public static int[] rearrangeBarcodes(int[] barcodes) {
        // Create max heap for frequency-based ordering
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.count - a.count);
        
        // Count frequency of each number
        Arrays.sort(barcodes);
        for (int i = 0; i < barcodes.length; i++) {
            int count = 1;
            int j = i + 1;
            while (j < barcodes.length && barcodes[j] == barcodes[i]) {
                count++;
                j++;
            }
            pq.add(new Pair(barcodes[i], count));
            i = j - 1;
        }

        // Rearrange barcodes using max heap
        Pair prev = new Pair(-1, 0);
        int[] out = new int[barcodes.length];
        int i = 0;
        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            out[i++] = curr.num;
            curr.count--;
            if (prev.count > 0) {
                pq.add(prev);
            }
            prev = curr;
        }

        return out;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] barcodes1 = {1,1,1,2,2,2};
        System.out.println("Input: " + Arrays.toString(barcodes1));
        System.out.println("Expected: [2,1,2,1,2,1] (or similar), Output: " + 
            Arrays.toString(rearrangeBarcodes(barcodes1)));

        // Test Case 2: All same numbers
        System.out.println("\nSpecial Case - All Same Numbers:");
        int[] barcodes2 = {1,1,1,1};
        System.out.println("Input: " + Arrays.toString(barcodes2));
        System.out.println("Expected: [1,1,1,1], Output: " + 
            Arrays.toString(rearrangeBarcodes(barcodes2)));

        // Test Case 3: Alternating numbers
        System.out.println("\nSpecial Case - Already Alternating:");
        int[] barcodes3 = {1,2,1,2,1,2};
        System.out.println("Input: " + Arrays.toString(barcodes3));
        System.out.println("Expected: [1,2,1,2,1,2], Output: " + 
            Arrays.toString(rearrangeBarcodes(barcodes3)));

        // Test Case 4: Single number
        System.out.println("\nEdge Case - Single Number:");
        int[] barcodes4 = {1};
        System.out.println("Input: " + Arrays.toString(barcodes4));
        System.out.println("Expected: [1], Output: " + 
            Arrays.toString(rearrangeBarcodes(barcodes4)));

        // Test Case 5: Three different numbers
        System.out.println("\nSpecial Case - Three Different Numbers:");
        int[] barcodes5 = {1,1,2,2,3,3};
        System.out.println("Input: " + Arrays.toString(barcodes5));
        System.out.println("Expected: [3,1,3,1,2,2] (or similar), Output: " + 
            Arrays.toString(rearrangeBarcodes(barcodes5)));
    }
}
