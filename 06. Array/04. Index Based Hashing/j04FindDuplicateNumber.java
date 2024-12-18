/**
 * Problem Statement:
 * 
 *     Given an array `nums` containing `n + 1` integers where each integer is in the range `[1, n]`, 
 *     find the duplicate number. You must solve the problem without modifying the array if possible 
 *     and use constant extra space. The solution should have a runtime complexity of less than O(n^2).
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the number of unique elements.
 *     - An array `nums` of size `n + 1`, containing numbers in the range `[1, n]` with exactly one duplicate.
 * 
 * Output:
 *     - An integer representing the duplicate number.
 * 
 * Example:
 *     Input:
 *         n = 5, arr = [1, 3, 4, 2, 2]
 *     Output:
 *         2
 * 
 *     Explanation:
 *         - The number `2` appears twice in the array.
 */

import java.util.Scanner;

public class j04FindDuplicateNumber {

  public static void main(String args[]) {
    // Reading input
    Scanner in = new Scanner(System.in);
    int n = in.nextInt(); // Input: size of the array
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = in.nextInt(); // Input: array elements
    }

    // Printing the duplicate number found using the method
    System.out.println(findDuplicate(arr));
    in.close();
  }

  /**
   * Approach 1: Index-Based Hashing (With Array Modification)
   * 
   * Intuition:
   * - The duplicate number can be identified using the array itself as a marker by modifying its values.
   * - By using the numbers as indices and incrementing the corresponding index value, we can track which number is visited more than once.
   * 
   * Explanation:
   * - Traverse the array and for each number `nums[i]`, use `nums[i] % (nums.length + 1)` to find the original value.
   * - Increment the value at the corresponding index by `(nums.length + 1)`, marking that the index has been visited.
   * - In a second pass, any index whose value exceeds `(nums.length + 1)` twice indicates the duplicate number.
   * 
   * Time Complexity:
   * - O(n): We traverse the array twice, making the overall time complexity linear.
   * 
   * Space Complexity:
   * - O(1): The solution modifies the array in place without using additional space.
   * 
   * @param nums The input array of numbers.
   * @return The duplicate number in the array.
   */
  public static int findDuplicate(int[] nums) {
    // Step 1: Mark the array using index-based hashing
    for (int i = 0; i < nums.length; i++) {
      int original = nums[i] % (nums.length + 1); // Recover original number
      nums[original] += (nums.length + 1); // Increment the index value
    }

    // Step 2: Identify the duplicate number
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] / (nums.length + 1) > 1) { // Check if index value is incremented more than once
        return i;
      }
    }

    // Return -1 if no duplicate is found (though guaranteed by constraints)
    return -1;
  }
}
