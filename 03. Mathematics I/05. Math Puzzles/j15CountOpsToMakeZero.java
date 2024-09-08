import java.util.Scanner;

public class j15CountOpsToMakeZero {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int num1 = in.nextInt();
        int num2 = in.nextInt();
        System.out.println(countOperations(num1, num2));
        in.close();
    }

    public static int countOperations(int num1, int num2) {
        int op = 0;
        while (num1 != 0 && num2 != 0) {
            if (num1 > num2)
                num1 = num1 - num2;
            else
                num2 = num2 - num1;
            op++;
        }
        return op;
    }

    public static int ops(int n1, int n2, int op) {
        if (n1 == 0 || n2 == 0)
            return op;
        if (n1 > n2)
            return ops(n1 - n2, n2, op + 1);
        else
            return ops(n2 - n1, n1, op + 1);
    }
}
