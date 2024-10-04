import java.util.Scanner;

public class j06SubarrayProductLessThanK {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(numSubarrayProductLessThanK(arr, k));
        in.close();
    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0)
            return 0;
        int currentProd = 1;
        int j = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            currentProd *= nums[i];
            while (currentProd >= k && j < i) {
                currentProd /= nums[j];
                j++;
                if (j == nums.length)
                    return ans;
            }
            if (currentProd < k)
                ans += (i - j + 1);
        }
        return ans;
    }
}
