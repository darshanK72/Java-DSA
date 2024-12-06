import java.util.Scanner;

public class j02BinaryRepresentation {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(getBinaryUsingMethod(n));
        System.out.println(toBinaryStringForPositive(n));
        System.out.println(toBinaryStringForBoth(n));
        System.out.println(toBinaryStringEfficient(n));
        in.close();
    }

    public static String getBinaryUsingMethod(int N) {
        String output = "";
        String bin = Integer.toBinaryString(N);
        for (int i = 1; i <= 32 - bin.length(); i++) {
            output += "0";
        }
        output += bin;
        return output;
    }

    public static String toBinaryStringForPositive(int n) {
        StringBuilder out = new StringBuilder();
        while (n > 0) {
            out.append(n & 1);
            n >>= 1;
        }
        int l = out.length();
        for (int i = 1; i <= 32 - l; i++) {
            out.append("0");
        }
        return out.reverse().toString();
    }

    public static String toBinaryStringForBoth(int n) {
        StringBuilder out = new StringBuilder();
        // Handle the case when the number is negative
        if (n < 0) {
            n = Integer.MAX_VALUE + 1 + n; // Convert negative to two's complement
        }
        while (n > 0) {
            out.append(n & 1);
            n >>= 1;
        }
        // If the number was zero, return a string of 30 zeros
        if (out.length() == 0) {
            out.append("0");
        }
        // Add leading zeros to make the string 32 bits long (as 32-bit integers are
        // standard in Java)
        int length = out.length();
        for (int i = 1; i <= 32 - length; i++) {
            out.append("0");
        }
        return out.reverse().toString();
    }

    public static String toBinaryStringEfficient(int n) {
        StringBuilder out = new StringBuilder();
        for (int i = 31; i >= 0; i--) { // Iterate through all 32 bits
            out.append((n >> i) & 1); // Extract the bit at position `i`
        }
        return out.toString(); // Return the binary string
    }

}