import java.util.Scanner;

public class j04GuessNumberHigherLower {
    private int guessed;

    public j04GuessNumberHigherLower(int guessed) {
        this.guessed = guessed;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int guessed = in.nextInt();
        j04GuessNumberHigherLower guessingGame = new j04GuessNumberHigherLower(guessed);
        int n = in.nextInt();
        System.out.println(guessingGame.guessNumber(n));
        in.close();
    }

    public int guess(int n) {
        if (guessed == n)
            return 0;
        else if (guessed < n)
            return 1;
        else
            return -1;
    }

    public int guessNumber(int n) {
        int s = 1;
        int e = n;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (guess(mid) == -1) {
                e = mid - 1;
            } else if (guess(mid) == 1) {
                s = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
