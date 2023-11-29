import java.util.Scanner;

public class j9Pattern {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        for (int i = 1; i <= n; i++) {
            if (i % 2 == 1) {
                for (int j = 1; j <= i; j++) {
                    System.out.print(j % 2 + " ");
                }
            } else {
                for (int j = 1; j <= i; j++) {
                    System.out.print((j-1) % 2 + " ");
                }

            }

            System.out.println();

        }

        in.close();
    }

}
