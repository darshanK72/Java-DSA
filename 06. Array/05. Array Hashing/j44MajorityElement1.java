import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class j44MajorityElement1{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        System.out.println(majorityElement1BruitForce(arr));
        System.out.println(majorityElement1UsingHashing(arr));
        System.out.println(majorityElement1Efficient(arr));
        in.close();
    }

    // TC : O(n^2) SC : O(n);
    public static int majorityElement1BruitForce(int[] arr){
        boolean[] visited = new boolean[arr.length];
        int majorityElement = -1;
        for(int i = 0; i < arr.length; i++){
            int tempCount = 0;
            for(int j = 0; j < arr.length; j++){
                if(!visited[j] && arr[j] == arr[i]){
                    tempCount++;
                    visited[j] = true;
                }
            }
            if(tempCount > arr.length / 2){
                majorityElement = arr[i];
                break;
            }
        }
        return majorityElement;
    }

    // TC : O(n) SC : O(n)
    public static int majorityElement1UsingHashing(int[] arr){
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0; i < arr.length; i++){
            if(!map.containsKey(arr[i])){
                map.put(arr[i],0);
            }
            map.put(arr[i],map.get(arr[i]) + 1);
        }
        int out = -1;
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            if(entry.getValue() > arr.length / 2){
                out = entry.getKey();
                break;
            }
        }
        return out;
    }

    // Boyrs Moore Voting Alrogithm 
    // TC : O(n) SC : O(1)
    public static int majorityElement1Efficient(int[] arr){
        int majority = 0;
        int lead = 0;
        for(int i = 0; i < arr.length; i++){
            if(lead == 0){
                majority = arr[i];
                lead = 1;
            }else if(arr[i] == majority){
                lead++;
            }else{
                lead--;
            }
        }

        int c = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == majority) c++;
        }
        if(c > arr.length / 2) return  majority;
        return -1;
    }
}