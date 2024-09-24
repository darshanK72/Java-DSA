import java.util.HashMap;
import java.util.Scanner;

public class j02FourSumII {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        int[] arr3 = new int[n];
        int[] arr4 = new int[n];
        for (int j = 0; j < n; j++) {
            arr1[j] = in.nextInt();
        }
        for (int j = 0; j < n; j++) {
            arr2[j] = in.nextInt();
        }
        for (int j = 0; j < n; j++) {
            arr3[j] = in.nextInt();
        }
        for (int j = 0; j < n; j++) {
            arr4[j] = in.nextInt();
        }

        System.out.println(fourSumCount(arr1, arr2, arr3, arr4));
        System.out.println(fourSumCountHashMap(arr1, arr2, arr3, arr4));
        in.close();
    }

    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n; l++) {
                        if (nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    public static int fourSumCountHashMap(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int a : nums1) {
            for (int b : nums2) {
                map.put(a + b, map.getOrDefault(a + b, 0) + 1);
            }
        }

        for (int a : nums3) {
            for (int b : nums4) {
                int target = -(a + b);
                count += map.getOrDefault(target, 0);
            }
        }
        return count;
    }
}
