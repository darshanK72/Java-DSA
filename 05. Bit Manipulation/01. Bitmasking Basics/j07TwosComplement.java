import java.util.Scanner;

public class j07TwosComplement {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String num = in.next();
        System.out.println(twosComplement(num));
        in.close();
    }
    
    public static String twosComplement(String bi) {
        String flipped = "";
        for (int i = 0; i < bi.length(); i++) {
            flipped += (bi.charAt(i) - '0' == 0) ? '1' : '0';
        }
        int carry = 1;
        StringBuilder output = new StringBuilder();
        for (int i = flipped.length() - 1; i >= 0; i--) {
            int d = (flipped.charAt(i) - '0' + carry) % 2;
            carry = (flipped.charAt(i) - '0' + carry) / 2;
            output.insert(0, d);
        }
        return output.toString();
    }
}
