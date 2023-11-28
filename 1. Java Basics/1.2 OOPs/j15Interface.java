public class j15Interface {

	public static void main(String[] args) {
		
	}

}

interface shapes
{
	float area();
	float volume();
}

class Cone implements shapes
{
	int radious;
	int height;
	public float area()
	{
		return (float)(Math.PI*radious*(radious + Math.sqrt(radious*radious + height*height)))  ;
	}
	
	public float volume()
	{
		return (float) (Math.PI * radious * radious * height)/3;
	}
	
}

