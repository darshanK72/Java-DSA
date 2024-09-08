import java.util.Scanner;

public class j16CountIntWithEvenDigitSum {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int num1 = in.nextInt();
        System.out.println(countEven(num1));
        in.close();
    }

    public static int countEven(int num) {
        int count = 0;
        for (int i = 2; i <= num; i++) {
            int x = i;
            int s = 0;
            while (x > 0) {
                s += x % 10;
                x /= 10;
            }
            if (s % 2 == 1)
                count++;
        }
        return count;
    }
}
