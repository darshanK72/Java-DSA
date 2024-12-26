import java.util.ArrayList;
import java.util.Scanner;

public class j17GetMazePathsWithObstacles{
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        boolean[][] maze = {
            {true,true,true},
            {true,false,true},
            {true,true,true}
        };

        System.out.println(getMazePaths(1,1,3,3,maze));
        printMazePaths(1,1,3,3,"",maze);
        in.close();
    }

    public static void printMazePaths(int sr,int sc,int dr,int dc,String path,boolean[][] maze){
        if(sc > dc || sr > dr){
            return;
        }
        if(sr == dr && sc == dc){
            System.out.println(path);
            return;
        }
        if(!maze[sr-1][sc-1]){
            return;
        }
        printMazePaths(sr,sc+1,dr,dc,path + "h",maze);
        printMazePaths(sr+1,sc,dr,dc,path + "v",maze);
    }

    public static ArrayList<String> getMazePaths(int sr,int sc,int dr,int dc,boolean[][] maze){
        if(sr == dr && sc == dc){
            ArrayList<String> arr = new ArrayList<>();
            arr.add("");
            return arr;
        }
        ArrayList<String> output = new ArrayList<>();

        if(!maze[sr-1][sc-1]){
            return output;
        }
        
        ArrayList<String> rpaths = new ArrayList<>();
        ArrayList<String> dpaths = new ArrayList<>();

        if(sc < dc){    
            rpaths = getMazePaths(sr,sc+1,dr,dc,maze);
        }
        if(sr < dr){
            dpaths = getMazePaths(sr+1,sc,dr,dc,maze);
        }

        for(String path : rpaths){
            output.add("h" + path);
        }

        for(String path : dpaths){
            output.add("v" + path);
        }
        return output;
    }
}
