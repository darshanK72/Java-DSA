import java.util.Arrays;
import java.util.Scanner;

public class j06PairSumClosestToK {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(Arrays.toString(sumClosest(arr, k)));
        in.close();
    }

    public static int[] sumClosest(int[] arr, int k) {
        int s = 0;
        int e = arr.length - 1;
        int minDiff = Integer.MAX_VALUE;
        int[] out = new int[2];
        while (s < e) {
            int sum = arr[s] + arr[e];
            int d = Math.abs(sum - k);
            if (d < minDiff) {
                out[0] = arr[s];
                out[1] = arr[e];
                minDiff = d;
            } else if (sum > k) {
                e--;
            } else {
                s++;
            }
        }
        return out;
    }
}
