import java.util.Scanner;

public class j01NimGame {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(canWinNim(n));
        in.close();
    }

    public static boolean canWinNim(int n) {
        return !(n % 4 == 0);
    }
}
