public class j20StringUsingBufferAndBuilder {

	public static void main(String[] args) {
		/*
		 * StringBuffer is mutable objecte means we can change content of StringBuffer
		 * 
		 */
		StringBuffer str1 = new StringBuffer("Hello, ");

		System.out.println(str1);

		// Appending to string
		str1.append("my name is ");
		System.out.println(str1);

		// insert in string
		str1.insert(10, "Java Language");
		System.out.println(str1);

		str1.insert(10, " ");
		System.out.println(str1);

		// delete string
		str1.delete(10, 31);
		System.out.println(str1);

		str1.reverse();
		System.out.println(str1);

		StringBuilder str2 = new StringBuilder("new ");

		// Appending to string
		str2.append("my name is ");
		System.out.println(str2);

		// insert in string
		str2.insert(10, "Java Language");
		System.out.println(str2);

		str2.insert(10, " ");
		System.out.println(str2);

		// delete string
		str2.delete(10, 31);
		System.out.println(str2);

		str2.reverse();
		System.out.println(str2);

	}

}
