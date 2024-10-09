import java.util.Scanner;

public class j03FindFirstBadVersion {
    private int bad;

    public j03FindFirstBadVersion(int bad) {
        this.bad = bad;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int bad = in.nextInt();
        j03FindFirstBadVersion badVersion = new j03FindFirstBadVersion(bad);
        System.out.println(badVersion.firstBadVersion(n));
        in.close();
    }

    public boolean isBadVersion(int n) {
        if (n < this.bad) {
            return false;
        }
        return true;
    }

    public int firstBadVersion(int n) {
        int s = 1;
        int e = n;
        while (s <= e) {
            int mid = s + ((e - s) / 2);
            if (!isBadVersion(mid)) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return s;
    }
}
