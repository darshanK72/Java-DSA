import java.io.FileWriter;
import java.io.IOException;

public class j07FileWriter {
    public static void main(String args[]) {
        try(FileWriter fw = new FileWriter("file4.txt")){

            fw.write("Hello World");
            
        }catch(IOException exp){
            System.out.println(exp.getMessage());
        }
    }
}
