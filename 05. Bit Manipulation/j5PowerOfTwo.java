import java.util.Scanner;

public class j5PowerOfTwo{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        
        int n = in.nextInt();

        System.out.println(isPowerOfTwo(n));

        in.close();
    }

    public static boolean isPowerOfTwo(int n){
        return (n & (n - 1)) == 0;
    }
}