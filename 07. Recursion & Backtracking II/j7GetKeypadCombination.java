import java.util.Scanner;
import java.util.ArrayList;
public class j7GetKeypadCombination{

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
        in.close();
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