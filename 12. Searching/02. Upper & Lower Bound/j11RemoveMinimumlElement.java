import java.util.Arrays;
import java.util.Scanner;

public class j11RemoveMinimumlElement {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(minRemoval(arr));
        in.close();
    }

    public static int minRemoval(int[] arr) {
        Arrays.sort(arr);
        int maxL = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int j = ceilIndex(i, arr, 2 * arr[i]);
            maxL = Math.max(maxL, j - i + 1);
        }
        return arr.length - maxL;
    }

    public static int ceilIndex(int s, int[] arr, int target) {
        int e = arr.length - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] > target) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return e;
    }
}
