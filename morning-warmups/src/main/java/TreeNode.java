//    Definition for a binary tree node.

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        if (left != null && right != null) {
            if (left.val > right.val) {
                this.left = right;
                this.right = left;
            } else {
                this.left = left;
                this.right = right;
            }
        } else {
            this.left = left;
            this.right = right;
        }
    }
}
