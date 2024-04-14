import java.util.Scanner;

public class j5NegativeNumber{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        System.out.println(toBinaryInt(n));

        System.out.println("Positive Number (" + n + ") : " + Integer.toBinaryString(n));
        System.out.println("Negative Number (" + getNegativeNumber(n) + ") : " + Integer.toBinaryString(getNegativeNumber(n)));

        in.close();
    }

    public static int getNegativeNumber(int n){
        int ones = ~n;
        int twos = ones+1;
        return twos;
    }

    public static int toBinaryInt(int n){
        int i = 1;
        int out = 0;
        while(n > 0){
            int d = n % 2;
            out = d * i + out;
            n /= 2;
            i *= 10;
        }
        return out;
    }

     public static int binaryAddition(int n1,int n2){
        int ans = 0;
        int carry = 0;
        int i = 1;
        while(n1 > 0 || n2 > 0 || carry > 0){
            int d1 = n1%10;
            int d2 = n2%10;
            ans = ((d1 + d2 + carry) % 2) * i + ans;
            carry = (d1 + d2 + carry)/2;
            i *= 10;
            n1 /= 10;
            n2 /= 10;
        }
        return ans;
    }
}