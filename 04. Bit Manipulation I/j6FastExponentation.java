import java.util.Scanner;
public class j6FastExponentation{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int p = in.nextInt();

        System.out.println(fastExp(n,p));
        in.close();
    }

    public static int fastExp(int n,int p){
        int ans = 1;
        while(p > 0){
            if((p & 1) == 1) ans = ans * n;
            n = n * n;
            p >>= 1; // signed right shift
        }
        return ans;
    }

    // handles negative numbers and negative powers
    public static double fastExp2(double x,int n){
        double result = 1.0;
        if(n < 0){
            n = -1 * n;
            x = 1/x;
        }
        while(n != 0){
            if((n & 1) != 0) result *= x;
            x *= x;
            n >>>= 1; // not signed right shift
        }
        return result;
        
    }
}