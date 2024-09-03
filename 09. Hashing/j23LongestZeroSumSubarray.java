import java.util.HashMap;
import java.util.Scanner;

public class j23LongestZeroSumSubarray {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(maxLen(arr, n));
        System.out.println(maxLenHashMap(arr, n));
        in.close();
    }

     // O(N^2)
     public static int maxLen(int arr[], int n) {
        int maxL = 0;
        for (int i = 0; i < n; i++) {
            int s = 0;
            for (int j = i; j < n; j++) {
                s += arr[j];
                if (s == 0)
                   maxL = Math.max(maxL,j - i + 1);
            }
        }
        return maxL;
    }

    // O(N)
    public static int maxLenHashMap(int arr[], int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxL = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (sum == 0) {
                maxL = i + 1;
            } else if (map.containsKey(sum)) {
                maxL = Math.max(maxL, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return maxL;
    }
}
