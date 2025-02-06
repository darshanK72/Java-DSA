/**
 * Problem Statement:
 * 
 *     Given an array arr[] containing integers in the range from 1 to n. We need to find the inverse permutation of that array.
 *     Inverse Permutation is a permutation that you will get by inserting the position of an element at the position specified by the element value in the array.
 *     For a better understanding, consider the following example:
 *     
 *     Suppose we found element 4 at position 3 in an array, then in the inverse permutation, we insert 3 (position of element 4 in the array) in position 4 (element value).
 * 
 * Input:
 *     - An array arr[] containing integers in the range [1, n].
 * 
 * Output:
 *     - An array representing the inverse permutation of arr[].
 * 
 * Example:
 *     Input:
 *         arr = [4, 3, 2, 1]
 *     Output:
 *         [4, 3, 2, 1]
 * 
 *     Explanation:
 *         - Element 4 is at position 3, so in the inverse permutation, 3 goes to position 4.
 *         - Element 3 is at position 2, so 2 goes to position 3.
 *         - Element 2 is at position 1, so 1 goes to position 2.
 *         - Element 1 is at position 0, so 4 goes to position 1.
 */

import java.util.ArrayList;

public class j04InversePermutationI {

    public static void main(String[] args) {
        // Test cases for inversePermutation and inversePermutationEfficient

        // Test case 1: Sample input array
        int[] arr1 = { 4, 3, 2, 1 };
        ArrayList<Integer> result1 = inversePermutation(arr1);
        System.out.println("Inverse Permutation Test 1: " + result1);

        // Test case 2: Another sample input
        int[] arr2 = { 1, 2, 3, 4 };
        ArrayList<Integer> result2 = inversePermutation(arr2);
        System.out.println("Inverse Permutation Test 2: " + result2);

        // Test case 3: Efficient method
        ArrayList<Integer> result3 = inversePermutationEfficient(arr1);
        System.out.println("Inverse Permutation Efficient Test 1: " + result3);

        // Test case 4: Efficient method for another input
        ArrayList<Integer> result4 = inversePermutationEfficient(arr2);
        System.out.println("Inverse Permutation Efficient Test 2: " + result4);
    }

    /**
     * Approach 1: Basic Approach (O(n) time complexity)
     * 
     * Intuition:
     * - The basic idea is to create a new array `inverse` of the same size as the input.
     * - For every element `arr[i]`, place `i + 1` at position `arr[i] - 1` in the `inverse` array.
     * - This approach uses extra space for the `inverse` array.
     * 
     * Time Complexity:
     * - O(n), where n is the size of the input array.
     * 
     * Space Complexity:
     * - O(n) for the `inverse` array.
     * 
     * @param arr The input array representing the permutation.
     * @return The inverse of the input array.
     */
    public static ArrayList<Integer> inversePermutation(int arr[]) {
        int[] inverse = new int[arr.length]; // Initialize the inverse array
        for (int i = 0; i < arr.length; i++) {
            inverse[arr[i] - 1] = i + 1; // Fill the inverse array based on the position
        }
        ArrayList<Integer> out = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            out.add(inverse[i]); // Convert the result to ArrayList
        }
        return out; // Return the inverse permutation
    }

    /**
     * Approach 2: Efficient Approach (O(n) time complexity, O(n) space complexity)
     * 
     * Intuition:
     * - Instead of using a separate array, this approach utilizes an ArrayList.
     * - The ArrayList is initialized with zero values.
     * - For each element `arr[i]`, set the position `arr[arr[i] - 1] - 1` in the ArrayList 
     *   to `arr[i]`. This effectively builds the inverse permutation.
     * - This approach avoids the use of an extra array, directly utilizing ArrayList.
     * 
     * Time Complexity:
     * - O(n), where n is the size of the input array.
     * 
     * Space Complexity:
     * - O(n) for the ArrayList used to store the inverse permutation.
     * 
     * @param arr The input array representing the permutation.
     * @return The inverse of the input array as an ArrayList.
     */
    public static ArrayList<Integer> inversePermutationEfficient(int arr[]) {
        ArrayList<Integer> out = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            out.add(0); // Initialize the ArrayList with zeros
        }
        for (int i = 0; i < arr.length; i++) {
            out.set(arr[arr[i] - 1] - 1, arr[i]); // Set values in the correct positions
        }
        return out; // Return the inverse permutation
    }

}
