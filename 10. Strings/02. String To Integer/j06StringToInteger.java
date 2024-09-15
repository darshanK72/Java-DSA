import java.util.Scanner;

public class j06StringToInteger {

     public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(atoi(s));
        in.close();
    }

    // O(s.length())
    public static int atoi(String s) {
        if(s.length() == 0) return 0;
        int i = 0;
        boolean sign = false;

        while(i < s.length() && s.charAt(i) == ' '){
           i++;
        }
        
        if(i == s.length()) return 0;

        if(s.charAt(i) == '+'){
            i++;
        }else if(s.charAt(i) == '-'){
            i++;
            sign = true;
        }

        while(i < s.length() && s.charAt(i) == '0'){
            i++;
        }

        if(i == s.length()) return 0;

        int j = i;

        while(j < s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9'){
            j++;
        }

        int ans = 0;
        for(int k = i; k < j; k++){
            int temp = ans * 10 + (s.charAt(k) - '0');
            if(temp / 10 != ans){
                if(sign) return Integer.MIN_VALUE;
                return Integer.MAX_VALUE;
            }else{
                ans = temp;
            }
        }
        return sign ? -1 * ans : ans;
    }
}
