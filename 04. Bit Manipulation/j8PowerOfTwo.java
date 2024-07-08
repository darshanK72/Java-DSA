import java.util.Scanner;

public class j8PowerOfTwo{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        
        int n = in.nextInt();

        System.out.println(isPowerOfTwo(n));

        in.close();
    }

    public static boolean isPowerOfTwo(int n){
        return n != 0 && (n & (n - 1)) == 0;
    }
}