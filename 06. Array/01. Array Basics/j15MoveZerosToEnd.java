/**
 * Problem Statement:
 *     Given an integer array `arr` of size `n`, move all zeros in the array to the end
 *     while maintaining the relative order of the non-zero elements.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), the size of the array.
 *     - An integer array `arr` of size `n` where (-10^5 <= arr[i] <= 10^5).
 *
 * Output:
 *     - The modified array with all zeros moved to the end.
 *       Print the modified array as output.
 *
 * Example:
 *     Input:
 *         n = 6
 *         arr = [0, 1, 0, 3, 12, 0]
 *     Output:
 *         [1, 3, 12, 0, 0, 0]
 *
 *     Explanation:
 *         All zeros are moved to the end, and the relative order of non-zero elements is maintained.
 *
 *     Input:
 *         n = 5
 *         arr = [1, 2, 3, 4, 5]
 *     Output:
 *         [1, 2, 3, 4, 5]
 *
 *     Explanation:
 *         No zeros are present, so the array remains unchanged.
 */

 import java.util.Scanner;
 import java.util.Arrays;
 
 public class j15MoveZerosToEnd {
 
     public static void main(String args[]) {
         Scanner in = new Scanner(System.in);
         int n = in.nextInt(); // Input: size of the array
         int[] arr = new int[n];
         for (int i = 0; i < n; i++) {
             arr[i] = in.nextInt(); // Input: array elements
         }
 
         System.out.println("Original Array: " + Arrays.toString(arr));
 
         // Approach 1: Optimized In-Place Swap
         moveZerosToEnd(arr);
         System.out.println("Approach 1 (Optimized): " + Arrays.toString(arr));
 
         // Reset the array for the alternative approach
         System.out.println("Resetting array...");
         for (int i = 0; i < n; i++) {
             arr[i] = in.nextInt();
         }
 
         // Approach 2: Using Additional Array (Alternative)
         int[] result = moveZerosToEndAlternative(arr);
         System.out.println("Approach 2 (Alternative): " + Arrays.toString(result));
 
         in.close();
     }
 
     /**
      * Approach 1: Optimized In-Place Swap
      *     This approach uses a two-pointer technique to move all zeros to the end
      *     without using extra space.
      * 
      * Intuition:
      *     - Use a pointer `k` to track the position where the next non-zero element
      *       should be placed.
      *     - Traverse the array and whenever a non-zero element is encountered,
      *       swap it with the element at position `k` and increment `k`.
      *     - By the end of the traversal, all non-zero elements are shifted to the left,
      *       and zeros are at the end.
      *
      * Time Complexity:
      *     O(n), where `n` is the size of the array. We traverse the array once.
      *
      * Space Complexity:
      *     O(1), as no additional space is used.
      *
      * @param arr The input array to be modified.
      */
     public static void moveZerosToEnd(int[] arr) {
         int k = 0; // Pointer to track the next position for non-zero elements
         for (int i = 0; i < arr.length; i++) {
             if (arr[i] != 0) {
                 int temp = arr[k];
                 arr[k] = arr[i];
                 arr[i] = temp;
                 k++;
             }
         }
     }
 
     /**
      * Approach 2 (Alternative): Using Additional Array
      *     This approach uses an additional array to store the non-zero elements
      *     and appends zeros to the remaining positions.
      *
      * Intuition:
      *     - Traverse the input array and copy all non-zero elements to an auxiliary array.
      *     - After copying, fill the remaining positions with zeros.
      *     - Return the modified array.
      *
      * Time Complexity:
      *     O(n), where `n` is the size of the array. We traverse the array twice:
      *     once to copy non-zero elements and once to append zeros.
      *
      * Space Complexity:
      *     O(n), as we use an additional array of size `n`.
      *
      * @param arr The input array.
      * @return A new array with zeros moved to the end.
      */
     public static int[] moveZerosToEndAlternative(int[] arr) {
         int[] result = new int[arr.length];
         int index = 0;
 
         // Copy all non-zero elements to the result array
         for (int i = 0; i < arr.length; i++) {
             if (arr[i] != 0) {
                 result[index++] = arr[i];
             }
         }
 
         // Remaining positions are already initialized to zero
         return result;
     }
 }
 