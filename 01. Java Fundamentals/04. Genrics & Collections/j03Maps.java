import java.util.HashMap;
import java.util.Map;

public class j03Maps {

    public static void main(String args[])
    {
        Map<String,Integer> mp = new HashMap<>();

        mp.put("one",1);
        mp.put("two",2);
        mp.put("three",3);

        System.out.println(mp);

        mp.forEach((k,v)-> {System.out.println(k + "|" + v);});

    }
    
}
