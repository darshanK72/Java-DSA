import java.util.ArrayList;
import java.util.List;

public class j03CammleCaseMatching {

      public static void main(String args[]) {
        String[] strs = new String[]{"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"};
        String pattern = "FoBaT";
        System.out.println(camelMatch(strs,pattern));
    }
    public static List<Boolean> camelMatch(String[] queries, String pattern) {
        ArrayList<Boolean> out = new ArrayList<>();
        for(String s : queries){
            out.add(isMatch(s,pattern));
        }
        return out;
    }

    public static boolean isMatch(String s,String pattern){
        int i = 0;
        for(char c : s.toCharArray()){
            if(i < pattern.length() && c == pattern.charAt(i)){
                i++;
            }else if(Character.isUpperCase(c)){
                return false;
            }
        }

        return i == pattern.length();
    }
}
