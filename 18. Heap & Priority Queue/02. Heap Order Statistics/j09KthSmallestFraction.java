/*-
 * LeetCode 786: K-th Smallest Prime Fraction
 * 
 * Problem Statement:
 *     You are given a sorted integer array arr containing 1 and prime numbers,
 *     where all the integers of arr are unique. You are also given an integer k.
 *     For every i and j where 0 <= i < j < arr.length, we consider the fraction
 *     arr[i] / arr[j]. Return the kth smallest fraction considered.
 * 
 * Input:
 *     - arr[]: Sorted array of unique integers (1 and primes)
 *     - k: Position of smallest fraction to find (1-based)
 * 
 * Output:
 *     - int[]: Array containing [numerator, denominator] of kth smallest fraction
 * 
 * Example:
 *     Input: arr = [1,2,3,5], k = 3
 *     Output: [2,5]
 * 
 *     Explanation:
 *     The fractions to be considered in sorted order are:
 *     1/5, 1/3, 2/5, 1/2, 3/5, 2/3
 *     The third fraction is 2/5.
 */

import java.util.PriorityQueue;
import java.util.Arrays;

public class j09KthSmallestFraction {

    /**
     * Approach 1: Max Heap with All Fractions
     * 
     * Intuition:
     * - Generate all possible fractions from the array
     * - Use a min heap to maintain fractions in sorted order
     * - Extract kth smallest fraction after processing all possibilities
     * 
     * Explanation:
     * 1. Create a min heap with custom comparator for fractions
     * 2. Generate all possible fractions (i < j)
     * 3. Add each fraction to the heap
     * 4. Extract k-1 fractions to reach kth smallest
     * 5. Return the kth fraction
     * 
     * Time Complexity: O(n² log k) where n is array length
     *                  - O(n²) to generate all fractions
     *                  - O(log k) for each heap operation
     * Space Complexity: O(k) for storing k fractions in heap
     * 
     * @param arr    Input sorted array of primes and 1
     * @param k      Position of smallest fraction to find (1-based)
     * @return       Array containing [numerator, denominator]
     */
    public static int[] kthSmallestPrimeFractionUsingMaxHeap(int[] arr, int k) {
        // Initialize min heap with custom comparator for fractions
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(a[0] / a[1], b[0] / b[1]));

