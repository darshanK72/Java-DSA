import java.util.Scanner;

public class j08DoubleReversal {
     public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(isSameAfterReversals(n));
        System.out.println(isSameAfterReversalsEfficient(n));
        in.close();
    }

    public static boolean isSameAfterReversals(int num){
        int rev1 = 0;
        int rev2 = 0;
        int x = num;
        while(x > 0){
            rev1 = rev1 * 10 + x % 10;
            x /= 10;
        }

        x = rev1;
        while(x > 0){
            rev2 = rev2 * 10 + x % 10;
            x /= 10;
        }

        if(num == rev2) return true;
        return false;
    }
    public static boolean isSameAfterReversalsEfficient(int num) {
        return num == 0 || num % 10 != 0;
    }
}
