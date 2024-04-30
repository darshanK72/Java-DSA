import java.util.Scanner;
import java.util.ArrayList;

public class j8GetStairPaths{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(getStairPaths(n));
        in.close();
    }

    // T(n) = T(n-1) + T(n-2) + T(n-3) + c 
    public static ArrayList<String> getStairPaths(int n){
        if(n == 0){
            ArrayList<String> arr = new ArrayList<>();
            arr.add("");
            return arr;
        }
        if(n < 0){
            return new ArrayList<String>();
        }

        ArrayList<String> arr1 = getStairPaths(n - 1);
        ArrayList<String> arr2 = getStairPaths(n - 2);
        ArrayList<String> arr3 = getStairPaths(n - 3);

        ArrayList<String> output = new ArrayList<>();

        for(String s : arr1){
            output.add("1" + s);
        }
        for(String s : arr2){
            output.add("2" + s);
        }
        for(String s : arr3){
            output.add("3" + s);
        }

        return output;

    }
}