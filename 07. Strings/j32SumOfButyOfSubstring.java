import java.util.Scanner;

public class j32SumOfButyOfSubstring {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        System.out.println(beautySum(str));
        in.close();
    }
    public static int beautySum(String s) {
        int ans = 0;
        for(int i = 0; i < s.length(); i++){
            for(int j = i+1; j <= s.length(); j++){
                ans += getBeauty(s.substring(i,j));
            }
        }
        return ans;
    }

    public static int getBeauty(String s){
        int[] hash = new int[26];
        for(int i = 0; i < s.length(); i++){
            hash[s.charAt(i) - 'a']++;
        }
        int min = Integer.MAX_VALUE;
        int max = 0;
        for(int i = 0; i < 26; i++){
           if(hash[i] > 0){
                if(hash[i] > max){
                    max = hash[i];
                }
                if(hash[i] < min){
                    min = hash[i];
                }
           }
        }
        return max - min;
    }
}
