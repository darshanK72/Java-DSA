import java.util.Scanner;

public class j02FloorIndexInSortedArray {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong();
        }
        long target = in.nextInt();
        System.out.println(findFloor(arr, n, target));
        in.close();
    }

    // Floor Index returns e (the index of the largest element that is less than or equal to x).
    public static int findFloor(long arr[], int n, long x) {
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
        return e;
    }
}
