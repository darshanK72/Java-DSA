import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j30SpiralMatrixI {
    
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
        System.out.println(spiralOrder1(matrix));
        in.close();
    }

    public static List<Integer> spiralOrder1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int rowStart = 0;
        int rowEnd = m - 1;
        int colStart = 0;
        int colEnd = n - 1;

        ArrayList<Integer> out = new ArrayList<Integer>();

        while (true) {
            for (int i = colStart; i <= colEnd; i++) {
                out.add(matrix[rowStart][i]);
            }
            rowStart++;
            if (rowStart > rowEnd)
                break;
            for (int j = rowStart; j <= rowEnd; j++) {
                out.add(matrix[j][colEnd]);
            }
            colEnd--;
            if (colStart > colEnd)
                break;
            for (int i = colEnd; i >= colStart; i--) {
                out.add(matrix[rowEnd][i]);
            }
            rowEnd--;
            if (rowStart > rowEnd)
                break;
            for (int j = rowEnd; j >= rowStart; j--) {
                out.add(matrix[j][colStart]);
            }
            colStart++;
            if (colStart > colEnd)
                break;

        }
        return out;
    }

    public List<Integer> spiralOrder2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int rowStart = 0;
        int rowEnd = m - 1;
        int colStart = 0;
        int colEnd = n - 1;

        ArrayList<Integer> out = new ArrayList<Integer>();

        while (rowStart <= rowEnd && colStart <= colEnd) {
            for (int i = colStart; i <= colEnd; i++) {
                out.add(matrix[rowStart][i]);
            }
            rowStart++;

            for (int j = rowStart; j <= rowEnd; j++) {
                out.add(matrix[j][colEnd]);
            }
            colEnd--;
            if (rowStart <= rowEnd) {
                for (int i = colEnd; i >= colStart; i--) {
                    out.add(matrix[rowEnd][i]);
                }
                rowEnd--;
            }
            if (colStart <= colEnd) {
                for (int j = rowEnd; j >= rowStart; j--) {
                    out.add(matrix[j][colStart]);
                }
                colStart++;
            }
        }
        return out;
    }
}
