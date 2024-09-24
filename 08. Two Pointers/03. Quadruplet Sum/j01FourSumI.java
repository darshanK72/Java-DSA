import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class j01FourSumI {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int target = in.nextInt();

        System.out.println(fourSum(arr, target));
        System.out.println(fourSumEfficient(arr, target));
        in.close();
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        ArrayList<List<Integer>> out = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                for (int k = j + 1; k < nums.length - 1; k++) {
                    if (k > j + 1 && nums[k] == nums[k - 1])
                        continue;
                    for (int l = k + 1; l < nums.length; l++) {
                        if (l > k + 1 && nums[l] == nums[l - 1])
                            continue;
                        long sum = (long) nums[i] + nums[j] + nums[k] + nums[l];
                        if (sum == (long) target) {
                            out.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        }
                    }
                }
            }
        }
        return out;
    }

    public static List<List<Integer>> fourSumEfficient(int[] nums, int target) {
        ArrayList<List<Integer>> out = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        int i = 0;
        while (i < n) {
            int j = i + 1;
            long t1 = (long) target - nums[i];
            while (j < n) {
                int s = j + 1;
                int e = n - 1;
                long t = (long) t1 - nums[j];
                while (s < e) {
                    long sum = (long) nums[s] + nums[e];
                    if (sum == t) {
                        out.add(Arrays.asList(nums[i], nums[j], nums[s], nums[e]));
                        s++;
                        e--;

                        while (s < e && nums[s] == nums[s - 1])
                            s++;
                        while (s < e && nums[e] == nums[e + 1])
                            e--;
                    } else if (sum > t) {
                        e--;
                    } else {
                        s++;
                    }
                }
                while (j < n - 1 && nums[j] == nums[j + 1])
                    j++;
                j++;
            }
            while (i < n - 1 && nums[i] == nums[i + 1])
                i++;
            i++;
        }

        return out;
    }
}
