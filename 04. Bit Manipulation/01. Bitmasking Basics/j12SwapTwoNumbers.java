import java.util.Scanner;

public class j12SwapTwoNumbers {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        swapNums(a, b);
        in.close();
    }

    public static void swapNums(int a, int b) {
        System.out.println("Before Swapping : a = " + a + ", b = " + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("After Swapping : a = " + a + ", b = " + b);
    }
}