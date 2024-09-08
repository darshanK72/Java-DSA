import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class j02InputOutputStream{

	public static void main(String[] args) throws IOException{
		
		// File output stream creation
		FileOutputStream fo = new FileOutputStream("file1.txt");
		
		// Writing one byte
		fo.write('a');
		
		// Writing stream of bytes
		String str1 = "\nthis is first line of text document\n";
		fo.write(str1.getBytes());
		
		// Writing stream of bytes in offset
		fo.write(str1.getBytes(),3,20);
		
		
		fo.flush();
		fo.close();
		
		
		FileInputStream fi = new FileInputStream("file1.txt");
		
		int b;
		
		while((b = fi.read()) != -1)
		{
			System.out.print((char)b);
		}
		
		fi.close();
		
	}

}
