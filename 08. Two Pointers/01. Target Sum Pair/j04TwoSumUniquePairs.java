import java.util.Scanner;
import java.util.HashSet;

public class j04TwoSumUniquePairs {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int target = in.nextInt();
        System.out.println(countUniquePairs(nums, target));
        System.out.println(countUniquePairsHashMap(nums, target));
        System.out.println(countUniquePairsEfficient(nums, target));
        in.close();
    }

    public static int countUniquePairs(int[] nums, int k) {
        int count = 0;
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == k && !set.contains(nums[i] + "#" + nums[j])) {
                    count++;
                    set.add(nums[i] + "#" + nums[j]);
                }
            }
        }
        return count;
    }

    public static int countUniquePairsHashMap(int[] nums, int k) {
        HashSet<Integer> seen = new HashSet<>();
        HashSet<Integer> used = new HashSet<>();
        int count = 0;
        for (int num : nums) {
            int target = k - num;
            // Check if (k - num) exists in the set and is not already used in a pair
            if (seen.contains(target) && !used.contains(num) && !used.contains(target)) {
                count++;
                // Mark both numbers as used
                used.add(num);
                used.add(target);
            }
            // Add current number to the set of seen elements
            seen.add(num);
        }
        return count;
    }

    public static int countUniquePairsEfficient(int[] nums, int k) {
        int count = 0;
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == k) {
                count++;
                i++;
                j--;

                while (i < j && nums[i] == nums[i + 1])
                    i++;
                while (i < j && nums[j] == nums[j - 1])
                    j--;
            } else if (sum > k) {
                j--;
            } else {
                i++;
            }
        }
        return count;
    }
}
