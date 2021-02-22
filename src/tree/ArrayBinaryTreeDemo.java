package tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree binaryTree = new ArrayBinaryTree(arr);
        binaryTree.postOrder();
    }
}

class ArrayBinaryTree {

    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        preOrder(0);
    }

    public void infixOrder() {
        infixOrder(0);
    }

    public void postOrder() {
        postOrder(0);
    }

    private void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        System.out.print(arr[index] + "\t\t");
        if ((index << 1) + 1 < arr.length)
            preOrder((index << 1) + 1);
        if ((index << 1) + 2 < arr.length)
            preOrder((index << 1) + 2);
    }

    private void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        if ((index << 1) + 1 < arr.length)
            infixOrder((index << 1) + 1);
        System.out.print(arr[index] + "\t\t");
        if ((index << 1) + 2 < arr.length)
            infixOrder((index << 1) + 2);
    }

    private void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        if ((index << 1) + 1 < arr.length)
            infixOrder((index << 1) + 1);
        if ((index << 1) + 2 < arr.length)
            infixOrder((index << 1) + 2);
        System.out.print(arr[index] + "\t\t");
    }
}
