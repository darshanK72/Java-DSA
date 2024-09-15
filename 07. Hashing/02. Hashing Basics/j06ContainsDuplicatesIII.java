import java.util.Scanner;
import java.util.TreeSet;

public class j06ContainsDuplicatesIII {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int indexDiff = in.nextInt();
        int valueDiff = in.nextInt();

        System.out.println(containsNearbyAlmostDuplicate(arr, indexDiff,valueDiff));
        in.close();
    }

    public static boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i - indexDiff - 1 >= 0) {
                set.remove((long) nums[i - indexDiff - 1]);
            }

            Long floor = set.floor((long) nums[i]);
            Long ceil = set.ceiling((long) nums[i]);

            if (floor != null && (long) nums[i] - floor <= valueDiff)
                return true;
            if (ceil != null && ceil - (long) nums[i] <= valueDiff)
                return true;

            set.add((long) nums[i]);
        }
        return false;
    }
}
