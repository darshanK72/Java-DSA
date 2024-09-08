import java.util.Scanner;

public class j22DecimalToHexadecimal {
     public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(toHex(n));
        in.close();
    }
    // it will work for negative numbers also
    public static String toHex(int num) {
        if(num == 0) return "0";
        StringBuilder out = new StringBuilder();
        while(num != 0){
            int d = num & 15;
            if(d < 10){
               out.insert(0,(char)('0' + d));
            }else{
               out.insert(0,(char)('a' + d - 10));
            }
            num >>>= 4;
        }
        return out.toString();
    }
}
