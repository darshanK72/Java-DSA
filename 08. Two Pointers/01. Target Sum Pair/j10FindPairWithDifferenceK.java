import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class j10FindPairWithDifferenceK {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(Arrays.toString(findPairDifferenceK(arr, k)));
        System.out.println(Arrays.toString(findPairDifferenceKHashMap(arr, k)));
        System.out.println(Arrays.toString(findPairDifferenceKEfficient(arr, k)));
        in.close();
    }

    public static int[] findPairDifferenceK(int[] arr, int k) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[j] - arr[i] == k) {
                    return new int[] { arr[i], arr[j] };
                }
            }
        }
        return new int[] {};
    }

    public static int[] findPairDifferenceKHashMap(int[] nums, int target) {
        int[] indices = new int[2];
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return indices;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i] + target)) {
                indices[0] = map.get(nums[i] + target) + 1;
                indices[1] = i + 1;
                return indices;
            }
            if (map.containsKey(nums[i] - target)) {
                indices[0] = map.get(nums[i] - target) + 1;
                indices[1] = i + 1;
                return indices;
            }
            map.put(nums[i], i);
        }
        return indices;
    }

    public static int[] findPairDifferenceKEfficient(int[] arr, int k) {
        int n = arr.length;
        Arrays.sort(arr);
        int left = 0, right = 1;
        while (right < n) {
            int diff = arr[right] - arr[left];
            if (diff == k && left != right) {
                return new int[] { arr[left], arr[right] };
            }
            if (diff < k) {
                right++;
            } else {
                left++;
            }
            if (left == right) {
                right++;
            }
        }
        return new int[] {};
    }
}
