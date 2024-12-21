/**
 * Problem Statement:
 * 
 *     Given three arrays A, B, and C, the task is to find all elements 
 *     that appear in at least two out of the three arrays. The result should 
 *     contain distinct elements that are common to at least two arrays, 
 *     and it should be sorted in ascending order.
 * 
 * Input:
 *     - An integer `n1` (1 <= n1 <= 10^5), the size of the first array.
 *     - An integer `n2` (1 <= n2 <= 10^5), the size of the second array.
 *     - An integer `n3` (1 <= n3 <= 10^5), the size of the third array.
 *     - Arrays `A`, `B`, and `C` containing the elements, where each element satisfies (1 <= A[i], B[i], C[i] <= 10^5).
 * 
 * Output:
 *     - A sorted list of distinct integers that appear in at least two of the three arrays.
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 3 4 5
 *     4
 *     3 4 5 6
 *     3
 *     5 6 7
 *     
 *     Output:
 *     [5]
 * 
 *     Explanation:
 *     The element `5` is present in both arrays A, B, and C. Therefore, it appears in the result.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class j12TwoOutOfThree {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);

        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();
        ArrayList<Integer> C = new ArrayList<>();

        int n1 = in.nextInt(); // Input: size of the first array
        for (int i = 0; i < n1; i++) {
            A.add(in.nextInt()); // Input: elements of the first array
        }
        int n2 = in.nextInt(); // Input: size of the second array
        for (int i = 0; i < n2; i++) {
            B.add(in.nextInt()); // Input: elements of the second array
        }
        int n3 = in.nextInt(); // Input: size of the third array
        for (int i = 0; i < n3; i++) {
            C.add(in.nextInt()); // Input: elements of the third array
        }

        // Call the function to find common elements
        System.out.println(commonInTwoOutOfThree(A, B, C));

        in.close(); // Close the scanner
    }

    /**
     * Approach 1: Using HashMap to Count Occurrences
     * 
     * Intuition:
     * - We use a HashMap to track the occurrences of each element across the three arrays.
     * - We first add elements of each array to the HashMap, ensuring that duplicates within 
     *   each array are not counted multiple times (using a HashSet for each array).
     * - After processing all three arrays, we check if the count of each element is 2 or more, 
     *   indicating that it appears in at least two arrays.
     * - The result is then sorted in ascending order.
     * 
     * Time Complexity:
     * - O(n1 + n2 + n3), where n1, n2, and n3 are the sizes of the three arrays. 
     *   We are iterating over each element in each array once.
     *   Sorting the result takes O(k log k), where k is the number of distinct elements.
     * 
     * Space Complexity:
     * - O(n1 + n2 + n3), because we store elements from all three arrays in the HashMap and HashSet.
     * 
     * @param A The first input array.
     * @param B The second input array.
     * @param C The third input array.
     * @return A sorted list of elements that appear in at least two out of the three arrays.
     */
    public static ArrayList<Integer> commonInTwoOutOfThree(ArrayList<Integer> A, ArrayList<Integer> B,
            ArrayList<Integer> C) {
        HashMap<Integer, Integer> map = new HashMap<>(); // Initialize the HashMap to store element counts

        // Add elements from each array to the map
        addToMap(map, A);
        addToMap(map, B);
        addToMap(map, C);

        ArrayList<Integer> out = new ArrayList<Integer>(); // Initialize the result list
        // Check which elements have appeared in at least two arrays
        for (int key : map.keySet()) {
            if (map.get(key) >= 2)
                out.add(key);
        }

        Collections.sort(out); // Sort the result list in ascending order
        return out;
    }

    /**
     * Helper Method: addToMap
     * 
     * Intuition:
     * - This helper method adds elements from a given list to the HashMap, ensuring no duplicates 
     *   within the list are counted multiple times (using a HashSet for each list).
     * 
     * Time Complexity:
     * - O(n), where n is the size of the array being processed.
     * 
     * Space Complexity:
     * - O(n), because we use a HashSet to store unique elements of the array.
     * 
     * @param map The HashMap that tracks occurrences.
     * @param list The array to process.
     */
    private static void addToMap(HashMap<Integer, Integer> map, ArrayList<Integer> list) {
        Set<Integer> set = new HashSet<>(); // Use a HashSet to avoid counting duplicates within the array
        // Add each element to the map, counting its occurrences across arrays
        for (int i : list) {
            if (!set.contains(i)) {
                map.put(i, map.getOrDefault(i, 0) + 1);
                set.add(i);
            }
        }
    }
}
