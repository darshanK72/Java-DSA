import java.util.Scanner;

public class j9MultiplyTwoStrings {
     public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        System.out.println(multiply(s1,s2));
        in.close();
    }

     // O(num1.length() + num2.length())
    public static String multiply(String num1, String num2) {
        if(num2.equals("0") || num1.equals("0")) return "0";
        int i = num2.length() - 1;
        String ans = "";
        String k = "";
        while(i >= 0){
            int carry = 0;
            int j = num1.length() - 1;
            StringBuilder temp = new StringBuilder("");
            while(j >= 0 || carry > 0){
                int d = 0;
                if(j >= 0){
                    d = (num1.charAt(j) - '0');
                }
                if(i >= 0){
                    d *= (num2.charAt(i) - '0');
                }

                d += carry;
                    
                temp.append((char)(d % 10 + '0'));
                carry = d / 10;
                j--;
            }
            ans = add(ans,temp.reverse() + k);
            k += "0";
            i--;
        }
        return ans;
    }

    public static String add(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        StringBuilder ans = new StringBuilder();
        while(i >= 0 || j >= 0 || carry > 0){
            int d = carry;
            if(i >= 0){
                d += num1.charAt(i) - '0';
            }
            if(j >= 0){
                d += num2.charAt(j) - '0';
            }
            ans.append((char)('0' + d % 10));
            carry = d / 10;
            i--;
            j--;
        }
        return ans.reverse().toString();
    }
}
