import java.util.Scanner;
public class j14Pattern {
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j <= (n-i); j++)
            {
                System.out.print("  ");
            }
            for(int j = 1; j <= 2*i-1; j++)
            {
                System.out.print("* ");
            }
            System.out.println();
        }

        for(int i = n-1; i >= 1; i--)
        {
            for(int j = 1; j <= (n-i); j++)
            {
                System.out.print("  ");
            }
            for(int j = 1; j <= 2*i-1; j++)
            {
                System.out.print("* ");
            }
            System.out.println();
        }

        in.close();
    }
    
}
