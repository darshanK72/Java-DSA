/**
 * GeeksForGeeks: Minimum sum of two numbers formed from digits of an array
 * 
 * Problem Statement:
 *     Given an array of digits (values are from 0 to 9), find the minimum possible sum of two
 *     numbers formed from digits of the array. All digits of given array must be used to form
 *     the two numbers.
 * 
 * Input:
 *     - arr (int[]): Array of digits (0-9)
 *     - n (int): Size of the array
 * 
 * Output:
 *     - long: Minimum possible sum of two numbers formed using all digits
 * 
 * Example:
 *     Input: arr[] = {6, 8, 4, 5, 2, 3}
 *     Output: 604
 * 
 *     Explanation:
 *     - First number: 246 (formed by digits at even indices)
 *     - Second number: 358 (formed by digits at odd indices)
 *     - Sum = 246 + 358 = 604
 */

import java.util.Arrays;

public class j08SumFormedByDigits {

    /**
     * Approach: Greedy with Alternating Digits
     * 
     * Intuition:
     * - To minimize sum, we need to minimize both numbers
     * - Sort array to get smallest digits first
     * - Skip leading zeros as they don't contribute to number value
     * - Alternate digits between two numbers to keep them balanced
     * 
     * Explanation:
     * - Step 1: Sort array to get smallest digits first
     * - Step 2: Skip leading zeros
     * - Step 3: Alternate digits between two numbers
     * - Step 4: Return sum of both numbers
     * 
     * Time Complexity: O(n log n) where n is size of array
     *                  - Due to sorting of array
     * Space Complexity: O(1) as we modify array in-place
     * 
     * @param arr    Array of digits (0-9)
     * @param n      Size of the array
     * @return       Minimum possible sum of two numbers
     */
    public static long minSum(int arr[], int n) {
        // Sort array to get smallest digits first
        Arrays.sort(arr);
        
        // Skip leading zeros
        int i = 0;
        while(i < n && arr[i] == 0) i++;
        
        // Form two numbers by alternating digits
        long n1 = 0;
        long n2 = 0;
        for(; i < n; i++){
            if(i % 2 == 0) n1 = n1 * 10 + arr[i];
            else n2 = n2 * 10 + arr[i];
        }
        
        return n1 + n2;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] arr1 = {6, 8, 4, 5, 2, 3};
        System.out.println("Input: " + Arrays.toString(arr1));
        System.out.println("Expected: 604, Output: " + minSum(arr1, arr1.length));

        // Test Case 2: Array with zeros
        System.out.println("\nSpecial Case - With Zeros:");
        int[] arr2 = {0, 1, 2, 3, 4, 5};
        System.out.println("Input: " + Arrays.toString(arr2));
        System.out.println("Expected: 246, Output: " + minSum(arr2, arr2.length));

        // Test Case 3: All same digits
        System.out.println("\nSpecial Case - All Same Digits:");
        int[] arr3 = {5, 5, 5, 5};
        System.out.println("Input: " + Arrays.toString(arr3));
        System.out.println("Expected: 110, Output: " + minSum(arr3, arr3.length));

        // Test Case 4: Single digit
        System.out.println("\nEdge Case - Single Digit:");
        int[] arr4 = {9};
        System.out.println("Input: " + Arrays.toString(arr4));
        System.out.println("Expected: 9, Output: " + minSum(arr4, arr4.length));

        // Test Case 5: All zeros
        System.out.println("\nEdge Case - All Zeros:");
        int[] arr5 = {0, 0, 0, 0};
        System.out.println("Input: " + Arrays.toString(arr5));
        System.out.println("Expected: 0, Output: " + minSum(arr5, arr5.length));
    }
}
