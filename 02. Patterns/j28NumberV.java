import java.util.Scanner;

public class j28NumberV {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        for(int i = 1; i <= n; i++){
            int j;
            for(j = 1; j <= i; j++){
                System.out.print(j);
            }

            for(int k = 1; k <= (2*n-2*i); k++)
            {
                System.out.print(" ");
            }
            j--;

            for(; j >= 1; j--){
                System.out.print(j);
            }

            System.out.println();
        }

        in.close();
    }
}


// 35.    1      1
//        12    21
//        123  321
//        12344321

