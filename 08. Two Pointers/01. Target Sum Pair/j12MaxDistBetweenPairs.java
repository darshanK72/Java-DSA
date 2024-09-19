import java.util.Scanner;

public class j12MaxDistBetweenPairs {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr1 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = in.nextInt();
        }
        int[] arr2 = new int[n];
        for (int i = 0; i < n; i++) {
            arr2[i] = in.nextInt();
        }
        System.out.println(maxDistance(arr1, arr2));
        System.out.println(maxDistanceEfficient(arr1, arr2));
        in.close();
    }

    public static int maxDistance(int[] nums1, int[] nums2) {
        int maxDist = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = i; j < nums2.length; j++) {
                if (nums1[i] <= nums2[j]) {
                    maxDist = Math.max(maxDist, j - i);
                }
            }
        }
        return maxDist;
    }

    public static int maxDistanceEfficient(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int maxDist = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                maxDist = Math.max(maxDist, j - i);
                j++;
            } else {
                i++;
            }
        }
        return maxDist;
    }
}
