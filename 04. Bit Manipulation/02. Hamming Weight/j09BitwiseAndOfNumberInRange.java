import java.util.Scanner;

public class j09BitwiseAndOfNumberInRange {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int left = in.nextInt();
        int right = in.nextInt();
        System.out.println(rangeBitwiseAnd(left, right));
        in.close();
    }

    public static int rangeBitwiseAnd(int left, int right) {
        while (left < right) {
            right &= (right - 1);
        }
        return right;
    }
}
