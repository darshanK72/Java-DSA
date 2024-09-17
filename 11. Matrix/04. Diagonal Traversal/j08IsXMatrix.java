import java.util.Scanner;

public class j08IsXMatrix {
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
        System.out.println(checkXMatrix(matrix));
        in.close();
    }

    public static boolean checkXMatrix(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (i == j || i + j == grid.length - 1) {
                    if (grid[i][j] == 0)
                        return false;
                } else {
                    if (grid[i][j] != 0)
                        return false;
                }
            }
        }
        return true;
    }
}
