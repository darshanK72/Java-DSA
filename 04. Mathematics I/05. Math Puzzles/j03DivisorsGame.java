import java.util.Scanner;

public class j03DivisorsGame {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(divisorGame(n));
        in.close();
    }

    public static boolean divisorGame(int n) {
        return n % 2 == 0;
    }
}
