package tree.threadedbinarytree;

import java.util.ArrayList;
import java.util.List;

public class ThreadBinaryTree {

    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node4);
        node4.setRight(node5);
        node4.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
        threadedBinaryTree.threadedBinaryPostNode(root);
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号结点的前驱结点是：" + leftNode);
        System.out.println("10号结点的后继结点是：" + rightNode);

        threadedBinaryTree.threadedTreeListByPost();
    }
}

class ThreadedBinaryTree {
    private HeroNode root;
    private HeroNode pre = null;

    public ThreadedBinaryTree(HeroNode root) {
        this.root = root;
    }

    public void threadedBinaryNode() {
        this.threadedBinaryInfixNode(root);
    }

    public void threadedBinaryPreNode() {
        this.threadedBinaryPreOrderNode(root);
    }

    public void threadedBinaryPreOrderNode(HeroNode node) {
        if (node == null)
            return;
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        if (node.getLeftType() == 0)
            threadedBinaryPreOrderNode(node.getLeft());
        if (node.getRightType() == 0)
            threadedBinaryPreOrderNode(node.getRight());

    }

    public void threadedBinaryInfixNode(HeroNode node) {
        if (node == null)
            return;
        threadedBinaryInfixNode(node.getLeft());
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        threadedBinaryInfixNode(node.getRight());
    }

    public void threadedBinaryPostNode(HeroNode node) {
        if (node == null)
            return;
        threadedBinaryPostNode(node.getLeft());
        threadedBinaryPostNode(node.getRight());
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
    }

    public void threadedTreeListByInfix() {
        HeroNode node = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    public void threadedTreeListByPre() {
        HeroNode node = root;
        while (node != null) {
            System.out.println(node);
            while (node.getLeftType() == 0) {
                node = node.getLeft();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    public void threadedTreeListByPost() {
        HeroNode node = root;
        List<HeroNode> nodeList = new ArrayList<>();
        nodeList.add(node);
        while (node.getLeft() != null) {
            while (node.getRight() != null && node.getRightType() == 0) {
                node = node.getRight();
                nodeList.add(node);
            }
            if (node.getLeft() != null) {
                node = node.getLeft();
                nodeList.add(node);
            }
        }
        for (int i = nodeList.size() - 1; i >= 0; i--)
            System.out.println(nodeList.get(i));
    }

}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //中序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }

    //中序
    public void inOrder() {
        if (this.left != null)
            this.left.inOrder();
        System.out.println(this);
        if (this.right != null)
            this.right.inOrder();
    }

    //后序
    public void postOrder() {
        if (this.left != null)
            this.left.postOrder();
        if (this.right != null)
            this.right.postOrder();
        System.out.println(this);
    }

    //前序遍历
    public HeroNode preOrderSearch(int no) {
        System.out.println("前序遍历~~");
        if (this.getNo() == no)
            return this;
        HeroNode resultNode = null;
        if (this.left != null)
            resultNode = this.left.preOrderSearch(no);
        if (resultNode != null)
            return resultNode;
        if (this.right != null)
            resultNode = this.right.preOrderSearch(no);

        return resultNode;
    }

    //中序遍历
    public HeroNode infixOrderSearch(int no) {
        HeroNode resultNode = null;
        if (this.left != null)
            resultNode = this.left.infixOrderSearch(no);
        if (resultNode != null)
            return resultNode;
        System.out.println("中序遍历~~");
        if (this.getNo() == no)
            return this;
        if (this.right != null)
            resultNode = this.right.infixOrderSearch(no);

        return resultNode;
    }

    //后序遍历
    public HeroNode postOrderSearch(int no) {
        HeroNode resultNode = null;
        if (this.left != null)
            resultNode = this.left.postOrderSearch(no);
        if (resultNode != null)
            return resultNode;
        if (this.right != null)
            resultNode = this.right.postOrderSearch(no);
        if (this.getNo() == no)
            return this;
        System.out.println("后序遍历~~");
        return resultNode;
    }

    public void delNode(int no) {
        if (this.left != null && this.left.getNo() == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.getNo() == no) {
            this.right = null;
            return;
        }
        if (this.left != null)
            this.left.delNode(no);
        if (this.right != null)
            this.right.delNode(no);
    }

}
