import java.util.Scanner;
public class j11SubsetOfString{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String s = in.next();
        // System.out.println(getSubsets(s),new ArrayList<String>());
        getSubsets(s,"",0);
        in.close();
    }

    public static void getSubsets(String s,String curr,int index){
        if(index == s.length()) {
            System.out.println(curr);
            return;
        }

        getSubsets(s,curr,index+1);
        getSubsets(s,curr + s.charAt(index),index+1);
    }
}