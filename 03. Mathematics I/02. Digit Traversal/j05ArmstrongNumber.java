import java.util.Scanner;

public class j05ArmstrongNumber {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(armstrongNumber(n));
        in.close();
    }

    public static String armstrongNumber(int n){
        int ans = 0;
        int temp = n;
       while(temp > 0){
           int d = temp % 10;
           temp /= 10;
           ans += d * d * d;
       }
       if(ans == n){
           return "Yes";
       }
       return "No";
    }
}
