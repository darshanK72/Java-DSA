import java.util.Scanner;

public class j21HollowDiamondReactangle {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        if (n % 2 != 1) {
            System.out.print("Enter Odd Number : ");
            n = in.nextInt();
        }

        int st = n / 2;
        int sp = 1;

        for (int i = 1; i <= n; i++) {
            if (i == 1 || i == n) {
                for (int j = 1; j <= n; j++) {
                    System.out.print("* ");
                }
            } else {
                for (int j = 1; j <= st; j++) {
                    System.out.print("* ");
                }
                for (int j = 1; j <= sp; j++) {
                    System.out.print("  ");
                }
                for (int j = 1; j <= st; j++) {
                    System.out.print("* ");
                }

                if (i <= n / 2) {
                    st--;
                    sp += 2;
                } else {
                    st++;
                    sp -= 2;
                }

            }
            System.out.println();
        }

        // for(int i = 1; i <= n*2; i++){
        // System.out.print("*");
        // }

        // System.out.println();

        // for(int i = 1; i <= n - 1; i++){
        // for(int j = n-i; j >= 1; j-- ){
        // System.out.print("*");
        // }

        // for(int j = 1; j <= 2*i; j++){
        // System.out.print(" ");
        // }

        // for(int j = n-i; j >= 1; j-- ){
        // System.out.print("*");
        // }
        // System.out.println();
        // }

        // for(int i = n-2; i >= 1; i--){
        // for(int j = n-i; j >= 1; j-- ){
        // System.out.print("*");
        // }

        // for(int j = 1; j <= 2*i; j++){
        // System.out.print(" ");
        // }

        // for(int j = n-i; j >= 1; j-- ){
        // System.out.print("*");
        // }

        // System.out.println();

        // }

        // for(int i = 1; i <= n*2; i++){
        // System.out.print("*");
        // }

        in.close();
    }
}

// n = 5

// * * * * *      5
// * *   * *     2,1,2
// *       *     1,3,1
// * *   * *     2,1,2
// * * * * *      5

// n = 7
// * * * * * * *     7
// * * *   * * *     3,1,3
// * *       * *     2,3,2
// *           *     1,5,1