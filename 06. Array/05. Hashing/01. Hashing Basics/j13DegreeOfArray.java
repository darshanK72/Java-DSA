import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class j13DegreeOfArray {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(findShortestSubArray(arr));
        System.out.println(findShortestSubArrayEfficient(arr));
        in.close();
    }

    public static int findShortestSubArray(int[] nums) {
        ArrayList<Integer> arr = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            arr.add(nums[i]);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        int max = 0;
        int ans = Integer.MAX_VALUE;
        for (int key : map.keySet()) {
            if (map.get(key) > max) {
                max = map.get(key);
                ans = arr.lastIndexOf(key) - arr.indexOf(key) + 1;
            } else if (map.get(key) == max) {
                ans = Math.min(ans, arr.lastIndexOf(key) - arr.indexOf(key) + 1);
            }
        }
        return ans;
    }

    public static int findShortestSubArrayEfficient(int[] nums) {
        HashMap<Integer, Integer[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer[] v = map.getOrDefault(nums[i], new Integer[] { 0, -1, -1 });
            if (v[1] == -1)
                v[1] = i;
            v[2] = i;
            v[0]++;
            map.put(nums[i], v);
        }

        int max = 0;
        int ans = Integer.MAX_VALUE;
        for (int key : map.keySet()) {
            Integer[] v = map.get(key);
            if (v[0] > max) {
                max = v[0];
                ans = v[2] - v[1] + 1;
            } else if (v[0] == max) {
                ans = Math.min(ans, v[2] - v[1] + 1);
            }
        }
        return ans;
    }
}
