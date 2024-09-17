import java.util.ArrayList;
import java.util.Scanner;

public class j09SumOfUpperLower {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        System.out.println(sumTriangles(matrix, n));
        in.close();
    }

    public static ArrayList<Integer> sumTriangles(int matrix[][], int n) {
        int upSum = 0;
        int lowSum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                upSum += matrix[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                lowSum += matrix[i][j];
            }
        }

        ArrayList<Integer> out = new ArrayList<Integer>();
        out.add(upSum);
        out.add(lowSum);
        return out;
    }
}
