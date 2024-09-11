import java.util.*;

public class j02MajorityElement2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(majorityElement2BruitForce(arr));
        System.out.println(majorityElement2UsingHashing(arr));
        System.out.println(majorityElement2Efficient(arr));
        in.close();
    }

    // TC : O(n62) SC : O(n)
    public static ArrayList<Integer> majorityElement2BruitForce(int[] arr) {
        ArrayList<Integer> output = new ArrayList<Integer>();
        boolean[] visited = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (visited[i])
                continue;
            int tempCount = 0;
            for (int j = 0; j < arr.length; j++) {
                if (!visited[j] && arr[j] == arr[i]) {
                    tempCount++;
                    visited[j] = true;
                }
            }
            if (tempCount > arr.length / 3) {
                output.add(arr[i]);
            }
        }
        return output;
    }

    // TC : O(n) SC : O(n)
    public static ArrayList<Integer> majorityElement2UsingHashing(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 0);
            }
            map.put(arr[i], map.get(arr[i]) + 1);
        }
        ArrayList<Integer> output = new ArrayList<Integer>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > arr.length / 3) {
                output.add(entry.getKey());
            }
        }
        return output;
    }

    // Boyrs Moore Voting Alrogithm
    // O(n)
    public static ArrayList<Integer> majorityElement2Efficient(int[] arr) {
        ArrayList<Integer> output = new ArrayList<Integer>();
        int majorityA = Integer.MIN_VALUE;
        int leadA = 0;
        int majorityB = Integer.MIN_VALUE;
        int leadB = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == majorityA) {
                leadA++;
            } else if (arr[i] == majorityB) {
                leadB++;
            } else if (leadA == 0) {
                majorityA = arr[i];
                leadA = 1;
            } else if (leadB == 0) {
                majorityB = arr[i];
                leadB = 1;
            } else {
                leadA--;
                leadB--;
            }
        }

        int ca = 0;
        int cb = 0;
        for (int i = 0; i < arr.length; i++) {
            if (majorityA == arr[i])
                ca++;
            if (majorityB == arr[i])
                cb++;
        }
        if (ca > arr.length / 3)
            output.add(majorityA);
        if (cb > arr.length / 3)
            output.add(majorityB);
        return output;
    }
}