package binarysearchtree;

public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] array = {10, 11,7,6,8,9};
        AVLTree tree = new AVLTree();
        for (int value : array) {
            tree.add(new Node(value));
        }
        System.out.println("树的高度为");
        System.out.println(tree.getHeight());
        System.out.println("左子树的高度为：" + tree.getRoot().getLeftHeight());
        System.out.println("右子树的高度为：" + tree.getRoot().getRightHeight());
    }
}
