import java.util.Scanner;

public class j27ContinuousLeftRightTriangle {
   public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        int l = 1; 
        int r = Integer.MIN_VALUE;

        for(int i = 1; i <= n; i++){
            for(int j = i-1; j >= 1; j--){
                System.out.print("  ");
            }
            for(int j = 1; j <= (n-i+1); j++){
                System.out.print(l + " ");
                l++;
            }
            r = getBelowTerms(n-i);

            for(int j = 1; j <= (n-i+1); j++){
                System.out.print((l+r) + " ");
                r++;
            }
            System.out.println();
        }

        in.close();
   } 


   public static int getBelowTerms(int n){
        int count = 0;
        for(int i = 1;i <= n;i++){
            count+=(2*i);
        }
        return count;
   }
}


// 27.   1 2 3 4  17 18 19 20
//         5 6 7  14 15 16
//           8 9  12 13
//             10 11