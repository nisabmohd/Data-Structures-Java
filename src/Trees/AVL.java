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
        return getHeight(node.left) - getHeight(node.left);
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
        // Left  Left Rotation
        if (balanceFactor > 1 && root.left.val > val) {
            root = leftRotate(root);
        }
        // Right  Right Rotation
        if (balanceFactor < -1 && root.right.val < val) {
            root = rightRotate(root);
        }
        // Left  Right Rotation
        if (balanceFactor > 1 && root.left.val < val) {
            root.left = leftRotate(root.left);
            root = rightRotate(root);
        }
        // Right  Left Rotation
        if (balanceFactor < -1 && root.right.val > val) {
            root.right = rightRotate(root.right);
            root = leftRotate(root);
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
