import java.util.HashMap;
import java.util.Scanner;

public class j01CountPairsWithAbsDiffK {

    // Given an integer array nums and an integer k, return the number of pairs (i,
    // j) where i < j such that |nums[i] - nums[j]| == k.
    // The value of |x| is defined as:
    // x if x >= 0.
    // -x if x < 0.
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(countKDifference(arr, k));
        System.out.println(countKDifferenceHashMap(arr, k));
        in.close();
    }

    public static int countKDifference(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k)
                    count++;
            }
        }
        return count;
    }

    public static int countKDifferenceHashMap(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            count += map.getOrDefault(nums[i] - k, 0);
            count += map.getOrDefault(nums[i] + k, 0);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return count;
    }
}
