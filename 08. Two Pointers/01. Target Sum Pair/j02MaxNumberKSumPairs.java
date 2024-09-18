import java.util.Arrays;
import java.util.Scanner;

public class j02MaxNumberKSumPairs {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int target = in.nextInt();
        System.out.println(maxOperations(nums, target));
        in.close();
    }

    public static int maxOperations(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -1)
                continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == -1)
                    continue;
                if (nums[i] + nums[j] == k) {
                    nums[i] = -1;
                    nums[j] = -1;
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public static int maxOperationsEfficient(int[] nums, int k) {
        Arrays.sort(nums);
        int s = 0;
        int e = nums.length - 1;
        int count = 0;
        while (s < e) {
            if (nums[s] + nums[e] == k) {
                nums[s] = -1;
                nums[e] = -1;
                s++;
                e--;
                count++;
            } else if (nums[s] + nums[e] > k)
                e--;
            else
                s++;
        }
        return count;
    }
}
