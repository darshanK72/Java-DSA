import java.util.Scanner;
public class j24RightDiagonal {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i+j == n+1){
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

//             *
//           *
//         *
//       *
//     *
//   *
// *