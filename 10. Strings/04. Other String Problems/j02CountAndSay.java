import java.util.Scanner;

public class j02CountAndSay {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(countAndSay(n));
        in.close();
    }

    public static String countAndSay(int n) {
        StringBuilder out = new StringBuilder("1");
        for(int i = 2; i <= n; i++){
            out = runLengthEncoding(out);
        }
        return out.toString();
    }
    public static StringBuilder runLengthEncoding(StringBuilder n){
        StringBuilder out = new StringBuilder();
        for(int i = 0; i < n.length(); i++){
            int j = i;
            int c = 0;
            while(j < n.length() && n.charAt(i) == n.charAt(j)){
                j++;
                c++;
            }
            j--;
            out.append(c + "" + n.charAt(j));
            i = j;
        }
        return out;
    }
}
