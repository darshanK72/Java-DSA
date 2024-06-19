import java.util.Scanner;
import java.lang.Math;
import java.lang.StringBuilder;

public class j11PowerSet{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int n = s.length();

        for(int i = 0; i < Math.pow(2,n); i++){
            StringBuilder out = new StringBuilder("");
            for(int j = 0; j < n; j++){
                if((i & (1 << j)) != 0){
                    out.append(s.charAt(j));
                }
            }
            System.out.println(out);
        }

        in.close();
    }
}