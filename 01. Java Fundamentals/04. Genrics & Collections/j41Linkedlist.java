import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class j41Linkedlist {
    public static void main(String args[])
    {
        List<Object> l = new LinkedList<>();

        // add(ele)
        l.add("HTML");
        l.add(52.2);
        l.add(2242);
        l.add(new StringBuffer("Hello World"));
        l.add(true);

        // add(index,ele)
        l.add(3,"CSS");

        // prining using toString()
        System.out.println(l);
        System.out.println(l.toString());
        System.out.println("---------------------------");

        // forEach()
        l.forEach((e)->{System.out.println(e);});
        System.out.println("---------------------------");

        // For loop using index
        for(int i = 0; i < l.size(); i++)
        {
            System.out.println(l.get(i));
        }
        System.out.println("---------------------------");

        // Iterator
        Iterator<Object> it = l.iterator();

        while(it.hasNext())
        {
            System.out.println(it.next());
        }
        System.out.println("---------------------------");

        // contains()
        System.out.println(l.contains("Java"));

        // addAll()
        List<Object> arr1 = new LinkedList<Object>();
        arr1.add("Git & GitHub");
        arr1.add("Linux");
        arr1.add("Jenkins");

        l.addAll(arr1);


    }
}
