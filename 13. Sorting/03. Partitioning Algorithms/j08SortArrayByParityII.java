/**
 * Problem Statement:
 * 
 *     Given an array `nums` of integers, sort it such that:
 *     - All even numbers are at the even indices (0, 2, 4, ...).
 *     - All odd numbers are at the odd indices (1, 3, 5, ...).
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (−10^5 <= nums[i] <= 10^5) and `nums[i]` is either even or odd.
 * 
 * Output:
 *     - The array where even elements are at even indices and odd elements are at odd indices.
 * 
 * Example:
 *     Input:
 *     4
 *     [4, 2, 5, 7]
 *     Output:
 *     [4, 5, 2, 7]
 * 
 *     Explanation:
 *     - The even numbers (4, 2) are placed at indices 0 and 2, respectively.
 *     - The odd numbers (5, 7) are placed at indices 1 and 3, respectively.
 */

import java.util.Scanner;

public class j08SortArrayByParityII {
    
    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] nums = new int[n];
        
        // Input: elements of the array
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }

        // Call the method to sort the array by parity
        int[] result = sortArrayByParityII(nums);

        // Output the sorted array
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }

        in.close();
    }
    
    /**
     * Approach: Sorting Array by Parity with Two Pointers
     * 
     * Intuition:
     * - We need to arrange even and odd elements at specific indices.
     * - We will use two pointers:
     *   - `k` to place even elements at even indices (0, 2, 4, ...).
     *   - `l` to place odd elements at odd indices (1, 3, 5, ...).
     * - The idea is to iterate through the array and place the elements directly at their respective positions.
     * 
     * Time Complexity:
     * - O(n): We iterate through the array once.
     * 
     * Space Complexity:
     * - O(n): We use an extra array to store the result.
     * 
     * @param nums The input array of integers.
     * @return The array sorted by parity, where even numbers are at even indices and odd numbers are at odd indices.
     */
    public static int[] sortArrayByParityII(int[] nums) {
        int[] out = new int[nums.length]; // Create an output array
        int k = 0; // Pointer for even indices
        int l = 1; // Pointer for odd indices
        
        // Iterate through the input array and place even and odd elements at their respective indices
        for(int i = 0; i < nums.length; i++){
            if(nums[i] % 2 == 0) {
                out[k] = nums[i]; // Place even number at the even index
                k += 2; // Move to the next even index
            } else {
                out[l] = nums[i]; // Place odd number at the odd index
                l += 2; // Move to the next odd index
            }
        }
        return out; // Return the sorted array
    }
}
