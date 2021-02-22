package stack;

public class ListStackDemo {


}

class ListStack {
    private Node head = new Node();

    public ListStack() {
    }

    public boolean isEmpty() {
        return head.next == null;
    }

    public boolean isFull() {
        //链表实现，永远不满(不考虑内存问题)
        return false;
    }

    public void push(int value) {
        Node node = new Node(value);
        node.next = head.next;
        head.next = node;
    }

    public int pop() {
        if (head.next == null) {
            throw new RuntimeException("stack is empty");
        }
        int value = head.next.getValue();
        head.next = head.next.next;
        return value;
    }

    public void list() {
        if(isEmpty()){
            System.out.println("stack is empty");
            return;
        }
        for (Node node = head.next; node !=null ; node =node.next) {
            System.out.println("stack[ "+node.getValue()+" ]");
        }
    }
}

class Node {
    private int value;
    public Node next;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
