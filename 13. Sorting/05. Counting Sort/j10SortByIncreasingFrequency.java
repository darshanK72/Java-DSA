
import java.util.ArrayList;
import java.util.Collections;

public class j10SortByIncreasingFrequency {

    public int[] frequencySort(int[] nums) {
        int min = -100;
        int max = 100;
        int[] freq = new int[max - min + 1];
        for (int n : nums) {
            freq[n - min]++;
        }

        ArrayList<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int i = 0; i < nums.length + 1; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int i = min; i <= max; i++) {
            buckets[freq[i - min]].add(i);
        }

        int[] out = new int[nums.length];
        int idx = 0;
        for (int frq = 1; frq <= nums.length; frq++) {
            Collections.reverse(buckets[frq]);
            for (int ele : buckets[frq]) {
                for (int j = 0; j < frq; j++) {
                    out[idx++] = ele;
                }
            }
        }
        return out;
    }
}
