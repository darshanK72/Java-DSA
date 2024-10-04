import java.util.Scanner;

// The score of an array is defined as the product of its sum and its length.
// For example, the score of [1, 2, 3, 4, 5] is (1 + 2 + 3 + 4 + 5) * 5 = 75.

public class j05CountSubarraysScoreLessThanK {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        long k = in.nextLong();
        System.out.println(countSubarrays(arr, k));
        in.close();
    }

    public static long countSubarrays(int[] nums, long k) {
        long currentSum = 0;
        int j = 0;
        long out = 0;
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            while (currentSum * (i - j + 1) >= k) {
                currentSum -= nums[j];
                j++;
            }
            out += (i - j + 1);
        }
        return out;
    }
}
