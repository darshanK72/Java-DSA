import java.util.Scanner;

public class j14InternalMemoryLeak {
      public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int memory1 = in.nextInt();
        int memory2 = in.nextInt();
        System.out.println(memLeak(memory1, memory2));
        in.close();
    }

    public static int[] memLeak(int memory1, int memory2) {
        int crash = 1;
        while (memory1 > 0 || memory2 > 0) {
            if (memory1 < crash && memory2 < crash)
                break;
            if (memory1 >= memory2)
                memory1 -= crash;
            else
                memory2 -= crash;
            crash++;
        }
        return new int[] { crash, memory1, memory2 };
    }
}
