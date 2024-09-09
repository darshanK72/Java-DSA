import java.util.Scanner;
public class j04SumWithoutPlus{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        System.out.println(getSum(a,b));
        in.close();
    }

    public static int getSum(int a, int b){
        while(b != 0){
            int carry = (a & b) << 1; // carry
            a = a ^ b; // sum of two numbers without carry;
            b = carry;
        }
        return a;
    }
}