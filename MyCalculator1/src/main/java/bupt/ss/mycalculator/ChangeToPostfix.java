package bupt.ss.mycalculator;

/**
 * Created by Administrator on 2016/7/29.
 */
public class ChangeToPostfix {
    private String[] strEq;
    private String[] strPo;

	/* String[] strEq = { "1", "+", "(", "2", "-", "3", ")", "*", "4", "+" ,
    "10", "/", "5", "#" } */

    public ChangeToPostfix() {
        // 中缀表达式：1+(2-3)*4+10/5
        strEq = new String[1];
        strEq[0] = "#";
    }

    public ChangeToPostfix(String[] str) {
        strEq = str;
    }

    public void printPostfix() {

        strPo = toPostfix(strEq);

        System.out.println("后缀表达式是：");
        for (int i = 0; i < strPo.length; i++) {
            System.out.print(strPo[i] + " ");
        }

    }

    public String[] toPostfix(String[] strEq) {

        // 初始参数
        int eqL = strEq.length;
        String tempstr;
        String[] strPo = new String[eqL];
        MyArrayStack Opts = new MyArrayStack(eqL);
        // MyArrayStack Opnd = new MyArrayStack(eqL + 2);
        int j = 0;

        // 遍历中缀表达式
        for (int i = 0; i < eqL; i++) {

            // 判断是否空
            if (strEq[i].equals("")){
                continue;
            }

            // 判断是否是数字,数字直接输出
            if (strEq[i].charAt(0) >= '0' && strEq[i].charAt(0) <= '9') {
                strPo[j] = strEq[i];
                j++;
            }

            // 判断是否“）”
            else if (strEq[i].equals(")")) {
                tempstr = (String) Opts.pop();

                // 直到把左括号弹出
                while (!tempstr.equals("(")) {
                    strPo[j] = tempstr;
                    j++;
                    tempstr = (String) Opts.pop();
                }
            }

            // 判断是否加减号
            else if (strEq[i].equals("+") || strEq[i].equals("-")) {

                // 如果栈为空，则入栈
                if (Opts.empty()) {
                    Opts.push(strEq[i]);
                }

                // 栈非空，且不是"("左括号，先把栈中符号弹出，然后把新符号入栈
                else {
                    do {
                        tempstr = (String) Opts.pop();
                        if (tempstr.equals("(")) {
                            Opts.push(tempstr);
                        } else {
                            strPo[j] = tempstr;
                            j++;
                        }
                    } while (!Opts.empty() && !tempstr.equals("("));
                    Opts.push(strEq[i]);
                }
            }

            // 判断是否乘除号
            else if (strEq[i].equals("*") || strEq[i].equals("/")) {

                // 当栈空时入栈
                if (Opts.empty()) {
                    Opts.push(strEq[i]);
                }

                // 非空时判断优先级，弹出大于等于自己优先级的所有符号
                else {
                    do {
                        tempstr = (String) Opts.pop();
                        if (tempstr.equals("-") || tempstr.equals("+")) {
                            Opts.push(tempstr);
                        } else {
                            strPo[j] = tempstr;
                            j++;
                        }
                    } while (!Opts.empty() && !tempstr.equals("(")
                            && !tempstr.equals("+") && !tempstr.equals("-"));
                    Opts.push(strEq[i]);
                }

            }

            // 判断是否"("左括号
            else if (strEq[i].equals("(")) {
                Opts.push(strEq[i]);
            }

            // 如果收到#号，则式子结束
            else if (strEq[i].equals("#")) {
                break;
            }

            // 如果均不属于以上情况，则输入格式有误
            else {
                System.err.println("输入格式有误！");
                break;
            }
        }

        // 最后当栈不为空的时候弹栈
        while (!Opts.empty()) {
            strPo[j] = (String) Opts.pop();
            j++;
        }
        strPo[j] = strEq[eqL - 1];

        // 返回后缀表达式
        return strPo;

    }
}
