import java.util.Scanner;

// Complexity : O(log10 N)

public class j17ReverseNumber {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        int reverse = 0;

        while(n > 0){
            reverse = reverse*10 + n%10;
            n /= 10;
        }

        System.out.println(reverse);
        in.close();
    }
}
