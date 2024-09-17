import java.util.Scanner;

public class j08RowColContainsAllNums {
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
        System.out.println(checkValid(matrix));
        in.close();
    }

    public static boolean checkValid(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            int[] map = new int[matrix.length];
            for (int j = 0; j < matrix.length; j++) {
                map[matrix[i][j] - 1]++;
            }
            for (int j = 0; j < map.length; j++) {
                if (map[j] == 0 || map[j] > 1)
                    return false;
            }
            map = new int[matrix.length];
            for (int j = 0; j < matrix.length; j++) {
                map[matrix[j][i] - 1]++;
            }
            for (int j = 0; j < map.length; j++) {
                if (map[j] == 0 || map[j] > 1)
                    return false;
            }
        }
        return true;
    }
}
