import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class j07CountNumbersWithNFactors {

     public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int q = in.nextInt();
        ArrayList<ArrayList<Integer>> quries = new ArrayList<>();

        for (int i = 1; i <= q; i++) {
            int s = in.nextInt();
            int e = in.nextInt();
            quries.add(new ArrayList<>(Arrays.asList(s,e)));
        }

        System.out.println(getNfactor(quries));

        in.close();
    }

    public static ArrayList<Integer> getNfactor(ArrayList<ArrayList<Integer>> queries) {
        ArrayList<Integer> output = new ArrayList<Integer>();
        for (ArrayList<Integer> query : queries) {
            int c = 0;
            for (int i = query.get(0); i <= query.get(1); i++) {
                if (isNUniqueFactors(i, query.get(2))) {
                    c++;
                }
            }
            output.add(c);
        }
        return output;
    }

    public static boolean isNUniqueFactors(int x, int n) {
        int c = 0;
        for (int i = 2; i <= x; i++) {
            if (x % i == 0) {
                c++;
                while (x % i == 0) {
                    x /= i;
                }
            }
            if (c > n)
                return false;
        }
        return c < n ? false : true;
    }
}
