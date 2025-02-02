/**
 * Problem Statement:
 * 
 *     Given an array `heights` representing the heights of students in a line,
 *     return the number of students who are not in the correct position if the
 *     students were to stand in non-decreasing order.
 * 
 * Input:
 *     - An integer array `heights` where (1 <= heights.length <= 100) and
 *       (1 <= heights[i] <= 100).
 * 
 * Output:
 *     - An integer representing the number of students who are not standing in 
 *       the expected order.
 * 
 * Example:
 *     Input:
 *         heights = [1,1,4,2,1,3]
 *     Output:
 *         3
 * 
 *     Explanation:
 *         - Expected order after sorting: [1,1,1,2,3,4]
 *         - Differences with the original array: Positions (2, 4, 5) are incorrect.
 *         - Count of incorrect positions: 3.
 */

 import java.util.Arrays;

 public class j04HeightChecker {
     public static void main(String[] args) {
         int[][] testCases = {
                 { 1, 1, 4, 2, 1, 3 },
                 { 5, 1, 2, 3, 4 },
                 { 1, 2, 3, 4, 5 },
                 { 2, 1, 2, 1, 1, 2 }
         };

         for (int[] heights : testCases) {
             int result = heightCheckerCountingSort(heights);
             System.out.println("Number of students in the wrong position: " + result);
         }
     }
    
     /**
      * Approach 1: Inbuilt Sorting Approach
      * 
      * Intuition:
      * - To determine how many students are out of order, we first create a sorted
      *   version of the array (`sortedHeights`).
      * - By comparing each student's original position in `heights` with its
      *   corresponding position in `sortedHeights`, we can count how many are misplaced.
      * 
      * Time Complexity:
      * - O(n log n) due to sorting, followed by an O(n) comparison loop.
      * - Overall: O(n log n).
      * 
      * Space Complexity:
      * - O(n) due to the extra sorted array.
      * 
      * @param heights The array representing students' heights.
      * @return The number of students in the wrong position.
      */
     public static int heightCheckerInbuiltSorting(int[] heights) {
         int n = heights.length;
         int out = 0;
         int[] sortedHeights = Arrays.copyOf(heights, n);
         Arrays.sort(sortedHeights);
         for (int i = 0; i < n; i++) {
             if (heights[i] != sortedHeights[i]) {
                 out++;
             }
         }
         return out;
     }
 
     /**
      * Approach 2: Counting Sort Approach
      * 
      * Intuition:
      * - Since heights are constrained between 1 and 100, we can use a counting
      *   sort approach to determine the correct order without explicitly sorting.
      * - First, count the occurrences of each height.
      * - Then, iterate through `heights` and compare each student's height with
      *   the expected height derived from the counting array.
      * - Count how many students are misplaced.
      * 
      * Explanation:
      * - Construct a frequency array `count` where `count[i]` represents the number
      *   of students with height `i`.
      * - Use this array to determine the expected order.
      * - Compare with the original array and count mismatches.
      * 
      * Time Complexity:
      * - O(n) since we traverse `heights` and `count` once.
      * 
      * Space Complexity:
      * - O(1) since the `count` array has a fixed size of 101.
      * 
      * @param heights The array representing students' heights.
      * @return The number of students in the wrong position.
      */
     public static int heightCheckerCountingSort(int[] heights) {
         int[] count = new int[101]; // Since heights range from 1 to 100
         for (int h : heights) {
             count[h]++;
         }
 
         int ans = 0;
         int j = 0;
         for (int h : heights) {
             // Find the next expected height in sorted order
             while (count[j] == 0) {
                 j++;
             }
             // Compare with actual height in `heights`
             if (h != j) {
                 ans++;
             }
             count[j]--;
         }
         return ans;
     }
 }
 