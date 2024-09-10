import java.util.Scanner;

public class j07MaxProductOfPair {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(maxProduct(arr));
        System.out.println(maxProductEfficient(arr));
        in.close();
    }

    public static int maxProduct(int[] nums) {
        int maxProd = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int prod = (nums[i] - 1) * (nums[j] - 1);
                if (prod > maxProd) {
                    maxProd = prod;
                }
            }
        }
        return maxProd;
    }

    public static int maxProductEfficient(int[] nums) {
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                secondMax = max;
                max = nums[i];
            } else if (nums[i] <= max && nums[i] > secondMax) {
                secondMax = nums[i];
            }
            if (nums[i] < min) {
                secondMin = min;
                min = nums[i];
            } else if (nums[i] >= max && nums[i] < secondMin) {
                secondMin = nums[i];
            }
        }

        int res = (max - 1) * (secondMax - 1);
        int res2 = (min - 1) * (secondMin - 1);
        return (res > res2) ? res : res2;
    }
}
