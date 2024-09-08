import java.util.Scanner;

public class j07WaterBottlesI {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int numBottles = in.nextInt();
        int numExchange = in.nextInt();
        System.out.println(numWaterBottles(numBottles, numExchange));
        in.close();
    }

    public static int numWaterBottles(int numBottles, int numExchange) {
        int ans = numBottles;
        while (numBottles / numExchange > 0) {
            ans += numBottles / numExchange;
            numBottles = numBottles / numExchange + numBottles % numExchange;
        }
        return ans;
    }
}
