import java.util.Scanner;

public class j03FirstMissingPositive {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        System.out.println(firstMissingPositive(arr));
        in.close();
    }

    public static int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 || nums[i] > nums.length) {
                nums[i] = 0;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int original = nums[i] % (nums.length + 1);
            if (original > 0) {
                nums[original - 1] += (nums.length + 1);
            }

        }

        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] / (nums.length + 1) == 0))
                return (i + 1);
        }
        return nums.length + 1;

    }
}
