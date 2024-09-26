import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class j01ThreeSum {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        System.out.println(threeSum(nums));
        System.out.println(threeSumHashSet(nums));
        System.out.println(threeSumEfficient1(nums));
        in.close();
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> out = new ArrayList<>();
        HashSet<ArrayList<Integer>> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        Collections.sort(temp);
                        if (!set.contains(temp)) {
                            out.add(temp);
                            set.add(temp);
                        }
                    }
                }
            }
        }
        return out;
    }

    public static List<List<Integer>> threeSumHashSet(int[] nums) {
        HashSet<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            HashSet<Integer> tSet = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                int tar = -(nums[i] + nums[j]);
                if (tSet.contains(tar)) {
                    ArrayList<Integer> lst = new ArrayList<>(Arrays.asList(nums[i], nums[j], tar));
                    Collections.sort(lst);
                    set.add(lst);
                }
                tSet.add(nums[j]);
            }
        }

        return new ArrayList<>(set);
    }

    public static List<List<Integer>> threeSumEfficient1(int[] nums) {
        int n = nums.length;
        List<List<Integer>> out = new ArrayList<>();
        Arrays.sort(nums);
        int i = 0;
        while (i < n) {
            int j = i + 1;
            int k = n - 1;
            int target = 0 - nums[i];
            while (j < k) {
                int sum = nums[j] + nums[k];
                if (sum == target) {
                    ArrayList<Integer> lst = new ArrayList<>();
                    lst.add(nums[i]);
                    lst.add(nums[j]);
                    lst.add(nums[k]);
                    out.add(lst);
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                } else if (sum > target) {
                    k--;
                } else {
                    j++;
                }
            }
            i++;
            while (i < n && nums[i] == nums[i - 1]) {
                i++;
            }
        }
        return out;
    }

    public static List<List<Integer>> threeSumEfficient2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        ArrayList<List<Integer>> out = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    out.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1])
                        j++;
                    while (j < k && nums[k] == nums[k + 1])
                        k--;
                } else if (sum > 0) {
                    k--;
                } else {
                    j++;
                }
            }

        }
        return out;
    }

    // Modular
    public List<List<Integer>> threeSumModular(int[] nums) {
        List<List<Integer>> out = new ArrayList<>();
        Arrays.sort(nums);
        int i = 0;
        while (i < nums.length) {
            twoSum(nums, i, out);
            i++;
            while (i < nums.length && nums[i] == nums[i - 1]) {
                i++;
            }
        }
        return out;
    }

    public static void twoSum(int[] nums, int i, List<List<Integer>> out) {
        int j = i + 1;
        int k = nums.length - 1;
        int target = 0 - nums[i];
        while (j < k) {
            int sum = nums[j] + nums[k];
            if (sum == target) {
                ArrayList<Integer> lst = new ArrayList<>();
                lst.add(nums[i]);
                lst.add(nums[j]);
                lst.add(nums[k]);
                out.add(lst);
                j++;
                k--;
                while (j < k && nums[j] == nums[j - 1]) {
                    j++;
                }
                while (j < k && nums[k] == nums[k + 1]) {
                    k--;
                }
            } else if (sum > target) {
                k--;
            } else {
                j++;
            }
        }
    }
}
