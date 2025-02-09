import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class j02GetUniquePermutations {
    public List<List<Integer>> permuteUnique1(int[] nums) {
        HashSet<List<Integer>> set = new HashSet<>();
        generateUniquePermutations1(nums, new boolean[nums.length], new ArrayList<>(), set);
        return new ArrayList<>(set);
    }

    public static void generateUniquePermutations1(int[] nums, boolean[] visited, ArrayList<Integer> curr,
            HashSet<List<Integer>> set) {
        if (curr.size() == nums.length) {
            set.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                curr.add(nums[i]);
                visited[i] = true;
                generateUniquePermutations1(nums, visited, curr, set);
                visited[i] = false;
                curr.remove(curr.size() - 1);
            }
        }
    }

    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> set = new ArrayList<>();
        Arrays.sort(nums);
        generatePermutations2(nums, new boolean[nums.length], new ArrayList<>(), set);
        return set;
    }

    public static void generatePermutations2(int[] nums, boolean[] visited, ArrayList<Integer> curr,
            List<List<Integer>> set) {
        if (curr.size() == nums.length) {
            set.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i])
                continue;
            if (i > 0 && !visited[i - 1] && nums[i - 1] == nums[i])
                continue;
            curr.add(nums[i]);
            visited[i] = true;
            generatePermutations2(nums, visited, curr, set);
            visited[i] = false;
            curr.remove(curr.size() - 1);
        }
    }
}
