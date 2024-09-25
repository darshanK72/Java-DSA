import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j14TwoSumAbsValSortedArray {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int target = in.nextInt();
        System.out.println(twoSumVII(nums, target));
        in.close();
    }

    public static List<List<Integer>> twoSumVII(int[] nums, int target) {
        List<List<Integer>> out = new ArrayList<>();
        int e = getEnd(nums, nums.length);
        int s = getStart(nums, nums.length);
        System.out.println(e);
        System.out.println(s);
        while (s >= 0 && e >= 0) {
            int sum = nums[s] + nums[e];
            if (sum == target) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(s);
                temp.add(e);
                out.add(temp);
                s = getStart(nums, s);
                e = getEnd(nums, e);
            } else if (sum > target) {
                e = getEnd(nums, e);
            } else {
                s = getStart(nums, s);
            }
        }
        return out;
    }

    public static int getStart(int[] nums, int e) {
        int j = e - 1;
        for (; j >= 0; j--) {
            if (nums[j] < 0)
                break;
        }
        if (j >= 0)
            return j;
        j = 0;
        for (; j < nums.length; j++) {
            if (nums[j] >= 0)
                break;
        }
        return j;
    }

    public static int getEnd(int[] nums, int e) {
        int j = e - 1;
        for (; j >= 0; j--) {
            if (nums[j] >= 0)
                break;
        }
        if (j >= 0)
            return j;
        for (; j < nums.length; j++) {
            if (nums[j] < 0)
                break;
        }
        return j;
    }
}
