import java.util.ArrayList;
import java.util.Scanner;

public class j20GetAllMazePaths {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        boolean[][] visited = new boolean[m][n];
        System.out.println(getAllMazePaths(1,1,m,n,"",visited));
        printAllMazePaths(1,1,m,n,"",visited);
        in.close();
    }


    public static void printAllMazePaths(int sr,int sc,int dr,int dc,String step,boolean[][] visited){
        if(sr < 1 || sc < 1 || sc > dc || sr > dr || visited[sr-1][sc-1]){
            return;
        }
        if(sr == dr && sc == dc){
            System.out.println(step);
        }
        visited[sr-1][sc-1] = true;
        printAllMazePaths(sr - 1,sc,dr,dc,step + "u",visited);
        printAllMazePaths(sr,sc - 1,dr,dc,step + "l",visited);
        printAllMazePaths(sr + 1,sc,dr,dc,step + "d",visited);
        printAllMazePaths(sr,sc + 1,dr,dc,step + "r",visited);
        visited[sr-1][sc-1] = false;
    }

    public static ArrayList<String> getAllMazePaths(int sr,int sc,int dr,int dc,String step,boolean[][] visited){
        if(sr < 1 || sc < 1 || sc > dc || sr > dr || visited[sr-1][sc-1]){
            return new ArrayList<String>();
        }
        if(sr == dr && sc == dc){
            ArrayList<String> arr = new ArrayList<String>();
            arr.add(step);
            return arr;
        }
        visited[sr-1][sc-1] = true;
        ArrayList<String> upPaths = getAllMazePaths(sr - 1,sc,dr,dc,step + "u",visited);
        ArrayList<String> leftPaths = getAllMazePaths(sr,sc - 1,dr,dc,step + "l",visited);
        ArrayList<String> rightPaths = getAllMazePaths(sr + 1,sc,dr,dc,step + "d",visited);
        ArrayList<String> downPaths = getAllMazePaths(sr,sc + 1,dr,dc,step + "r",visited);
        visited[sr-1][sc-1] = false;

        upPaths.addAll(leftPaths);
        upPaths.addAll(rightPaths);
        upPaths.addAll(downPaths);
        return upPaths;
    }
}
