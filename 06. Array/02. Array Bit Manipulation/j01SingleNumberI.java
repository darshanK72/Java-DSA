import java.util.Scanner;

public class j01SingleNumberI {
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

    // Single number in twice appearing numbers
    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
