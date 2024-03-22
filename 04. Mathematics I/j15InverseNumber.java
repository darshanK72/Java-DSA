import java.util.Scanner;

// Complexity : O(log10(N))

public class j15InverseNumber {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        // int out = 0;

        System.out.println(findInverse(n));

        // int i = 1;

        // while(n > 0){
        //     int d = n % 10;

        //     out += i * Math.pow(10,d-1);
        //     i++;

        //     // System.out.println(d);
        //     n /= 10;
        // }

        // System.out.println(out);

        in.close();
    }

    public static int findInverse(int number) {
        int inverse = 0;
        int position = 1;

        while (number != 0) {
            int digit = number % 10; // Extract the last digit
            inverse += position * Math.pow(10, digit - 1); // Swap digit with its position from the end
            number /= 10; // Remove the last digit
            position++; // Move to the next position
        }

        return inverse;
    }
}
