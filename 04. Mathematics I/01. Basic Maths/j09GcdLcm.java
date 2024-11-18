import java.util.Scanner;

public class j09GcdLcm {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();
        int b = in.nextInt();

        int hcf = gcdMmodulus(a, b);

        System.out.printf("HCF of (%d,%d) = %d\n", a, b, hcf);

        in.close();
    }

    public static int gcdBruteForce(int a, int b) {
        int gcd = 1;
        for (int i = 1; i <= Math.min(a, b); i++) {
            if (a % i == 0 && b % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }

    // Repeted Subtraction
    public static int gcdSubtraction(int a, int b) {
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    // Repeated Modulo
    public static int gcdMmodulus(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Recursive Modulo
    public static int gcdRecursive(int a, int b) {
        if (b == 0)
            return a;
        return gcdRecursive(b, a % b);
    }

    // Recursive LCM
    public static int lcm(int a, int b) {
        return (a * b) / gcdRecursive(a, b);
    }

}
