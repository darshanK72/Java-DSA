import java.util.Scanner;

public class j20PerfectNumber {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(checkPerfectNumber(n));
        in.close();
    }

    public static boolean checkPerfectNumber(int num) {
        int sum = 1;
        if (num == 1)
            return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                sum += (num / i);
            }
        }
        if (sum == num)
            return true;
        return false;
    }
}
