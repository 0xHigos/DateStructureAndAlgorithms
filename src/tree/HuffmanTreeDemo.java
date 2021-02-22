package tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HuffmanTreeDemo {
    public static void main(String[] args) {
        HuffmanTree huffmanTree = new HuffmanTree();
        Node root = huffmanTree.createHuffmanTree(new int[]{13, 7, 8, 3, 29, 6, 1});
        huffmanTree.preOrder(root);
    }
}

class HuffmanTree {

    public void preOrder(Node node) {
        if (node != null)
            node.preOrder();
        else
            System.out.println("是空树，不能遍历~~");
    }

    public Node createHuffmanTree(int[] array) {
        List<Node> nodeList = new ArrayList<>();
        for (int value : array) {
            nodeList.add(new Node(value));
        }
        while (nodeList.size() > 1) {
            Collections.sort(nodeList);
            Node leftNode = nodeList.get(0);
            Node rightNode = nodeList.get(1);
            Node parent = new Node(leftNode.getValue() + rightNode.getValue());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            nodeList.add(parent);
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
        }
        return nodeList.get(0);
    }

}

class Node implements Comparable<Node> {
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }

    @Override
    public String toString() {
        return "Node[" +
                "value=" + value +
                ']';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
