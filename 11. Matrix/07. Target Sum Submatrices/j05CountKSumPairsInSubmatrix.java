import java.util.Scanner;

public class j05CountKSumPairsInSubmatrix {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] matrix1 = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix1[i][j] = in.nextInt();
            }
        }
        int[][] matrix2 = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix2[i][j] = in.nextInt();
            }
        }
        int k = in.nextInt();
        System.out.println(countPairs(matrix1, matrix2, n, k));
        System.out.println(countPairsEfficient(matrix1, matrix2, n, k));
        in.close();
    }

    public static int countPairs(int mat1[][], int mat2[][], int n, int x) {
        int count = 0;
        int[] fmat1 = new int[n * n];
        int[] fmat2 = new int[n * n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fmat1[k] = mat1[i][j];
                k++;
            }
        }
        k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fmat2[k] = mat2[i][j];
                k++;
            }
        }

        int s = 0;
        int e = n * n - 1;
        while (s < n * n && e >= 0) {
            int sum = fmat1[s] + fmat2[e];
            if (sum == x) {
                count++;
                s++;
                e--;
            } else if (sum > x) {
                e--;
            } else {
                s++;
            }
        }
        return count;
    }

    public static int countPairsEfficient(int mat1[][], int mat2[][], int n, int x) {
        int rowStart = 0;
        int colStart = 0;
        int rowEnd = n - 1;
        int colEnd = n - 1;
        int count = 0;
        while (rowStart < n && colStart < n && rowEnd >= 0 && colEnd <= n) {
            int sum = mat1[rowStart][colStart] + mat2[rowEnd][colEnd];
            if (sum == x) {
                count++;
                if (colStart < n - 1) {
                    colStart++;
                } else {
                    rowStart++;
                    colStart = 0;
                }
                if (colEnd > 0) {
                    colEnd--;
                } else {
                    rowEnd--;
                    colEnd = n - 1;
                }
            } else if (sum > x) {
                if (colEnd > 0) {
                    colEnd--;
                } else {
                    rowEnd--;
                    colEnd = n - 1;
                }
            } else {
                if (colStart < n - 1) {
                    colStart++;
                } else {
                    rowStart++;
                    colStart = 0;
                }
            }
        }
        return count;
    }
}
