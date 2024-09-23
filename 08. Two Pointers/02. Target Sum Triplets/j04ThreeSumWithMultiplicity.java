import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class j04ThreeSumWithMultiplicity {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int target = in.nextInt();
        System.out.println(threeSumMultiHashMap(arr, target));
        System.out.println(threeSumMultiTwoPointer(arr, target));
        in.close();
    }

    public static int threeSumMultiHashMap(int[] arr, int target) {
        int MOD = 1_000_000_007;
        Map<Integer, Long> countMap = new HashMap<>();

        // Fill the frequency map
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0L) + 1);
        }

        long result = 0;

        // Iterate through all unique combinations of i, j, k
        for (Integer i : countMap.keySet()) {
            for (Integer j : countMap.keySet()) {
                int k = target - i - j;

                if (countMap.containsKey(k)) {
                    long countI = countMap.get(i);
                    long countJ = countMap.get(j);
                    long countK = countMap.get(k);

                    if (i == j && j == k) {
                        // Case 1: All three are the same (i == j == k)
                        result += countI * (countI - 1) * (countI - 2) / 6;
                    } else if (i == j && j != k) {
                        // Case 2: i == j != k
                        result += countI * (countI - 1) / 2 * countK;
                    } else if (i < j && j < k) {
                        // Case 3: i != j != k
                        result += countI * countJ * countK;
                    }
                }

                result %= MOD;
            }
        }

        return (int) result;
    }

    public static int threeSumMultiTwoPointer(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int MOD = 1_000_000_007;
        int i = 0;
        long count = 0;
        while (i < n) {
            int s = i + 1;
            int e = n - 1;
            int tar = target - nums[i];

            while (s < e) {
                int sum = nums[s] + nums[e];
                if (sum == tar) {
                    if (nums[s] == nums[e]) {
                        int c = e - s + 1;
                        count += (c * (c - 1)) / 2;
                        count %= MOD;
                        break;
                    } else {
                        int c1 = 1;
                        int c2 = 1;
                        while (s + 1 < e && nums[s] == nums[s + 1]) {
                            s++;
                            c1++;
                        }
                        while (e - 1 > s && nums[e] == nums[e - 1]) {
                            e--;
                            c2++;
                        }
                        count += (c1 * c2);
                        count %= MOD;
                        s++;
                        e--;
                    }
                } else if (sum > tar) {
                    e--;
                } else {
                    s++;
                }
            }
            i++;
        }
        return (int) count;
    }
}
