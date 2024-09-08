import java.util.Scanner;

public class j12PassThePillow {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int time = in.nextInt();
        System.out.println(passThePillow(n, time));
        in.close();
    }

    public static int passThePillow(int n, int time) {
        boolean lastPass = (time / (n - 1)) % 2 == 1;
        if (lastPass) {
            return n - time % (n - 1);
        } else {
            return time % (n - 1) + 1;
        }
    }
}
