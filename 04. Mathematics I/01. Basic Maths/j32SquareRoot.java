import java.util.Scanner;


// Complexity : O(log N * f(N))
public class j32SquareRoot {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.printf("Square Root of %d is %.5f",n,sqrt(n));
        in.close();
    }

    // Newton Raphson Method
    public static double sqrt(int n){
        double x = n;
        double root;
        while(true){
            root = 0.5 * (x + (n/x));
            if(Math.abs(root - x) <= 1e-6){
                break;
            }
            x = root;
        }
        return root;
    }
}
