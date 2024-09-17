import java.util.Scanner;

public class j07SumIthRowIthCol {
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
        System.out.println(sumOfRowCol(row, col, matrix));
        in.close();
    }

    public static int sumOfRowCol(int N, int M, int A[][]) {
        int n = Math.min(N, M);
        for (int i = 0; i < n; i++) {
            int rowSum = 0;
            for (int j = 0; j < N; j++) {
                rowSum += A[j][i];
            }
            int colSum = 0;
            for (int j = 0; j < M; j++) {
                colSum += A[i][j];
            }
            if (rowSum != colSum)
                return 0;
        }
        return 1;
    }
}
