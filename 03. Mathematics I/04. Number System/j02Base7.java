import java.util.Scanner;

public class j02Base7 {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(convertToBase7(n));
        System.out.println(Integer.toString(n,7));
        in.close();
    }

    public static String convertToBase7(int num) {
        if(num == 0) return "0";
        boolean flag = num < 0 ? true : false;
        num = Math.abs(num);
        String out = "";
        while(num > 0){
            out = (char)('0' + num % 7) + out;
            num /= 7;
        }
        return flag ? "-" + out : out;
    }
}
