import java.util.Scanner;

public class j27StringCompression {
     public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        char[] s = in.nextLine().toCharArray();
        int i = compress(s);

        for(int j = 0; j <= i; j++){
            System.out.print(s[j]);
        }

        in.close();
    }

    public static int compress(char[] chars) {
        int i = 0;
        int index = 0;
        while(i < chars.length){
            char c = chars[i];
            int count = 0;
            while(i < chars.length && c == chars[i]){
                i++;
                count++;
            }
            chars[index++] = c;
            if(count > 1){
                for(char chr : String.valueOf(count).toCharArray()){
                    chars[index++] = chr;
                }
            }
        }
        return index;
    }
}
