import java.util.Scanner;

public class j08PrimeSetBitsInRange {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int left = in.nextInt();
        int right = in.nextInt();
        System.out.println(countPrimeSetBits(left, right));
        in.close();
    }

    public static int countPrimeSetBits(int left, int right) {
        boolean[] seive = new boolean[33];
        seive[0] = true;
        seive[1] = true;
        for (int i = 2; i * i < 32; i++) {
            if (!seive[i]) {
                for (int j = i * i; j < 32; j += i) {
                    seive[j] = true;
                }
            }
        }
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (!seive[countSetBits(i)])
                count++;
        }
        return count;
    }

    public static int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            count++;
            n &= (n - 1);
        }
        return count;
    }
}
