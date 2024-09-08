import java.util.Scanner;

public class j06SubtractSumAndProduct {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(subtractProductAndSum(n));
        in.close();
    }
    
    public static int subtractProductAndSum(int n) {
        int sum = 0;
        int product = 1;
        while(n > 0){
            sum += n % 10;
            product *= (n%10);
            n /= 10;
        }
        return product - sum;
    }
}
