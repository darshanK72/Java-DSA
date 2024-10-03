import java.util.HashSet;
import java.util.Scanner;

public class j08MaximumErasureValue {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(maximumUniqueSubarray(arr));
        System.out.println(maximumUniqueSubarrayEfficient(arr));
        in.close();
    }

    public static int maximumUniqueSubarray(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            HashSet<Integer> set = new HashSet<>();
            for (int j = i; j < nums.length; j++) {
                if (set.contains(nums[j])) {
                    break;
                } else {
                    sum += nums[j];
                    set.add(nums[j]);
                }
            }
            ans = Math.max(sum, ans);
        }
        return ans;
    }

    public static int maximumUniqueSubarrayEfficient(int[] nums) {
        int ans = 0;
        int sum = 0;
        int j = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            while (set.contains(nums[i])) {
                set.remove(nums[j]);
                sum -= nums[j];
                j++;
            }

            set.add(nums[i]);
            sum += nums[i];
            ans = Math.max(sum, ans);
        }

        return ans;
    }
}
