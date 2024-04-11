public class j3RightmostSetBitFinder {
    public static int findRightmostSetBit(int num) {
        // Performing bitwise AND with two's complement to isolate rightmost set bit
        int rightmostSetBit = num & -num; // num & ~(num - 1)
        return rightmostSetBit;
    }

    public static void main(String[] args) {
        int number = 25; // Example number
        int rightmostSetBit = findRightmostSetBit(number);
        System.out.println("Rightmost set bit position: " + (int)(Math.log(rightmostSetBit) / Math.log(2)) + " (counting from right)");
    }
}