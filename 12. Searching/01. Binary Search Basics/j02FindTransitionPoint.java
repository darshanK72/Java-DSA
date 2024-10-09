import java.util.Scanner;

public class j02FindTransitionPoint {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(transitionPoint(arr, n));
        in.close();
    }

    public static int transitionPoint(int arr[], int n) {
        if (arr[n - 1] == 0)
            return -1;
        if (arr[0] == 1)
            return 0;
        int s = 0;
        int e = n - 1;
        while (s <= e) {
            int mid = (s + e) / 2;
            if (arr[mid] == 0) {
                s = mid + 1;
            } else if (arr[mid - 1] == 1) {
                e = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int transitionPointEfficient(int arr[], int n) {
        if (arr[n - 1] == 0)
            return -1;
        int s = 0;
        int e = n - 1;
        while (s <= e) {
            int mid = (s + e) / 2;
            if (arr[mid] == 0) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return s;
    }
}
