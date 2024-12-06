import java.util.Scanner;

public class j32Swastika {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        if (n % 2 != 1) {
            System.out.print("Enter Odd Number : ");
            n = in.nextInt();
        }

        printSwastika(n);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                if (i == n / 2 + 1 || j == n / 2 + 1) {
                    System.out.print("* ");
                } else if (i <= n / 2) {
                    if (j == 1 && i <= n / 2) {
                        System.out.print("* ");
                    } else if (i == 1 && j > n / 2 + 1) {
                        System.out.print("* ");
                    } else {
                        System.out.print(" ");
                    }
                } else {

                    if (i == n && j <= n / 2) {
                        System.out.print("* ");
                    } else if (j == n && i > n / 2 + 1) {
                        System.out.print("* ");
                    } else {
                        System.out.print(" ");
                    }

                }
            }
            System.out.println();
        }

        in.close();
    }

    public static void printSwastika(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == n / 2 + 1 || j == n / 2 + 1) {
                    System.out.print("* ");
                }else if(i <= n/2){
                    if(j == 1 || (i == 1 && j > n/2)){
                        System.out.print("* ");
                    }else{
                        System.out.print("  ");
                    }
                }else{
                    if((i == n && j <= n/2) || j == n){
                        System.out.print("* ");
                    }else{
                        System.out.print("  ");
                    }
                }
            }
            System.out.println();
        }
    }
}

// * * * *
// * *
// * * * * *
// * *
// * * * *