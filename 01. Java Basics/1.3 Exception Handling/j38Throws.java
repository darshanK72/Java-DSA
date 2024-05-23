import java.util.Scanner;
public class j38Throws {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		try {
			int a = in.nextInt();
			int b = in.nextInt();
			
			System.out.println(divide(a,b));
		}
		catch(ArithmeticException ex)
		{
			System.out.println(ex.getMessage());
		}
		in.close();
	}
	
	static int divide(int a,int b) throws ArithmeticException
	{
		return a/b;
	}

}
