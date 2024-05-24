import java.util.Scanner;
public class j18InvertedHollowTriangle {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        for(int i = 1; i <= 2*n - 1; i++){
            System.out.print("*");
        }
        System.out.println();

        for(int i = n-1; i >= 1; i--){
            for(int j = 1; j <= (n-i); j++){
                System.out.print(" ");
            }
            
            System.out.print("*");

            for(int j = 3; j <= 2*i - 1; j++){
                System.out.print(" ");
            }

            if(i > 1)
            System.out.print("*");

            System.out.println();
        }
        in.close();
    }
}

// 5
// *********
//  *     *
//   *   *
//    * *
//     *
