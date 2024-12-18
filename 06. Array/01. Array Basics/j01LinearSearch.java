/**
 * Problem Statement:
 * 
 *     Given an array of integers, `arr[]`, and a target integer `k`, perform the following operations:
 *     - Find the first occurrence of `k` using linear search.
 *     - Find the first occurrence of `k` using recursion.
 *     - Find the last occurrence of `k` using linear search.
 *     - Find the last occurrence of `k` using recursion.
 *     - Find both the first and last occurrence of `k` in the array.
 *     - Find all indices where `k` appears in the array using linear search.
 *     - Find all indices where `k` appears in the array using recursion.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr[]` of size `n`, where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `k` (1 <= k <= 10^5), representing the target value to search for in the array.
 * 
 * Output:
 *     - The result for each of the operations mentioned above:
 *         1. The index of the first occurrence of `k` using linear search.
 *         2. The index of the first occurrence of `k` using recursion.
 *         3. The index of the last occurrence of `k` using linear search.
 *         4. The index of the last occurrence of `k` using recursion.
 *         5. An array of size 2, where the first element is the index of the first occurrence of `k` and the second element is the index of the last occurrence of `k`.
 *         6. A list of all indices where `k` appears using linear search.
 *         7. A list of all indices where `k` appears using recursion.
 * 
 * Example:
 *     Input:
 *     7
 *     1 2 3 4 2 5 2
 *     2
 *     Output:
 *     1
 *     1
 *     6
 *     6
 *     [1, 6]
 *     [1, 6, 7]
 *     Explanation:
 *     - The first occurrence of `2` is at index 1 using both linear and recursive search.
 *     - The last occurrence of `2` is at index 6 using both linear and recursive search.
 *     - The first and last occurrence of `2` is [1, 6].
 *     - The list of all occurrences of `2` are indices [1, 6, 7].
 * 
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class j01LinearSearch {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: target element to search for

        // Call your solution methods
        System.out.println(findFirstLinear(arr, k)); // Find first occurrence using linear search
        System.out.println(findFirstRecursive(arr, 0, k)); // Find first occurrence using recursion
        System.out.println(findLastLinear(arr, k)); // Find last occurrence using linear search
        System.out.println(findLastRecursive(arr, n - 1, k)); // Find last occurrence using recursion
        System.out.println(Arrays.toString(findFirstAndLast(arr, k))); // Find both first and last occurrences
        System.out.println(findAllLinear(arr, k)); // Find all occurrences using linear search
        System.out.println(findAllRecursive(arr, k, 0)); // Find all occurrences using recursion

        in.close();
    }

    /**
     * Approach 1: Linear Search (Iterative)
     * 
     * Intuition:
     * - Traverse the array from start to end.
     * - If the target element `k` is found, return its index.
     * - If no occurrence is found, return -1.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array.
     * 
     * Space Complexity:
     * - O(1), since we are using only a constant amount of space.
     * 
     * @param arr The input array of numbers.
     * @param k The target element to search for.
     * @return The index of the first occurrence of `k` or -1 if not found.
     */
    public static int findFirstLinear(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == k)
                return i;
        }
        return -1;
    }

    /**
     * Approach 2: Recursive Search (First Occurrence)
     * 
     * Intuition:
     * - Use recursion to search for the first occurrence of the target element `k` starting from a given index.
     * - If the end of the array is reached and `k` is not found, return -1.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array, since we may have to check all elements.
     * 
     * Space Complexity:
     * - O(n), due to recursive call stack.
     * 
     * @param arr The input array of numbers.
     * @param index The current index to check.
     * @param k The target element to search for.
     * @return The index of the first occurrence of `k` or -1 if not found.
     */
    public static int findFirstRecursive(int[] arr, int index, int k) {
        if (index == arr.length)
            return -1;
        if (arr[index] == k)
            return index;
        return findFirstRecursive(arr, index + 1, k);
    }

    /**
     * Approach 3: Linear Search (Last Occurrence)
     * 
     * Intuition:
     * - Traverse the array from end to start.
     * - If the target element `k` is found, return its index.
     * - If no occurrence is found, return -1.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array.
     * 
     * Space Complexity:
     * - O(1), since we are using only a constant amount of space.
     * 
     * @param arr The input array of numbers.
     * @param k The target element to search for.
     * @return The index of the last occurrence of `k` or -1 if not found.
     */
    public static int findLastLinear(int[] arr, int k) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == k)
                return i;
        }
        return -1;
    }

    /**
     * Approach 4: Recursive Search (Last Occurrence)
     * 
     * Intuition:
     * - Use recursion to search for the last occurrence of the target element `k` starting from a given index.
     * - If the start of the array is reached and `k` is not found, return -1.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array, since we may have to check all elements.
     * 
     * Space Complexity:
     * - O(n), due to recursive call stack.
     * 
     * @param arr The input array of numbers.
     * @param index The current index to check.
     * @param k The target element to search for.
     * @return The index of the last occurrence of `k` or -1 if not found.
     */
    public static int findLastRecursive(int[] arr, int index, int k) {
        if (index < 0)
            return -1;
        if (arr[index] == k)
            return index;
        return findFirstRecursive(arr, index - 1, k);
    }

    /**
     * Approach 5: Find First and Last Occurrence
     * 
     * Intuition:
     * - Traverse the array from start to end to find the first occurrence.
     * - Traverse again to find the last occurrence, storing both in a result array.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array.
     * 
     * Space Complexity:
     * - O(1), since only two elements are stored in the result array.
     * 
     * @param arr The input array of numbers.
     * @param k The target element to search for.
     * @return An array containing the first and last indices of `k`.
     */
    public static int[] findFirstAndLast(int[] arr, int k) {
        int[] result = { -1, -1 };
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == k) {
                if (result[0] == -1) {
                    result[0] = i;
                }
                result[1] = i;
            }
        }
        return result;
    }

    /**
     * Approach 6: Find All Occurrences (Linear Search)
     * 
     * Intuition:
     * - Traverse the array and collect all indices where `k` occurs.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array.
     * 
     * Space Complexity:
     * - O(m), where `m` is the number of occurrences of `k` in the array.
     * 
     * @param arr The input array of numbers.
     * @param k The target element to search for.
     * @return A list of all indices where `k` appears.
     */
    public static ArrayList<Integer> findAllLinear(int[] arr, int k) {
        ArrayList<Integer> out = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == k) {
                out.add(i);
            }
        }
        return out;
    }

    /**
     * Approach 7: Find All Occurrences (Recursive Search)
     * 
     * Intuition:
     * - Use recursion to find all occurrences of `k` by accumulating indices in a list.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array.
     * 
     * Space Complexity:
     * - O(m), where `m` is the number of occurrences of `k` in the array, plus the recursive call stack.
     * 
     * @param arr The input array of numbers.
     * @param k The target element to search for.
     * @param index The current index to check.
     * @return A list of all indices where `k` appears.
     */
    public static ArrayList<Integer> findAllRecursive(int[] arr, int k, int index) {
        if (index == arr.length) {
            return new ArrayList<>();
        }
        ArrayList<Integer> res = findAllRecursive(arr, k, index + 1);
        if (arr[index] == k) {
            res.add(0, index);
        }
        return res;
    }
}
