import java.util.Scanner;

public class j15ArrowHead {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        if(n%2 != 1){
            System.out.print("Enter Odd Number : ");
            n = in.nextInt();
        }

        int st = 1; 

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= st; j++){
                System.out.print("*");
            }
            if(i <= n/2){
                st++;
            }
            else{
                st--;
            }
            System.out.println();
        }

        // for (int i = 1; i <= n; i++) {
            
        //     for(int j = 1; j <= i; j++){
        //         System.out.print("* ");
        //     }
        //     System.out.println();

        // }

        // for(int i = n - 1; i >= 1; i--){
        //     for(int j = i; j >= 1; j--){
        //         System.out.print("* ");
        //     }
        //     System.out.println();

        // }

        in.close();
    }
}

// *
// **
// ***
// **
// *

// n = 7

// *
// * *
// * * *
// * * * *
// * * * * *
// * * * * * *
// * * * * * * *
// * * * * * *
// * * * * *
// * * * *
// * * *
// * *
// *
