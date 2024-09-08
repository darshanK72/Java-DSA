import java.util.Scanner;

public class j18FourDivisors {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        System.out.println(sumFourDivisors(nums));
        in.close();
    }

    public static int sumFourDivisors(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int tempSum = 0;
            int c = 0;
            for (int j = 1; j * j <= nums[i]; j++) {
                if (nums[i] % j == 0) {
                    tempSum += j;
                    tempSum += (nums[i] / j);
                    c += 2;
                    if (j == (nums[i]) / j) {
                        tempSum -= (nums[i] / j);
                        c--;
                    }
                }
                if (c > 4)
                    break;
            }
            if (c == 4) {
                sum += tempSum;
            }
        }
        return sum;
    }
}
