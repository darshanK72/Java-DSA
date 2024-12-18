/**
 * Problem Statement:
 * 
 *     Given an array of integers and a positive integer `k`, rotate the array to the left by `k` positions. 
 *     A left rotation operation shifts each element of the array to its left by one position, and the first element moves 
 *     to the end.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `k` (1 <= k <= 10^5), representing the number of positions to rotate the array.
 * 
 * Output:
 *     - The array after performing `k` left rotations.
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 3 4 5
 *     2
 *     Output:
 *     [3, 4, 5, 1, 2]
 * 
 *     Explanation:
 *     - After 2 left rotations:
 *         - The first rotation shifts the array to [2, 3, 4, 5, 1].
 *         - The second rotation shifts it further to [3, 4, 5, 1, 2].
 */

import java.util.Scanner;
import java.util.Arrays;

public class j18LeftRotate {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: array elements
        }

        int k = in.nextInt(); // Input: number of positions to rotate

        // Print the original array
        System.out.println("Original Array: " + Arrays.toString(arr));

        // Call the most efficient solution
        leftRotateMoreEfficient(arr, k);

        // Print the rotated array
        System.out.println("Rotated Array: " + Arrays.toString(arr));

        in.close();
    }

    /**
     * Approach 1: Brute Force Rotation (Repeated Rotation)
     * 
     * Intuition:
     * - To perform left rotation, we repeatedly shift all elements to the left by one position, and the first element 
     *   moves to the last position.
     * - For `k` rotations, this process is repeated `k` times.
     * 
     * Time Complexity:
     * - O(n * k), where `n` is the size of the array and `k` is the number of rotations.
     * - Each rotation requires shifting `n` elements.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used.
     * 
     * @param arr The input array of integers.
     * @param k   The number of positions to rotate the array.
     */
    public static void leftRotate(int[] arr, int k) {
        while (k > 0) {
            int i;
            int temp = arr[0]; // Store the first element
            for (i = 1; i < arr.length; i++) {
                arr[i - 1] = arr[i]; // Shift elements to the left
            }
            arr[arr.length - 1] = temp; // Move the first element to the last position
            k--;
        }
    }

    /**
     * Approach 2: Using Extra Space (Efficient Solution)
     * 
     * Intuition:
     * - Instead of repeatedly shifting elements, we can use an auxiliary array to temporarily store the first `k` elements.
     * - Move the remaining elements of the original array to the front and then append the temporary array to the back.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. Each element is processed once.
     * 
     * Space Complexity:
     * - O(k), where `k` is the number of positions to rotate. Extra space is used to store the first `k` elements.
     * 
     * @param arr The input array of integers.
     * @param k   The number of positions to rotate the array.
     */
    public static void leftRotateEfficient(int[] arr, int k) {
        k = k % arr.length; // Handle cases where k > n
        int[] temp = new int[k];

        // Store the first k elements in a temporary array
        for (int i = 0; i < k; i++) {
            temp[i] = arr[i];
        }

        // Shift the remaining elements to the left
        for (int i = k; i < arr.length; i++) {
            arr[i - k] = arr[i];
        }

        // Append the temporary array to the end
        for (int i = 0; i < k; i++) {
            arr[arr.length - k + i] = temp[i];
        }
    }

    /**
     * Approach 3: Reversal Algorithm (Most Efficient)
     * 
     * Intuition:
     * - This approach involves reversing parts of the array to achieve the desired rotation.
     * - Steps:
     *   1. Reverse the first `k` elements.
     *   2. Reverse the remaining `n-k` elements.
     *   3. Reverse the entire array.
     * - The reversals rearrange the elements efficiently without extra space.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. Each element is part of a reverse operation once.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used.
     * 
     * @param arr The input array of integers.
     * @param k   The number of positions to rotate the array.
     */
    public static void leftRotateMoreEfficient(int[] arr, int k) {
        k = k % arr.length; // Handle cases where k > n
        reverse(arr, 0, k - 1); // Reverse the first k elements
        reverse(arr, k, arr.length - 1); // Reverse the remaining n-k elements
        reverse(arr, 0, arr.length - 1); // Reverse the entire array
    }

    /**
     * Utility Method: Reverse a portion of the array
     * 
     * @param arr The input array.
     * @param s   The start index.
     * @param e   The end index.
     */
    public static void reverse(int[] arr, int s, int e) {
        while (s < e) {
            int temp = arr[s];
            arr[s] = arr[e];
            arr[e] = temp;
            s++;
            e--;
        }
    }
}
