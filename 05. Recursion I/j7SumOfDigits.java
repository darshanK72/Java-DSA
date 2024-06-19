import java.util.Scanner;
public class j7SumOfDigits{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(sumOfDigits(n));
        in.close();
    }

    public static int sumOfDigits(int n){
        if(n <= 0) return 0;
        return sumOfDigits(n/10) + n%10;
    }
}