import java.util.Scanner;

public class j07TotalHammingDistance {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        in.close();
    }

    public static int totalHammingDistance(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int countOnes = 0;
            int countZeros = 0;
            for (int n : nums) {
                if (((n >> i) & 1) == 1)
                    countOnes++;
                else
                    countZeros++;
            }
            ans += countOnes * countZeros;
        }
        return ans;
    }
}
