import java.util.Scanner;

public class j24RemoveDuplicates {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(removeDuplicates(s));
        in.close();
    }
    public static String removeDuplicates(String str) {
        int[] map = new int[256];
        for(int i = 0; i < str.length(); i++){
            map[str.charAt(i)]++;
        }
        StringBuilder out = new StringBuilder("");
        for(int i = 0; i < str.length(); i++){
            if(map[str.charAt(i)] != 0){
                out.append(str.charAt(i));
                map[str.charAt(i)] = 0;
            }
        }
        return out.toString();
    }
}
