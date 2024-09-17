import java.util.Scanner;

public class j11MaximizingXOR {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int l = in.nextInt();
        int r = in.nextInt();
        System.out.println(maximizingXor(l, r));
        in.close();
    }

    public static int maximizingXor(int l, int r) {
        if (l == r)
            return 0;
        int leftMostBit = Integer.highestOneBit(l ^ r);
        return (leftMostBit << 1) - 1;
    }
}
