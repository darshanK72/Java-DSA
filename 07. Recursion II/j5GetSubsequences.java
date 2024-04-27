import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
public class j5GetSubsequences{
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
        char c = str.charAt(0);
        String substr = str.substring(1);
        ArrayList<String> res = getSubsequences(substr);
        ArrayList<String> output = new ArrayList<>();
        for(String s : res){
            output.add(s);
            output.add(c + s);
        }
        return output;
    }
}