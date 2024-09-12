import java.util.Scanner;

public class j3ReverseWords1 {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(reverseWords(s));
        System.out.println(reverseWordsTwoPointers(s));
        in.close();
    }

    // Using Split & Regular Expression
    // O(n)
    public static String reverseWords(String str){
        String[] arr = str.trim().split("\\s+");
        StringBuilder out = new StringBuilder("");
        for(int i = arr.length - 1; i > 0; i--){
            out.append(arr[i] + " ");
            if(i > 0){
                out.append(" ");
            }
        }
        return out.toString();
    }

    // O(str.length())
    public static String reverseWordsTwoPointers(String str){
        StringBuilder out = new StringBuilder();
        int i = str.length()-1;
        while(i >= 0){
            while(i >= 0 && str.charAt(i) == ' '){
                i--;
            }
            int j = i;
            while(j >= 0 && str.charAt(j) != ' '){
                j--;
            }
            if(out.length() > 0 && j < i){
                out.append(" ");
            }
    
            for(int k = j+1; k <= i; k++){
                out.append(str.charAt(k));
            }
            i = j;
        }
        return out.toString();
    }
}