        // Generate all possible fractions
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                pq.add(new double[] {
                        (double) (arr[i]),
                        (double) (arr[j])
                });
            }
        }

        // Extract kth smallest fraction
        int[] out = new int[2];
        for (int i = 0; i < k - 1; i++) {
            pq.poll();
        }

        double[] pair = pq.peek();
        out[0] = (int) pair[0];
        out[1] = (int) pair[1];
        pq.remove();
        return out;
    }

    /**
     * Helper Class: Result
     * 
     * Stores fraction information including:
     * - fraction value: The actual fraction value (numerator/denominator)
     * - numIndex: Index of numerator in original array
     * - denoIndex: Index of denominator in original array
     * 
     * Used to track fraction information in the min heap approach
     */
    static class Result {
        double fraction;
        int numIndex;
        int denoIndex;

        Result(double f, int n, int d) {
            this.fraction = f;
            this.numIndex = n;
            this.denoIndex = d;
        }
    }

    /**
     * Approach 2: Min Heap with Fraction Tracking
     * 
     * Intuition:
     * - Start with smallest possible fractions (numerator with largest denominator)
     * - For each fraction, consider next smaller denominator
     * - Use min heap to maintain k smallest fractions
     * - Track numerator and denominator indices to generate next fractions
     * 
     * Explanation:
     * 1. Initialize min heap with smallest possible fractions
     *    (each numerator paired with largest denominator)
     * 2. For k-1 iterations:
     *    - Extract smallest fraction
     *    - Add next possible fraction with smaller denominator
     * 3. Return kth smallest fraction
     * 
     * Time Complexity: O(k log n) where n is array length
     *                  - O(log n) for each heap operation
     *                  - O(k) fractions to process
     * Space Complexity: O(n) for storing fractions in heap
     * 
     * @param arr    Input sorted array of primes and 1
     * @param k      Position of smallest fraction to find (1-based)
     * @return       Array containing [numerator, denominator]
     */
    public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
        // Initialize min heap with custom comparator for fractions
        PriorityQueue<Result> pq = new PriorityQueue<>(
                (a, b) -> Double.compare(a.fraction, b.fraction));

        // Initialize heap with smallest possible fractions
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            pq.add(new Result((double) arr[i] / arr[n - 1], i, n - 1));
        }

        // Extract kth smallest fraction
        for (int i = 0; i < k - 1; i++) {
            if (pq.isEmpty())
                break;
            Result res = pq.poll();
            if (res.denoIndex > res.numIndex + 1) {
                pq.add(new Result(
                        (double) arr[res.numIndex] / arr[res.denoIndex - 1],
                        res.numIndex,
                        res.denoIndex - 1));
            }
        }

        if (pq.isEmpty()) {
            return new int[] { 0, 0 };
        }

        Result ans = pq.peek();
        return new int[] {
                arr[ans.numIndex],
                arr[ans.denoIndex]
        };
    }

    /**
     * Approach 3: Binary Search
     * 
     * Intuition:
     * - Use binary search on possible fraction values between 0 and 1
     * - For each mid value, count fractions less than mid
     * - Adjust search range based on count comparison with k
     * - Track the largest fraction less than mid
     * 
     * Explanation:
     * 1. Initialize search range from 0 to 1
     * 2. While search range is significant:
     *    - Calculate mid value
     *    - Count fractions less than mid
     *    - Update answer if count >= k
     *    - Adjust search range based on count
     * 3. Return the tracked fraction
     * 
     * Time Complexity: O(n log M) where n is array length and M is max fraction value
     *                  - O(log M) for binary search
     *                  - O(n) for counting fractions in each iteration
     * Space Complexity: O(1) as we only store a few variables
     * 
     * @param arr    Input sorted array of primes and 1
     * @param k      Position of smallest fraction to find (1-based)
     * @return       Array containing [numerator, denominator]
     */
    public static int[] kthSmallestPrimeFractionBinarySearch(int[] arr, int k) {
        double s = 0;
        double e = 1;
        int[] ans = new int[2];
        
        // Binary search on fraction values
        while (e - s > 1e-9) {
            double mid = (s + e) / 2;
            int[] res = countFractionsLessThanMid(arr, mid);
            if (res[2] < k) {
                s = mid;
            } else {
                ans[0] = res[0];
                ans[1] = res[1];
                e = mid;
            }
        }
        return ans;
    }

    /**
     * Helper Method: Count Fractions Less Than Mid
     * 
     * Intuition:
     * - Count how many fractions are less than the given mid value
     * - Use two-pointer technique to efficiently count fractions
     * - Track the largest fraction less than mid
     * 
     * Explanation:
     * 1. Initialize pointers and tracking variables
     * 2. For each denominator (j):
     *    - Move numerator pointer (i) until fraction > mid
     *    - Add count of valid fractions
     *    - Update largest fraction if current is larger
     * 3. Return [numerator, denominator, count]
     * 
     * Time Complexity: O(n) where n is array length
     *                  - Single pass through array with two pointers
     * Space Complexity: O(1) for storing variables
     * 
     * @param arr    Input sorted array of primes and 1
     * @param mid    Current fraction value to compare against
     * @return       Array containing [numerator, denominator, count]
     */
    private static int[] countFractionsLessThanMid(int[] arr, double mid) {
        int count = 0;
        int i = 0;
        int num = arr[0];
        int den = arr[arr.length - 1];
        
        // Count fractions less than mid
        for (int j = 1; j < arr.length; j++) {
            while (i < j && arr[i] <= arr[j] * mid) {
                i++;
            }
            count += i;
            if (i > 0 && arr[i - 1] * den > num * arr[j]) {
                num = arr[i - 1];
                den = arr[j];
            }
        }
        return new int[] { num, den, count };
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] arr1 = {1, 2, 3, 5};
        System.out.println("Input: arr = [1,2,3,5], k = 3");
        System.out.println("Max Heap Output: " + Arrays.toString(kthSmallestPrimeFractionUsingMaxHeap(arr1, 3)));
        System.out.println("Min Heap Output: " + Arrays.toString(kthSmallestPrimeFraction(arr1, 3)));
        System.out.println("Binary Search Output: " + Arrays.toString(kthSmallestPrimeFractionBinarySearch(arr1, 3)));

        // Test Case 2: k equals 1
        System.out.println("\nK equals 1 Test:");
        int[] arr2 = {1, 2, 3, 5};
        System.out.println("Input: arr = [1,2,3,5], k = 1");
        System.out.println("Max Heap Output: " + Arrays.toString(kthSmallestPrimeFractionUsingMaxHeap(arr2, 1)));
        System.out.println("Min Heap Output: " + Arrays.toString(kthSmallestPrimeFraction(arr2, 1)));
        System.out.println("Binary Search Output: " + Arrays.toString(kthSmallestPrimeFractionBinarySearch(arr2, 1)));

        // Test Case 3: k equals total possible fractions
        System.out.println("\nK equals total fractions Test:");
        int[] arr3 = {1, 2, 3};
        System.out.println("Input: arr = [1,2,3], k = 3");
        System.out.println("Max Heap Output: " + Arrays.toString(kthSmallestPrimeFractionUsingMaxHeap(arr3, 3)));
        System.out.println("Min Heap Output: " + Arrays.toString(kthSmallestPrimeFraction(arr3, 3)));
        System.out.println("Binary Search Output: " + Arrays.toString(kthSmallestPrimeFractionBinarySearch(arr3, 3)));

        // Test Case 4: Large numbers
        System.out.println("\nLarge Numbers Test:");
        int[] arr4 = {1, 7, 11, 13, 17};
        System.out.println("Input: arr = [1,7,11,13,17], k = 4");
        System.out.println("Max Heap Output: " + Arrays.toString(kthSmallestPrimeFractionUsingMaxHeap(arr4, 4)));
        System.out.println("Min Heap Output: " + Arrays.toString(kthSmallestPrimeFraction(arr4, 4)));
        System.out.println("Binary Search Output: " + Arrays.toString(kthSmallestPrimeFractionBinarySearch(arr4, 4)));
    }
}
