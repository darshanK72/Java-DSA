import java.util.Scanner;

public class j4ReverseWords2 {
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
        for(int i = 0; i < arr.length; i++){
            out.append(reverse(arr[i]));
            if(i < arr.length-1){
                out.append(" ");
            }
        }
        return out.toString();
    }

    // O(str.length())
    public static String reverse(String str){
        int s = 0;
        int e = str.length()-1;
        char[] out = str.toCharArray();
        while(s < e){
            char t = out[s];
            out[s] = out[e];
            out[e] = t;
            s++;
            e--;
        }
        return new String(out);
    }

    // O(str.length())
    public static String reverseWordsTwoPointers(String str){
        StringBuilder out = new StringBuilder("");
        int i = 0;
        while(i < str.length()){
            int j = i;
            while(i < str.length() && str.charAt(i) != ' '){
                i++;
            }
            StringBuilder sb = new StringBuilder("");
            for(int k = i-1; k >= j; k--){
                sb.append(str.charAt(k));
            }
            out.append(sb);
            while(i < str.length() && str.charAt(i) == ' '){
                out.append(' ');
                i++;
            }
        }
        return out.toString();
    }
}
