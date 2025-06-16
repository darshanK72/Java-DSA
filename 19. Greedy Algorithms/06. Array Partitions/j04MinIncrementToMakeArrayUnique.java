/**
 * Problem: Minimum Increment to Make Array Unique
 * 
 * Problem Statement:
 * You are given an integer array nums. In one move, you can pick an index i where 0 <= i < nums.length 
 * and increment nums[i] by 1. Return the minimum number of moves to make every value in nums unique.
 * 
 * Example:
 * Input: nums = [1,2,2]
 * Output: 1
 * Explanation: After 1 move, the array could be [1, 2, 3].
 * 
 * Input: nums = [3,2,1,2,1,7]
 * Output: 6
 * Explanation: After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
 * It can be shown with 5 or less moves that it is impossible for the array to have all unique values.
 * 
 * Constraints:
 * - 1 <= nums.length <= 105
 * - 0 <= nums[i] <= 105
 * 
 * Approach:
 * Method 1 (HashSet):
 * 1. Use HashSet to track unique numbers
 * 2. For each number, increment until it becomes unique
 * 3. Count total increments needed
 * 
 * Method 2 (Sorting):
 * 1. Sort the array
 * 2. For each number, ensure it's greater than previous
 * 3. Count increments needed
 * 
 * Time Complexity:
 * - Method 1: O(n²) in worst case when many duplicates
 * - Method 2: O(n log n) due to sorting
 * 
 * Space Complexity:
 * - Method 1: O(n) for HashSet
 * - Method 2: O(1) as we modify array in-place
 */

import java.util.Arrays;
import java.util.HashSet;

public class j04MinIncrementToMakeArrayUnique {
    
    /**
     * Method 1: Using HashSet to track unique numbers
     * 
     * @param nums Input array of integers
     * @return Minimum number of increments needed to make array unique
     */
    public static int minIncrementForUniqueHashSet(int[] nums) {
        int move = 0;
        HashSet<Integer> set = new HashSet<>();
        set.add(nums[0]);
        for(int i = 1; i < nums.length; i++){
            // Increment current number until it becomes unique
            while(set.contains(nums[i])){
                move++;
                nums[i]++;
            }
            set.add(nums[i]);
        }
        return move;
    }
    
    /**
     * Method 2: Using sorting for more efficient solution
     * 
     * @param nums Input array of integers
     * @return Minimum number of increments needed to make array unique
     */
    public static int minIncrementForUniqueEfficient(int[] nums) {
        Arrays.sort(nums);
        int moves = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == nums[i - 1]){
                // If current number equals previous, increment it
                nums[i]++;
                moves++;
            }else if(nums[i] < nums[i - 1]){
                // If current number is less than previous, make it greater
                moves += (nums[i - 1] - nums[i] + 1);
                nums[i] = nums[i - 1] + 1; 
            }
        }
        return moves;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        int[] nums1 = {1, 2, 2};
        System.out.println("Test Case 1:");
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Output (HashSet): " + minIncrementForUniqueHashSet(nums1.clone()));
        System.out.println("Output (Efficient): " + minIncrementForUniqueEfficient(nums1.clone()));
        System.out.println();

        // Test Case 2: Larger array with multiple duplicates
        int[] nums2 = {3, 2, 1, 2, 1, 7};
        System.out.println("Test Case 2:");
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("Output (HashSet): " + minIncrementForUniqueHashSet(nums2.clone()));
        System.out.println("Output (Efficient): " + minIncrementForUniqueEfficient(nums2.clone()));
        System.out.println();

        // Test Case 3: Already unique array
        int[] nums3 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 3:");
        System.out.println("Input: " + Arrays.toString(nums3));
        System.out.println("Output (HashSet): " + minIncrementForUniqueHashSet(nums3.clone()));
        System.out.println("Output (Efficient): " + minIncrementForUniqueEfficient(nums3.clone()));
        System.out.println();

        // Test Case 4: All same numbers
        int[] nums4 = {2, 2, 2, 2};
        System.out.println("Test Case 4:");
        System.out.println("Input: " + Arrays.toString(nums4));
        System.out.println("Output (HashSet): " + minIncrementForUniqueHashSet(nums4.clone()));
        System.out.println("Output (Efficient): " + minIncrementForUniqueEfficient(nums4.clone()));
        System.out.println();

        // Test Case 5: Descending order
        int[] nums5 = {5, 4, 3, 2, 1};
        System.out.println("Test Case 5:");
        System.out.println("Input: " + Arrays.toString(nums5));
        System.out.println("Output (HashSet): " + minIncrementForUniqueHashSet(nums5.clone()));
        System.out.println("Output (Efficient): " + minIncrementForUniqueEfficient(nums5.clone()));
    }
}
