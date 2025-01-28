/**
 * Problem Statement:
 * 
 *     Given an integer array `nums` sorted in non-decreasing order, return an array of the squares of each 
 *     number sorted in non-decreasing order.
 * 
 * Input:
 *     - An integer array `nums` of size `n` where each element satisfies (−10^4 ≤ nums[i] ≤ 10^4).
 * 
 * Output:
 *     - An integer array containing the squares of each number in `nums`, sorted in non-decreasing order.
 * 
 * Example:
 *     Input:
 *     nums = [-4, -1, 0, 3, 10]
 * 
 *     Output:
 *     [0, 1, 9, 16, 100]
 * 
 *     Explanation:
 *     The squares of the numbers in the array are [16, 1, 0, 9, 100], and when sorted in non-decreasing 
 *     order, the result is [0, 1, 9, 16, 100].
 */

public class j11SqauresOfSortedArray {

    public static void main(String[] args) {
        // Create an array of integers (Example input)
        int[] nums = { -4, -1, 0, 3, 10 };

        // Calling the sortedSquares function to get the output
        int[] result = sortedSquares(nums);

        // Displaying the result array
        System.out.print("Squared and sorted array: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i != result.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /**
     * Approach 1: Two Pointer Approach
     * 
     * Intuition:
     * - We utilize two pointers, one at the beginning (`i = 0`) and one at the end (`j = n - 1`) of the sorted array.
     * - By comparing the absolute values of elements at these two pointers, we select the larger square and place it at 
     *   the end of the result array.
     * - This approach ensures that we place the largest squares first, in a non-decreasing order, while avoiding the need 
     *   for an additional sort step.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of elements in the input array. We traverse the array once to form the result.
     * 
     * Space Complexity:
     * - O(n), where `n` is the number of elements in the input array, due to the output array that stores the squared values.
     * 
     * @param nums The input array of integers, sorted in non-decreasing order.
     * @return A new array with the squares of the integers, sorted in non-decreasing order.
     */
    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] out = new int[n];
        int i = 0;
        int j = n - 1;
        int k = n - 1;

        while (i <= j) {
            // Compare absolute values of nums[i] and nums[j]
            if (Math.abs(nums[i]) >= nums[j]) {
                out[k--] = nums[i] * nums[i]; // Square the larger absolute value and add to the output array
                i++;
            } else {
                out[k--] = nums[j] * nums[j]; // Square the smaller absolute value and add to the output array
                j--;
            }
        }
        return out;
    }
}
