import java.util.Scanner;

public class j22ZigZag {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int length = in.nextInt();
        int height = in.nextInt();

        for (int i = 1; i <= height; i++) {

            for (int j = height - i; j >= 1; j--) {
                System.out.print("  ");
            }
            for (int j = 1; j <= length; j++) {

                System.out.print("*");

                for (int k = 1; k <= 3 + (i - 2) * 4; k++) {
                    System.out.print(" ");
                }

                if (i > 1 && i < height)
                    System.out.print("*");

                if (i == height && j == length) {
                    System.out.print("*");
                }

                for (int k = 1; k <= 3 + ((height - i + 1) - 2) * 4; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        in.close();
    }
}

//         *               *               *               *               *
//       *   *           *   *           *   *           *   *           *   *
//     *       *       *       *       *       *       *       *       *       *
//   *           *   *           *   *           *   *           *   *           *   
// *               *               *               *               *               *