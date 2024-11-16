import java.util.Scanner;

public class j32IsPowerOfTwo {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        System.out.println(isPowerofTwo(n));
        in.close();
    }
    
    public static boolean isPowerofTwo(long n) {
        if(n == 0) return false;
        if(n == 1) return true;
        while(n > 1){
            if(n % 2 == 0) n/= 2;
            else return false;
        }
        return true;
    }
}
