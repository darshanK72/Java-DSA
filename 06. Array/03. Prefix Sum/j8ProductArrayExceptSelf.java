import java.util.Arrays;
import java.util.Scanner;

public class j8ProductArrayExceptSelf {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(Arrays.toString(productExceptSelf(arr)));
        System.out.println(Arrays.toString(productExceptSelfEfficient(arr)));
        in.close();
    }

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] preProd = new int[n];
        int[] suffProd = new int[n];
        preProd[0] = 1;
        suffProd[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            preProd[i] = (nums[i - 1] * preProd[i - 1]);
            suffProd[n - i - 1] = (nums[n - i] * suffProd[n - i]);
        }

        int[] out = new int[n];
        for (int i = 0; i < n; i++) {
            out[i] = preProd[i] * suffProd[i];
        }

        return out;
    }

    public static int[] productExceptSelfEfficient(int[] nums) {
        int p = 1;
        int zeros = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                zeros++;
            else
                p *= nums[i];
        }
        int[] out = new int[nums.length];
        if (zeros > 1) {
            return out;
        } else if (zeros == 1) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0)
                    out[i] = p;
            }
        } else {
            for (int i = 0; i < nums.length; i++) {
                out[i] = p / nums[i];
            }
        }
        return out;
    }
}
