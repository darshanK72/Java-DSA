import java.util.Scanner;

public class j07IntegerReplacement {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(integerReplacement1(n));
        System.out.println(integerReplacement2(n));
        in.close();
    }

    public static int integerReplacement1(int n) {
        int count = 0;
        if (n == Integer.MAX_VALUE)
            return 32;
        while (n != 1) {
            if (n % 2 == 0) {
                n = n / 2;
            } else if (n == 3 || (n & 3) == 1) {
                n = n - 1;
            } else if ((n & 3) == 3) {
                n = n + 1;
            }
            count++;
        }
        return count;
    }

    public static int integerReplacement2(int n) {
        if (n == Integer.MAX_VALUE)
            return 32;

        if (n == 1)
            return 0;

        if ((n & 1) == 0)
            return 1 + integerReplacement2(n / 2);

        if (n == 3 || (n & 3) == 1)
            return 1 + integerReplacement2(n - 1);
        else
            return 1 + integerReplacement2(n + 1);
    }
}
