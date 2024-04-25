import java.util.Scanner;

// Complexity : O(1)

public class j16RotateNumber {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int r = in.nextInt();

        System.out.println("Original : " + n);

        int div = (int)Math.pow(10,r);

        int d = n % div;
        n /= div;

        int out = n + d * (int)Math.pow(10,(int)Math.ceil(Math.log10(n)));

        System.out.println("Rotated : " + out);

        in.close();
    }
}
