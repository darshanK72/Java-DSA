import java.util.Scanner;

public class j23SortCharsByFrequency {
     public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(frequencySort(s));
        in.close();
    }
    public static String frequencySort(String s) {
        int[] hash = new int[128];
        for(int i = 0; i < s.length(); i++){
            hash[s.charAt(i)]++;
        }

        StringBuilder out = new StringBuilder();
        while(out.length() < s.length()){
            int max = 0;
            char c = '\0';
            for(int i = 0; i < 128; i++){
                if(hash[i] > max){
                    max = hash[i];
                    c = (char)i;
                }
            }
            for(int i = 0; i < max; i++){
                out.append(c);
            }
            hash[c] = 0;
        }
        return out.toString();
    }
}
