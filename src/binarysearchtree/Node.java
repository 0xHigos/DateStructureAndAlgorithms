package binarysearchtree;

public class Node {
    int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    public int height() {
        return Math.max(this.left == null ? 0 : left.height(), this.right == null ? 0 : right.height()) + 1;
    }

    public int getLeftHeight() {
        if (left != null)
            return left.height();
        else
            return 0;
    }

    public int getRightHeight() {
        if (right != null)
            return right.height();
        else
            return 0;
    }

    public void add(Node node) {
        if (node == null)
            return;
        if (node.value < this.value) {
            if (this.left == null)
                this.left = node;
            else
                this.left.add(node);
        } else {
            if (this.right == null)
                this.right = node;
            else
                this.right.add(node);
        }
    }


    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public void leftRotate() {
        Node temp = new Node(value);
        temp.left =this.left;
        temp.right =this.right.left;
        this.value = right.value;
        this.right = this.right.right;
        this.left = temp;
    }

    public void rightRotate() {
        Node temp = new Node(value);
        temp.right = this.right;
        temp.left =this.left.right;
        this.value = left.value;
        this.left = this.left.left;
        this.right = temp;
    }


    public Node search(int value) {
        if (this.value == value)
            return this;
        else if (this.value > value) {
            if (this.left == null)
                return null;
            else
                return this.left.search(value);
        } else {
            if (this.right == null)
                return null;
            else
                return this.right.search(value);
        }
    }

    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        } else if (this.left != null && this.value > value)
            return this.left.searchParent(value);
        else if (this.right != null && this.value < value)
            return this.right.searchParent(value);
        else
            return null;
    }



    @Override
    public String toString() {
        return "Node[" +
                "value=" + value +
                ']';
    }
}
