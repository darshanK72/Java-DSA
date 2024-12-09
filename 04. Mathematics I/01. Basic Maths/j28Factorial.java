import java.util.Scanner;

// Complexity : O(n)
public class j28Factorial {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        long n = in.nextLong();

        // long fact = 1;

        // for(long i = 1; i <= n; i++){
        //     fact *= i;
        // }

        System.out.println("Factorial of " + n + " is " + fact(n));

        in.close();
    }

    // factorial iterative
    public static long factorial(long n){
        long ans = 1;
        for(long i = 1; i <= n; i++){
            ans *= i;
        }
        return ans;
    }

    // recursive
    public static long fact(long n){
        if(n <= 1){
            return 1;
        }
        return n * fact(n-1);
    }
}
