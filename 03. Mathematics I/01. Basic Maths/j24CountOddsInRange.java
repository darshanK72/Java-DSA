import java.util.Scanner;

public class j24CountOddsInRange {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int low = in.nextInt();
        int high = in.nextInt();
        System.out.println(countOdds(low, high));
        in.close();
    }

    public static int countOdds(int low, int high) {
        return (high + 1) / 2 - low / 2;
    }
}
