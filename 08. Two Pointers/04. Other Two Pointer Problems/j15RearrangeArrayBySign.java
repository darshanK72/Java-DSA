import java.util.Scanner;

public class j15RearrangeArrayBySign {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(rearrangeArray(arr));
        System.out.println(rearrangeArrayEfficient(arr));
        in.close();
    }

    public static int[] rearrangeArray(int[] nums) {
        int[] out = new int[nums.length];
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                out[k] = nums[i];
                k += 2;
            }
        }
        k = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                out[k] = nums[i];
                k += 2;
            }
        }
        return out;
    }

    public static int[] rearrangeArrayEfficient(int[] nums) {
        int[] out = new int[nums.length];
        int k = 0;
        int l = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                out[k] = nums[i];
                k += 2;
            } else {
                out[l] = nums[i];
                l += 2;
            }
        }
        return out;
    }
}
