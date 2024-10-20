import java.util.Scanner;
public class j14FastExponentation{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        double n = in.nextDouble();
        int p = in.nextInt();

        System.out.println(fastExpNive(n,p));
        System.out.println(fastExpEfficnent(n,p));
        in.close();
    }

    // O(log2(n)) -> this will work for positie numbers
    public static double fastExpNive(double x,int n){
        double ans = 1;
        while(x > 0){
            if((n & 1) == 1) ans = ans * x;
            x = x * x;
            n >>= 1; // unsigned right shift
        }
        return ans;
    }

    //O(log2(n)) handles negative numbers and negative powers
    public static double fastExpEfficnent(double x,int n){
        double result = 1.0;
        if(n < 0){
            n = -1 * n;
            x = 1/x;
        }
        while(n > 0){
            if((n & 1) == 1) result *= x;
            x *= x;
            n >>>= 1; // signed right shift
        }
        return result;
    }

    public static long modMul(long a,long b, long m){
        return (a % m * b % m) % m;
    }


    public static long fastExponention(long a,long b,long m){
        long result = 1;
        while(b > 0){
            if((b & 1) == 1){
                result = modMul(result, a, m);
            }
            a = modMul(a, a, m);
            b >>= 2;
        }
        return result;
    }
    
}