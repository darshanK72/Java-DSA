import java.util.Scanner;

public class j31W {
   public static void main(String args[]){
    Scanner in = new Scanner(System.in);

    int n = in.nextInt();

    if(n%2 != 1){
        System.out.print("Enter Odd Number : ");
        n = in.nextInt();
    }


    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= n; j++){
            if(j == 1 || j == n){
                System.out.print("* ");
            }
            else if(i > n/2 && (i == j || i+j==n+1) ){
                System.out.print("* ");
            }
            else{
                System.out.print("  ");
            }
        }
        System.out.println();
    }
    in.close();
   } 
}

// *       *
// *       *
// *   *   *
// * *   * *
// *       *
