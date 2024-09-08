import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class j03FileReading {

    public static void main(String args[]) throws IOException
    {
        FileInputStream file = new FileInputStream("file.txt");
        InputStreamReader in = new InputStreamReader(file);


        int ch = in.read();
        while(ch != -1)
        {
            System.out.print((char)ch);
            ch = in.read();
        }

        in.close();
        file.close();
    }
    
}
