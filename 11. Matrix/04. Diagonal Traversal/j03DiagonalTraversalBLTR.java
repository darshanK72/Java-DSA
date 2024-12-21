/**
 * Problem Statement:
 * 
 *     Given a jagged array `nums`, representing an m x n matrix, perform a diagonal traversal 
 *     from bottom-left to top-right. For each diagonal, start from the bottom-most element of 
 *     the diagonal and proceed upwards to the top-most element, concatenating all the diagonals 
 *     in this order.
 * 
 * Input:
 *     - An integer `m` (1 <= m <= 1000), representing the number of rows in the matrix.
 *     - A list of lists `nums`, where each list represents a row of the matrix, and each row 
 *       can have a different number of columns, satisfying (1 <= nums[i][j] <= 10^5).
 * 
 * Output:
 *     - An array of integers representing the diagonal traversal from bottom-left to top-right.
 * 
 * Example:
 * 
 *     Input:
 *     3
 *     3 1 4
 *     2 5
 *     7 8 9
 * 
 *     Output:
 *     [7, 2, 3, 5, 1, 4, 8, 9]
 * 
 *     Explanation:
 *     The diagonal traversal from bottom-left to top-right is:
 *     - Diagonal 1: 7
 *     - Diagonal 2: 2
 *     - Diagonal 3: 3, 5
 *     - Diagonal 4: 1
 *     - Diagonal 5: 4
 *     - Diagonal 6: 8
 *     - Diagonal 7: 9
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class j03DiagonalTraversalBLTR {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: number of rows in the matrix
        ArrayList<ArrayList<Integer>> nums = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            int col = in.nextInt(); // Input: number of columns in the current row
            ArrayList<Integer> lst = new ArrayList<>();
            for (int j = 0; j < col; j++) {
                lst.add(in.nextInt()); // Input: elements of the matrix
            }
            nums.add(lst);
        }

        // Call solution methods
        System.out.println(Arrays.toString(diagonalTraverseBLTR(nums)));
        System.out.println(Arrays.toString(diagonalTraverseBLTREfficient(nums)));

        in.close();
    }

    /**
     * Approach 1: Diagonal Traversal using Simple Iteration
     * 
     * Intuition:
     * - We start from the bottom-left corner of the matrix and iterate upwards diagonally. 
     * - For each diagonal, we collect elements starting from the bottom-most element and move upwards.
     * 
     * Time Complexity:
     * - O(M * N), where `M` is the number of rows and `N` is the maximum number of columns.
     *   Each element in the matrix is visited once during the traversal.
     * 
     * Space Complexity:
     * - O(M * N), where `M * N` is the size of the result array, which stores all the matrix elements.
     * 
     * @param nums The input jagged matrix (list of lists).
     * @return The diagonal traversal array from bottom-left to top-right.
     */
    public static int[] diagonalTraverseBLTR(ArrayList<ArrayList<Integer>> nums) {
        int m = nums.size();
        int n = 0;
        int s = 0;

        // Calculate the total number of elements and the maximum column size
        for (int i = 0; i < m; i++) {
            s += nums.get(i).size();
            n = Math.max(n, nums.get(i).size());
        }

        int[] out = new int[s];
        int k = 0;

        // Loop through diagonals
        for (int d = 0; d < m + n - 1; d++) {
            int row = d < m ? d : m - 1;
            int col = d < m ? 0 : d - m + 1;

            // Traverse each diagonal
            while (row >= 0 && col < n) {
                if (row < nums.size() && col < nums.get(row).size()) {
                    out[k] = nums.get(row).get(col); // Add element to the result array
                    k++;
                }
                row--;
                col++;
            }
        }

        return out;
    }

    /**
     * Approach 2: Diagonal Traversal using HashMap
     * 
     * Intuition:
     * - We use a `HashMap` to store elements of each diagonal, where the key is the sum of the 
     *   row and column indices (`i + j`), and the value is a list of elements in that diagonal.
     * - After storing all elements, we retrieve them in order and reverse the order for each diagonal 
     *   to achieve the desired bottom-left to top-right order.
     * 
     * Time Complexity:
     * - O(M * N), where `M` is the number of rows and `N` is the maximum number of columns.
     *   We traverse the matrix once to populate the `HashMap`, and then once again to create the result array.
     * 
     * Space Complexity:
     * - O(M * N), for storing the elements in the `HashMap` and the result array.
     * 
     * @param nums The input jagged matrix (list of lists).
     * @return The diagonal traversal array from bottom-left to top-right.
     */
    public static int[] diagonalTraverseBLTREfficient(ArrayList<ArrayList<Integer>> nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int count = 0;

        // Traverse the matrix and store the elements in diagonals
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                if (!map.containsKey(i + j)) {
                    map.put(i + j, new ArrayList<>());
                }
                map.get(i + j).add(nums.get(i).get(j)); // Add element to the corresponding diagonal
                count++;
            }
        }

        int[] result = new int[count];
        int k = 0;

        // Retrieve the diagonals and concatenate them to form the result array
        for (int i = 0; i < map.size(); i++) {
            List<Integer> diagonal = map.get(i);
            // Reverse each diagonal list to get the bottom-left to top-right order
            for (int j = diagonal.size() - 1; j >= 0; j--) {
                result[k++] = diagonal.get(j);
            }
        }

        return result;
    }
}
