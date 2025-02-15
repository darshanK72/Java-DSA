import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class j22KnightTours {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] chess = new int[n][n];
        // knightTour(chess, 0, 0, 1);
        // System.out.println(Arrays.deepToString(chess));
        System.out.println(Arrays.deepToString(knightTour(n, n)));
        in.close();
    }

    public static void knightTour(int[][] chess, int row, int col, int move) {
        if (row < 0 || col < 0 || row >= chess.length || col >= chess.length || chess[row][col] > 0) {
            return;
        } else if (move == chess.length * chess.length) {
            chess[row][col] = move;
            chess[row][col] = 0;
            return;
        }

        chess[row][col] = move;
        knightTour(chess, row - 2, col + 1, move + 1);
        knightTour(chess, row - 1, col + 2, move + 1);
        knightTour(chess, row + 1, col + 2, move + 1);
        knightTour(chess, row + 2, col + 1, move + 1);

        knightTour(chess, row + 2, col - 1, move + 1);
        knightTour(chess, row + 1, col - 2, move + 1);
        knightTour(chess, row - 1, col - 2, move + 1);
        knightTour(chess, row - 2, col - 1, move + 1);
        chess[row][col] = 0;
    }

    public static int[][] knightTour(int rows, int cols) {
        int[] dx = { -1, -1, +1, +1, +2, +2, -2, -2 };
        int[] dy = { +2, -2, -2, +2, -1, +1, -1, +1 };
        int[][] chess = new int[rows][cols];

        for (int i = 0; i < rows; i++)
            Arrays.fill(chess[i], -1);

        if (generateKnightTours(chess, dx, dy, 0, 0, 0))
            System.out.println("Possible");

        return chess;
    }

    public static boolean generateKnightTours(int[][] chess, int[] dx, int[] dy, int r, int c, int count) {
        int rows = chess.length, cols = chess[0].length;
        if (count == rows * cols)
            return true;
        if (r < 0 || r >= rows || c < 0 || c >= cols)
            return false;
        if (chess[r][c] != -1)
            return false;

        for (int i = 0; i < 8; i++) {
            chess[r][c] = count;
            if (generateKnightTours(chess, dx, dy, r + dx[i], c + dy[i], count + 1) == true)
                return true;
            chess[r][c] = -1;
        }

        return false;
    }

}