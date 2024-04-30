import java.util.ArrayList;
import java.util.Scanner;

public class j10GetMazePathsWithJump {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        System.out.println(getMazeJumpPaths(1,1,m,n));
        in.close();
    }

    public static ArrayList<String> getMazeJumpPaths(int sr,int sc,int dr,int dc){

        if(sr == dr && sc == dc){
            ArrayList<String> arr = new ArrayList<>();
            arr.add("");
            return arr;
        }
        ArrayList<String> output = new ArrayList<>();
        
        for(int ms = 1; ms <= dc - sc; ms++){
            ArrayList<String> hpaths = getMazeJumpPaths(sr, sc + ms, dr, dc);
            for(String path: hpaths){
                output.add("h" + ms + path);
            }
        }

        for(int ms = 1; ms <= dr - sr; ms++){
            ArrayList<String> vpaths = getMazeJumpPaths(sr + ms, sc, dr, dc);
            for(String path: vpaths){
                output.add("v" + ms + path);
            }
        }

        for(int ms = 1;ms <= dr - sr && ms <= dc - sc; ms++){
            ArrayList<String> dpaths = getMazeJumpPaths(sr + ms, sc + ms, dr, dc);
            for(String path: dpaths){
                output.add("d" + ms + path);
            }
        }

        return output;
    }
}
