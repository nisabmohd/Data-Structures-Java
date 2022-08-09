package Trees;

public class BinaryTree {

    protected void postorder(TreeNode root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val + " ");
    }

    protected void preorder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }

    protected void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        TreeNode root = new TreeNode(45, new TreeNode(99, new TreeNode(76, null, null), null), new TreeNode(98, null, new TreeNode(55, null, null)));
        tree.preorder(root);
        System.out.println("");
        tree.postorder(root);
        System.out.println("");
        tree.inorder(root);
                System.out.println("");

        TreeUtils.levelorder(root);
    }
}
