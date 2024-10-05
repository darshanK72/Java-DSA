import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class j12CountEquivalentSubarrays {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(countDistinctSubarray(arr, n));
        in.close();
    }

    public static int countDistinctSubarray(int arr[], int n) {
        HashSet<Integer> set = new HashSet<>();
        for (int e : arr) {
            set.add(e);
        }
        int k = set.size();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            HashSet<Integer> set1 = new HashSet<>();
            for (int j = i; j < n; j++) {
                set1.add(arr[j]);
                if (set1.size() == k) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static int countDistinctSubarrayEfficient(int arr[], int n) {
        HashSet<Integer> set = new HashSet<>();
        for (int e : arr) {
            set.add(e);
        }

        int k = set.size();
        return atMostKDistinct(arr, k) - atMostKDistinct(arr, k - 1);
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
