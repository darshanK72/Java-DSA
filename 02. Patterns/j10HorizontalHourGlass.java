import java.util.Scanner;

public class j10HorizontalHourGlass {
    
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        if(n%2 != 1){
            System.out.print("Enter Odd Number : ");
            n = in.nextInt();
        }

        int st = 1; 
        int sp = n-1;

        for(int i = 1; i <= n; i++){
           for(int j = 1; j <= st; j++){
            System.out.print("* ");
           }

           for(int j = 1; j <= sp; j++){
            System.out.print("  ");
           }

           for(int j = 1; j <= st; j++){
            System.out.print("* ");
           }

            if(i <= n/2){
                st++;
                sp -= 2;
            }
            else{
                st--;
                sp += 2;
            }

            System.out.println();
        }



        // for(int i = 1; i <= n; i++)
        // {
        //     for(int j = 1; j <= i; j++)
        //     {
        //         System.out.print("* ");
        //     }
        //     for(int j = 1; j <= 2*n - i*2; j++)
        //     {
        //         System.out.print("  ");
        //     }
        //     for(int j = 1; j <= i; j++)
        //     {
        //         System.out.print("* ");
        //     }
        //     System.out.println();
        // }
        // for(int i = n; i >= 1; i--)
        // {
        //     for(int j = 1; j <= i; j++)
        //     {
        //         System.out.print("* ");
        //     }
        //     for(int j = 1; j <= 2*n - i*2; j++)
        //     {
        //         System.out.print("  ");
        //     }
        //     for(int j = 1; j <= i; j++)
        //     {
        //         System.out.print("* ");
        //     }
        //     System.out.println();
        // }

        in.close();
    }
}

// n = 5

// *         *   1 4 1
// * *     * *   2 2 2
// * * * * * *   3 0 3
// * *     * *   2 2 2
// *         *   1 4 1


// n = 9
// *                 *
// * *             * *
// * * *         * * *
// * * * *     * * * *
// * * * * * * * * * *
// * * * *     * * * *
// * * *         * * *
// * *             * *
// *                 *