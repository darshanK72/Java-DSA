import java.util.Scanner;

public class j03UpperBound {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int target = in.nextInt();
        System.out.println(getUpperBound(arr, n, target));
        in.close();
    }

    // Upper Bound returns s (the index of the first element strictly greater than x)
    public static int getUpperBound(int arr[], int n, int x) {
        int s = 0;
        int e = n - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] > x) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return s;
    }
}
