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
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        int balanceFactor = getBalanceFactor(root);
        if (balanceFactor > 1) {
            if (val < root.left.val) {
                return rightRotate(root);
            } else if (val > root.left.val) {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        }
        if (balanceFactor < -1) {
            if (val > root.right.val) {
                return leftRotate(root);
            } else if (val < root.right.val) {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }
        return root;
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
