import java.util.Scanner;

public class j04AddDigits {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(addDigits(n));
        in.close();
    }

    public static int addDigits(int num) {
        return 1 + (num - 1) % 9;
    }
}
