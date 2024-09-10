import java.util.Scanner;

public class j03SingleNumberIII {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int[] arr = new int[s];
        for (int i = 0; i < s; i++) {
            arr[i] = in.nextInt();
        }

        System.out.println(singleNumber(arr));
        in.close();
    }

    // Two numbers in twice appearning numbers
    public static int[] singleNumber(int[] nums) {
        int r = 0;
        for (int num : nums) {
            r ^= num;
        }
        int rb = r & ~(r - 1);
        int r1 = 0;
        int r2 = 0;
        for (int num : nums) {
            if ((num & rb) == 0)
                r1 ^= num;
            else
                r2 ^= num;
        }
        return new int[] { r1, r2 };
    }

}
