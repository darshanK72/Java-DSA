import java.util.Scanner;

public class j06AnyBaseAddition{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt();
        int n2 = in.nextInt();
        int b = in.nextInt();

        System.out.println(anyBaseAddition(n1,n2,b));

        in.close();
    }

    // Will work only for bases 1 to 10
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