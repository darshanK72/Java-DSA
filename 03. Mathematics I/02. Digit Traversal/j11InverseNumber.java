import java.util.Scanner;

// Complexity : O(log10(N))

public class j11InverseNumber {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(findInverse(n));
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
