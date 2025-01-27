/**
 * Problem Statement:
 *
 *     Given an unsorted array of integers, find the length of the longest consecutive 
 *     elements sequence.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr[]` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 *
 * Output:
 *     - An integer representing the length of the longest consecutive elements sequence.
 *
 * Example:
 *     Input:
 *     7
 *     100 4 200 1 3 2 1
 *     Output:
 *     4
 *
 *     Explanation:
 *     The longest consecutive elements sequence is [1, 2, 3, 4], which has length 4.
 */

import java.util.Scanner;
import java.util.Arrays;
import java.util.HashSet;

public class j14LongestConsecutiveSequence {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call different approaches
        System.out.printf("Brute Force Approach: %d\n", longestConsecutiveSequenceBruitForce(arr));
        System.out.printf("Sorting Approach: %d\n", longestConsecutiveSequenceUsingSorting(arr));
        System.out.printf("Hashing Approach: %d\n", longestConsecutiveSequenceUsingHashing(arr));

        in.close();
    }

    /**
     * Approach 1: Brute Force Approach (O(n^2))
     *
     * Intuition:
     * - This approach iterates through each element and attempts to find the next consecutive element 
     *   (n+1) by scanning the entire array for every element.
     * - The time complexity is O(n^2) because for each element, we search for its consecutive numbers.
     *
     * Time Complexity:
     * - O(n^2).
     *
     * Space Complexity:
     * - O(1) as we are not using any extra space.
     *
     * @param arr The input array of integers.
     * @return The length of the longest consecutive sequence.
     */
    public static int longestConsecutiveSequenceBruitForce(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int n = arr[i];
            int l = 1;
            while (search(arr, n + 1)) {
                n++;
                l++;
            }
            ans = Math.max(ans, l);
        }
        return ans;
    }

    /**
     * Approach 2: Sorting Approach (O(n log n))
     *
     * Intuition:
     * - First, we sort the array, which takes O(n log n) time.
     * - Then, we iterate over the array and check for consecutive numbers. We count the length of 
     *   consecutive sequences and update the result.
     * - Sorting allows us to easily detect consecutive elements in a single pass.
     *
     * Time Complexity:
     * - O(n log n) due to sorting.
     *
     * Space Complexity:
     * - O(1) as we are modifying the array in place.
     *
     * @param arr The input array of integers.
     * @return The length of the longest consecutive sequence.
     */
    public static int longestConsecutiveSequenceUsingSorting(int[] arr) {
        Arrays.sort(arr);
        int l = 0;
        int ans = 0;
        int last = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (last == arr[i] - 1) {
                l++;
                last = arr[i];
            } else {
                ans = Math.max(ans, l);
                l = 1;
                last = arr[i];
            }
        }
        return ans;
    }

    /**
     * Approach 3: Hashing Approach (O(n))
     *
     * Intuition:
     * - We can use a HashSet to store the elements of the array for constant time lookup.
     * - For each element in the set, we check if it is the start of a consecutive sequence by 
     *   ensuring that the previous number (`n-1`) is not in the set.
     * - Once we find a start of a sequence, we keep checking for consecutive elements and count 
     *   the length of the sequence.
     * - This approach is efficient because it uses O(n) time complexity and requires O(n) space for 
     *   the HashSet.
     *
     * Time Complexity:
     * - O(n), as we perform constant time operations for each element in the set.
     *
     * Space Complexity:
     * - O(n), as we store all elements in a HashSet.
     *
     * @param arr The input array of integers.
     * @return The length of the longest consecutive sequence.
     */
    public static int longestConsecutiveSequenceUsingHashing(int[] arr) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        int ans = 0;
        for (int n : set) {
            if (!set.contains(n - 1)) {
                int l = 1;
                int x = n;
                while (set.contains(x + 1)) {
                    x++;
                    l++;
                }
                ans = Math.max(ans, l);
            }
        }
        return ans;
    }

    /**
     * Helper Method: search
     *
     * Intuition:
     * - A simple linear search that checks if a given number exists in the array.
     * 
     * Time Complexity:
     * - O(n), as we iterate through the array once.
     *
     * Space Complexity:
     * - O(1), since we are using a constant amount of extra space.
     *
     * @param arr The input array of integers.
     * @param n The number to be searched.
     * @return true if the number is found, false otherwise.
     */
    public static boolean search(int[] arr, int n) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == n)
                return true;
        }
        return false;
    }
}
