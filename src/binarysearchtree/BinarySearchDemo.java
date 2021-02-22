package binarysearchtree;

public class BinarySearchDemo {
    public static void main(String[] args) {
        int[] array = {7, 3,7,7,7,7};
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for (int value : array) {
            binarySearchTree.add(new Node(value));
        }
        binarySearchTree.infixOrder();

        System.out.println("=============================================");
        binarySearchTree.delNode(7);
        binarySearchTree.infixOrder();
    }
}

class BinarySearchTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    private void delDoubleSubtree(Node target) {
        Node temp = target.right;
        Node tempParent = target;
        while (temp.left != null) {
            tempParent = temp;
            temp = temp.left;
        }
        delSingletonSubtree(temp, tempParent);
        target.value = temp.value;
    }

    private void delSingletonSubtree(Node target, Node parent) {
        if (target.left != null) {
            if (parent != null) {
                if (parent.left.value == target.value) {
                    parent.left = target.left;
                } else {
                    parent.right = target.left;
                }
            } else {
                root = target.left;
            }
        } else {
            if (parent != null) {
                if (parent.left.value == target.value) {
                    parent.left = target.right;
                } else {
                    parent.right = target.right;
                }
            }else{
                root = target.right;
            }
        }
    }

    private void delSingletonNode(Node target, Node parent) {
        if (parent.left != null && parent.left.value == target.value) {
            parent.left = null;
        } else if (parent.right != null && parent.right.value == target.value) {
            parent.right = null;
        }
    }

    public void delNode(int value) {
        if (root != null) {
            Node targetNode = search(value);

            if (targetNode == null)
                return;
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            Node parent = searchParent(value);

            if (targetNode.left == null && targetNode.right == null) { // issue one
                delSingletonNode(targetNode, parent);
            } else if (targetNode.left != null && targetNode.right != null) { // issue two
                delDoubleSubtree(targetNode);
            } else { //issue three
                delSingletonSubtree(targetNode, parent);
            }
        }
    }

    public void infixOrder() {
        if (root == null)
            System.out.println("二叉搜索树为空~~");
        else root.infixOrder();
    }
}


