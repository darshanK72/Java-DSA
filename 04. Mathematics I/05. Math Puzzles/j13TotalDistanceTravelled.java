import java.util.Scanner;

public class j13TotalDistanceTravelled {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int mainTank = in.nextInt();
        int additionalTank = in.nextInt();
        System.out.println(distanceTraveled(mainTank, additionalTank));
        in.close();
    }

    public static int distanceTraveled(int mainTank, int additionalTank) {
        int ans = 0;
        if (mainTank < 5)
            return mainTank * 10;
        while (mainTank > 0) {
            if (mainTank < 5) {
                ans += mainTank * 10;
                break;
            } else {
                ans += 50;
                mainTank -= 5;
                if (additionalTank >= 1) {
                    mainTank++;
                    additionalTank--;
                }
            }
        }
        return ans;
    }
}
