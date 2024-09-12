import java.util.HashMap;
import java.util.Scanner;

public class j05CountBadPairs {
    // You are given a 0-indexed integer array nums. A pair of indices (i, j) is a
    // bad pair if i < j and j - i != nums[j] - nums[i].
    // Return the total number of bad pairs in nums.

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(countBadPairs(arr));
        in.close();
    }

    public static long countBadPairs(int[] nums) {
        long count = 0;
        long n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i] - i)) {
                count += map.get(nums[i] - i);
                map.put(nums[i] - i, map.get(nums[i] - i) + 1);
            } else {
                map.put(nums[i] - i, 1);
            }
        }
        return (n * (n - 1)) / 2 - count;
    }
}
