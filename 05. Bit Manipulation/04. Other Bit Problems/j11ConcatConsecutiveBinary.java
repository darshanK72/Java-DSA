import java.util.Scanner;

public class j11ConcatConsecutiveBinary {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(concatenatedBinary(n));
        in.close();
    }

    public static int concatenatedBinary(int n) {
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = ((ans << ((int) (Math.log(i) / Math.log(2)) + 1)) | i) % 1000000007;
        }

        return (int) ans;
    }
}
