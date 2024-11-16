import java.util.Scanner;

public class j05DivisibleBy7 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String n = in.next();
        System.out.println(isDivisibleBy7(n));
        in.close();
    }

    // Divisibility by 7 can be checked by a recursive method. A number of the form
    // 10a + b is divisible by 7 if and only if a – 2b is divisible by 7. In other
    // words, subtract twice the last digit from the number formed by the remaining
    // digits. Continue to do this until a small number.
    // Example: the number 371: 37 – (2×1) = 37 – 2 = 35; 3 – (2 × 5) = 3 – 10 = -7;
    // thus, since -7 is divisible by 7, 371 is divisible by 7.

    static boolean isDivisibleBy7(int num)
    {
        if( num < 0 )
            return isDivisibleBy7( -num );
        if( num == 0 || num == 7 )
            return true;
        if( num < 10 )
            return false;
        return isDivisibleBy7( num / 10 - 2 * ( num - num / 10 * 10 ) );
    }

    public static boolean isDivisibleBy7(String numStr) {
        long num = Long.parseLong(numStr);
        return num % 7 == 0;
    }
}
