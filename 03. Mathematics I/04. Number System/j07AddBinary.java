import java.util.Scanner;

public class j07AddBinary {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String n1 = in.next();
        String n2 = in.next();

        System.out.println(addBinary(n1, n2));

        in.close();
    }

    public static String addBinary(String a, String b) {
        StringBuilder output = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;
        while (i >= 0 || j >= 0 || carry > 0) {
            int d = carry;
            if (i >= 0) {
                d += a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                d += b.charAt(j) - '0';
                j--;
            }
            output.insert(0, d % 2);
            carry = d / 2;
        }
        return output.toString();
    }
}
