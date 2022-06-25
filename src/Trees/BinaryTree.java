package Trees;

public class BinaryTree {

    private class Node {

        int val;
        Node left;
        Node right;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }

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
        BinaryTree.Node root = tree.new Node(45, tree.new Node(99, tree.new Node(76, null, null), null), tree.new Node(98, null, tree.new Node(55, null, null)));
        tree.preorder(root);System.out.println("");
        tree.postorder(root);System.out.println("");
        tree.inorder(root);
    }
}
