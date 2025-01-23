/**
 * Problem Statement:
 * 
 *     Given an array `quantities` where `quantities[i]` represents the number of
 *     products in the i-th warehouse, and an integer `n` representing the number
 *     of stores, find the minimized maximum number of products that can be
 *     distributed to any store.
 * 
 * Input:
 *     - An integer `k` (1 <= k <= 10^5), representing the size of the array.
 *     - An array `quantities` of size `k` where each element is a positive integer.
 *     - An integer `n` (1 <= n <= 10^9), representing the number of stores.
 * 
 * Output:
 *     - An integer representing the minimized maximum number of products that can
 *       be distributed to any store.
 * 
 * Example:
 *     Input:
 *     k = 5
 *     quantities = [5, 8, 6, 4, 7]
 *     n = 3
 *     Output:
 *     5
 * 
 *     Explanation:
 *     The minimized maximum number of products that can be distributed to any store
 *     is 5.
 */
import java.util.Scanner;

public class j13MinimizeMaxProdDistributionToStore {

    public static void main(String args[]) {
        // Input reading
        Scanner in = new Scanner(System.in);
        int k = in.nextInt(); // Number of elements in the array
        int[] quantities = new int[k];

        for (int i = 0; i < k; i++) {
            quantities[i] = in.nextInt();
        }
        int n = in.nextInt(); // Number of stores

        // Output the result of the approach
        System.out.printf("Minimized Maximum products: %d\n", minimizedMaximum(n, quantities));
        // Closing the input scanner
        in.close();
    }

    /**
     * Approach: Binary Search on Answer
     * 
     * Intuition:
     * - The problem can be solved using binary search on the answer. The minimized
     *   maximum number of products that can be distributed to any store must be at
     *   least 1, and at most the maximum number of products in any warehouse.
     * - We perform binary search within this range to find the minimized maximum
     *   number of products that can be distributed to any store.
     * 
     * Time Complexity:
     * - O(k log(max(quantities))). This is because we perform binary search on the
     *   range [1, max(quantities)], and for each mid value, we iterate through the
     *   array to calculate the number of stores needed.
     * 
     * Space Complexity:
     * - O(1). We only use a few extra variables for the binary search and the
     *   feasibility check.
     * 
     * @param n The number of stores.
     * @param quantities The input array of product quantities.
     * @return The minimized maximum number of products that can be distributed to
     *         any store.
     */
    public static int minimizedMaximum(int n, int[] quantities) {
        int s = 1;
        int e = Integer.MIN_VALUE;
        for (int i = 0; i < quantities.length; i++) {
            e = Math.max(e, quantities[i]);
        }
        int ans = -1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (isPossible(n, quantities, mid)) {
                ans = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return ans;
    }

    /**
     * Helper method to check if it is possible to distribute the given number of
     * products to the given number of stores such that the maximum number of
     * products any store receives is less than or equal to the given value.
     * 
     * @param n The number of stores.
     * @param quantities The input array of product quantities.
     * @param maxProducts The maximum number of products to check feasibility.
     * @return True if it is possible to distribute the given number of products to
     *         the given number of stores such that the maximum number of products
     *         any store receives is less than or equal to the given value,
     *         otherwise false.
     */
    public static boolean isPossible(int n, int[] quantities, int maxProducts) {
        int storesNeeded = 0;
        for (int i = 0; i < quantities.length; i++) {
            storesNeeded += (quantities[i] + maxProducts - 1) / maxProducts;
        }
        return storesNeeded <= n;
    }
}