/**
 * LeetCode 670. Maximum Swap
 * 
 * Problem Statement:
 *     You are given an integer num. You can swap two digits at most once to get the
 *     maximum valued number. Return the maximum valued number you can get.
 * 
 * Input:
 *     - num (0 <= num <= 10^8)
 * 
 * Output:
 *     - Return the maximum valued number you can get after at most one swap
 * 
 * Example:
 *     Input: num = 2736
 *     Output: 7236
 * 
 *     Explanation:
 *     Swap the number 2 and the number 7.
 *     After swapping, we get 7236, which is the maximum number we can get.
 */

public class j06MaximumSwap {

    /**
     * Approach: Greedy with Rightmost Maximum
     * 
     * Intuition:
     * - For each position, find the rightmost maximum digit
     * - If the rightmost maximum is greater than current digit, swap them
     * - This ensures we get the maximum possible number with one swap
     * 
     * Explanation:
     * - Step 1: Convert number to char array for easy manipulation
     * - Step 2: For each position i:
     *   * Find the rightmost maximum digit after position i
     *   * If this maximum is greater than digit at i, swap them
     *   * Break after first swap as we can swap at most once
     * 
     * Time Complexity: O(n²) where n is the number of digits
     *                  - O(n) to convert number to string
     *                  - O(n²) to find maximum digit for each position
     * Space Complexity: O(n) to store the char array
     * 
     * @param num    Input number
     * @return       Maximum number after at most one swap
     */
    public static int maximumSwap(int num) {
        // Convert number to char array for easy digit manipulation
        char[] str = Integer.toString(num).toCharArray();

        // Try to find a digit to swap for each position
        for(int i = 0; i < str.length; i++) {
            // Initialize variables to track maximum digit
            char maxDigit = str[i];        // Current maximum digit
            int maxDigitIndex = -1;        // Index of maximum digit

            // Find the rightmost maximum digit after current position
            for(int j = i + 1; j < str.length; j++) {
                if(str[j] >= maxDigit) {   // Use >= to get rightmost maximum
                    maxDigit = str[j];
                    maxDigitIndex = j;
                }
            }

            // If we found a larger digit and it's different from current digit
            if(maxDigitIndex != -1 && str[maxDigitIndex] != str[i]) {
                swap(str, i, maxDigitIndex);  // Perform the swap
                break;  // Break after first swap as we can swap at most once
            }
        }

        // Convert char array back to integer
        return Integer.parseInt(new String(str));
    }

    /**
     * Efficient Approach: Using Suffix Array
     * 
     * Intuition:
     * - Instead of finding the maximum digit for each position repeatedly,
     *   we can precompute the rightmost maximum digit for each position
     * - This eliminates the need for nested loops, improving time complexity
     * 
     * Explanation:
     * - Step 1: Create a suffix array that stores the index of the rightmost maximum digit
     *   for each position
     * - Step 2: Build the suffix array from right to left:
     *   * For each position i, compare current digit with the maximum digit after it
     *   * If current digit is larger, it becomes the new maximum
     *   * Otherwise, keep the previous maximum
     * - Step 3: Scan from left to right and perform the first possible swap
     * 
     * Time Complexity: O(n) where n is the number of digits
     *                  - O(n) to convert number to string
     *                  - O(n) to build suffix array
     *                  - O(n) to find and perform swap
     * Space Complexity: O(n) to store the char array and suffix array
     * 
     * @param num    Input number
     * @return       Maximum number after at most one swap
     */
    public static int maximumSwapEfficient(int num) {
        // Convert number to char array for easy digit manipulation
        char[] str = Integer.toString(num).toCharArray();
        
        // Create suffix array to store indices of rightmost maximum digits
        int[] suffix = new int[str.length];
        
        // Initialize last position with its own index
        suffix[str.length - 1] = str.length - 1;
        
        // Build suffix array from right to left
        for(int i = str.length - 2; i >= 0; i--) {
            // If current digit is larger than the maximum digit after it
            if(str[suffix[i + 1]] < str[i]) {
                suffix[i] = i;  // Current digit becomes the new maximum
            } else {
                suffix[i] = suffix[i + 1];  // Keep the previous maximum
            }
        }

        // Scan from left to right to find first possible swap
        for(int i = 0; i < str.length; i++) {
            // If current digit is smaller than the maximum digit after it
            if(str[i] < str[suffix[i]]) {
                swap(str, i, suffix[i]);  // Perform the swap
                break;  // Break after first swap as we can swap at most once
            }
        }

        // Convert char array back to integer
        return Integer.parseInt(new String(str));
    }

    /**
     * Helper method to swap two characters in the array
     * 
     * @param str    Character array
     * @param i      First index
     * @param j      Second index
     */
    public static void swap(char[] str, int i, int j) {
        char temp = str[i];  // Store first character
        str[i] = str[j];     // Put second character in first position
        str[j] = temp;       // Put first character in second position
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int num1 = 2736;
        System.out.println("Input: num = 2736");
        System.out.println("Expected: 7236, Output: " + maximumSwap(num1));

        // Test Case 2: Already maximum
        System.out.println("\nAlready Maximum Case:");
        int num2 = 9973;
        System.out.println("Input: num = 9973");
        System.out.println("Expected: 9973, Output: " + maximumSwap(num2));

        // Test Case 3: Single digit
        System.out.println("\nSingle Digit Case:");
        int num3 = 5;
        System.out.println("Input: num = 5");
        System.out.println("Expected: 5, Output: " + maximumSwap(num3));

        // Test Case 4: Multiple same digits
        System.out.println("\nMultiple Same Digits Case:");
        int num4 = 9999;
        System.out.println("Input: num = 9999");
        System.out.println("Expected: 9999, Output: " + maximumSwap(num4));
    }
}
