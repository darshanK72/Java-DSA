import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j01SaddlePoint {
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
        System.out.println(getSaddlePoint(matrix));
        in.close();
    }

    public static List<Integer> getSaddlePoint(int[][] matrix) {
        ArrayList<Integer> out = new ArrayList<Integer>();
        for (int i = 0; i < matrix.length; i++) {
            int minColIndex = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] < matrix[i][minColIndex])
                    minColIndex = j;
            }
            int maxRowIndex = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[j][minColIndex] > matrix[maxRowIndex][minColIndex])
                    maxRowIndex = j;
            }

            if (maxRowIndex == i) {
                out.add(matrix[maxRowIndex][minColIndex]);
                break;
            }
        }
        return out;
    }
}
