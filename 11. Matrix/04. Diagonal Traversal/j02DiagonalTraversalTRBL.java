/**
 * Problem Statement:
 * 
 *     Given an `m x n` matrix, traverse the matrix diagonally from top-right to bottom-left. 
 *     You need to output the diagonal elements in the order starting from the top-right corner 
 *     and moving downwards, processing each diagonal one by one.
 * 
 * Input:
 *     - An integer `m` (1 <= m <= 1000) representing the number of rows in the matrix.
 *     - An integer `n` (1 <= n <= 1000) representing the number of columns in the matrix.
 *     - A matrix `mat` of size `m x n` where each element satisfies (1 <= mat[i][j] <= 10^5).
 * 
 * Output:
 *     - An array of integers representing the diagonal traversal from top-right to bottom-left.
 * 
 * Example:
 * 
 *     Input:
 *     3 3
 *     1 2 3
 *     4 5 6
 *     7 8 9
 * 
 *     Output:
 *     [3, 2, 1, 6, 5, 4, 9, 8, 7]
 * 
 *     Explanation:
 *     The diagonal traversal from top-right to bottom-left is:
 *     - Diagonal 1: 3
 *     - Diagonal 2: 2, 4
 *     - Diagonal 3: 1, 5, 7
 *     - Diagonal 4: 6
 *     - Diagonal 5: 9, 8
 *     - Diagonal 6: 9
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class j02DiagonalTraversalTRBL {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: number of rows in the matrix
        int col = in.nextInt(); // Input: number of columns in the matrix
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt(); // Input: elements of the matrix
            }
        }

        // Call solution methods
        System.out.println(Arrays.toString(diagonalTraverseTRBL1(matrix)));
        System.out.println(Arrays.toString(diagonalTraverseTRBL2(matrix)));

        in.close();
    }

    /**
     * Approach 1: Diagonal Traversal using Simple Iteration
     * 
     * Intuition:
     * - The traversal starts from the top-right corner and proceeds diagonally down-left, visiting each diagonal.
     * - For each diagonal, we start at the topmost point and move downwards while maintaining valid row and column indices.
     * 
     * Time Complexity:
     * - O(m * n), where `m` is the number of rows and `n` is the number of columns. 
     *   We visit every element once during the traversal.
     * 
     * Space Complexity:
     * - O(m * n), where `m * n` is the size of the output array. We store all matrix elements in the output.
     * 
     * @param mat The input matrix.
     * @return The diagonal traversal array.
     */
    public static int[] diagonalTraverseTRBL1(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[] out = new int[m * n]; // Result array to store the traversal
        int k = 0; // Index for storing elements in the output array

        // Loop through diagonals starting from top-right corner
        for (int d = 0; d < m + n - 1; d++) {
            // Determine starting row and column for each diagonal
            int row = d < n ? 0 : d - n + 1;
            int col = d < n ? d : n - 1;

            // Traverse each diagonal
            while (row < m && col >= 0) {
                out[k++] = mat[row][col]; // Add element to the result array
                row++;
                col--;
            }
        }

        return out;
    }

    /**
     * Approach 2: Diagonal Traversal using HashMap
     * 
     * Intuition:
     * - In this approach, we use a HashMap to group elements by their diagonals.
     * - The key for each diagonal is the sum of row and column indices (`i + j`), and the value is a list of elements in that diagonal.
     * - After populating the map, we concatenate all diagonal lists to form the result.
     * 
     * Time Complexity:
     * - O(m * n), where `m` is the number of rows and `n` is the number of columns. 
     *   We traverse each matrix element once to store it in the map and retrieve it.
     * 
     * Space Complexity:
     * - O(m * n), for storing the matrix elements in the map and the result array.
     * 
     * @param mat The input matrix.
     * @return The diagonal traversal array.
     */
    public static int[] diagonalTraverseTRBL2(int[][] mat) {
        Map<Integer, List<Integer>> map = new HashMap<>(); // Map to store diagonals
        int count = 0; // Count of elements to fill the result array

        // Populate the map with diagonals
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (!map.containsKey(i + j)) {
                    map.put(i + j, new ArrayList<>());
                }
                map.get(i + j).add(mat[i][j]); // Add element to the corresponding diagonal
                count++;
            }
        }

        int[] result = new int[count]; // Result array to store the traversal
        int k = 0; // Index for the result array

        // Concatenate the diagonals from the map to the result array
        for (int i = 0; i < map.size(); i++) {
            List<Integer> diagonal = map.get(i);
            for (int j = 0; j < diagonal.size(); j++) {
                result[k++] = diagonal.get(j);
            }
        }

        return result;
    }
}
