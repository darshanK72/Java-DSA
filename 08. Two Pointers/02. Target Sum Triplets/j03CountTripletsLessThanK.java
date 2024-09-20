import java.util.Arrays;
import java.util.Scanner;

public class j03CountTripletsLessThanK {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong();
        }
        int target = in.nextInt();
        System.out.println(countTriplets(n, target, arr));
        System.out.println(countTripletsEfficient(n, target, arr));
        in.close();
    }

    public static long countTriplets(int n, int sum, long arr[]) {
        long count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    long s = arr[i] + arr[j] + arr[k];
                    if (s < sum) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static long countTripletsEfficient(int n, int sum, long arr[]) {
        Arrays.sort(arr);
        long count = 0;
        int i = 0;
        while (i < n) {
            int s = i + 1;
            int e = n - 1;
            long tar = sum - arr[i];
            while (s < e) {
                long ss = arr[s] + arr[e];
                if (ss < tar) {
                    count += e - s;
                    s++;
                } else {
                    e--;
                }
            }
            i++;
        }
        return count;
    }
}
