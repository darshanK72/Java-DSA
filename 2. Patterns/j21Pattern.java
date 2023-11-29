import java.util.Scanner;

public class j21Pattern {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        for(int i = 1; i <= n*2; i++){
            System.out.print("*");
        }

        System.out.println();

        for(int i = 1; i <= n - 1; i++){
            for(int j = n-i; j >= 1; j-- ){
                System.out.print("*");
            }

            for(int j = 1; j <= 2*i; j++){
                System.out.print(" ");
            }

            for(int j = n-i; j >= 1; j-- ){
                System.out.print("*");
            }
            System.out.println();
        }

        for(int i = n-2; i >= 1; i--){
            for(int j = n-i; j >= 1; j-- ){
                System.out.print("*");
            }

            for(int j = 1; j <= 2*i; j++){
                System.out.print(" ");
            }

            for(int j = n-i; j >= 1; j-- ){
                System.out.print("*");
            }

            System.out.println();

        }

        for(int i = 1; i <= n*2; i++){
            System.out.print("*");
        }


        // for (int i = 1; i <= n * 2; i++) {
        //     for (int j = 1; j <= n * 2; j++) {
        //         if (i <= n) {
        //             if (j <= n-i+1 || j >= n + i) {
        //                 System.out.print("*");
        //             } else {
        //                 System.out.print(" ");
        //             }
        //         } else {
        //             if (j <= i - n || j >= 3*n-i) {
        //                 System.out.print("*");
        //             } else {
        //                 System.out.print(" ");
        //             }
        //         }
        //     }
        //     System.out.println();
        // }

        in.close();
    }
}

// 18.   **********
//       ****  ****
//       ***    ***
//       **      **
//       *        *
//       *        *
//       **      **
//       ***    ***
//       ****  ****
//       **********
