import java.util.HashMap;
import java.util.Scanner;

public class j26LongestKSumSubarray {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(longestKSumSubarray(arr, n, k));
        System.out.println(longestKSumSubarrayHashMap(arr, n, k));
        in.close();
    }

    public static int longestKSumSubarray(int[] arr, int n, int k) {
        int maxL = 0;
        for (int i = 0; i < n; i++) {
            int s = 0;
            for (int j = i; j < n; j++) {
                s += arr[j];
                if (s == k) {
                    maxL = Math.max(maxL, j - i + 1);
                }
            }
        }
        return maxL;
    }

    public static int longestKSumSubarrayHashMap(int arr[], int n, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxL = 0;
        map.put(0,-1);
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                maxL = Math.max(maxL, i - map.get(sum - k));
            }
            if(!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return maxL;
    }
}
