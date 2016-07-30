package bupt.ss.mycalculator;

/**
 * Created by Administrator on 2016/7/29.
 */
public class Calculator {

    public static float countResult(String inEq) {

        String[] strEq = prepare(inEq);

        ChangeToPostfix change = new ChangeToPostfix(strEq);
        String[] postfix = change.toPostfix(strEq);

        change.printPostfix();
        float result = countEq(postfix);
        System.out.println("计算结果: " + result);
        return result;
    }

    private static float countEq(String[] strEq) {
        int eqL = strEq.length;
        MyArrayStack stack = new MyArrayStack(eqL + 2);
        float result = 0;
        int i = 0;
        while (!strEq[i].equals("#")) {
            float add1 = 0;
            float add2 = 0;
            if (strEq[i].equals("+") || strEq[i].equals("-")
                    || strEq[i].equals("*") || strEq[i].equals("/")) {
                add1 = Float.parseFloat((String) stack.pop());
                add2 = Float.parseFloat((String) stack.pop());
                switch (strEq[i]) {
                    case "+":
                        result = add2 + add1;
                        stack.push("" + result);
                        break;
                    case "-":
                        result = add2 - add1;
                        stack.push("" + result);
                        break;
                    case "*":
                        result = add2 * add1;
                        stack.push("" + result);
                        break;
                    case "/":
                        result = add2 / add1;
                        stack.push("" + result);
                        break;
                    default:
                        break;
                }
            } else {
                stack.push(strEq[i]);
            }
            i++;
        }
        return result;
    }

    // 预处理中缀表达式
    private static String[] prepare(String inEq) {
        String str = inEq + " #";
        String[] strEq = str.split(" ");

        int sL = strEq.length;
        for (int i = 0; i < sL; i++) {
            if (strEq[i].equals("×")) {
                strEq[i] = "*";
            }
            else if (strEq[i].equals("÷")) {
                strEq[i] = "/";
            }
        }

        return strEq;
    }

}
