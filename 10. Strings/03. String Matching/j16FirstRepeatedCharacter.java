import java.util.Scanner;

public class j16FirstRepeatedCharacter {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(firstRep(s));
        in.close();
    }
    public static char firstRep(String S){
        int[] map = new int[26];
        for(int i = 0; i < S.length(); i++){
            map[S.charAt(i) - 'a']++;
        }
        for(int i = 0; i < S.length(); i++){
            if(map[S.charAt(i) - 'a'] >= 2) return S.charAt(i);
        }
        return '#';
    }
}
