import java.util.Scanner;

// Complexity : O(1)
public class j01DigitCount {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.print("Number of Digits in " + n + " are " + digitCount(n));
        System.out.println("Number of Digits in " + n + " are " + digitCountLog(n));
        in.close();
    }

    public static int digitCount(int n) {
        int count = 0;
        while (n > 0) {
            count++;
            n /= 10;
        }
        return count;
    }

    public static int digitCountLog(int n) {
        return (int) (Math.floor(Math.log10(n)) + 1);
    }
}
