import java.util.Scanner;

public class j11LcmOfArray {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(lcmOfArray(arr));
        in.close();
    }

    static long lcmOfArray(int arr[]) {
        long ans = 1;
        for (int i = 0; i < arr.length; i++) {
            ans = lcm(ans,(long)arr[i]);
        }
        return ans;
    }

    // prints within module 10^9 + 7
    static long lcmOfArrayMod(int arr[]) {
        long ans = 1;
        for (int i = 0; i < arr.length; i++) {
            ans = (ans * (long) arr[i]) / gcd(ans, (long) arr[i]) % 1000000007;
        }
        return ans;
    }

    public static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    public static long gcd(long a, long b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}
