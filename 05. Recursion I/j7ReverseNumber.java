import java.util.Scanner;
import java.lang.Math;

public class j7ReverseNumber{
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(reverseNum(n));
        in.close();
    }

    public static int reverseNum(int n){
        if(n%10 == n) return n;
        return (n%10)*(int)Math.pow(10,(int)(Math.log10(n/10)) + 1) + reverseNum(n/10);
    }
}