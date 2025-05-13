public class j04DiameterOfBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 0;
        int[] diameter = new int[1];
        maxDepth(root, diameter);
        return diameter[0] - 1;
    }

    public static int maxDepth(TreeNode root, int[] diameter) {
        if (root == null)
            return 0;
        int leftMaxDepth = maxDepth(root.left, diameter);
        int rightMaxDepth = maxDepth(root.right, diameter);
        diameter[0] = Math.max(diameter[0], leftMaxDepth + rightMaxDepth + 1);
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }
}
