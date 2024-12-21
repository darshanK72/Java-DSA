/**
 * Problem Statement:
 *     Given an integer array `nums`, find the first repeated element in the array. The element should be returned as the value of its first occurrence.
 *     If no repeated element exists, return -1.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr[]` of integers of size `n`, where each element satisfies (-10^9 <= arr[i] <= 10^9).
 * 
 * Output:
 *     - An integer representing the value of the first repeated element.
 *     - Return `-1` if there is no repeated element.
 * 
 * Example:
 *     Input:
 *     5
 *     2 3 5 3 6
 *     Output:
 *     3
 * 
 *     Input:
 *     4
 *     1 2 3 4
 *     Output:
 *     -1
 * 
 *     Explanation:
 *     In the first example, `3` is the first repeated element.
 *     In the second example, there are no repeated elements, so the output is `-1`.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j07FirstRepeated {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: array elements
        }

        System.out.println(firstRepeated(arr)); // Output the result
        in.close();
    }

    /**
     * Approach: Using a HashMap to track the indices of the elements
     * 
     * Intuition:
     * - As we iterate through the array, we maintain a HashMap where the key is the element of the array, and the value is the index of its first occurrence.
     * - If an element is encountered that is already present in the HashMap, we return its index, as it's the first repeated element.
     * - If no repeated element is found by the end of the loop, we return `-1`.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. We perform constant time operations for each element in the array.
     * 
     * Space Complexity:
     * - O(n), as we store the elements in the HashMap.
     * 
     * @param nums The input array of integers.
     * @return The value of the first repeated element, or `-1` if no repeated element exists.
     */
    public static int firstRepeated(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>(); // Create a HashMap to store the first occurrence index of
                                                         // elements
        int out = -1; // Variable to track the first repeated element (initialize to -1)

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && (map.get(nums[i]) < out || out == -1)) {
                // If element already exists in the map, and it's the first repetition, update
                // `out`
                out = map.get(nums[i]);
            } else {
                // Otherwise, store the index of the element (store i+1 as the index to avoid
                // confusion with unvisited elements)
                map.put(nums[i], i + 1);
            }
        }

        return out; // Return the first repeated element index or -1 if no repetition found
    }
}
