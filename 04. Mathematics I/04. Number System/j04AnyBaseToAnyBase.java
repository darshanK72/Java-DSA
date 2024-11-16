import java.util.Scanner;

public class j04AnyBaseToAnyBase{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int b1 = in.nextInt();
        int b2 = in.nextInt();
        System.out.println(decimalToAnyBase(anyBaseToDecimal(n,b1),b2));
        in.close();
    }
    
    // Will work only for bases 1 to 10
    public static int decimalToAnyBase(int n,int b){
        int out = 0;
        int i = 1;
        while(n > 0){
           int d =  n % b;
           out = d * i  + out;
           n /= b;
           i *= 10;
        }
        return out;
    }

    // Will work only for bases 1 to 10
    public static int anyBaseToDecimal(int n,int b1){
        int dec = 0;
        int temp = n;
        int i = 1;
        while(temp > 0){
            int d = temp % 10;
            dec = d * i + dec;
            temp /= 10;
            i *= b1;
        }
        return dec;
    }
}