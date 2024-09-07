import java.util.Scanner;

public class j30LongestPalindrome {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        System.out.println(longestPalindrome(str));
        System.out.println(longestPalindromeEfficient(str));
        System.out.println(longestPalindromeEfficientExpandFromCenter(str));
        in.close();
    }

    // Iterating over all possible substring, and finding max length substring
    public static String longestPalindrome(String s) {
        if(s.length() == 1) return s;
        String out = "";
        for(int i = 0; i < s.length(); i++){
            StringBuilder substr = new StringBuilder();
            for(int j = i; j < s.length(); j++){
                substr.append(s.charAt(j));
                if(isPalindrome(substr.toString()) && substr.length() > out.length()){
                    out = substr.toString();
                }
            }
        }
        return out;
    }

    // Once found out longest till now, always check for substrings longer that that
    public static String longestPalindromeEfficient(String s) {
        if(s.length() == 1) return s;
        int maxLen = 1;
        // String out = s.substring(0,1);
        String out = "";
        for(int i = 0; i < s.length(); i++){
            for(int j = i + maxLen; j <= s.length(); j++){
                if( j - i > maxLen && isPalindrome(s.substring(i,j))){
                    maxLen = j - i;
                    out = s.substring(i,j);
                }
            }
        }
        return out;
    }

    public static String longestPalindromeEfficientExpandFromCenter(String s){
        if(s.length() == 1) return s;
        // String maxStr = s.substring(0, 1);
        String maxStr = "";
        for(int i = 0; i < s.length() - 1; i++){
            String odd = expandFromCenter(s, i, i);
            String even = expandFromCenter(s, i, i + 1);

            if(odd.length() > maxStr.length()){
                maxStr = odd;
            }
            if(even.length() > maxStr.length()){
                maxStr = even;
            }
        }
        return maxStr;

    }

    public static String expandFromCenter(String str,int s,int e){
        while((s >= 0 && e < str.length()) && str.charAt(s) == str.charAt(e)){
            s--;
            e++;
        }
        return str.substring(s+1,e);
    }

    public static boolean isPalindrome(String str){
        int s = 0;
        int e = str.length() - 1;
        while(s < e){
            if(str.charAt(s) != str.charAt(e)) return false;
            s++;
            e--;
        }
        return true;
    }
}
