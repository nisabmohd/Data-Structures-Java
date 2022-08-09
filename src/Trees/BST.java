package Trees;

public class BST extends BinaryTree {

    TreeNode root;

    public boolean add(int val) {
        TreeNode check = add(val, root);
        root = check;
        return check == null ? false : true;
    }

    private TreeNode add(int val, TreeNode node) {
        if (node == null) {
            node = new TreeNode(val, null, null);
        } else {
            if (node.val > val) {
                node.left = add(val, node.left);
            } else if (node.val < val) {
                node.right = add(val, node.right);
            }
        }
        return node;
    }

    public void remove(int val) {
        root = remove(val, root);
    }

    private TreeNode remove(int val, TreeNode node) {
        if (node.val > val) {
            if (node.left != null) {
                node.left = remove(val, node.left);
            }
        } else if (node.val < val) {
            if (node.right != null) {
                node.right = remove(val, node.right);
            }
        } else {
            if (node.left == null && node.right == null) {
                return node = null;
            } else if (node.left == null && node.right != null) {
                return node.right;
            } else if (node.left != null && node.right == null) {
                return node.left;
            } else {
                int removeable = getMin(node.right);
                remove(removeable, root);
                node.val = removeable;
            }
        }
        return node;
    }

    private int getMin(TreeNode root) {
        if (root.left == null) {
            return root.val;
        }
        return getMin(root.left);
    }

    public int getMin() {
        return getMin(root);
    }

    public boolean contains(int val) {
        return contains(val, root);
    }

    private boolean contains(int val, TreeNode node) {
        if (node == null) {
            return false;
        }
        if (node.val == val) {
            return true;
        } else if (val > node.val) {
            return contains(val, node.right);
        } else if (val < node.val) {
            return contains(val, node.left);
        }
        return false;
    }

    public void inorder() {
        super.inorder(root);
    }

    public void preorder() {
        super.preorder(root);
    }

    public void postorder() {
        super.postorder(root);
    }

    public void levelorder() {
        TreeUtils.levelorder(root);
    }

    public void leftView() {
        TreeUtils.leftView(root);
    }

    public void rightView() {
        TreeUtils.rightView(root);
    }

    public int height() {
        return TreeUtils.height(root);
    }

    public int size() {
        return TreeUtils.size(root);
    }

    public void topViewTraversal() {
        TreeUtils.topView(root);
    }
    
    public void bottomViewTraversal(){
        TreeUtils.botttomView(root);
    }

}
