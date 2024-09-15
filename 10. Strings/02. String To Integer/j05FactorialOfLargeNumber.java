import java.math.BigInteger;
import java.util.ArrayList;

public class j05FactorialOfLargeNumber {
    static ArrayList<Integer> factorial(int N) {
        BigInteger fact = new BigInteger("1");
        for (int i = 1; i <= N; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        String f = fact.toString();
        ArrayList<Integer> out = new ArrayList<Integer>();
        for (int i = 0; i < f.length(); i++) {
            out.add(f.charAt(i) - '0');
        }
        return out;
    }
}
