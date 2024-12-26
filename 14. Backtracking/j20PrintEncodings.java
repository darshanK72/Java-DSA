import java.util.Scanner;

public class j20PrintEncodings{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String s = in.next();
        printEncodings(s,"");
        in.close();
    }

    public static void printEncodings(String s,String current){
        if(s.length() == 0){
            System.out.println(current);
            return;
        }
        else if(s.length() == 1){
            char c = s.charAt(0);
            if(c == '0'){
                return;
            }
            else{
                int chv = c - '0';
                char code = (char)('a' + chv - 1);
                current += code;
                System.out.println(current);
            }
        }
        else{
            char c = s.charAt(0);
            String res = s.substring(1);
            if(c == '0'){
                return;
            }
            else{
                int chv = c - '0';
                char code = (char)('a' + chv - 1);
                printEncodings(res,current + code);
            }

            String ch12 = s.substring(0,2);
            String newres = s.substring(2);

            int chv = Integer.parseInt(ch12);
            if(chv <= 26){
                char code = (char)('a' + chv - 1);
                printEncodings(newres,current+code);
            }
        }
    }
}