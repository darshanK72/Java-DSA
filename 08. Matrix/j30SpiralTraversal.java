import java.util.ArrayList;
import java.util.List;

public class j30SpiralTraversal {
    public List<Integer> spiralOrder(int[][] matrix) {
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
}
