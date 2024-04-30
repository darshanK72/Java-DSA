import java.util.Scanner;
import java.util.ArrayList;
public class j6GetSubsequences{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String s = in.next();
        System.out.println(getSubsequences(s).toString());
        System.out.println(generateSubsequences(s, ""));
        printSubsequences(s, "");
        in.close();
    }

    public static void printSubsequences(String str,String current){
        if(str.length() == 0){
            System.out.println(current);
            return;
        }
        char c = str.charAt(0);
        printSubsequences(str.substring(1), current);
        printSubsequences(str.substring(1), current + c);
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

    public static ArrayList<String> generateSubsequences(String str,String current){
        if(str.length() == 0){
            ArrayList<String> output = new ArrayList<>();
            output.add(current);
            return output;
        }

        ArrayList<String> right = generateSubsequences(str.substring(1), current);
        ArrayList<String> left =  generateSubsequences(str.substring(1), current + str.charAt(0));
        left.addAll(right);
        return left;
    }
}