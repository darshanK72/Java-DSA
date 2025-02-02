
import java.util.ArrayList;

public class j09BucketSort {

    public static int[] bucketSort(int[] nums) {
        int min = -50000;
        int max = 50000;
        ArrayList<Integer>[] buckets = new ArrayList[max - min + 1];

        for (int i = 0; i < max - min + 1; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int n : nums) {
            buckets[n - min].add(n);
        }
        int[] out = new int[nums.length];
        int idx = 0;
        for (int i = 0; i < max - min + 1; i++) {
            int k = buckets[i].size();
            for (int j = 0; j < k; j++) {
                out[idx] = buckets[i].get(j);
                idx++;
            }
        }
        return out;
    }
}
