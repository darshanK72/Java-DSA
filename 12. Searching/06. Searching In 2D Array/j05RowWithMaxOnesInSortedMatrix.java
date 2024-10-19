import java.util.Scanner;

public class j05RowWithMaxOnesInSortedMatrix {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        System.out.println(rowWithMax1s(arr));
        in.close();
    }

    public static int rowWithMax1s(int arr[][]) {
        int max = 0;
        int ans = -1;
        for (int i = 0; i < arr.length; i++) {
            int oneCount = countOnesInRow(arr[i]);
            if (oneCount > max && oneCount != 0) {
                max = oneCount;
                ans = i;
            }
        }
        return ans;
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
        return arr.length - e;
    }
}
