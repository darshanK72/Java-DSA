import java.util.Scanner;

public class j14ValidateIpAddress {

     public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(validIPAddress(s));
        in.close();
    }
    public static String validIPAddress(String queryIp) {
        if (queryIp.contains(".")) {
            return isIPv4(queryIp) ? "IPv4" : "Neither";
        } else if (queryIp.contains(":")) {
            return isIPv6(queryIp) ? "IPv6" : "Neither";
        }
        return "Neither";
    }

    public static boolean isIPv4(String s) {
        if (s.startsWith(".") || s.endsWith(".")) {
            return false;
        }

        String[] nums = s.split("\\.");
        if (nums.length != 4) return false;

        for (String n : nums) {
            if (n.length() == 0 || n.length() > 3 || (n.length() > 1 && n.charAt(0) == '0')) return false;
            for (char c : n.toCharArray()) {
                if (!Character.isDigit(c)) return false;
            }

            int val = Integer.parseInt(n);
            if (val < 0 || val > 255) return false;
        }
        return true;
    }

    public static boolean isIPv6(String s) {
        if (s.startsWith(":") || s.endsWith(":")) {
            return false;
        }

        String[] nums = s.split(":");
        if (nums.length != 8) return false;

        for (String n : nums) {
            if (n.length() == 0 || n.length() > 4) return false;
            for (char c : n.toCharArray()) {
                if (!Character.isDigit(c) && !isHexLetter(c)) return false;
            }
        }
        return true;
    }

    public static boolean isHexLetter(char c) {
        return (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
    }
}

// Given a string queryIP, return "IPv4" if IP is a valid IPv4 address, "IPv6" if IP is a valid IPv6 address or "Neither" if IP is not a correct IP of any type.
// A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255 and xi cannot contain leading zeros. For example, "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses while "192.168.01.1", "192.168.1.00", and "192.168@1.1" are invalid IPv4 addresses.

// A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:

// 1 <= xi.length <= 4
// xi is a hexadecimal string which may contain digits, lowercase English letter ('a' to 'f') and upper-case English letters ('A' to 'F').
// Leading zeros are allowed in xi.
// For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6 addresses, while "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6 addresses.