import java.io.DataInputStream;
import java.io.FileOutputStream;
public class j01FileOStream {

    public static void main(String args[])
    {
        try
        {

            // Scanner in = new Scanner(System.in);
            DataInputStream dis = new DataInputStream(System.in);
            FileOutputStream file = new FileOutputStream("file.txt");
            
            char ch = (char) dis.read();

            while(ch != '#')
            {
                file.write(ch);
                ch = (char) dis.read();
            }
            dis.close();
            file.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
}
