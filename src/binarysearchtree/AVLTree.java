package binarysearchtree;

public class AVLTree extends BinarySearchTree {

    public int getHeight() {
        return getRoot().height();
    }

    @Override
    public void add(Node node) {
        super.add(node);
        if (getRoot().getRightHeight() - getRoot().getLeftHeight() > 1) {
            if (this.getRoot().right != null &&
                    this.getRoot().right.getLeftHeight() > getRoot().right.getRightHeight() ) {
                getRoot().right.rightRotate();
            }
            getRoot().leftRotate();
            return;
        }
        if (getRoot().getLeftHeight() - getRoot().getRightHeight() > 1) {
            if (this.getRoot().left.getRightHeight() > getRoot().left.getLeftHeight() ) {
                getRoot().left.leftRotate();
            }
            getRoot().rightRotate();
        }
    }
}
