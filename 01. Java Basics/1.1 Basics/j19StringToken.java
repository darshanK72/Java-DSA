import java.util.StringTokenizer;

public class j19StringToken {

	public static void main(String[] args) {
		StringTokenizer str = new StringTokenizer("hi this is new sentence");
		
		for(int i = 0; str.hasMoreTokens(); i++)
		{
			System.out.println("Token " + i + " : " + str.nextToken());
		}
			
	}

}
