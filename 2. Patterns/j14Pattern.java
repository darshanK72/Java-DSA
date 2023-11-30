import java.util.Scanner;
public class j14Pattern {
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        if(n%2 != 1){
            System.out.print("Enter Odd Number : ");
            n = in.nextInt();
        }

        int sp = (n/2);
        int st = 1;

        for(int i = 1; i <= n; i++){
            // System.out.println(sp + "," + st);
            for(int j = 1; j <= sp; j++){
                System.out.print("  ");
            }
            for(int j = 1; j <= st; j++){
                System.out.print("* ");
            }
            if(i <= n/2){
                sp--;
                st+=2;
            }
            else{
                sp++;
                st-=2;
            }
            System.out.println();
        }

        // for(int i = 1; i <= n; i++)
        // {
        //     for(int j = 1; j <= (n-i); j++)
        //     {
        //         System.out.print("  ");
        //     }
        //     for(int j = 1; j <= 2*i-1; j++)
        //     {
        //         System.out.print("* ");
        //     }
        //     System.out.println();
        // }

        // for(int i = n-1; i >= 1; i--)
        // {
        //     for(int j = 1; j <= (n-i); j++)
        //     {
        //         System.out.print("  ");
        //     }
        //     for(int j = 1; j <= 2*i-1; j++)
        //     {
        //         System.out.print("* ");
        //     }
        //     System.out.println();
        // }

        in.close();
    }
    
}

//     *
//   * * *
// * * * * *
//   * * * 
//     * 



//         *
//       * * *
//     * * * * *
//   * * * * * * *
// * * * * * * * * *
//   * * * * * * *
//     * * * * *
//       * * *
//         *