import java.util.HashMap;
import java.util.Scanner;

public class j05CheckPairsDivisibleByK {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(checkPairsDivisibleByK(arr, k));
        in.close();
    }

    public static boolean checkPairsDivisibleByK(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int rem = (arr[i] % k + k) % k;
            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }

        for (int i = 0; i < arr.length; i++) {
            int rem = (arr[i] % k + k) % k;
            if (rem == 0) {
                if (map.get(rem) % 2 != 0)
                    return false;
            } else {
                int count1 = map.get(rem);
                int count2 = map.getOrDefault(k - rem, 0);
                if (count1 != count2)
                    return false;
            }
        }
        return true;
    }
}
