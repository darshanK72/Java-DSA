import java.util.HashMap;

public class j04LargestEqualBinarySubmatrix {
    public static int maximumArea(int[][] mat, int n, int m) {
        // Step 1: Convert the matrix so that 0 becomes -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    mat[i][j] = -1;
                }
            }
        }

        // Step 2: Now we need to find the largest zero-sum submatrix
        int maxArea = 0;

        // Iterate over all possible pairs of rows
        for (int startRow = 0; startRow < n; startRow++) {
            int[] colPrefixSum = new int[m];

            for (int endRow = startRow; endRow < n; endRow++) {
                // Add the values of the current row to colPrefixSum
                for (int col = 0; col < m; col++) {
                    colPrefixSum[col] += mat[endRow][col];
                }

                // Find the largest zero-sum subarray in colPrefixSum
                int area = largestBinaryArray(colPrefixSum, endRow - startRow + 1);

                // Update the maximum area
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }

    // Helper function to find the largest zero sum subarray in a 1D array
    // Returns the area of the largest zero sum subarray (row count * subarray
    // length)
    private static int largestBinaryArray(int[] arr, int rowCount) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxLength = 0;
        map.put(0, -1); // To handle the case where the zero sum starts from index 0

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (map.containsKey(sum)) {
                // Calculate the length of the subarray
                int startIndex = map.get(sum);
                int subarrayLength = i - startIndex;

                // Calculate the area of the submatrix
                int area = subarrayLength * rowCount;
                maxLength = Math.max(maxLength, area);
            } else {
                map.put(sum, i);
            }
        }

        return maxLength;
    }

}
