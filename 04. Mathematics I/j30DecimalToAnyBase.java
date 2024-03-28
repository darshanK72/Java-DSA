import java.util.Scanner;
import java.lang.Math;

public class j30DecimalToAnyBase{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int b = in.nextInt();

        System.out.println(decimalToAnyBase(n,b));
        in.close();
    }

    public static long decimalToAnyBase(int n,int b){
        long out = 0;
        int i = 1;
        while(n > 0){
           int d =  n % b;
           out = d * i  + out;
           n /= b;
           i *= 10;
        }
        return out;
    }
}