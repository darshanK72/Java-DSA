import java.util.Scanner;
import java.util.ArrayList;

public class j14GetPermutations {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String s = in.next();
        System.out.println(getPermutations(s,""));
        printPermutations(s, "");
        permute(s,0,s.length()-1);
        in.close();
    }

    public static void printPermutations1(String s,String current){
        if(s.length() == 0){
            System.out.println(current);
            return;
        }

        char c = s.charAt(0);
        for(int i = 0; i <= current.length(); i++){
            String f = current.substring(0, i);
            String n = current.substring(i);
            printPermutations(s.substring(1), f + c + n);
        }
    }

    public static void printPermutations(String s,String current){
        if(s.length() == 0){
            System.out.println(current);
            return;
        }

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            String left = s.substring(0,i);
            String right = s.substring(i+1);
            String nes = left + right;
            printPermutations(nes,current + c);
        }
    }

    public static ArrayList<String> getPermutations(String s,String current){
        if(s.length() == 0){
            ArrayList<String> output = new ArrayList<>();
            output.add(current);
            return output;
        }

        char c = s.charAt(0);
        ArrayList<String> out = new ArrayList<>();
        for(int i = 0; i <= current.length(); i++){
            String f = current.substring(0, i);
            String n = current.substring(i);
            ArrayList<String> arr = getPermutations(s.substring(1), f + c + n);
            out.addAll(arr);
        }
        return out;
    }

    public static void permute(String str, int left, int right) {
        if (left == right) {
            System.out.println(str);
        } else {
            for (int i = left; i <= right; i++) {
                str = swap(str, left, i);
                permute(str, left + 1, right);
                str = swap(str, left, i); // backtrack
            }
        }
    }
    
    public static String swap(String str, int i, int j) {
        char[] charArray = str.toCharArray();
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
}
