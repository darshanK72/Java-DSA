
public class j05RelativeSortArray {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] count = new int[1001];
        for (int n : arr1) {
            count[n]++;
        }

        int[] out = new int[arr1.length];
        int idx = 0;
        for (int n : arr2) {
            while (count[n] > 0) {
                out[idx++] = n;
                count[n]--;
            }
        }

        for (int i = 0; i < 1001; i++) {
            while (count[i] > 0) {
                out[idx++] = i;
                count[i]--;
            }
        }
        return out;
    }
}
