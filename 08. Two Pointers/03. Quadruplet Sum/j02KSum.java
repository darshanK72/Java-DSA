import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class j02KSum {
    public static void main(String[] args) {
        // Test case
        int[] nums = { 1, 0, -1, 0, -2, 2 };
        int target = 0;
        int k = 4;
        // Call kSum method and get the result
        List<List<Integer>> result = kSum(nums, target, k);

        // Print the result
        System.out.println("Combinations that sum to " + target + ":");
        for (List<Integer> combination : result) {
            System.out.println(combination);
        }
    }

    public static List<List<Integer>> kSum(int[] nums, int target, int k) {
        Arrays.sort(nums);
        return kSumHelper(nums, 0, target, k);
    }

    public static ArrayList<List<Integer>> twoSum(int[] nums, int s, long target) {
        ArrayList<List<Integer>> out = new ArrayList<>();
        int e = nums.length - 1;
        while (s < e) {
            long sum = (long) nums[s] + (long) nums[e];
            if (sum == target) {
                List<Integer> temp = new ArrayList<>();
                temp.add(nums[s]);
                temp.add(nums[e]);
                out.add(temp);
                s++;
                e--;
                while (s < e && nums[s] == nums[s - 1])
                    s++; // skip duplicates
                while (s < e && nums[e] == nums[e + 1])
                    e--; // skip duplicates
            } else if (sum > target) {
                e--;
            } else {
                s++;
            }
        }
        return out;
    }

    public static ArrayList<List<Integer>> kSumHelper(int[] nums, int s, long target, int k) {
        ArrayList<List<Integer>> out = new ArrayList<>();
        if (k == 2) {
            return twoSum(nums, s, target);
        } else {
            for (int i = s; i < nums.length - (k - 1); i++) {
                if (i > s && nums[i] == nums[i - 1])
                    continue; // skip duplicates
                ArrayList<List<Integer>> kList = kSumHelper(nums, i + 1, target - nums[i], k - 1);
                for (List<Integer> lst : kList) {
                    lst.add(nums[i]);
                    out.add(lst);
                }
            }
        }
        return out;
    }
}
