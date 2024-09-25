import java.util.HashMap;
import java.util.Scanner;

public class j12TuplesWithSameProduct {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(tupleSameProduct(arr));
        in.close();
    }

    public static int tupleSameProduct(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int p = nums[i] * nums[j];
                map.put(p, map.getOrDefault(p, 0) + 1);
            }
        }
        int count = 0;
        for (Integer key : map.keySet()) {
            int v = map.get(key);
            count += (v * (v - 1)) / 2;
        }
        return 8 * count;
    }
}
