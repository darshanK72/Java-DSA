import java.util.Scanner;

public class j19Pattern {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        if(n%2 != 1){
            System.out.print("Enter Odd Number : ");
            n = in.nextInt();
        }

        int sp1 = n/2; 
        int sp2 = -1;

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= sp1; j++){
                System.out.print("  ");
            }
            System.out.print("* ");
            for(int j = 1; j <= sp2; j++){
                System.out.print("  ");
            }
            if(i != 1 && i != n)
                System.out.print("* ");
            if(i <= n/2){
                sp1--;
                sp2+=2;
            }
            else{
                sp1++;
                sp2-=2;
            }
            System.out.println();
        }




        // for(int i = 1; i <= n; i++){
        //     for(int j = 1; j <= n-i; j++){
        //         System.out.print(" ");
        //     }
        //     System.out.print("*");
        //     for(int j = 3; j <= 2*i-1; j++)
        //     {
        //         System.out.print(" ");
        //     }
        //     if(i > 1)
        //     System.out.print("*");

        //     System.out.println();
        // }

        // for(int i = n-1; i >= 1; i--)
        // {
        //     for(int j = 1; j <= (n-i); j++){
        //         System.out.print(" ");
        //     }
        //     System.out.print("*");
        //     for(int j = 3; j <= 2*i -1; j++){
        //         System.out.print(" ");
        //     }
        //     if(i > 1)
        //     System.out.print("*");
        //     System.out.println();

        // }



        in.close();
    }
}


// 15.      *
//         * *
//        *   *
//       *     *
//      *       *
//       *     *
//        *   *
//         * *
//          *


//     *         2,-1
//   *   *       1,1
// *       *     0,3
//   *   *       1,1
//     *         2,-1