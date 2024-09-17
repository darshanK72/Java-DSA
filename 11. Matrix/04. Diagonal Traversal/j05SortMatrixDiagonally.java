import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class j05SortMatrixDiagonally {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        System.out.println(diagonalSort(matrix));
        in.close();
    }

    
    public static int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        for (int d = 1 - n; d <= m - 1; d++) {
            ArrayList<Integer> out = new ArrayList<Integer>();
            int row = d <= 0 ? 0 : d;
            int col = d <= 0 ? Math.abs(d) : 0;
            while (row < m && col < n) {
                out.add(mat[row][col]);
                row++;
                col++;
            }
            Collections.sort(out);
            int i = 0;
            row = d <= 0 ? 0 : d;
            col = d <= 0 ? Math.abs(d) : 0;
            while (row < m && col < n) {
                mat[row][col] = out.get(i++);
                row++;
                col++;
            }

        }
        return mat;
    }
}