package stack;

import java.util.Scanner;

public class ArrayStackDemo {

    public static void main(String[] args) {
        ListStack stack = new ListStack();
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("show:表示显示栈");
        System.out.println("exit:退出栈");
        System.out.println("push:表示添加数据到栈(入栈)");
        System.out.println("pop:表示从栈中取出数据(出栈)");
        System.out.println("please enter your choose");
        while (loop) {
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("please enter one number:");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int pop = stack.pop();
                        System.out.printf("出栈的数据是：%d\n", pop);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop =false;
                    break;
            }
        }
        System.out.println("程序退出~~~~");
    }

}

class ArrayStack {
    private int[] stack;
    private int top = -1;
    private int maxSize;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //栈满
    public boolean isFuLL() {
        return top == maxSize - 1;
    }

    public void push(int value) {
        if (isFuLL()) {
            System.out.println("stack is full");
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException();
        }
        int value = stack[top--];
        return value;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("stack is empty");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}
