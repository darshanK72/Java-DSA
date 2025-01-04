/**
 * Problem Statement:
 * 
 *     Given a sorted array of characters `letters` and a target character `target`, 
 *     find the smallest character in the array that is strictly greater than `target`. 
 *     If no such character exists, return the first character in the array (circular behavior).
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), the size of the array.
 *     - A sorted array `letters` of size `n`, where each element is a lowercase English letter.
 *     - A character `target` (a lowercase English letter).
 * 
 * Output:
 *     - The smallest character strictly greater than `target`. 
 *       If no such character exists, return the first character of the array.
 * 
 * Example:
 *     Input:
 *     4
 *     c d f j
 *     g
 *     Output:
 *     j
 *     
 *     Explanation:
 *     In the array `['c', 'd', 'f', 'j']`, the smallest character strictly greater than `g` 
 *     is `j`. Hence, the output is `j`.
 */

import java.util.Scanner;

public class j04SmallestCharLargerThanTarget {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        char[] arr = new char[n]; // Array to store the characters
        for (int i = 0; i < n; i++) {
            arr[i] = in.next().charAt(0); // Input: sorted characters
        }
        char target = in.next().charAt(0); // Input: target character
        System.out.println(searchChar(arr, target)); // Output: smallest character greater than target
        in.close();
    }

    /**
     * Approach 1: Binary Search to Find the Smallest Character Larger Than Target
     * 
     * Intuition:
     * - The array is sorted, which allows us to efficiently apply binary search.
     * - We need to find the smallest character that is strictly greater than the target.
     * - The search is performed by checking the middle character of the array:
     *     - If the middle character is less than or equal to the target, we search the right half.
     *     - If the middle character is greater than the target, we search the left half.
     * - This process continues until we find the correct character or determine that no such character exists.
     * - The final result will be the character found by the search or the first character in the array 
     *   if no larger character is found.
     * 
     * Time Complexity:
     * - O(log n), as binary search halves the search space with each iteration.
     * 
     * Space Complexity:
     * - O(1), as we only use a few variables to perform the binary search.
     * 
     * @param letters The input sorted array of characters.
     * @param target The target character to compare against.
     * @return The smallest character strictly greater than the target, or the first character if no such character exists.
     */
    public static char searchChar(char[] letters, char target) {
        int s = 0; // Start index
        int e = letters.length - 1; // End index
        while (s <= e) {
            int mid = (s + e) / 2; // Calculate middle index
            if (letters[mid] <= target) {
                s = mid + 1; // Search in the right half if letters[mid] <= target
            } else {
                e = mid - 1; // Search in the left half if letters[mid] > target
            }
        }
        // The result will be the index `s`, which is either the smallest character
        // greater than target
        // or wrapped around to the first character if no such character exists.
        return letters[s % letters.length];
    }
}
