import java.util.Scanner;

public class j20FindUniqueCharacter {

     public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(firstUniqChar(s));
        in.close();
    }
    public static int firstUniqChar(String str) {
        int[] map = new int[26];
        for(int i = 0; i < str.length(); i++){
            map[str.charAt(i) - 'a']++;
        }
        for(int i = 0; i < str.length(); i++){
            if(map[str.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }
}
