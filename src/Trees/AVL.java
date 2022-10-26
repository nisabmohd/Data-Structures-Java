package Trees;

import java.util.*;

public class AVL {

    private class AVLNode {

        int val;
        int height;
        AVLNode left, right;

        public AVLNode(int val) {
            this.val = val;
            this.height = 1;
        }

    }
    private AVLNode root;

    public void add(int val) {
        root = add(val, root);
    }

    private int getHeight(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    private int getBalanceFactor(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private AVLNode add(int val, AVLNode root) {
        if (root == null) {
            return new AVLNode(val);
        } else if (root.val > val) {
            root.left = add(val, root.left);
        } else if (root.val < val) {
            root.right = add(val, root.right);
        } else {
            return root;
        }
        return balance(root, val);
    }

    public void remove(int val) {
        root = delete(val, root);
    }

    private int getMin(AVLNode node) {
        if (node.left == null) {
            return node.val;
        }
        return getMin(node.left);
    }

    public int getMin() {
        return getMin(root);
    }

    private AVLNode delete(int val, AVLNode node) {
        if (node == null) {
            return null;
        }
        if (node.val > val) {
            node.left = delete(val, node.left);
        } else if (node.val < val) {
            node.right = delete(val, node.right);
        } else {
            if (node.left == null && node.right != null) {
                return node.right;
            } else if (node.left != null && node.right == null) {
                return node.left;
            } else if (node.left != null && node.right != null) {
                int min = getMin(node.right);
                node.val = min;
                node.right = delete(min, node.right);
                return node;
            } else {
                return null;
            }
        }
        return balance(node, val);
    }

    private AVLNode balance(AVLNode node, int val) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (val < node.left.val) {
                return rightRotate(node);
            } else if (val > node.left.val) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        if (balanceFactor < -1) {
            if (val > node.right.val) {
                return leftRotate(node);
            } else if (val < node.right.val) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    private AVLNode leftRotate(AVLNode node) {
        AVLNode temp = node.right;
        AVLNode x = temp.left;
        temp.left = node;
        node.right = x;
        temp.height = 1 + Math.max(getHeight(temp.left), getHeight(temp.right));
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        return temp;
    }

    private AVLNode rightRotate(AVLNode node) {
        AVLNode temp = node.left;
        AVLNode y = temp.right;
        temp.right = node;
        node.left = y;
        temp.height = 1 + Math.max(getHeight(temp.left), getHeight(temp.right));
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        return temp;
    }

    public List<Object> inOrder() {
        return inOrder(root);
    }

    private List<Object> inOrder(AVLNode node) {
        if (node == null) {
            return new ArrayList<>();
        }
        List<Object> ans = new ArrayList<>();
        ans.addAll(inOrder(node.left));
        ans.add(node.val);
        ans.addAll(inOrder(node.right));
        return ans;
    }

    @Override
    public String toString() {
        return inOrder().toString();
    }

}
