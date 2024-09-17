import java.util.Scanner;

public class j15StepsToReduceNumToZero {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        System.out.println(numberOfSteps(num));
        System.out.println(numberOfStepsEfficient(num));
        in.close();
    }

    public static int numberOfSteps(int num) {
        return counter(num, 0);
    }

    public static int counter(int n, int count) {
        if (n == 0)
            return count;
        if ((n & 1) == 0)
            return counter(n / 2, ++count);
        return counter(--n, ++count);
    }

    public static int numberOfStepsEfficient(int num) {
        int count = 0;
        while (num > 0) {
            if ((num & 1) == 0)
                num >>= 1;
            else
                num--;
            count++;
        }
        return count;
    }
}
