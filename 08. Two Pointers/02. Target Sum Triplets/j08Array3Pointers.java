/**
 * Problem Statement:
 * 
 *     Given three sorted arrays A, B, and C, find the indices i, j, and k such that:
 *     max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])) is minimized.
 *     Return the minimum max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])).
 * 
 * Input:
 *     - Three sorted arrays A, B, and C. All elements of the arrays are positive integers.
 * 
 * Output:
 *     - An integer representing the minimized maximum absolute difference.
 * 
 * Example:
 *     Input:
 *     A = [1, 4, 10]
 *     B = [2, 15, 20]
 *     C = [10, 12]
 * 
 *     Output:
 *     4
 * 
 *     Explanation:
 *     The minimized maximum absolute difference is achieved when i = 1, j = 0, and k = 0.
 *     The differences are:
 *     - abs(4 - 2) = 2
 *     - abs(2 - 10) = 8
 *     - abs(10 - 4) = 6
 *     The maximum of these differences is 6, and the minimum possible is 4.
 */

import java.util.Arrays;
import java.util.List;

public class j08Array3Pointers {

    public static void main(String[] args) {
        // Initialize the three sorted arrays
        List<Integer> A = Arrays.asList(1, 4, 10);
        List<Integer> B = Arrays.asList(2, 15, 20);
        List<Integer> C = Arrays.asList(10, 12);

        // Call the minimize method and print the result
        int result = minimize(A, B, C);
        System.out.println("The minimized maximum difference is: " + result);
    }

    /**
     * Approach: Three Pointer Technique
     * 
     * Intuition:
     * - The key observation is that by moving the pointer pointing to the smallest value,
     *   we can potentially minimize the maximum absolute difference. This is because,
     *   since the arrays are sorted, the smallest value needs to catch up with the others
     *   to reduce the distance between the three values.
     * 
     * Time Complexity:
     * - O(n + m + p), where n, m, and p are the lengths of the arrays A, B, and C respectively.
     *   In each iteration, one of the pointers is moved forward, so we iterate over each array at most once.
     * 
     * Space Complexity:
     * - O(1), as we are only using a constant amount of extra space.
     * 
     * @param A The sorted array A.
     * @param B The sorted array B.
     * @param C The sorted array C.
     * @return The minimized maximum absolute difference.
     */
    public static int minimize(final List<Integer> A, final List<Integer> B, final List<Integer> C) {
        int i = 0, j = 0, k = 0;
        int ans = Integer.MAX_VALUE;

        // Traverse through the arrays using three pointers
        while (i < A.size() && j < B.size() && k < C.size()) {
            // Get the current values from each array
            int a = A.get(i);
            int b = B.get(j);
            int c = C.get(k);

            // Calculate the current maximum of absolute differences
            int currentMax = Math.max(Math.abs(a - b), Math.max(Math.abs(b - c), Math.abs(c - a)));

            // Update the answer with the minimum of the current maximum difference
            ans = Math.min(ans, currentMax);

            // Move the pointer that has the smallest value to try and minimize the max
            // difference
            if (a <= b && a <= c) {
                i++;
            } else if (b <= a && b <= c) {
                j++;
            } else {
                k++;
            }
        }

        return ans;
    }
}
