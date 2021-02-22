package stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "2+2*3-2*2+1";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int index = 0, num1 = 0, num2 = 0, oper = 0, res = 0;
        char ch;
        String keepNum = "";
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            //如果是操作符
            if (operStack.isOper(ch)) {
                //如果栈中有操作符，需要比较优先级
                if (!operStack.isEmpty()) {
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        calculate(numStack, operStack);
                    }
                }
                operStack.push(ch);

                //如果是数字，直接入栈
            } else {
                //存储两位数或者三位数的数字
                keepNum += ch;

                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        if (!operStack.isEmpty() && operStack.peek() == '-') {
                            numStack.push(-Integer.parseInt(keepNum));
                            operStack.pop();
                            operStack.push('+');
                        } else
                            numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        while (!operStack.isEmpty()) {
            calculate(numStack, operStack);
        }
        System.out.printf("表达式 %s = %d", expression, numStack.pop());
    }

    private static void calculate(ArrayStack2 numStack, ArrayStack2 operStack) {
        int num1 = numStack.pop();
        int num2 = numStack.pop();
        int oper = operStack.pop();
        int res = numStack.cal(num1, num2, oper);
        numStack.push(res);
    }
}

class ArrayStack2 {
    private int[] stack;
    private int top = -1;
    private int maxSize;

    public ArrayStack2(int maxSize) {
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

    public int peek() {
        if (!isEmpty())
            return stack[top];
        else
            throw new RuntimeException();
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

    public int priority(int oper) {
        if (oper == '*' || oper == '/')
            return 1;
        else if (oper == '+' || oper == '-')
            return 0;
        else
            return -1;
    }

    public boolean isOper(char oper) {
        return oper == '+' || oper == '-'
                || oper == '*' || oper == '/';
    }

    public int cal(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
        }
        return result;
    }
}
