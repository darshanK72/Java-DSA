import java.util.Scanner;

public class j03SearchInRowColSortedMatrixI {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        int target = in.nextInt();
        System.out.println(searchMatrix(arr, target));
        in.close();
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int s = 0;
        int e = m * n - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            int row = mid / n;
            int col = mid % n;
            if (matrix[row][col] == target)
                return true;
            else if (matrix[row][col] > target) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return false;
    }
}
