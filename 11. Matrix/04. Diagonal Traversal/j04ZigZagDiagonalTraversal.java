import java.util.Arrays;
import java.util.Scanner;

public class j04ZigZagDiagonalTraversal {
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
        if (row == col) {
            System.out.println(Arrays.toString(zigZagTraversalForSquareMatrix(matrix)));
        } else {
            System.out.println(Arrays.toString(zigZagTraversalForRactangleMatrix(matrix)));
        }
        in.close();
    }

    // O(N ^ 2)
    public static int[] zigZagTraversalForSquareMatrix(int[][] mat) {
        int n = mat.length;

        int[] out = new int[n * n];
        int k = 0;

        for (int d = 0; d < 2 * n - 1; d++) {
            int i = d < n ? 0 : d - n + 1;
            int j = d < n ? d : n - 1;

            while (i < n && j >= 0) {
                if (d % 2 == 1) {
                    out[k++] = mat[i][j];
                } else {
                    out[k++] = mat[j][i];
                }
                i++;
                j--;
            }
        }
        return out;
    }

    // O(M * N)
    public static int[] zigZagTraversalForRactangleMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] out = new int[m * n];
        int i = 0;
        for (int d = 0; d < m + n - 1; d++) {
            if (d % 2 == 1) {
                int row = d < n ? 0 : d - n + 1;
                int col = d < n ? d : n - 1;
                while (row < m & col >= 0) {
                    out[i++] = mat[row][col];
                    row++;
                    col--;
                }
            } else {
                int row = d < m ? d : m - 1;
                int col = d < m ? 0 : d - m + 1;
                while (row >= 0 & col < n) {
                    out[i++] = mat[row][col];
                    row--;
                    col++;
                }
            }
        }
        return out;
    }

}
