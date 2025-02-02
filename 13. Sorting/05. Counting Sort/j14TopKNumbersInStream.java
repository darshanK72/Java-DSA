/**
 * Problem Statement:
 * 
 *     Given an array `arr` of size `N` and an integer `K`, your task is to keep at most the top `K` most frequent numbers from the array as you iterate over it.
 *     After each iteration, determine the top `K` most frequent numbers and store them in an array. The numbers should be stored in decreasing order of frequency. 
 *     If two numbers have the same frequency, the smaller number should appear first in the array. If there are fewer than `K` distinct numbers, store all distinct numbers.
 * 
 * Input:
 *     - An integer `N` (1 <= N <= 10^4), representing the size of the array.
 *     - An array `arr` of size `N` where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `K` (1 <= K <= N) representing the number of most frequent numbers to return.
 * 
 * Output:
 *     - An array of arrays, where each array represents the top `K` most frequent numbers after each iteration.
 * 
 * Example:
 *     Input:
 *     arr = [1, 1, 1, 2, 2, 3], N = 6, K = 2
 *     Output:
 *     [
 *         [1],
 *         [1],
 *         [1],
 *         [1, 2],
 *         [1, 2],
 *         [1, 2]
 *     ]
 * 
 *     Explanation:
 *     - After the first iteration, the array contains only `1`, as it is the most frequent number.
 *     - After the second and third iterations, `1` is still the most frequent number.
 *     - After the fourth and fifth iterations, both `1` and `2` are in the top 2 most frequent
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class j14TopKNumbersInStream {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Input: size of the array
        int K = in.nextInt(); // Input: top K most frequent numbers
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the solution
        ArrayList<ArrayList<Integer>> result = kTop(arr, N, K);

        // Print the result
        for (ArrayList<Integer> list : result) {
            System.out.println(list);
        }

        in.close();
    }

    /**
     * Approach:
     * 
     * Intuition:
     * - We iterate over the array and maintain a HashMap to store the frequency of each element.
     * - After each iteration, we sort the elements based on their frequencies in descending order.
     * - If frequencies are the same, we place the smaller element first.
     * - We then trim the list to ensure only the top `K` most frequent elements are stored.
     * - The result is added to the output after each iteration.
     * 
     * Time Complexity:
     * - O(N * M log M), where N is the number of elements in the array and M is the number of distinct elements.
     *     Sorting the distinct elements at each iteration takes O(M log M), and there are N iterations.
     * 
     * Space Complexity:
     * - O(M), where M is the number of distinct elements in the array. This is used for storing frequencies in the map.
     * 
     * @param arr The input array of integers.
     * @param N The size of the array.
     * @param K The number of most frequent numbers to return.
     * @return An array of arrays, where each array contains the top `K` most frequent elements after each iteration.
     */
    public static ArrayList<ArrayList<Integer>> kTop(int[] arr, int N, int K) {
        ArrayList<ArrayList<Integer>> out = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            // Update frequency count for current number
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);

            // Prepare a list of keys (numbers) to sort by frequency
            ArrayList<Integer> list = new ArrayList<>(map.keySet());

            // Sorting first by frequency, then by value if frequencies are equal
            Collections.sort(list, (a, b) -> {
                if (map.get(a) == map.get(b))
                    return a - b; // Smaller number first if frequencies are equal
                else
                    return map.get(b) - map.get(a); // Higher frequency first
            });

            // Trim the list to the top K most frequent elements
            if (list.size() > K) {
                while (list.size() > K)
                    list.remove(list.size() - 1);
            }

            // Add the current list to the output
            out.add(list);
        }

        return out;
    }
}
