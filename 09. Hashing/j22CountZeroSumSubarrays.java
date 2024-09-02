import java.util.HashMap;
import java.util.Scanner;

public class j22CountZeroSumSubarrays {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong();
        }
        System.out.println(findSubarray(arr, n));
        in.close();
    }

    public static long findSubarray(long[] arr, int n) {
        HashMap<Long, Long> map = new HashMap<>();
        long sum = 0;
        int count = 0;
        map.put(0L, 1L);
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            count += map.getOrDefault(sum, 0L);
            map.put(sum, map.getOrDefault(sum, 0L) + 1);
        }
        return count;
    }
}
