import java.util.Scanner;
import java.util.Arrays;

public class j01SearchInRowSortedMatrix {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        int target = in.nextInt();
        System.out.println(Arrays.toString(searchInRowSortedMatrix(arr, target)));
        in.close();
    }

    public static int[] searchInRowSortedMatrix(int[][] arr, int target) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (target >= arr[i][0] && target <= arr[i][n - 1] ) {
                int j = binarySearch(arr[i], target);
                if (j != -1)
                    return new int[] { i, j };
            }
        }
        return new int[] { -1, -1 };
    }

    public static int binarySearch(int[] arr, int target) {
        int s = 0;
        int e = arr.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return -1;
    }
}