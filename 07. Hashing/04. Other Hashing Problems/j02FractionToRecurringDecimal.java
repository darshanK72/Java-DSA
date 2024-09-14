import java.util.HashMap;
import java.util.Scanner;

public class j02FractionToRecurringDecimal {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int den = in.nextInt();
        System.out.println(fractionToDecimal(num, den));
        in.close();
    }

    public static String fractionToDecimal(int numerator, int denominator) {
        StringBuilder ans = new StringBuilder();
        if (numerator == 0)
            return "0";
        else if (denominator == 0)
            return "";
        if ((numerator < 0) ^ (denominator < 0)) {
            ans.append("-");
        }
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        ans.append(num / den);
        long r = num % den;

        if (r == 0) {
            return ans.toString();
        } else {
            ans.append(".");
            HashMap<Long, Integer> map = new HashMap<>();
            while (r != 0) {
                if (map.containsKey(r)) {
                    ans.insert(map.get(r), "(");
                    ans.append(")");
                    break;
                } else {
                    map.put(r, ans.length());
                    r *= 10;
                    ans.append(r / den);
                    r = r % denominator;
                }
            }
            return ans.toString();
        }
    }
}
