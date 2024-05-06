import java.util.Scanner;
import java.util.ArrayList;
public class j8GetKeypadCombination{

    public static String[] keymap = {
        "",
        "abc",
        "def",
        "ghi",
        "jkl",
        "mno",
        "pqrs",
        "tuv",
        "wxyz"
    };

    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String s = in.next();
        System.out.println(getKeypadCombinations(s).toString());
        printKeypadCombinations(s, "");
        in.close();
    }

    public static void printKeypadCombinations(String str,String current){
        if(str.length() == 0){
            System.out.println(current);
            return;
        }
        char c = str.charAt(0);
        String keys = keymap[c - '1'];
        for(int i = 0; i < keys.length(); i++){
            printKeypadCombinations(str.substring(1),current + keys.charAt(i));
        }
    }

    public static ArrayList<String> getKeypadCombinations(String str){
        if(str.length() == 0) {
            ArrayList<String> arr = new ArrayList<String>();
            arr.add("");
            return arr;
        }
        char c = str.charAt(0);
        String s = str.substring(1);
        ArrayList<String> res = getKeypadCombinations(s);
        String keys = keymap[c - '1'];
        System.out.println(keys);
        ArrayList<String> output = new ArrayList<>();
         for(int i = 0; i < keys.length(); i++){
            for(String r : res){
                output.add(keys.charAt(i)+r);
            }
        }
    
        return output;
    }
}