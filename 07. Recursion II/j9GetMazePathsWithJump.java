import java.util.ArrayList;
import java.util.Scanner;

public class j9GetMazePathsWithJump {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        System.out.println(getMazeJumpPaths(1,1,m,n));
        in.close();
    }

    public static ArrayList<String> getMazeJumpPaths(int sr,int sc,int dr,int dc){
        if(i == m && j == n){
            ArrayList<String> arr = new ArrayList<>();
            arr.add("");
            return arr;
        }

        for(int ms = 1; ms < n - j)
    }
}
