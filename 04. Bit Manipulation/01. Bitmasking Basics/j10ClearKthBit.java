import java.util.Scanner;

public class j10ClearKthBit {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int k = in.nextInt();
        System.out.println(checkKthBit(num,k));
        in.close();
    }

    // k is starting from right index from zero (o)
    public static boolean checkKthBit(int num, int k) {
        return (num & ~(1 << k)) != 0;
    }

    // n is starting from 1, which is position of bit from right
    public static boolean checkNthBit(int num,int n){
        return (num & ~(1 << (n-1))) != 0;
    }
}
