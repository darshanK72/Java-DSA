import java.util.Scanner;

// Complexity : O(log N)

public class j4SquareRoot {
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int p = in.nextInt();

        System.out.printf("Square Root of %d is %.3f",n,sqrt(n,p));

        in.close();
    }

    public static double sqrt(int n,int p){
        int s = 0; 
        int e = n;
        double root = 0.0;

        while(s <= e){
            int m = s + (e - s)/2;

            if(m * m == n){
                return m;
            }

            if(m * m > n){
                e = m - 1;
            }
            else{
                s = m + 1;
            }
        }

        double precision = 0.1;

        for(int i = 1; i <= p; i++){
            while(root * root <= n){
                root += precision;
            }
            root -= precision;
            precision /= 10;
        }

        return root;
    }
}
