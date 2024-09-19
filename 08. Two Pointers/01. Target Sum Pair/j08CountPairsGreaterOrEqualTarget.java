import java.util.Arrays;
import java.util.Scanner;

public class j08CountPairsGreaterOrEqualTarget {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(twoSumGreaterOrEqualTarget(arr, k));
        in.close();
    }

    public static int twoSumGreaterOrEqualTarget(int[] nums, int target) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] >= target)
                    count++;
            }
        }
        return count;
    }

    public static int twoSumGreaterOrEqualTargetEfficient(int[] nums, int target) {
        Arrays.sort(nums);
        int count = 0;
        int s = 0;
        int e = nums.length - 1;
        while (s < e) {
            int sum = nums[s] + nums[e];
            if (sum >= target) {
                count += (e - s);
                e--;
            } else {
                s++;
            }
        }
        return count;
    }
}
