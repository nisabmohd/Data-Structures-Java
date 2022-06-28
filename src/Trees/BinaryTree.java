package Trees;

public class BinaryTree extends AddOns{

    protected void postorder(Node root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val + " ");
    }

    protected void preorder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }

    protected void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Node root = new Node(45, new Node(99, new Node(76, null, null), null), new Node(98, null, new Node(55, null, null)));
        tree.preorder(root);
        System.out.println("");
        tree.postorder(root);
        System.out.println("");
        tree.inorder(root);
                System.out.println("");

        tree.levelorder(root);
    }
}
