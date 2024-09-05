import java.util.HashMap;
import java.util.Scanner;

public class j37LongestEqualBinarySubarray {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(longestContineousBinarySubarray(arr));
        in.close();
    }

    public static int longestContineousBinarySubarray(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ans = 0;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i] == 1 ? arr[i] : -1;
            if (map.containsKey(sum)) {
                ans = Math.max(ans, i - map.get(sum));
            }else
                map.put(sum, i);
        }
        return ans;
    }
}
