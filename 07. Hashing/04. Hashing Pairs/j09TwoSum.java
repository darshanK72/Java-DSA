import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class j09TwoSum {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(Arrays.toString(twoSumEfficient(arr, k)));
        in.close();
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    ans[0] = nums[0];
                    ans[1] = nums[1];
                    break;
                }
            }
        }
        return ans;
    }

    public static int[] twoSumEfficient(int[] nums, int target) {
        int[] ans = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                ans[0] = map.get(target - nums[i]);
                ans[1] = i;
                break;
            } else {
                map.put(nums[i], i);
            }
        }
        return ans;
    }
}
