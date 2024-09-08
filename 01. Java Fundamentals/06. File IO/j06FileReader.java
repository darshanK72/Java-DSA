import java.io.FileReader;
import java.io.IOException;

public class j06FileReader {
    public static void main(String args[]){
        try(FileReader fr = new FileReader("file3.txt")){
            int c = fr.read();

            while(c != -1){
                System.out.print((char)c);
                c = fr.read();
            }
        }catch(IOException exp){
            System.out.println(exp.getMessage());
        }
    }
}
