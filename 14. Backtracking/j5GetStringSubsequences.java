import java.util.Scanner;
import java.util.ArrayList;
public class j5GetStringSubsequences{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String s = in.next();
        System.out.println(getSubsequences(s));
        System.out.println(generateSubsequences(s, ""));
        printSubsequences(s, "");
        in.close();
    }

    public static void printSubsequences(String str,String current){
        if(str.length() == 0){
            System.out.println(current);
            return;
        }
        printSubsequences(str.substring(1), current + str.charAt(0));
        printSubsequences(str.substring(1), current);
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
            output.add(str.charAt(0) + s);
        }
        for(String s : res){
            output.add(s);
        }
        return output;
    }

    public static ArrayList<String> generateSubsequences(String str,String current){
        ArrayList<String> output = new ArrayList<>();
        if(str.length() == 0){
            output.add(current);
            return output;
        }

        output.addAll(generateSubsequences(str.substring(1), current + str.charAt(0)));
        output.addAll(generateSubsequences(str.substring(1), current));
        return output;
    }
}