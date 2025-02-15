import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j21NQueens {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        printNQueenArrangements(new boolean[n][n], "", 0);

        List<List<String>> set = new ArrayList<>();
        placeNQueens(new boolean[n][n], n, 0, set);
        System.out.println(set);
        in.close();
    }

    public static void printNQueenArrangements(boolean[][] chess, String ans, int row) {
        if (row == chess.length) {
            System.out.println(ans);
            return;
        }
        for (int col = 0; col < chess.length; col++) {
            if (canPlaceQueen(chess, row, col)) {
                chess[row][col] = true;
                printNQueenArrangements(chess, ans + row + "-" + col + ",", row + 1);
                chess[row][col] = false;
            }
        }
    }

    public static void placeNQueens(boolean[][] mat, int n, int row, List<List<String>> set) {
        if (row == n) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String s = "";
                for (int j = 0; j < n; j++) {
                    if (mat[i][j]) {
                        s += "Q";
                    } else {
                        s += ".";
                    }
                }
                list.add(s);
            }
            set.add(list);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (canPlaceQueen(mat, row, i)) {
                mat[row][i] = true;
                placeNQueens(mat, n, row + 1, set);
                mat[row][i] = false;
            }
        }
    }

    public static boolean canPlaceQueen(boolean[][] mat, int row, int col) {
        for (int i = row - 1; i >= 0; i--) {
            if (mat[i][col])
                return false;
        }

        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (mat[i][j])
                return false;
        }

        for (int i = row - 1, j = col + 1; i >= 0 && j < mat[0].length; i--, j++) {
            if (mat[i][j])
                return false;
        }

        return true;
    }
}