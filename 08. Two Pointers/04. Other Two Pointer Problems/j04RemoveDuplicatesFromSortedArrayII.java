import java.util.Arrays;
import java.util.Scanner;

public class j04RemoveDuplicatesFromSortedArrayII {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        System.out.println(Arrays.toString(arr));
        int k = removeDuplicates(arr);
        System.out.println(k);
        System.out.println(Arrays.toString(arr));
        in.close();
    }

    public static int removeDuplicates(int[] nums) {
        int j = 1;
        for (int i = 2; i < nums.length; i++) {
            if (nums[j] != nums[i]) {
                j++;
                nums[j] = nums[i];
            } else if (nums[j] == nums[i] && nums[j - 1] != nums[i]) {
                j++;
                nums[j] = nums[i];
            }
        }
        return j + 1;
    }
}
