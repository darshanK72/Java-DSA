import java.util.HashMap;
import java.util.Scanner;

public class j14CountBinarySubarraysWithSumK {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(numSubarraysWithSumHashMap(arr, k));
        System.out.println(numSubarraysWithSum(arr, k));
        in.close();
    }

    public static int numSubarraysWithSumHashMap(int[] nums, int goal) {
        int sum = 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            count += map.getOrDefault(sum - goal, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static int numSubarraysWithSum(int[] nums, int goal) {
        return subarraysLessThanGoal(nums, goal) - subarraysLessThanGoal(nums, goal - 1);
    }

    public static int subarraysLessThanGoal(int[] nums, int goal) {
        int ans = 0;
        int j = 0;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum > goal && j <= i) {
                sum -= nums[j];
                j++;
            }
            ans += i - j + 1;
        }
        return ans;
    }
}
