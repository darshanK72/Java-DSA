import java.io.FileInputStream;

public class j53FileStream
{
    public static void main(String args[])
    {
        try
        {
            FileInputStream file = new FileInputStream("file.txt");

            int c = file.read();

            while(c != -1)
            {
                System.out.print((char)c);
                c = file.read();
            }


            file.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}