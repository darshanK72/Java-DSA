public class j03BalancedBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String args[]) {

    }

    public static boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        int left = height(root.left);
        int right = height(root.right);
        if (Math.abs(left - right) > 1)
            return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public static int height(TreeNode root) {
        if (root == null)
            return 0;
        int left = height(root.left);
        int right = height(root.right);
        return Math.max(left, right) + 1;
    }

    public boolean isBalanced = true;

    public boolean isBalancedGlobalVariable(TreeNode root) {
        heightGlobalVariable(root);
        return isBalanced;
    }

    public int heightGlobalVariable(TreeNode root) {
        if (root == null)
            return 0;
        int left = heightGlobalVariable(root.left);
        int right = heightGlobalVariable(root.right);
        if (Math.abs(left - right) > 1) {
            isBalanced = false;
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    static class Pair {
        int height;
        boolean isBalanced;

        public Pair(int h, boolean b) {
            this.height = h;
            this.isBalanced = b;
        }
    }

    public boolean isBalancedUsingPair(TreeNode root) {
        return heightUsingPair(root).isBalanced;
    }

    public Pair heightUsingPair(TreeNode root) {
        if (root == null)
            return new Pair(0, true);
        Pair left = heightUsingPair(root.left);
        Pair right = heightUsingPair(root.right);
        if (!left.isBalanced)
            return left;
        if (!right.isBalanced)
            return right;
        if (Math.abs(left.height - right.height) > 1) {
            return new Pair(-1, false);
        }
        return new Pair(Math.max(left.height, right.height) + 1, true);
    }

    public boolean isBalancedUsingArray(TreeNode root) {
        boolean[] isBalanced = new boolean[1];
        heightUsingArray(root,isBalanced);
        return !isBalanced[0];
    }

    public int heightUsingArray(TreeNode root,boolean[] isBalanced){
        if(root == null) return 0;
        int left = heightUsingArray(root.left,isBalanced);
        int right = heightUsingArray(root.right,isBalanced);
        if(Math.abs(left - right) > 1){
            isBalanced[0] = true;
            return -1;
        }
        return Math.max(left,right) + 1;
    }
}