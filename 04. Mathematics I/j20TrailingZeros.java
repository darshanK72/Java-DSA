import java.util.Scanner;

// Complexity : O(Log5 N)

public class j20TrailingZeros {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        System.out.println("Trailing zeros at end of " + n + "! are " + findTrailingZeros(n));

        in.close();
    }

    // recursive
    public static int findTrailingZeros(int n){
        int count = 0;
        for(int i = 5; n/i >= 1; i*=5){
            count += n/i;
        }
        return count;
    }
}

// 1 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 5  * 3 * 7  * 3 * 3 * 5 * 11