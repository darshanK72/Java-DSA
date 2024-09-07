import java.util.Scanner;
import java.util.ArrayList;

public class j8GetStringSubsets{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String s = in.next();
        System.out.println(getSubsets(s,"",0));
        printSubsets(s,"",0);
        ArrayList<String> array = new ArrayList<String>();
        getSubsetsNew(s,"",0,array);
        System.out.println(array);
        in.close();
    }

    public static void printSubsets(String s,String curr,int index){
        if(index == s.length()) {
            System.out.println(curr);
            return;
        }


        printSubsets(s,curr,index+1);
        printSubsets(s,curr + s.charAt(index),index+1);
    }

    public static ArrayList<String> getSubsets(String s,String curr,int index){

        if(index == s.length()){
            ArrayList<String> out = new ArrayList<String>();
            out.add(curr);
            return out;
        }

        ArrayList<String> notTaken = getSubsets(s,curr,index + 1);
        ArrayList<String> taken = getSubsets(s,curr + s.charAt(index),index + 1);
        
        notTaken.addAll(taken);
        return notTaken;
    }

    public static void getSubsetsNew(String s,String curr,int index,ArrayList<String> out){

        if(index == s.length()){
            out.add(curr);
            return;
        }

        getSubsetsNew(s,curr + s.charAt(index),index + 1,out);
        getSubsetsNew(s,curr,index + 1,out);
    }
}