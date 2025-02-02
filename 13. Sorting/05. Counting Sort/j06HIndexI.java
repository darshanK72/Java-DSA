
import java.util.Arrays;

public class j06HIndexI {

    public static int hIndexSorting(int[] citations) {
        Arrays.sort(citations);
        int h = 1;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations[i] >= h) {
                h++;
            } else {
                break;
            }
        }
        return h - 1;
    }

    public static int hIndexCountingSort(int[] citations) {
        int n = citations.length;
        int[] count = new int[n + 1];
        for (int cit : citations) {
            count[Math.min(cit, n)]++;
        }
        int cummH = 0;
        for (int h = n; h >= 0; h--) {
            cummH += count[h];
            if (cummH >= h) {
                return h;
            }
        }
        return 0;
    }
}
