import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class j05CharStreams {

	public static void main(String[] args) throws IOException{

		
		FileWriter fw = new FileWriter("file2.txt");
		
		fw.write("My name is Darshan\n");
		fw.write("I am from Malegaon\n");
		
		fw.close();
		
		FileReader fr = new FileReader("file2.txt");
		
		int i;
		while((i = fr.read()) != -1)
		{
			System.out.print((char) i);
		}
		
		fr.close();
	}

}
