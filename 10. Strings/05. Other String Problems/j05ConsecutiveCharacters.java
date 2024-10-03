import java.util.Scanner;

public class j05ConsecutiveCharacters {
     public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(maxPower(s));
        in.close();
    }

    public static int maxPower(String s) {
        int power = 1;
        int currentPower = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                currentPower++;
            } else {
                power = Math.max(power, currentPower);
                currentPower = 1;
            }
        }
        power = Math.max(power, currentPower);
        return power;
    }
}