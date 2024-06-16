import java.util.Scanner;

public class j35AnyBaseMultiplication{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt();
        int n2 = in.nextInt();
        int b = in.nextInt();

        System.out.println(anyBaseMultiplication(n1,n2,b));

        in.close();
    }
    
    public static int anyBaseMultiplication(int n1,int n2,int b){
        int finalAns = 0;
        int k = 1;
        while(n2 > 0){
            int ans = 0;
            int carry = 0;
            int i = 1;
            int d = n2 % 10;
            int temp = n1;
            while(temp > 0 || carry > 0){
                int d1 = temp % 10;
                ans = ((d * d1 + carry) % b) * i + ans;
                carry = (d * d1 + carry)/b;
                temp /= 10;
                i *= 10;
            }
            
            finalAns = anyBaseAddition(finalAns,ans*k,b);
            n2 /= 10;
            k *= 10;
        }
        return finalAns;
      
    }

    public static int anyBaseAddition(int n1,int n2,int b){
        int ans = 0;
        int carry = 0;

        int i = 1;
        while(n1 > 0 || n2 > 0 || carry > 0){
            int d1 = n1%10;
            int d2 = n2%10;
            ans = ((d1 + d2 + carry) % b) * i + ans;
            carry = (d1 + d2 + carry)/b;
            i *= 10;
            n1 /= 10;
            n2 /= 10;
        }
        return ans;
    }

}