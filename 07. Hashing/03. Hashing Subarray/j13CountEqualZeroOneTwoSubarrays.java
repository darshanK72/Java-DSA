/**
 * Problem Statement:
 *
 *     Given a string `str` consisting of characters '0', '1', and '2', the task is to find the number of substrings where 
 *     the count of '0's, '1's, and '2's are equal.
 *
 * Input:
 *     - A string `str` containing only the characters '0', '1', and '2' (1 <= str.length <= 10^5).
 *
 * Output:
 *     - A long integer representing the number of substrings where the number of '0's, '1's, and '2's are equal.
 *
 * Example:
 *     Input:
 *     "0120"
 *     
 *     Output:
 *     3
 *     
 *     Explanation:
 *     The substrings with equal '0's, '1's, and '2's are:
 *     - "012" (from index 0 to 2)
 *     - "120" (from index 1 to 3)
 *     - "0120" (from index 0 to 3)
 */

 import java.util.HashMap;
 import java.util.Scanner;
 
 public class j13CountEqualZeroOneTwoSubarrays {
     public static void main(String[] args) {
         // Reading input
         Scanner in = new Scanner(System.in);
         String str = in.next();  // Input: the binary string consisting of '0', '1', and '2'
         System.out.println(getSubstringWithEqual012(str));  // Call the solution method
         in.close();
     }
 
     /**
      * Approach: Optimized Approach Using HashMap for Frequency Count
      *
      * Intuition:
      * - To solve the problem of finding substrings with equal '0's, '1's, and '2's, we can transform the problem using a 
      *   difference approach.
      * - We keep track of the difference between the counts of '0's and '1's, and also the difference between the counts of 
      *   '1's and '2's. These differences will help identify substrings that have an equal number of '0's, '1's, and '2's.
      * - For any substring that starts at index `i` and ends at index `j`, if the difference of '0's and '1's at index `i` 
      *   is the same as at index `j`, and the difference of '1's and '2's at index `i` is also the same as at index `j`, 
      *   then the substring from `i+1` to `j` has equal numbers of '0's, '1's, and '2's.
      * 
      * Time Complexity:
      * - O(n): We traverse the string once, and each operation with the HashMap (get and put) is O(1) on average.
      * 
      * Space Complexity:
      * - O(n): We store the frequency of each unique (difference of '0's-'1's, difference of '1's-'2's) pair in the HashMap, 
      *   which requires O(n) space in the worst case.
      * 
      * Explanation:
      * - The key to the solution is that for any substring to have equal '0's, '1's, and '2's, the differences in counts 
      *   between '0's and '1's, and '1's and '2's, must be the same at two different positions in the string. We use a 
      *   HashMap to store these differences and their frequencies.
      * - By using this difference approach, we can efficiently count the substrings that satisfy the condition.
      * 
      * @param str The input string consisting of '0', '1', and '2'.
      * @return The number of substrings where the count of '0's, '1's, and '2's are equal.
      */
     public static long getSubstringWithEqual012(String str) {
         int zeros = 0;  // Count of '0's
         int ones = 0;   // Count of '1's
         int twos = 0;   // Count of '2's
         long count = 0; // Variable to count the substrings with equal '0's, '1's, and '2's
         HashMap<String, Long> map = new HashMap<>(); // Map to store the frequency of (zeros-ones, ones-twos) pairs
         map.put("0,0", 1L);  // Initialize with the base case (0, 0) to handle substrings starting at index 0
         
         // Traverse through the string
         for (int i = 0; i < str.length(); i++) {
             // Update counts based on the character at the current position
             if (str.charAt(i) == '0') {
                 zeros++;
             } else if (str.charAt(i) == '1') {
                 ones++;
             } else {
                 twos++;
             }
 
             // Calculate the differences: (zeros-ones, ones-twos)
             String key = (zeros - ones) + "," + (ones - twos);
 
             // If this key has been seen before, it means there is a substring ending at this index that satisfies the condition
             count += map.getOrDefault(key, 0L);
 
             // Update the map with the current key (the count of substrings with this key)
             map.put(key, map.getOrDefault(key, 0L) + 1L);
         }
 
         return count;  // Return the total count of valid substrings
     }
 }
 