import java.util.Scanner;

public class j11CountPrefixAligned {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] flips = new int[n];
        for(int i = 0; i < n; i++){
            flips[i] = in.nextInt();
        }
        System.out.println(numTimesAllBlue(flips));
        in.close();
    }

    public static int numTimesAllBlue(int[] flips) {
        int maxTill = 0;
        int ans = 0;
        for (int i = 1; i <= flips.length; i++) {
            maxTill = Math.max(maxTill, flips[i - 1]);
            if (maxTill == i)
                ans++;
        }
        return ans;
    }
}
