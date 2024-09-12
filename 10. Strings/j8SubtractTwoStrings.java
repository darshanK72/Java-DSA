import java.util.Scanner;

public class j8SubtractTwoStrings {
     public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        System.out.println(subtract(s1,s2));
        in.close();
    }

     // O(num1.length() + num2.length())
    public static String subtract(String num1, String num2) {

        // for handling num1 less than num2
        if (num1.length() < num2.length() || (num1.length() == num2.length() && num1.compareTo(num2) < 0)) {
            return "-" + subtract(num2, num1);
        }

        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        StringBuilder ans = new StringBuilder();
        while(i >= 0 || j >= 0){
            int d1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int d2 = j >= 0 ? num2.charAt(j) - '0' : 0;

            if(d1 >= (d2 + carry)){
                ans.append((char)('0' + (d1 - (d2 + carry))));
                carry = 0;
            }else{
                ans.append((char)('0' + ((d1 + 10) - (d2 + carry))));
                carry = 1;
            }
            i--;
            j--;
        }
        while (ans.length() > 1 && ans.charAt(ans.length() - 1) == '0') {
            ans.setLength(ans.length() - 1);
        }

        return ans.reverse().toString();
    }
}
