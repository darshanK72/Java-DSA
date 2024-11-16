import java.util.Scanner;

public class j06TurnamentMatches {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(numberOfMatches(n));
        in.close();
    }

    public static int numberOfMatches(int n) {
        return n - 1;
    }
}
