import java.util.ArrayList;
import java.util.Scanner;

public class j8GetMazePaths {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        System.out.println(getMazePaths(1,1,m,n));
        in.close();
    }

    public static ArrayList<String> getMazePaths(int sr,int sc,int dr,int dc){
        if(sr == dr && sc == dc){
            ArrayList<String> arr = new ArrayList<>();
            arr.add("");
            return arr;
        }

        ArrayList<String> rpaths = new ArrayList<>();
        ArrayList<String> dpaths = new ArrayList<>();;

        if(sc < dc){
            rpaths = getMazePaths(sr,sc + 1, dr, dc);
        }
        if(sr < dr){
            dpaths = getMazePaths(sr+1,sc, dr, dc);
        }

        ArrayList<String> output = new ArrayList<>();

        for(String path : rpaths){  
           output.add("r" + path);
        }
        for(String path : dpaths){  
            output.add("d" + path);
        }

        return output;
    }
}
