import java.util.Scanner;

public class j33Arrow {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        if(n%2 != 1){
            System.out.print("Enter Odd Number : ");
            n = in.nextInt();
        }

        int st = 1;

        for(int i = 1; i <= n; i++){


            for(int j = 1; j <= n/2;j++){
                if(i != n/2+1)
                System.out.print("  ");
                else
                System.out.print("* ");
            }

            for(int j = 1; j <= st; j++){
                System.out.print("* ");
            }
           
            if(i <= n/2){
                st++;
            }
            else{
                st--;
            }

            System.out.println();
        }

        in.close();
    }
}


//     *      n/2  1
//     * *         2
// * * * * *       3
//     * *         2
//     *           1