import java.util.Scanner;

public class j02MinimumSizeSubarraySum {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(minSubArrayLen(k, arr));
        in.close();
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        int currentSum = 0;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            while (currentSum >= target) {
                minLength = Math.min(minLength, i - j + 1);
                currentSum -= nums[j];
                j++;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
