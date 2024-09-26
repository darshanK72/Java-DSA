import java.util.Arrays;
import java.util.Scanner;

public class j16NextPermutation {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        nextPermutation(arr);
        System.out.println(Arrays.toString(arr));
        in.close();
    }

    public static void nextPermutation(int[] nums) {
        int breakPoint = -1;
        int n = nums.length;
        for (int i = n - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                breakPoint = i - 1;
                break;
            }
        }
        if (breakPoint == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        for (int j = n - 1; j > breakPoint; j--) {
            if (nums[j] > nums[breakPoint]) {
                int temp = nums[breakPoint];
                nums[breakPoint] = nums[j];
                nums[j] = temp;
                break;
            }
        }
        reverse(nums, breakPoint + 1, nums.length - 1);
    }

    public static void reverse(int[] nums, int s, int e) {
        while (s < e) {
            int temp = nums[s];
            nums[s] = nums[e];
            nums[e] = temp;
            s++;
            e--;
        }
    }
}
