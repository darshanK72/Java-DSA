import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class j04CountDistinctInWindowSizeK {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(countDistinct(nums, n, k));
        in.close();
    }

    public static ArrayList<Integer> countDistinct(int A[], int n, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
        }

        ArrayList<Integer> out = new ArrayList<>();
        out.add(map.size());
        for (int i = k; i < n; i++) {
            int val = map.get(A[i - k]);
            if (val == 1)
                map.remove(A[i - k]);
            else
                map.put(A[i - k], val - 1);
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
            out.add(map.size());
        }
        return out;
    }
}
