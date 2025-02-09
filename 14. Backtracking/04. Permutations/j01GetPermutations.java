import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j01GetPermutations {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        printArrayPermutations(arr, new ArrayList<>(), new boolean[n]);

        List<List<Integer>> set1 = new ArrayList<>();
        generatePermutations1(arr, new ArrayList<>(), set1);
        System.out.println(set1);

        List<List<Integer>> set2 = new ArrayList<>();
        generatePermutations2(arr, new boolean[arr.length], new ArrayList<>(), set2);
        System.out.println(set2);

        List<List<Integer>> set3 = generatePermutations3(arr, 0);
        System.out.println(set3);

        in.close();
    }

    public static void printArrayPermutations(int[] arr, List<Integer> ans, boolean[] visited) {
        if (ans.size() == arr.length) {
            System.out.print(ans + " ");
        }
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                ans.add(arr[i]);
                visited[i] = true;
                printArrayPermutations(arr, ans, visited);
                ans.remove(ans.size() - 1);
                visited[i] = false;
            }
        }
    }

    public static void generatePermutations1(int[] nums, ArrayList<Integer> curr, List<List<Integer>> set) {
        if (curr.size() == nums.length) {
            set.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!curr.contains(nums[i])) {
                curr.add(nums[i]);
                generatePermutations1(nums, curr, set);
                curr.remove(curr.size() - 1);
            }
        }
    }

    public static void generatePermutations2(int[] nums, boolean[] visited, ArrayList<Integer> curr,
            List<List<Integer>> set) {
        if (curr.size() == nums.length) {
            set.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                curr.add(nums[i]);
                visited[i] = true;
                generatePermutations2(nums, visited, curr, set);
                visited[i] = false;
                curr.remove(curr.size() - 1);
            }
        }
    }

    public static List<List<Integer>> generatePermutations3(int[] arr, int index) {
        ArrayList<List<Integer>> output = new ArrayList<List<Integer>>();
        if (index == arr.length) {
            ArrayList<Integer> ans = new ArrayList<>();
            for (int i : arr) {
                ans.add(i);
            }
            output.add(ans);
            return output;
        }

        for (int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            output.addAll(generatePermutations3(arr, index + 1));
            swap(arr, index, i);
        }
        return output;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
