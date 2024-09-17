import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class j02LargestZeroSumSubmatrix {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        System.out.println(sumZeroMatrix(matrix));
        in.close();
    }

    public static ArrayList<ArrayList<Integer>> sumZeroMatrix(int[][] a) {
        int m = a.length;
        if (m == 0)
            return new ArrayList<>(); // Edge case: empty matrix
        int n = a[0].length;

        // To store the coordinates and size of the largest zero sum submatrix
        int maxArea = 0;
        int[] bestCoordinates = { -1, -1, -1, -1 }; // Top-left (r1, c1) and bottom-right (r2, c2)

        // Iterate over all pairs of rows
        for (int i = 0; i < m; i++) {
            int[] sumPrefix = new int[n]; // Array to store the sum of elements between row i and row j
            for (int j = i; j < m; j++) {
                // Add elements from row j to sumPrefix (effectively updating the column sums)
                for (int k = 0; k < n; k++) {
                    sumPrefix[k] += a[j][k];
                }

                // Find the largest zero sum subarray for the current sumPrefix array
                int[] subarrayResult = largestZeroSumSubarray(sumPrefix);

                // Check if we found a larger submatrix
                if (subarrayResult[0] != -1) { // If a zero-sum subarray was found
                    int area = (j - i + 1) * (subarrayResult[1] - subarrayResult[0] + 1);
                    if (area > maxArea) {
                        maxArea = area;
                        bestCoordinates[0] = i; // Top-left row
                        bestCoordinates[1] = subarrayResult[0]; // Top-left column
                        bestCoordinates[2] = j; // Bottom-right row
                        bestCoordinates[3] = subarrayResult[1]; // Bottom-right column
                    }
                }
            }
        }

        // If no zero-sum submatrix is found, return an empty ArrayList
        if (bestCoordinates[0] == -1)
            return new ArrayList<>();

        // Extract and return the largest zero-sum submatrix
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = bestCoordinates[0]; i <= bestCoordinates[2]; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = bestCoordinates[1]; j <= bestCoordinates[3]; j++) {
                row.add(a[i][j]);
            }
            result.add(row);
        }

        return result;
    }

    private static int[] largestZeroSumSubarray(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxLen = 0;
        int start = -1, end = -1;
        map.put(0, -1);

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (map.containsKey(sum)) {
                int prevIndex = map.get(sum);
                if (i - prevIndex > maxLen) {
                    maxLen = i - prevIndex;
                    start = prevIndex + 1;
                    end = i;
                }
            } else {
                map.put(sum, i);
            }
        }
        return new int[] { start, end }; // Return the start and end indices of the largest zero-sum subarray
    }
}
