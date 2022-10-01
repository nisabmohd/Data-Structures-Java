package Trees;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree {

    private TreeNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public void add(T val) {
        root = add(val, root);
    }

    private TreeNode add(T val, TreeNode node) {
        if (node == null) {
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

    public void remove(T val) {
        root = remove(val, root);
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
                return node.right;
            } else if (node.right == null && node.left != null) {
                return node.left;
            } else if (node.right != null && node.left != null) {
                T temp = getMin(node.right);
                node.val = temp;
                node.right = remove(temp, node.right);
                return node;
            } else {
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
    
    
}
