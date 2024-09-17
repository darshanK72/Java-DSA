import java.util.Scanner;

public class j06HammingDistance {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        System.out.println(hammingDistance(x, y));
        System.out.println(hammingDistanceEfficient(x, y));
        in.close();
    }

    public static int hammingDistance(int x, int y) {
        int r = x ^ y;
        int c = 0;
        while (r > 0) {
            r &= (r - 1);
            c++;
        }
        return c;
    }

    public static int hammingDistanceEfficient(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
