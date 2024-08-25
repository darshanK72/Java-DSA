import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class j20DiagonalTraversalBLTR {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        ArrayList<ArrayList<Integer>> nums = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            int col = in.nextInt();
            ArrayList<Integer> lst = new ArrayList<>();
            for (int j = 0; j < col; j++) {
               lst.add(in.nextInt());
            }
            nums.add(lst);
        }
        System.out.println(Arrays.toString(diagonalTraverseBLTR(nums)));
        System.out.println(Arrays.toString(diagonalTraverseBLTREfficient(nums)));
        in.close();
    }
    // O(M * N) - N largest value for cloumn
    public static int[] diagonalTraverseBLTR(ArrayList<ArrayList<Integer>> nums){
        int m = nums.size();
        int n = 0;
        int s = 0;
        for(int i = 0; i < m; i++){
            s += nums.get(i).size();
            n = Math.max(n,nums.get(i).size());
        }
        int[] out = new int[s];
        int k = 0;

        for(int d = 0; d < m + n - 1; d++){
            int row = d < m ? d : m - 1;
            int col = d < m ? 0 : d - m + 1;
            while(row >= 0 && col < n){
                if(row < nums.size() && col < nums.get(row).size()){
                    out[k] = nums.get(row).get(col);
                    k++;
                }
                row--;
                col++;
            }
        }
        return out;
    }

    // O (Number of elelents in array)
    public static int[] diagonalTraverseBLTREfficient(ArrayList<ArrayList<Integer>> nums){
        Map<Integer, List<Integer>> map = new HashMap<>();
        int count = 0;

        // Traverse the matrix and store the elements in diagonals
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                if(!map.containsKey(i + j)){
                    map.put(i + j,new ArrayList<>());
                }
                map.get(i+j).add(nums.get(i).get(j));
                count++;
            }
        }

        int[] result = new int[count];
        int k = 0;

        // Retrieve the diagonals and concatenate them to form the result array
        for (int i = 0; i < map.size(); i++) {
            List<Integer> diagonal = map.get(i);
            for (int j = diagonal.size() - 1; j >= 0; j--) {
                result[k++] = diagonal.get(j);
            }
        }

        return result;
    }
}
