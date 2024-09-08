import java.util.Random;

public class j02RandomString {
    public static void main(String args[]){
        Random rd = new Random();

        for(int i = 0; i < 26; i++){
            System.out.println((char)(97 + rd.nextFloat() * 26));
        }
    }
}
