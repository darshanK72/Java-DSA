import java.util.HashMap;
import java.util.Scanner;

public class j14LongestEqualZeroOneTwoSubarray {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(longestEqual012(arr));
        in.close();
    }

    public static int longestEqual012(int[] arr) {
        int zeros = 0;
        int ones = 0;
        int twos = 0;
        int maxL = -1;
        HashMap<String, Integer> map = new HashMap<>();
        map.put("0,0", -1);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0)
                zeros++;
            else if (arr[i] == 1)
                ones++;
            else
                twos++;

            String key = (zeros - ones) + "," + (ones - twos);
            if (map.containsKey(key)) {
                maxL = Math.max(maxL, i - map.get(key));
            } else {
                map.put(key, i);
            }
        }
        return maxL;
    }
}
