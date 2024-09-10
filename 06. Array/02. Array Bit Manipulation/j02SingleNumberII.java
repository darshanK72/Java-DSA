import java.util.Scanner;

public class j02SingleNumberII {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int[] arr = new int[s];
        for (int i = 0; i < s; i++) {
            arr[i] = in.nextInt();
        }

        System.out.println(singleNumber(arr));
        System.out.println(singleNumberEfficient(arr));
        in.close();
    }

    // Single number in thrice appearning numbers
    public static int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & (1 << i)) != 0)
                    count++;
            }
            if (count % 3 == 1) {
                ans = ans | (1 << i);
            }
        }
        return ans;
    }

    public static int singleNumberEfficient(int[] nums) {
        int tn = Integer.MAX_VALUE;
        int tnp1 = 0;
        int tnp2 = 0;

        for (int num : nums) {
            int ctn = num & tn;
            int ctn1 = num & tnp1;
            int ctn2 = num & tnp2;

            tn = tn & ~(ctn);
            tnp1 = tnp1 | ctn;

            tnp1 = tnp1 & ~(ctn1);
            tnp2 = tnp2 | ctn1;

            tnp2 = tnp2 & ~(ctn2);
            tn = tn | ctn2;
        }

        return tnp1;
    }
}
