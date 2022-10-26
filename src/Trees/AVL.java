package Trees;

import java.util.*;
import static Trees.BTree.TreeNode;

public class AVL<T extends Comparable<T>> extends BinarySearchTree<T> {

    // heights of nodes are stored
    Map<TreeNode<T>, Integer> map;

    private TreeNode<T> root;

    public AVL() {
        map = new HashMap<>();
    }

    @Override
    public void add(T val) {
        root = add(val, root);
    }

    private int getHeight(TreeNode node) {
        return map.getOrDefault(node, 0);
    }

    private int getBalanceFactor(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    @Override
    public void remove(T val) {
        root = delete(val, root);
    }

    private T getMin(TreeNode<T> node) {
        if (node.left == null) {
            return node.val;
        }
        return (T) getMin(node.left);
    }

    @Override
    public T getMin() {
        return getMin(root);
    }

    private TreeNode<T> delete(T val, TreeNode<T> node) {
        if (node == null) {
            return null;
        }
        if (node.val.compareTo(val) > 0) {
            node.left = delete(val, node.left);
        } else if (node.val.compareTo(val) < 0) {
            node.right = delete(val, node.right);
        } else {
            if (node.left == null && node.right != null) {
                return node.right;
            } else if (node.left != null && node.right == null) {
                return node.left;
            } else if (node.left != null && node.right != null) {
                T min = (T) getMin(node.right);
                node.val = min;
                node.right = delete(min, node.right);
                return node;
            } else {
                return null;
            }
        }
        return balance(node, val);
    }

    private TreeNode<T> add(T val, TreeNode< T> root) {
        if (root == null) {
            TreeNode<T> temp = new TreeNode(val);
            map.put(temp, 1);
            return temp;
        } else if (root.compareTo(val) > 0) {
            root.left = add(val, root.left);
        } else if (root.compareTo(val) < 0) {
            root.right = add(val, root.right);
        } else {
            return root;
        }
        return balance(root, val);
    }

    private TreeNode<T> balance(TreeNode<T> node, T val) {
        map.put(node, 1 + Math.max(getHeight(node.left), getHeight(node.right)));
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (node.compareTo(val) > 0) {
                return rightRotate(node);
            } else if (node.compareTo(val) < 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        if (balanceFactor < -1) {
            if (node.compareTo(val) < 0) {
                return leftRotate(node);
            } else if (node.compareTo(val) > 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    private TreeNode<T> leftRotate(TreeNode<T> node) {
        TreeNode<T> temp = node.right;
        TreeNode<T> x = temp.left;
        temp.left = node;
        node.right = x;
        map.put(temp, 1 + Math.max(getHeight(temp.left), getHeight(temp.right)));
        map.put(node, 1 + Math.max(getHeight(node.left), getHeight(node.right)));
        return temp;
    }

    private TreeNode<T> rightRotate(TreeNode<T> node) {
        TreeNode<T> temp = node.left;
        TreeNode<T> y = temp.right;
        temp.right = node;
        node.left = y;
        map.put(temp, 1 + Math.max(getHeight(temp.left), getHeight(temp.right)));
        map.put(node, 1 + Math.max(getHeight(node.left), getHeight(node.right)));
        return temp;
    }

    @Override
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

    @Override
    public List<Object> inOrder() {
        return super.inOrder(root);
    }

    @Override
    public List<Object> preOrder() {
        return super.preOrder(root);
    }

    @Override
    public List<Object> postOrder() {
        return super.postOrder(root);
    }

    @Override
    public List<List<Object>> levelOrder() {
        return super.levelOrder(root);
    }

    @Override
    public List<Object> leftView() {
        return super.leftView(root);
    }

    @Override
    public List<Object> rightView() {
        return super.rightView(root);
    }

    @Override
    public List<Object> topView() {
        return super.topView(root);
    }

    @Override
    public List<Object> botttomView() {
        return super.botttomView(root);
    }

    @Override
    public int height() {
        return super.height(root);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void clear() {
        root = null;
        map.clear();
    }

    @Override
    public String toString() {
        return inOrder().toString();
    }

}
