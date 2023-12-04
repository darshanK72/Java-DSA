import java.util.HashSet;

public class j5Hashset {
    public static void main(String args[])
    {
       HashSet<String> set = new HashSet<>();

       set.add("Java");
       set.add("Java");
       set.add("Python");
       set.add("HTML");
       set.add("SQL");

       for(String ele:set)
       {
            System.out.println(ele);
       }
    }
    
}
