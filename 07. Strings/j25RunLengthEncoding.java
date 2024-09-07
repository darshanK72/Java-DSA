import java.util.Scanner;

public class j25RunLengthEncoding {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(runLengthEncode(s));
        in.close();
    }
    public static String runLengthEncode(String s) {
        StringBuilder out = new StringBuilder();
        for(int i = 0; i < s.length();i++){
            int j = i;
            int c = 0;
            while(j < s.length() && s.charAt(i) == s.charAt(j)){
                j++;
                c++;
            }
            j--;
            out.append(s.charAt(j) + "" + c);
            i = j;
        }
        return out.toString();
    }
}
