import java.util.Scanner;

public class j19WinningPlayerInCoinGame {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int coin75 = in.nextInt();
        int coin10 = in.nextInt();
        System.out.println(losingPlayer(coin75,coin10));
        in.close();
    }

    public static String losingPlayer(int x, int y) {
        // Calculate how many full rounds can be played
        int rounds = Math.min(x / 2, y / 8);
        x -= rounds * 2; // Update remaining coins of value 75
        y -= rounds * 8; // Update remaining coins of value 10

        // Determine the winner based on remaining coins
        return (x > 0 && y >= 4) ? "Alice" : "Bob";
    }
}
