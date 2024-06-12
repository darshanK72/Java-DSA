import java.util.Scanner;

public class j12NthFibonacci {
     public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        
        System.out.println(nthFibonacci(n));
        in.close();
    }

    static int nthFibonacci(int n){
        if(n == 1) return 0;
        if(n == 2) return 1;
        return nthFibonacci(n - 1) + nthFibonacci(n - 2);
    }
}
