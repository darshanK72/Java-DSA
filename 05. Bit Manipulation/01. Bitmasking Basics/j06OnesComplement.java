import java.util.Scanner;

public class j06OnesComplement {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        System.out.println(Integer.toBinaryString(num));
        System.out.println(Integer.toBinaryString(~num)); // 32 Bit complement
        System.out.println(Integer.toBinaryString(findComplement(num)));
        System.out.println(Integer.toBinaryString(findComplementEfficient(num)));
        in.close();
    }

    public static int findComplement(int num) {
        int output = 0;
        int i = 0;
        while (num > 0) {
            if ((num & 1) == 0) {
                output = output | (1 << i);
            }
            num >>= 1;
            i++;
        }
        return output;
    }

    public static int findComplementEfficient(int num) {
        int noOfBits = (int) (Math.log(num) / Math.log(2) + 1);
        return num ^ ((1 << noOfBits) - 1);
    }
}
