import java.util.HashMap;
import java.util.Scanner;

public class j13CountEqualZeroOneTwoSubarrays {
      public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        System.out.println(getSubstringWithEqual012(str));
        in.close();
    }

    public static long getSubstringWithEqual012(String str) {
        int zeros = 0;
        int ones = 0;
        int twos = 0;
        int count = 0;
        HashMap<String, Long> map = new HashMap<>();
        map.put("0,0", 1L);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0')
                zeros++;
            else if (str.charAt(i) == '1')
                ones++;
            else
                twos++;

            String key = (zeros - ones) + "," + (ones - twos);
            count += map.getOrDefault(key, 0L);
            map.put(key, map.getOrDefault(key, 0L) + 1L);
        }
        return count;
    }
}
