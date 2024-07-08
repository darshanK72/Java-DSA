import java.util.Scanner;

// Complexity : O(1)


public class j12DigitCount {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        // System.out.print("Number of Digits in " + n );
        // int count = 0;

        // while(n > 0){
        //     count++;
        //     n /= 10;
        // }

        // System.out.print(" is " + count);

        System.out.println("Number of Digits in " + n + " are " + Math.round(Math.log10(n)));

        in.close();
    }
}
