package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {

        String expression = "1+((2+3)*4)-5";
        List<String> expressionList = toInfixExpressionList(expression);
        List<String> suffixExpressionList = parseInfix2SuffixExpressionList(expressionList);
        System.out.println(suffixExpressionList);
        int calculate = calculate(suffixExpressionList);
        System.out.println(calculate);


        //(30+4)*5-6 => 30 4 + 5 * 6 - => 164
        // 4*5-8+60+8/2 =>4 5 * 8 - 60 + 8 2 / +
        /*String suffixExpression = "30 4 + 5 * 6 -";
        //String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        List<String> list = getListString(suffixExpression);
        System.out.println("list:" + list);
        int result = calculate(list);
        System.out.println("计算的结果为: " + result);*/

    }

    private static List<String> parseInfix2SuffixExpressionList(List<String> expression) {
        Stack<String> s1 = new Stack<>();
        ArrayList<String> s2 = new ArrayList<>();
        for (String s : expression) {
            if (s.equals("(")) {
                s1.push(s);
            } else if (s.matches("\\d+")) {
                s2.add(s);
            } else if (s.equals(")")) {
                String tmp;
                while (!(tmp = s1.pop()).equals("(")) {
                    s2.add(tmp);
                }
            } else {
                while (!s1.isEmpty() && Operation.getValue(s1.peek()) >= Operation.getValue(s)) {
                    s2.add(s1.pop());
                }
                s1.push(s);
            }
        }
        while (!s1.isEmpty())
            s2.add(s1.pop());
        return s2;
    }

    private static List<String> toInfixExpressionList(String expression) {
        List<String> ls = new ArrayList<>();
        int index = 0;
        String str;
        char ch;
        do {
            if ((ch = expression.charAt(index)) < 48 || ch > 57) {
                ls.add(ch + "");
                index++;
            } else {
                str = "";
                while (index < expression.length() && (ch = expression.charAt(index)) >= 48 && ch <= 57) {
                    str += ch;
                    index++;
                }
                ls.add(str);
            }
        } while (index < expression.length());
        return ls;
    }

    private static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            if (s.matches("\\d+")) {
                stack.push(s);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int result;
                if (s.equals("+"))
                    result = num1 + num2;
                else if (s.equals("-"))
                    result = num1 - num2;
                else if (s.equals("*"))
                    result = num1 * num2;
                else if (s.equals("/"))
                    result = num1 / num2;
                else
                    throw new RuntimeException("运算符有误");
                stack.push("" + result);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    private static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>(Arrays.asList(split));
        return list;
    }

}

class Operation {
    private final static int ADD = 1;
    private final static int SUB = 1;
    private final static int MUL = 2;
    private final static int DIV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                break;
        }
        return result;
    }
}
