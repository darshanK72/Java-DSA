import java.util.Scanner;

public class j07CountSubarraysMaxBounded {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int left = in.nextInt();
        int right = in.nextInt();
        System.out.println(numSubarrayBoundedMax1(arr, left, right));
        System.out.println(numSubarrayBoundedMax2(arr, left, right));
        in.close();
    }

    public static int numSubarrayBoundedMax1(int[] nums, int left, int right) {
        int i = 0;
        int j = 0;
        int ans = 0;
        int current = 0;
        while (i < nums.length) {
            if (nums[i] > right) {
                current = 0;
                j = i + 1;
            } else if (nums[i] >= left) {
                current = i - j + 1;
            }
            ans += current;
            i++;
        }
        return ans;
    }

    public static int numSubarrayBoundedMax2(int[] nums, int left, int right) {
        return countSubarrayMaxLessThanK(nums, right) - countSubarrayMaxLessThanK(nums, left - 1);
    }

    public static int countSubarrayMaxLessThanK(int arr[], int k) {
        int ans = 0;
        int current = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= k) {
                current++;
            } else {
                current = 0;
            }
            ans += current;
        }
        return ans;
    }
}
