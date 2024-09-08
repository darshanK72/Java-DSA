import java.util.Scanner;

public class j02MovingStonesUntilConsecutive {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        System.out.println(numMovesStones(a,b,c));
        in.close();
    }
    public static int[] numMovesStones(int a, int b, int c) {
        int min = Math.min(a,Math.min(b,c));
        int max = Math.max(a,Math.max(b,c));
        int mid = a + b + c - min - max;

        int minMoves = 0;
        int maxMoves = 0;

        if(max - min > 2){
            minMoves = (max - mid < 3 || mid - min < 3) ? 1 : 2;
            maxMoves = max - min - 2;
        }

       return new int[]{minMoves,maxMoves};
    }
}
