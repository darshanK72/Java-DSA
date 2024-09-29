import java.util.Arrays;
import java.util.Scanner;

public class j03KRadiusSubarrayAverages {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(Arrays.toString(getAverages(nums, k)));
        in.close();
    }

    public static int[] getAverages(int[] nums, int k) {
        int[] out = new int[nums.length];
        Arrays.fill(out, -1);
        int s = 2 * k + 1;
        if (s > nums.length) {
            return out;
        }
        long sum = 0;
        for (int i = 0; i < s - 1; i++) {
            sum += nums[i];
        }

        for (int i = k; i < nums.length - k; i++) {
            sum += nums[i + k];
            out[i] = (int) (sum / s);
            sum -= nums[i - k];
        }

        return out;
    }
}
