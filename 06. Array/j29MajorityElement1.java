import java.util.Scanner;

public class j29MajorityElement1{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        // System.out.println(majorityElement1BruitForce(arr));
        System.out.println(majorityElement1Efficient(arr));
        in.close();
    }

    // O(n^2)
    public static int majorityElement1BruitForce(int[] arr){
        boolean[] visited = new boolean[arr.length];
        int majorityElement = 0;
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

    // Boyrs Moore Voting Alrogithm 
    // O(n)
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