import java.util.Scanner;

public class j02BinaryRepresentation {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(getBinaryRep(n));
        System.out.println(toBinaryString(n));
        in.close();
    }

    public static String getBinaryRep(int N) {
        String output = "";
        String bin = Integer.toBinaryString(N);
        for (int i = 1; i <= 30 - bin.length(); i++) {
            output += "0";
        }
        output += bin;
        return output;
    }

    public static String toBinaryString(int n) {
        StringBuilder out = new StringBuilder();
        while (n > 0) {
            out.append(n & 1);
            n >>= 1;
        }
        int l = out.length();
        for (int i = 1; i <= 30 - l; i++) {
            out.append("0");
        }
        return out.reverse().toString();
    }
}