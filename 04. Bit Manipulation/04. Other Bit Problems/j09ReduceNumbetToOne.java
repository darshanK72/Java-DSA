import java.util.Scanner;

public class j09ReduceNumbetToOne {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        System.out.println(reduceToOne(s));
        System.out.println(reduceToOneEfficient(s));
        in.close();
    }

    public static int reduceToOne(String s) {
        int count = 0;
        while (s.length() != 1) {
            if (s.charAt(s.length() - 1) == '0') {
                s = s.substring(0, s.length() - 1);
            } else {
                s = addOne(s);
            }
            System.out.println(s);
            count++;
        }
        return count;

    }

    public static String addOne(String s) {
        char carry = '1';
        StringBuilder sb = new StringBuilder("");
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '1' && carry == '1') {
                sb.append('0');
            } else if (s.charAt(i) == '0' && carry == '1') {
                sb.append('1');
                carry = '0';
            } else {
                sb.append(s.charAt(i));
            }
        }
        if (carry == '1')
            sb.append('1');
        return sb.reverse().toString();
    }

    public static int reduceToOneEfficient(String s) {
        boolean carry = false;
        int ans = 0;
        for (int i = s.length() - 1; i > 0; i--) {
            char c = s.charAt(i);
            if (carry) {
                if (c == '0') {
                    c = '1';
                    carry = false;
                } else {
                    c = '0';
                }
            }

            if (c == '1') {
                carry = true;
                ans++;
            }
            ans++;
        }
        if (carry) {
            ans++;
        }
        return ans;
    }
}
