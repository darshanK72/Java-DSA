/**
 * Problem Statement:
 * 
 *     Given an array of integers, you need to return an array such that each element in the output array is the product 
 *     of all elements in the input array except the one at the current index. This should be done without using division.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 * 
 * Output:
 *     - An array where each element is the product of all other elements except the one at the current index.
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 3 4 5
 *     Output:
 *     120 60 40 30 24
 * 
 *     Explanation:
 *     - Output[0] = 2 * 3 * 4 * 5 = 120
 *     - Output[1] = 1 * 3 * 4 * 5 = 60
 *     - Output[2] = 1 * 2 * 4 * 5 = 40
 *     - Output[3] = 1 * 2 * 3 * 5 = 30
 *     - Output[4] = 1 * 2 * 3 * 4 = 24
 */

 import java.util.Arrays;
 import java.util.Scanner;
 
 public class j8ProductArrayExceptSelf {
 
     public static void main(String args[]) {
         // Reading input from the user
         Scanner in = new Scanner(System.in);
         int n = in.nextInt();  // Input: size of the array
         int[] arr = new int[n]; 
         for (int i = 0; i < n; i++) {
             arr[i] = in.nextInt();  // Input: elements of the array
         }
         
         // Output: Calling both methods and printing the results
         System.out.println(Arrays.toString(productExceptSelf(arr)));               // Output using prefix and suffix product arrays
         System.out.println(Arrays.toString(productExceptSelfEfficient(arr)));     // Output using efficient approach
         in.close();  // Closing the scanner
     }
 
     /**
      * Approach 1: Prefix and Suffix Product Arrays
      * 
      * Intuition:
      * - We calculate two arrays: one for the prefix product (product of all elements before the current element) 
      *   and another for the suffix product (product of all elements after the current element).
      * - Then, the product for each index in the output array can be computed as the product of the corresponding 
      *   prefix and suffix products.
      * 
      * Time Complexity:
      * - O(n) for computing the prefix and suffix products and O(n) for computing the result array, so the total 
      *   time complexity is O(n).
      * 
      * Space Complexity:
      * - O(n) for storing the prefix, suffix, and result arrays.
      * 
      * @param nums The input array of numbers.
      * @return The product array with the element at each index as the product of all other elements in the array.
      */
     public static int[] productExceptSelf(int[] nums) {
         int n = nums.length;
         
         // Arrays to store prefix and suffix products
         int[] preProd = new int[n];
         int[] suffProd = new int[n];
         
         // Initialize first element of prefix product and last element of suffix product
         preProd[0] = 1;
         suffProd[n - 1] = 1;
         
         // Calculate prefix product array
         for (int i = 1; i < n; i++) {
             preProd[i] = (nums[i - 1] * preProd[i - 1]);  // Compute the prefix product for index i
         }
         
         // Calculate suffix product array
         for (int i = n - 2; i >= 0; i--) {
             suffProd[i] = (nums[i + 1] * suffProd[i + 1]);  // Compute the suffix product for index i
         }
 
         // Generate the final output array
         int[] out = new int[n];
         for (int i = 0; i < n; i++) {
             out[i] = preProd[i] * suffProd[i];  // Multiply prefix and suffix products to get the result
         }
 
         return out;  // Return the product array
     }
 
     /**
      * Approach 2: Efficient Approach Using One Pass
      * 
      * Intuition:
      * - We can optimize the space usage by calculating the result array in a single pass.
      * - First, we calculate the total product of all elements and count the number of zeros.
      * - If there are two or more zeros, the result will be all zeros.
      * - If there's exactly one zero, the result will have the product of all non-zero elements in the place of zero, 
      *   and all other elements will be zero.
      * - If there are no zeros, the result for each index can be calculated by dividing the total product by the element 
      *   at that index.
      * 
      * Time Complexity:
      * - O(n) for iterating through the array and calculating the product.
      * 
      * Space Complexity:
      * - O(n) for storing the result array.
      * 
      * @param nums The input array of numbers.
      * @return The product array with the element at each index as the product of all other elements in the array.
      */
     public static int[] productExceptSelfEfficient(int[] nums) {
         int p = 1;  // Variable to store the product of all non-zero elements
         int zeros = 0;  // Variable to store the count of zeros
         
         // Calculate the total product and count the zeros
         for (int i = 0; i < nums.length; i++) {
             if (nums[i] == 0)
                 zeros++;  // Increase zero count if the element is zero
             else
                 p *= nums[i];  // Multiply the element to get the total product
         }
         
         int[] out = new int[nums.length];  // Initialize the output array
         
         // If there are more than one zero, all products will be zero
         if (zeros > 1) {
             return out;  // Return all zeros if there are more than one zero
         } else if (zeros == 1) {
             // If there is exactly one zero, fill the output array with product except for the zero element
             for (int i = 0; i < nums.length; i++) {
                 if (nums[i] == 0)
                     out[i] = p;  // Set the product in the place of zero
             }
         } else {
             // If there are no zeros, calculate the product for each element by dividing the total product by the element at that index
             for (int i = 0; i < nums.length; i++) {
                 out[i] = p / nums[i];  // Divide the total product by the current element
             }
         }
         
         return out;  // Return the product array
     }
 }
 