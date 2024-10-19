import java.util.Scanner;

public class j07FindPeakElementII {
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
        System.out.println(findPeakGrid(arr));
        System.out.println(findPeakGridEfficient(arr));
        in.close();
    }

    public static int[] findPeakGrid(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                int point = mat[i][j];
                int top = i == 0 ? -1 : mat[i - 1][j];
                int bottom = i == mat.length - 1 ? -1 : mat[i + 1][j];
                int left = j == 0 ? -1 : mat[i][j - 1];
                int right = j == mat[0].length - 1 ? -1 : mat[i][j + 1];
                if (point > top && point > bottom && point > left && point > right)
                    return new int[] { i, j };
            }
        }
        return new int[] { -1, -1 };
    }

    public static int[] findPeakGridEfficient(int[][] mat) {
        int s = 0;
        int e = mat[0].length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            int maxIndex = getMaxIndex(mat, mid);
            int left = mid == 0 ? -1 : mat[maxIndex][mid - 1];
            int right = mid == mat[0].length - 1 ? -1 : mat[maxIndex][mid + 1];
            if (mat[maxIndex][mid] > left && mat[maxIndex][mid] > right) {
                return new int[] { maxIndex, mid };
            } else if (mat[maxIndex][mid] < left) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return new int[] { -1, -1 };
    }

    public static int getMaxIndex(int[][] mat, int col) {
        int maxValue = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < mat.length; i++) {
            if (mat[i][col] > maxValue) {
                maxValue = mat[i][col];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
