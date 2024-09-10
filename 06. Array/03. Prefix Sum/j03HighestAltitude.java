import java.util.Scanner;

public class j03HighestAltitude {
    public static void main(String args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] gain = new int[n];
        for(int i = 0; i < n; i++){
            gain[i] = in.nextInt();
        }
        System.out.println(largestAltitude(gain));
        in.close();
    }
    public static int largestAltitude(int[] gain) {
        int highestGain = 0;
        int tempGain = 0;
        for (int i = 0; i < gain.length; i++) {
            tempGain += gain[i];
            if (tempGain > highestGain) {
                highestGain = tempGain;
            }
        }

        return highestGain;
    }
}
