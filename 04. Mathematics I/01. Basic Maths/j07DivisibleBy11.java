import java.util.Scanner;

public class j07DivisibleBy11 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String n = in.next();
        System.out.println(isDivisibleBy11(n));
        in.close();
    }

    // Compute sum of even and odd digit sums
    // Check its difference is divisible by 11 or not
    public static boolean isDivisibleBy11(String str) {
        int n = str.length();
        int oddDigSum = 0, evenDigSum = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0)
                oddDigSum += (str.charAt(i) - '0');
            else
                evenDigSum += (str.charAt(i) - '0');
        }

        return ((oddDigSum - evenDigSum) % 11 == 0);
    }

}
