import java.util.Scanner;

public class j07ClosestNumber {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int target = in.nextInt();
        System.out.println(findClosest(n, target, arr));
        in.close();
    }

    public static int findClosest(int n, int k, int[] arr) {
        int s = 0;
        int e = n - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] == k)
                return arr[mid];
            else if (arr[mid] > k) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        int floorDist = e == -1 ? Integer.MAX_VALUE : k - arr[e];
        int ceilDist = s == n ? Integer.MAX_VALUE : arr[s] - k;

        if (floorDist < ceilDist) {
            return arr[e];
        } else {
            return arr[s];
        }
    }
}
