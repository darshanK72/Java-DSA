import java.util.Scanner;

public class j13SortColors {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        // sortColors(arr);
        sortColorsEfficient(arr);
        in.close();
    }

    public static void sortColors(int[] nums) {
        int cZero = 0;
        int cOne = 0;
        int cTwo = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                cZero++;
            } else if (nums[i] == 1) {
                cOne++;
            } else {
                cTwo++;
            }
        }

        int k = 0;
        while (cZero > 0) {
            nums[k] = 0;
            k++;
            cZero--;
        }

        while (cOne > 0) {
            nums[k] = 1;
            k++;
            cOne--;
        }

        while (cTwo > 0) {
            nums[k] = 2;
            k++;
            cTwo--;
        }
    }

    // Duch National Flag Algorithm
    public static void sortColorsEfficient(int[] nums) {
        int s = 0;
        int m = 0;
        int e = nums.length - 1;
        while (m <= e) {
            if (nums[m] == 0) {
                swap(nums, s, m);
                s++;
                m++;
            } else if (nums[m] == 2) {
                swap(nums, e, m);
                e--;
            } else {
                m++;
            }
        }
    }

    public static void swap(int[] nums, int s, int e) {
        int temp = nums[s];
        nums[s] = nums[e];
        nums[e] = temp;
    }
}
