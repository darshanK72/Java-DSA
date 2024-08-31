public class j32SpiralMatrixIII {
    public static int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] out = new int[rows * cols][2];
        int dir = 0;
        int len = 0;
        int i = 0;
        while(i < rows * cols){
            if(dir == 0 || dir == 2) len++;
            for(int k = 0; k < len; k++){
                if(rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols){
                    out[i++] = new int[]{rStart,cStart};
                }
                if(dir == 0) cStart++;
                else if(dir == 1) rStart++;
                else if(dir == 2) cStart--;
                else if(dir == 3) rStart--;
            }
            dir = (dir + 1) % 4;
        }
        return out;
    }
}
