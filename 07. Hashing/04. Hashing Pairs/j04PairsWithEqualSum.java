/**
 * Problem Statement:
 * 
 *     Given an array of integers, find all pairs of elements whose sum is the same. 
 *     Group these pairs based on their sum and output the pairs for each unique sum.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 500), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (-10^3 <= nums[i] <= 10^3).
 * 
 * Output:
 *     - For each unique sum, output the pairs of integers that add up to that sum.
 * 
 * Example:
 *     Input:
 *     5
 *     1 3 2 4 1
 *     Output:
 *     Sum = 4 : Pairs = (1,3)(3,1)
 *     Sum = 5 : Pairs = (1,4)
 *     Sum = 6 : Pairs = (2,4)
 * 
 *     Explanation:
 *     - The pair (1,3) and (3,1) both have a sum of 4.
 *     - The pair (1,4) has a sum of 5.
 *     - The pair (2,4) has a sum of 6.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class j04PairsWithEqualSum {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the solution
        pairsWithEqualSum(arr);

        in.close();
    }

    /**
     * Approach: Brute Force with HashMap (O(N^2))
     * 
     * Intuition:
     * - We iterate through all pairs of elements in the array and calculate their sum. For each sum, 
     *   we store the corresponding pair of integers in a HashMap where the key is the sum, and the value 
     *   is a list of all pairs that have that sum.
     * - The HashMap helps in grouping pairs with the same sum together efficiently.
     * 
     * Explanation:
     * - For every pair `(i, j)` where `i < j`, calculate `nums[i] + nums[j]` and use it as the key in the HashMap.
     * - If the sum already exists as a key, append the pair `(nums[i], nums[j])` to the list associated with that sum.
     * - Otherwise, create a new list for this sum and add the pair.
     * - Finally, iterate through the HashMap and print the pairs grouped by their sums.
     * 
     * Time Complexity:
     * - O(N^2), as we evaluate all possible pairs of elements in the array.
     * 
     * Space Complexity:
     * - O(N^2), as we store all the pairs in the HashMap. In the worst case, the number of pairs can be (N*(N-1))/2.
     * 
     * @param nums The input array of integers.
     */
    public static void pairsWithEqualSum(int[] nums) {
        // HashMap to store sums as keys and lists of pairs as values
        HashMap<Integer, ArrayList<int[]>> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int s = nums[i] + nums[j]; // Calculate the sum
                if (map.containsKey(s)) {
                    // Add the current pair to the list for this sum
                    map.get(s).add(new int[] { nums[i], nums[j] });
                } else {
                    // Create a new list for this sum and add the pair
                    ArrayList<int[]> list = new ArrayList<>();
                    list.add(new int[] { nums[i], nums[j] });
                    map.put(s, list);
                }
            }
        }

        // Print the pairs grouped by their sums
        for (int key : map.keySet()) {
            System.out.print("Sum = " + key + " : Pairs = ");
            for (int[] pair : map.get(key)) {
                System.out.print("(" + pair[0] + "," + pair[1] + ")");
            }
            System.out.println();
        }
    }
}
