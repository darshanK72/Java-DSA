import java.util.Scanner;

public class j04ShellRotateByOne {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Input: matrix dimensions and elements
        int row = in.nextInt();
        int col = in.nextInt();
        int[][] matrix = new int[row][col];

        // Reading matrix elements
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt();
            }
        }

        // Rotate the matrix by one shell and print the result
        rotateMatrix(row, col, matrix);

        // Print the rotated matrix
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        in.close();
    }

    /**
     * Method to rotate the matrix shell by one position (clockwise).
     * 
     * @param M      Number of rows in the matrix.
     * @param N      Number of columns in the matrix.
     * @param Mat    The matrix to be rotated.
     * @return       The matrix after rotation.
     */
    public static int[][] rotateMatrix(int M, int N, int[][] Mat) {
        int rowStart = 0;
        int rowEnd = M - 1;
        int colStart = 0;
        int colEnd = N - 1;

        // If matrix has only one row or column, no rotation needed
        if (M == 1 || N == 1)
            return Mat;

        // Process each shell
        while (rowStart < rowEnd && colStart < colEnd) {
            int prev = Mat[rowStart + 1][colStart]; // Start with the first element below the top-left corner

            // Top row (left to right)
            for (int j = colStart; j <= colEnd; j++) {
                int curr = Mat[rowStart][j];
                Mat[rowStart][j] = prev;
                prev = curr;
            }
            rowStart++;

            // Right column (top to bottom)
            for (int i = rowStart; i <= rowEnd; i++) {
                int curr = Mat[i][colEnd];
                Mat[i][colEnd] = prev;
                prev = curr;
            }
            colEnd--;

            // Bottom row (right to left)
            if (rowStart <= rowEnd) { // Ensure we still have rows to process
                for (int j = colEnd; j >= colStart; j--) {
                    int curr = Mat[rowEnd][j];
                    Mat[rowEnd][j] = prev;
                    prev = curr;
                }
                rowEnd--;
            }

            // Left column (bottom to top)
            if (colStart <= colEnd) { // Ensure we still have columns to process
                for (int i = rowEnd; i >= rowStart; i--) {
                    int curr = Mat[i][colStart];
                    Mat[i][colStart] = prev;
                    prev = curr;
                }
                colStart++;
            }
        }

        return Mat;
    }
}
