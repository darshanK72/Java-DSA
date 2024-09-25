import java.util.Arrays;
import java.util.Scanner;

public class j05RemoveElementFromArray {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int val = in.nextInt();

        System.out.println(Arrays.toString(arr));
        int k = removeElement(arr, val);
        System.out.println(k);
        System.out.println(Arrays.toString(arr));
        in.close();
    }

    public static int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}
