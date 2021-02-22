package tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);
        binaryTree.preOrder();
        System.out.println("===========================================");
        binaryTree.inOrder();
        System.out.println("===========================================");
        binaryTree.postOrder();
        System.out.println("===========================================");
        /*System.out.println("前序遍历方式~~");
        HeroNode heroNode = binaryTree.preOrderSearch(5);
        if (heroNode != null) {
            System.out.println("找到了，信息为：" +heroNode.toString());
        }else{
            System.out.println("没有找到编号为 5 的节点");
        }*/

        /*System.out.println("中序遍历方式~~");
        HeroNode heroNode = binaryTree.infixOrderSearch(5);
        if (heroNode != null) {
            System.out.println("找到了，信息为：" +heroNode.toString());
        }else{
            System.out.println("没有找到编号为 5 的节点");
        }*/

        System.out.println("删除前的排序");
        binaryTree.preOrder();
        binaryTree.delNode(3);
        System.out.println("删除后的排序");
        binaryTree.preOrder();

    }
}

class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void preOrder() {
        if (this.root != null)
            this.root.preOrder();
        else
            System.out.println("root is null");
    }

    public void inOrder() {
        if (this.root != null)
            this.root.inOrder();
        else
            System.out.println("root is null");
    }

    public void postOrder() {
        if (this.root != null)
            this.root.postOrder();
        else
            System.out.println("root is null");
    }

    public HeroNode preOrderSearch(int no) {
        return root == null ? null : root.preOrderSearch(no);
    }

    public HeroNode infixOrderSearch(int no) {
        return root == null ? null : root.infixOrderSearch(no);
    }

    public HeroNode postOrderSearch(int no) {
        return root == null ? null : root.postOrderSearch(no);
    }

    public void delNode(int no) {
        if(root != null){
            if(root.getNo() == no) {
                root = null;
            }else{
                root.delNode(no);
            }
        }else{
            System.out.println("不能删除空树~~");
        }
    }

}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

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
        if(this.left != null && this.left.getNo() ==no) {
            this.left = null;
            return;
        }
        if(this.right != null && this.right.getNo() ==no) {
            this.right = null;
            return;
        }
        if(this.left !=null)
            this.left.delNode(no);
        if(this.right != null)
            this.right.delNode(no);
    }

}
