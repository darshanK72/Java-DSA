import java.util.Scanner;
public class j17IsPowerOfFour{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(isPowerOfFour(n));
        System.out.println(isPowerOfFourOther(n));
        in.close();
    }

    public static boolean isPowerOfFour(int n){
        // 0b01010101010101010101010101010101
        int mask = 0x55555555;
        return n > 0 && (n & (n-1)) == 0 && (mask & n) > 0;
    }

    public static boolean isPowerOfFourOther(int n){
        return (n > 0) && ((n & (n-1)) == 0) && (n % 3 == 1);
    }
}