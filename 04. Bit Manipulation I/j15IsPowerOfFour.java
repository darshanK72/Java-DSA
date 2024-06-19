import java.util.Scanner;
public class j15IsPowerOfFour{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(isPowerOfFour(n));
        in.close();
    }

    public static boolean isPowerOfFour(int n){
        int mask = 0b01010101010101010101010101010101;
        return n > 0 && (n & (n-1)) == 0 && (mask & n) > 0;
    }
}