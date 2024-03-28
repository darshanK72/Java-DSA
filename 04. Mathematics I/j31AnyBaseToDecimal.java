import java.util.Scanner;

public class j31AnyBaseToDecimal{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int b1 = in.nextInt();

        System.out.println(anyBaseToDecimal(n,b1));

        in.close();
    }

    public static long anyBaseToDecimal(int n,int b1){
        long dec = 0;
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