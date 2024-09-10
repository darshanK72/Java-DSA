import java.util.Scanner;

public class j21PairSumDivisibleBy60 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] time = new int[n];
        for (int i = 0; i < n; i++) {
            time[i] = in.nextInt();
        }

        System.out.println(countPairsDivisibleBy60(time));
        in.close();
    }

    public static int countPairsDivisibleBy60(int[] time) {
        int[] count = new int[60];
        int result = 0;

        for (int t : time) {
            int remainder = t % 60;
            int complement = (remainder == 0) ? 0 : 60 - remainder;
            result += count[complement];
            count[remainder]++;
        }

        return result;
    }
}
