public class j34TryCatchFinally {

	public static void main(String[] args) {
		
		try
		{
			int a = 10;
			int b = 0;
			int c = a/b;
			System.out.println(c);
		}
		
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

}
