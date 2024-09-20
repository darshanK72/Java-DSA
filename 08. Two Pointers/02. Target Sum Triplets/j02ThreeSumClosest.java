import java.util.Arrays;
import java.util.Scanner;

public class j02ThreeSumClosest {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int target = in.nextInt();
        System.out.println(threeSumClosest(nums, target));
        System.out.println(threeSumClosestEfficient(nums, target));
        in.close();
    }

    public static int threeSumClosest(int[] nums, int target) {
        int ans = 0;
        int maxDiff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    int d = Math.abs(target - sum);
                    if (d < maxDiff) {
                        maxDiff = d;
                        ans = sum;
                    }
                }
            }
        }
        return ans;
    }

    public static int threeSumClosestEfficient(int[] nums, int target) {
        Arrays.sort(nums);
        int dist = Integer.MAX_VALUE;
        int ans = 0;
        int i = 0;
        while (i < nums.length) {
            int j = i + 1;
            int k = nums.length - 1;
            int tar = target - nums[i];
            while (j < k) {
                int sum = nums[j] + nums[k];
                int d = Math.abs(tar - sum);
                if (d < dist) {
                    ans = sum + nums[i];
                    dist = d;
                } else if (sum > tar) {
                    k--;
                } else {
                    j++;
                }
            }
            i++;
        }
        return ans;
    }
}
