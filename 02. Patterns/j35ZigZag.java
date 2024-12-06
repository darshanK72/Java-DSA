import java.util.Scanner;

public class j35ZigZag {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int height = in.nextInt();
        int length = in.nextInt();

        printZigZag(height,length);

        // for (int i = 1; i <= height; i++) {

        //     for (int j = height - i; j >= 1; j--) {
        //         System.out.print("  ");
        //     }
        //     for (int j = 1; j <= length; j++) {

        //         System.out.print("*");

        //         for (int k = 1; k <= 3 + (i - 2) * 4; k++) {
        //             System.out.print(" ");
        //         }

        //         if (i > 1 && i < height)
        //             System.out.print("*");

        //         if (i == height && j == length) {
        //             System.out.print("*");
        //         }

        //         for (int k = 1; k <= 3 + ((height - i + 1) - 2) * 4; k++) {
        //             System.out.print(" ");
        //         }
        //     }
        //     System.out.println();
        // }

        in.close();
    }

    public static void printZigZag(int n,int l){
        int sp1 = n - 1;
        int sp2 = -1;
        int sp3 = (n-1)*2-1;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= sp1; j++){
                System.out.print("  ");
            }
            for(int k = 1; k <= l; k++){
                System.out.print("* ");
                for(int j = 1; j <= sp2; j++){
                    System.out.print("  ");
                }
                if(i > 1 && i < n){
                    System.out.print("* ");
                }
                for(int j = 1; j <= sp3; j++){
                    System.out.print("  ");
                }
                if(k == l && i == n){
                    System.out.print("* ");
                }
            }
            sp1--;
            sp2+=2;
            sp3-=2;
           
            System.out.println();
        }
    }
}

//         *               *               *               *               *
//       *   *           *   *           *   *           *   *           *   *
//     *       *       *       *       *       *       *       *       *       *
//   *           *   *           *   *           *   *           *   *           *   
// *               *               *               *               *               *