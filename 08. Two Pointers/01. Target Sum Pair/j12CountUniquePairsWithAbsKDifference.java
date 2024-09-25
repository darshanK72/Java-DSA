import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class j12CountUniquePairsWithAbsKDifference {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(countPairs(arr, k));
        System.out.println(countPairsEfficient(arr, k));
        in.close();
    }

    public static int countPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int s = 0;
        int e = 1;
        int count = 0;
        HashSet<String> set = new HashSet<>();
        while (e < nums.length) {
            int diff = Math.abs(nums[s] - nums[e]);
            if (diff == k && s != e) {
                if (!set.contains(nums[s] + "#" + nums[e]))
                    count++;
                set.add(nums[s] + "#" + nums[e]);
                e++;
                s++;
            } else if (diff > k) {
                s++;
            } else {
                e++;
            }

            if (s == e)
                e++;
        }
        return count;
    }

    public static int countPairsEfficient(int[] nums, int k) {
        Arrays.sort(nums);
        int s = 0;
        int e = 1;
        int count = 0;
        while (e < nums.length) {
            int diff = Math.abs(nums[s] - nums[e]);
            if (diff == k && s != e) {
                count++;
                e++;
                s++;
                while (e < nums.length && nums[e] == nums[e - 1]) {
                    e++;
                }
                while (s < e && nums[s] == nums[s - 1]) {
                    s++;
                }
            } else if (diff > k) {
                s++;
            } else {
                e++;
            }

            if (s == e)
                e++;
        }
        return count;
    }
}
