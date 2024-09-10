public class j06UniqueXorPairSum {
    public long sumXOR(int arr[], int n) {
        long ans = 0;
        for (int i = 0; i < 32; i++) {
            long ones = 0;
            for (int num : arr) {
                ones += (((num & (1 << i)) > 0 ? 1 : 0));
            }
            ans += ((long) (ones * ((long) n - ones)) * (1 << i));
        }
        return ans;
    }
}
