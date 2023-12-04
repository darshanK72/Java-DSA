import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
public class j3Arraylist
{
    public static void main(String args[])
    {
        ArrayList<String> arr = new ArrayList<String>();

        // add(ele)
        arr.add("C");
        arr.add("C++");
        arr.add("Python");
        arr.add("Java");
        arr.add("HTML");
        arr.add("CSS");

        // add(index,ele)
        arr.add(0,"Basics");
        arr.add(7,"Bootstrap");

        // prining using toString()
        System.out.println(arr);
        System.out.println(arr.toString());
        System.out.println("---------------------------");

        // forEach()
        arr.forEach((e)->{System.out.println(e);});
        System.out.println("---------------------------");

        // For loop using index
        for(int i = 0; i < arr.size(); i++)
        {
            System.out.println(arr.get(i));
        }
        System.out.println("---------------------------");

        // Iterator
        Iterator<String> it = arr.iterator();

        while(it.hasNext())
        {
            System.out.println(it.next());
        }
        System.out.println("---------------------------");

        ListIterator<String> lt = arr.listIterator(arr.size());

        while(lt.hasPrevious())
        {
            System.out.println(lt.previous());
        }
        System.out.println("---------------------------");

        // contains()
        System.out.println(arr.contains("Java"));

        // addAll()
        ArrayList<String> arr1 = new ArrayList<String>();
        arr1.add("Git & GitHub");
        arr1.add("Linux");
        arr1.add("Jenkins");

        arr1.addAll(arr1);

        // Collection.sort()
        Collections.sort(arr);
        System.out.println(arr);


      
    }
}