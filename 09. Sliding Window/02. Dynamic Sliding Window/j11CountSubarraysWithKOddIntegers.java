import java.util.Scanner;

public class j11CountSubarraysWithKOddIntegers {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(numberOfSubarrays(arr, k));
        in.close();
    }

    public static int numberOfSubarrays(int[] nums, int k) {
        return atMostKOdd(nums, k) - atMostKOdd(nums, k - 1);
    }

    public static int atMostKOdd(int[] nums, int k) {
        int odds = 0;
        int ans = 0;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1)
                odds++;

            while (odds > k && j <= i) {
                if (nums[j] % 2 == 1) {
                    odds--;
                }
                j++;
            }
            ans += (i - j + 1);
        }
        return ans;
    }
}
