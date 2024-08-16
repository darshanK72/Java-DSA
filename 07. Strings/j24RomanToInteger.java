import java.util.Scanner;

public class j24RomanToInteger {

     public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String num = in.nextLine();
        System.out.println(romanToInt(num));
        in.close();
    }

    public static int romanToInt(String s) {
        int ans = 0;
        int num = 0;
        for(int i = 0; i < s.length(); i++){
            num = getVal(s.charAt(i));
            if(i < s.length() - 1 && num < getVal(s.charAt(i+1))) ans -= num;
            else ans += num;
        }
        return ans;
    }

    public static int getVal(char c){
        int number = 0;
        switch (c) {
            case 'M' -> number = 1000;
            case 'D' -> number = 500;
            case 'C' -> number = 100;
            case 'L' -> number = 50;
            case 'X' -> number = 10;
            case 'V' -> number = 5;
            case 'I' -> number = 1;
        }
        return number;
    }
}
