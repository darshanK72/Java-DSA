import java.util.Scanner;

public class j06CountZerosInSortedMatrix {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        System.out.println(countZeros(arr, n));
        in.close();
    }

    public static int countZeros(int A[][], int N) {
        int ans = 0;
        for (int i = 0; i < A.length; i++) {
            int oneCount = countOnesInRow(A[i]);
            ans += oneCount;
        }
        return N * N - ans;
    }

    public static int countOnesInRow(int[] arr) {
        int s = 0;
        int e = arr.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] == 0) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        if (s == arr.length)
            return 0;
        if (e == -1)
            return arr.length;
        return arr.length - s;
    }

    // Staircase Algorithm
    public static int countZerosEfficient(int A[][], int N) {
        int zeros = 0;
        int row = 0;
        int col = N - 1;
        ;
        while (row < N && col >= 0) {
            if (A[row][col] == 0) {
                zeros += col + 1;
                row++;
            } else {
                col--;
            }
        }
        return zeros;
    }
}
