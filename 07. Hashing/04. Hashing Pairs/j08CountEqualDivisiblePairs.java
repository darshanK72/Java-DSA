import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class j08CountEqualDivisiblePairs {

    // Given a 0-indexed integer array nums of length n and an integer k, return the
    // number of pairs (i, j) where 0 <= i < j < n, such that nums[i] == nums[j] and
    // (i * j) is divisible by k.

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(countEqualDivisiblePairs(arr, k));
        in.close();
    }

    public static int countEqualDivisiblePairs(int[] nums, int k) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int c = 0;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                for (int j : map.get(nums[i])) {
                    if (i * j % k == 0)
                        c++;
                }
            }
            map.putIfAbsent(nums[i], new ArrayList<Integer>());
            map.get(nums[i]).add(i);
        }
        return c;
    }
}
