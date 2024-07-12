import java.util.Scanner;

public class j1ReverseString{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(reverse1(s));
        System.out.println(reverse2(s));
        in.close();
    }

    // Using StringBuilder
    public static String reverse1(String str){
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    // Using Two Pointers
    public static String reverse2(String str){
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
}