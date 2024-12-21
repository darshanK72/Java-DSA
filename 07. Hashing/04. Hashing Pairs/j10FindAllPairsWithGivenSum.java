/**
 * Problem Statement:
 * 
 *     Given two arrays `arr1` and `arr2`, and an integer `x`, return all pairs `(a, b)` such that:
 * 
 *         - `a` is from `arr1` and `b` is from `arr2`.
 *         - `a + b = x`.
 * 
 *     The pairs should be returned in sorted order first by `a` (ascending), then by `b` (ascending).
 * 
 * Input:
 *     - An integer `n1` (1 <= n1 <= 1000), representing the size of the first array `arr1`.
 *     - An array `arr1` of size `n1` where each element satisfies (-10^9 <= arr1[i] <= 10^9).
 *     - An integer `n2` (1 <= n2 <= 1000), representing the size of the second array `arr2`.
 *     - An array `arr2` of size `n2` where each element satisfies (-10^9 <= arr2[i] <= 10^9).
 *     - An integer `x` (-10^9 <= x <= 10^9).
 * 
 * Output:
 *     - A list of pairs `(a, b)` such that `a + b = x`, sorted first by `a` and then by `b`.
 * 
 * Example:
 *     Input:
 *         4
 *         1 2 3 4
 *         4
 *         4 5 6 7
 *         8
 *     Output:
 *         (1,7)
 *         (2,6)
 *         (3,5)
 *         (4,4)
 * 
 *     Explanation:
 *         The pairs that sum up to 8 are:
 *         (1, 7), (2, 6), (3, 5), and (4, 4).
 *         They are sorted first by the first element, then by the second element.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;

class Pair {
    long first, second;

    public Pair(long first, long second) {
        this.first = first;
        this.second = second;
    }
}

public class j10FindAllPairsWithGivenSum {
    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt();
        int[] arr1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            arr1[i] = in.nextInt();
        }
        int n2 = in.nextInt();
        int[] arr2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            arr2[i] = in.nextInt();
        }
        int x = in.nextInt();

        // Output the result
        Pair[] pairs = allPairs(x, arr1, arr2);
        for (Pair pair : pairs) {
            System.out.println("(" + pair.first + "," + pair.second + ")");
        }
        in.close();
    }

    /**
     * Approach: HashSet and Sorting
     * 
     * Intuition:
     * - The idea is to find pairs from two arrays `arr1` and `arr2` that sum up to a given target `x`.
     * - First, use a HashSet to store the elements of `arr1` so that we can quickly check if the complement of an element in `arr2` exists in `arr1`.
     * - For every element in `arr2`, check if `x - arr2[i]` exists in `arr1` using the HashSet.
     * - If it does, then `(x - arr2[i], arr2[i])` forms a valid pair. 
     * - After identifying all valid pairs, sort them first by the first element of the pair, then by the second element.
     * 
     * Explanation:
     * - First, populate the HashSet with all elements of `arr1`.
     * - Then iterate through `arr2`, checking for each element if the corresponding complement (`x - arr2[i]`) exists in the HashSet.
     * - Store all such pairs in an ArrayList.
     * - Sort the pairs using a custom comparator that first compares the first element, then the second if necessary.
     * - Convert the list of pairs to an array and return it.
     * 
     * Time Complexity:
     * - O(n1 + n2 + n2 log n2), where `n1` and `n2` are the sizes of `arr1` and `arr2`, respectively.
     *   - O(n1) for inserting elements of `arr1` into the HashSet.
     *   - O(n2) for iterating through `arr2` and checking for complements.
     *   - O(n2 log n2) for sorting the pairs.
     * 
     * Space Complexity:
     * - O(n1 + n2), for storing the HashSet and the list of pairs.
     * 
     * @param x The target sum.
     * @param arr1 The first input array.
     * @param arr2 The second input array.
     * @return An array of pairs that sum up to `x`.
     */
    public static Pair[] allPairs(int x, int arr1[], int arr2[]) {
        HashSet<Integer> set = new HashSet<>();
        ArrayList<Pair> out = new ArrayList<Pair>();

        // Populate the set with elements from arr1
        for (int ele : arr1) {
            set.add(ele);
        }

        // Find pairs where arr1[i] + arr2[j] == x
        for (int ele : arr2) {
            if (set.contains(x - ele)) {
                out.add(new Pair(x - ele, ele));
            }
        }

        // Sort the pairs first by the first element, then by the second
        Collections.sort(out, new Comparator<Pair>() {
            public int compare(Pair p1, Pair p2) {
                if (p1.first == p2.first) {
                    return Long.compare(p1.second, p2.second);
                }
                return Long.compare(p1.first, p2.first);
            }
        });

        // Convert ArrayList to array and return
        Pair[] out1 = new Pair[out.size()];
        for (int i = 0; i < out.size(); i++) {
            out1[i] = out.get(i);
        }
        return out1;
    }
}
