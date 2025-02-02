
import java.util.Arrays;

public class j04HeightChecker {

    public int heightCheckerInbuiltSorting(int[] heights) {
        int n = heights.length;
        int out = 0;
        int[] sortedHeights = Arrays.copyOf(heights, n);
        Arrays.sort(sortedHeights);
        for (int i = 0; i < n; i++) {
            if (heights[i] != sortedHeights[i]) {
                out++;
            }
        }
        return out;
    }

    public int heightCheckerCountingSort(int[] heights) {
        int[] count = new int[100 + 1];
        for (int h : heights) {
            count[h]++;
        }
        int ans = 0;
        int j = 0;
        for (int h : heights) {
            while (j < count.length && count[j] == 0) {
                j++;
            }
            if (h != j) {
                ans++;
            }
            count[j]--;
        }
        return ans;
    }
}
