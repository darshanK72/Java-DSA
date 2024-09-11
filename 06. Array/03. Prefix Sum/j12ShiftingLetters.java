import java.util.Scanner;

public class j12ShiftingLetters {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int n = in.nextInt();
        int[] shifts = new int[n];
        for (int i = 0; i < n; i++) {
            shifts[i] = in.nextInt();
        }
        System.out.println(shiftingLetters(s, shifts));
        in.close();
    }

    public static String shiftingLetters(String s, int[] shifts) {
        char[] ans = s.toCharArray();
        long totalShifts = 0;
        for (int i = shifts.length - 1; i >= 0; i--) {
            totalShifts += shifts[i];
            ans[i] = (char) (((ans[i] - 'a' + totalShifts) % 26) + 'a');
        }
        return new String(ans);
    }
}
