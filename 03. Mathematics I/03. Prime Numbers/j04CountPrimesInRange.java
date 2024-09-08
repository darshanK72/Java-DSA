import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class j04CountPrimesInRange {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int q = in.nextInt();
        ArrayList<Integer[]> quries = new ArrayList<>();

        for (int i = 1; i <= q; i++) {
            int s = in.nextInt();
            int e = in.nextInt();
            quries.add(new Integer[] { s, e });
        }

        System.out.println(Arrays.toString(countPrimesInRange(quries)));

        in.close();
    }

    public static int[] countPrimesInRange(ArrayList<Integer[]> quries) {

        int[] seive = getSeive(1000000);
        int count = 0;
        for (int i = 1; i <= 1000000; i++) {
            count += seive[i];
            seive[i] = count;
        }

        int[] out = new int[quries.size()];
        for (int i = 0; i < quries.size(); i++) {
            out[i] = seive[quries.get(i)[1]] - seive[quries.get(i)[0]];
        }
        return out;
    }

    public static int[] getSeive(int n) {
        int[] seive = new int[n + 1];
        seive[0] = seive[1] = 0;

        for (int i = 2; i <= n; i++) {
            seive[i] = 1;
        }

        for (int i = 2; i * i <= n; i++) {
            if (seive[i] == 1) {
                for (int j = i * i; j <= n; j += i) {
                    seive[j] = 0;
                }
            }
        }
        return seive;
    }
}
