import java.util.Scanner;
public class j01DecimalToAnyBase{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int b = in.nextInt();

        System.out.println(decimalToAnyBase(n,b));
        System.out.println(decimalToAnyBaseEfficient(n,b));
        in.close();
    }

    // only for base 1 to 10
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

    // for all bases
    public static String decimalToAnyBaseEfficient(int n,int b){
        StringBuilder ans = new StringBuilder("");
        while(n != 0){
            int rem = n % b;
            if(rem < 10) ans.append((char)('0' + rem));
            else ans.append((char)('A' + rem - 10));
            n /= b;
        }
        return ans.reverse().toString();
    }
}