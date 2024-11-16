import java.util.Scanner;

public class j09CountPythaghorianTriplets {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(countTriples(n));
        in.close();
    }

    public static int countTriples(int n) {
        int c = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int sq = i * i + j * j;
                double sqr = Math.sqrt(sq);
                if (sq <= n * n && Math.floor(sqr) == sqr)
                    c++;
            }
        }
        return c;
    }
}
