import java.util.Scanner;

// Complexity : O(log10(N))

public class j03PrintDigitsReverse {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        printDigitsReverse(n);
        in.close();
    }

    public static void printDigitsReverse(int n){
        while(n > 0){
            int d = n % 10;
            System.out.println(d);
            n /= 10;
        }
    }
}
