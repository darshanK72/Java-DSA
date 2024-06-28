import java.util.Scanner;

public class j31AnyBaseToDecimal{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        String n = in.next();
        int b1 = in.nextInt();

        // System.out.println(anyBaseToDecimal(Integer.parseInt(n),b1));
        System.out.println(anyBaseToDecimalEfficient(n,b1));

        in.close();
    }

    // will work only for 1 to 10 base
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

    public static long anyBaseToDecimalEfficient(String n, int b){
        long ans = 0;
        int base = 1;
        for(int i = n.length()-1;i >= 0; i--){
            if(n.charAt(i) >= 'A' && n.charAt(i) <= 'F'){
                ans = (10 + n.charAt(i) - 'A') * base + ans;
            }else{
                ans = (n.charAt(i) - '0') * base + ans;
            }
            base *= b;
        }
        return ans;
    }

}