import java.util.LinkedHashSet;

public class j44Linkedhashset {
    public static void main(String args[])
    {
        LinkedHashSet<String> lset = new LinkedHashSet<>();

        lset.add("one");
        lset.add("two");
        lset.add("three");
        lset.add("four");
        lset.add("five");

        for (String lset2 : lset) {
            System.out.println(lset2);
        }
        }
}
    
