import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class j11SubarraysWithDifferentKIntegers {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(subarraysWithKDistinct(arr, k));
        System.out.println(subarraysWithKDistinctEfficient(arr, k));
        in.close();
    }

    public static int subarraysWithKDistinct(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            HashSet<Integer> set = new HashSet<>();
            for (int j = i; j < nums.length; j++) {
                set.add(nums[j]);
                if (set.size() == k)
                    ans++;
            }
        }
        return ans;
    }

    public static int subarraysWithKDistinctEfficient(int[] nums, int k) {
        return atMostKDistinct(nums, k) - atMostKDistinct(nums, k - 1);
    }

    public static int atMostKDistinct(int[] nums, int k) {
        int ans = 0;
        int j = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            while (map.size() > k) {
                map.put(nums[j], map.get(nums[j]) - 1);
                if (map.get(nums[j]) == 0) {
                    map.remove(nums[j]);
                }
                j++;
            }

            ans += (i - j + 1);
        }
        return ans;
    }
}
