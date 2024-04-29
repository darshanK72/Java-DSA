import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
public class j6GetSubsequences{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String s = in.next();
        System.out.println(getSubsequences(s).toString());
        in.close();
    }

    public static ArrayList<String> getSubsequences(String str){
        if(str.length() == 0){
            ArrayList<String> arr = new ArrayList<>();
            arr.add("");
            return arr;
        }
        ArrayList<String> res = getSubsequences(str.substring(1));
        ArrayList<String> output = new ArrayList<>();
        for(String s : res){
            output.add(s);
        }
        for(String s : res){
            output.add(str.charAt(0) + s);
        }
        return output;
    }
}