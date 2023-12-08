import java.util.Scanner;
public class j5Pattern {
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j <= n-i; j++)
            {
                System.out.print("  ");
            }
            for(int j = 1; j <= i; j++)
            {
                System.out.print("* ");
            }
            System.out.println();
        }

        // int sp = n-1; 
        // int st = 1;

        // for(int i = 1; i <= n; i++)
        // {
        //     System.out.println(sp + "" + st);
        //     sp--;
        //     st++;
        // }

        in.close();
    }
    
}

//         *
//       * *
//     * * *
//   * * * *
// * * * * *
