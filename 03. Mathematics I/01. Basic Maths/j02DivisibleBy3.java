import java.util.Scanner;

public class j02DivisibleBy3 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String n = in.next();
        System.out.println(isDivisibleBy3(n));
        in.close();
    }

    // Number is divisible by 3, if digital sum is divisible by 3
    public static boolean isDivisibleBy3(String str) {
        int digitSum = 0;
        for (int i = 0; i < str.length(); i++)
            digitSum += (str.charAt(i) - '0');
        return (digitSum % 3 == 0);
    }

}
