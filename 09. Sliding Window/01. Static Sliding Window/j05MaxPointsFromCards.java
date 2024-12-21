/**
 * Problem Statement:
 *
 *     You are given an integer array `cardPoints[]` of length `n` and an integer `k`. You need to find the maximum points you can obtain by selecting exactly `k` cards, either from the beginning or the end of the array.
 *
 * Input:
 *     - An integer `n` representing the size of the array `cardPoints[]`.
 *     - An array `cardPoints[]` of size `n` containing the points of the cards.
 *     - An integer `k` representing the number of cards to pick.
 *
 * Output:
 *     - An integer representing the maximum points that can be obtained by picking `k` cards from the array.
 *
 * Example:
 *     Input:
 *     4
 *     1 2 3 4
 *     2
 *     Output:
 *     7
 *
 *     Explanation:
 *     The maximum sum is obtained by picking the last two cards: [3, 4].
 */

import java.util.Scanner;

public class j05MaxPointsFromCards {
    public static void main(String args[]) {
        // Reading the input values
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the array
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt(); // Elements of the array
        }
        int k = in.nextInt(); // Number of cards to pick

        // Call the functions to calculate the maximum score using two different
        // approaches
        System.out.println(maxScore1(nums, k));
        System.out.println(maxScore2(nums, k));
        in.close();
    }

    /**
     * Solution 1 (Using Sliding Window Technique):
     * Approach:
     *     1. First, calculate the sum of the first `k` elements.
     *     2. Then, slide a window from the right side of the array to replace one element from the start with an element from the end.
     *     3. Update the sum and track the maximum score.
     *     4. The maximum score will be the highest sum obtained during the sliding process.
     *
     * Time Complexity: O(k), as we calculate the initial sum and slide the window at most `k` times.
     * Space Complexity: O(1), as we only use a few variables to store intermediate results.
     *
     * @param cardPoints The input array of card points.
     * @param k The number of cards to pick.
     * @return The maximum points that can be obtained by selecting `k` cards.
     */
    public static int maxScore1(int[] cardPoints, int k) {
        int sum = 0;
        int maxSum = 0;

        // Initial sum of the first `k` cards
        for (int i = 0; i < k; i++)
            sum += cardPoints[i];
        maxSum = sum;

        // Sliding window technique to maximize the sum by replacing cards
        int i = k - 1;
        int j = cardPoints.length - 1;
        while (i >= 0) {
            sum -= cardPoints[i]; // Remove the leftmost card
            sum += cardPoints[j]; // Add the rightmost card
            i--;
            j--;
            maxSum = Math.max(maxSum, sum); // Track the maximum sum
        }

        return maxSum;
    }

    /**
     * Solution 2 (Using Sliding Window to Minimize the Sum of Remaining Cards):
     * Approach:
     *     1. We want to maximize the sum of `k` cards, so we minimize the sum of the `n-k` remaining cards.
     *     2. We calculate the sum of the first `n-k` cards and use a sliding window to find the smallest sum of any contiguous subarray of size `n-k`.
     *     3. The maximum score is the total sum of the array minus the smallest sum of the remaining cards.
     *
     * Time Complexity: O(n), where `n` is the size of the array, because we iterate through the array once to calculate the total sum and once more to find the minimum sum of the `n-k` cards.
     * Space Complexity: O(1), as we only use a few variables for calculation.
     *
     * @param cardPoints The input array of card points.
     * @param k The number of cards to pick.
     * @return The maximum points that can be obtained by selecting `k` cards.
     */
    public static int maxScore2(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int s = n - k; // Size of the remaining cards after selecting `k` cards
        int totalSum = 0;
        int minSum = 0;
        int currSum = 0;

        // Calculate the total sum of the array and the sum of the first `n-k` cards
        for (int i = 0; i < n; i++) {
            totalSum += cardPoints[i];
            if (i < s) {
                currSum = totalSum; // Sum of the first `n-k` cards
            }
        }

        minSum = currSum;
        // Use sliding window to find the minimum sum of the remaining `n-k` cards
        for (int i = s; i < n; i++) {
            currSum += cardPoints[i] - cardPoints[i - s]; // Update the sum by sliding the window
            minSum = Math.min(minSum, currSum); // Track the minimum sum
        }

        // The maximum score is the total sum minus the minimum sum of the remaining
        // `n-k` cards
        return totalSum - minSum;
    }
}
