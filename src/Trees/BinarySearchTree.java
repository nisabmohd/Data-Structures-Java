package Trees;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree {

    private TreeNode<T> root;
    private int size = 0;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public void add(T val) {
        root = add(val, root);
    }

    private TreeNode add(T val, TreeNode node) {
        if (node == null) {
            size++;
            return new TreeNode<>(val);
        }
        if (node.compareTo(val) > 0) {
            node.left = add(val, node.left);
        } else if (node.compareTo(val) < 0) {
            node.right = add(val, node.right);
        }
        return node;
    }

    public List<Object> inOrder() {
        return super.inOrder(root);
    }

    public List<Object> preOrder() {
        return super.preOrder(root);
    }

    public List<Object> postOrder() {
        return super.postOrder(root);
    }

    private static boolean that = false;

    public void remove(T val) {
        that = false;
        root = remove(val, root);
        if (that) {
            size++;
        }
    }

    private TreeNode remove(T val, TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.compareTo(val) > 0) {
            node.left = remove(val, node.left);
        } else if (node.compareTo(val) < 0) {
            node.right = remove(val, node.right);
        } else {
            if (node.left == null && node.right != null) {
                size--;
                return node.right;
            } else if (node.right == null && node.left != null) {
                size--;
                return node.left;
            } else if (node.right != null && node.left != null) {
                T temp = getMin(node.right);
                node.val = temp;
                node.right = remove(temp, node.right);
                that = true;
                size--;
                return node;
            } else {
                size--;
                return null;
            }

        }
        return node;
    }

    private T getMin(TreeNode node) {
        if (node.left == null) {
            return (T) node.val;
        }
        return getMin(node.left);
    }

    public T getMin() {
        return getMin(root);
    }

    public boolean contains(T val) {
        return contains(val, root);
    }

    private boolean contains(T val, TreeNode node) {
        if (node == null) {
            return false;
        }
        if (node.compareTo(val) == 0) {
            return true;
        }
        return contains(val, node.left) || contains(val, node.right);
    }

    public List<List<Object>> levelOrder() {
        return super.levelOrder(root);
    }

    public List<Object> leftView() {
        return super.leftView(root);
    }

    public List<Object> rightView() {
        return super.rightView(root);
    }

    public int size() {
        return size;
    }

    public List<Object> topView() {
        return super.topView(root);
    }

    public List<Object> botttomView() {
        return super.botttomView(root);
    }

    public int height() {
        return super.height(root);
    }

    @Override
    public String toString() {
        return this.inOrder().toString();
    }

}
