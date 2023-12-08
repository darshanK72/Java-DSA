import java.util.Scanner;

// Complexity : O(log10(N))

public class j15InverseNumber {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int out = 0;

        int i = 1;

        while(n > 0){
            int d = n % 10;

            out += i * Math.pow(10,d-1);
            i++;

            // System.out.println(d);
            n /= 10;
        }

        System.out.println(out);

        in.close();
    }
}
