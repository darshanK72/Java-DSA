public class j09MaximizeTripletSum{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] A = new int[n];
        for(int i = 0; i < n; i++){
            A[i] = in.nextInt();
        }
        System.out.println(maximizeTripletSum(A));
        in.close();
    }

    public static int maximizeTripletSum(int[] A) {
        int n = A.length;
        int ans = 0;
        int[] rightMax = new int[n];
        rightMax[n - 1] = A[n - 1];
        for(int i = n - 2; i >= 0; i--){
            rightMax[i] = Math.max(rightMax[i + 1],A[i]);
        }
        
        TreeSet<Integer> set = new TreeSet<>();
        set.add(A[0]);
        for(int i = 1; i < n - 1; i++){
            Integer lowerBound = set.lower(A[i]);
            if(lowerBound == null){
                set.add(A[i]);
                continue;
            }
            int upperBound = rightMax[i + 1];
            if(upperBound > A[i]){
                ans = Math.max(ans,lowerBound + A[i] + upperBound);
            }
            set.add(A[i]);
        }
        return ans;
    }
}