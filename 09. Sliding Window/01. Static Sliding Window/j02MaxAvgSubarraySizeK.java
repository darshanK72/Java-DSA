import java.util.Scanner;

public class j02MaxAvgSubarraySizeK {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(findMaxAverage(nums, k));
        System.out.println(findMaxAverageEfficient(nums, k));
        in.close();
    }

    public static double findMaxAverage(int[] nums, int k) {
        double ans = (double) Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            double sum = 0.0;
            for (int j = i; i < k; i++) {
                sum += nums[j];
            }
            ans = Math.max(sum/k, ans);
        }
        return ans;
    }

    public static double findMaxAverageEfficient(int[] nums, int k) {
        double ans = (double) Integer.MIN_VALUE;
        double sum = 0.0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        ans = sum / k;
        for (int i = k; i < nums.length; i++) {
            sum -= nums[i - k];
            sum += nums[i];
            ans = Math.max(ans, (sum / k));
        }
        return ans;
    }
}
