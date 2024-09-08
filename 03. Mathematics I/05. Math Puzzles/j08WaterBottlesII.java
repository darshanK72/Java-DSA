import java.util.Scanner;

public class j08WaterBottlesII {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int numBottles = in.nextInt();
        int numExchange = in.nextInt();
        System.out.println(maxBottlesDrunk(numBottles, numExchange));
        in.close();
    }

    public static int maxBottlesDrunk(int numBottles, int numExchange) {
        int ans = 0;
        while (numBottles - numExchange >= 0) {
            ans += numExchange;
            numBottles = numBottles - numExchange + 1;
            numExchange += 1;
        }
        ans += numBottles;
        return ans;
    }
}
