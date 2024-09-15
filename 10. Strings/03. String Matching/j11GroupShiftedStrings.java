import java.util.ArrayList;
import java.util.HashMap;

public class j11GroupShiftedStrings {
    public static void main(String args[]){
        String[] words = new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        System.out.println(gropuShiftedStrings(words));
    }

    public static ArrayList<ArrayList<String>> gropuShiftedStrings(String[] words){
        HashMap<String,ArrayList<String>> map = new HashMap<>();
        for(String s : words){
            StringBuilder key = new StringBuilder();
            char[] chars = s.toCharArray();
            for(int i = 1; i < chars.length; i++){
                key.append((chars[i] - chars[i-1] + 26) % 26 + "#");
            }
            if(map.containsKey(key.toString())){
                ArrayList<String> list = map.get(key.toString());
                list.add(s);
            }else{
                ArrayList<String> list = new ArrayList<>();
                list.add(s);
                map.put(key.toString(),list);
            }
        }
        ArrayList<ArrayList<String>> out = new ArrayList<>();
        for(String key : map.keySet()){
            out.add(map.get(key));
        }
        return out;
    }
}
