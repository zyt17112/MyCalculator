package bupt.ss.mycalculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class GridCalculator extends Activity implements View.OnClickListener {

    // 计算结果
    static float result = 0F;

    // 前一个运算符号
    protected static int prevId = R.id.btn_equal;

    // 显示初始值
    protected final static String initialValue = "0.0";

    // 是否是中缀运算式的第一个元素
    protected static boolean isHead = true;

    // 是否是下一个元素的首次输入
    protected static boolean isFirst = true;

    // 是否存在小数点
    protected static boolean isPoint = false;

    // 是否是正数
    protected static boolean isPos = true;

    // 前一个元素是否是运算符
    protected static boolean isOp = false;

    // 存储数值字符串
    protected static StringBuilder num_first = new StringBuilder();

    // 计算式显示字符串
    protected static StringBuilder showEq = new StringBuilder();

    // 中缀表达式字符串
    protected static StringBuilder inEq = new StringBuilder();

    // 中缀表达式字符数组
    protected static String[] strEq;


    protected static Button btn_clear;
    protected static Button btn_back;
    protected static TextView tv_equ, tv_result;
    protected static StringBuilder showResult = new StringBuilder(initialValue);
    protected static Button[] btn_num;   // 数字键
    protected static Button[] btn_op;    // 运算符


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_grid_calculator);
        Log.d("GridCalculator", "onCreate execute");
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        tv_equ = (TextView) findViewById(R.id.txt_equ);
        tv_equ.setText("0.0");
        tv_result = (TextView) findViewById(R.id.txt_result);
        tv_result.setText("0.0");
        btn_clear.setOnClickListener(this);
        btn_back.setOnClickListener(this);

        //  给数字键赋值
        btn_num = new Button[11];
        btn_num[0] = (Button) findViewById(R.id.btn_0);
        btn_num[1] = (Button) findViewById(R.id.btn_1);
        btn_num[2] = (Button) findViewById(R.id.btn_2);
        btn_num[3] = (Button) findViewById(R.id.btn_3);
        btn_num[4] = (Button) findViewById(R.id.btn_4);
        btn_num[5] = (Button) findViewById(R.id.btn_5);
        btn_num[6] = (Button) findViewById(R.id.btn_6);
        btn_num[7] = (Button) findViewById(R.id.btn_7);
        btn_num[8] = (Button) findViewById(R.id.btn_8);
        btn_num[9] = (Button) findViewById(R.id.btn_9);
        btn_num[10] = (Button) findViewById(R.id.btn_point);

        // 设置数字键事件
        NumberButton nb = new NumberButton();
        for (Button bc : btn_num) {
            bc.setOnClickListener(nb);
        }

        // 运算符键赋值
        btn_op = new Button[7];
        btn_op[0] = (Button) findViewById(R.id.btn_plus);
        btn_op[1] = (Button) findViewById(R.id.btn_minus);
        btn_op[2] = (Button) findViewById(R.id.btn_times);
        btn_op[3] = (Button) findViewById(R.id.btn_division);
        btn_op[4] = (Button) findViewById(R.id.btn_equal);
        btn_op[5] = (Button) findViewById(R.id.btn_left);
        btn_op[6] = (Button) findViewById(R.id.btn_right);

        // 设置运算符事件
        OperationButton ob = new OperationButton();
        for (Button bp : btn_op) {
            bp.setOnClickListener(ob);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clear:
                Toast.makeText(GridCalculator.this, "清除数据", Toast.LENGTH_SHORT).show();
                clear();
                isHead = true;
                result = 0F;
                showEq.setLength(0);
                inEq.setLength(0);
                showResult.setLength(0);
                showResult.append(result);
                setShowText(tv_equ, showResult);
                setShowText(tv_result, showResult);
                break;
            case R.id.btn_back:
                back();
            default:
                break;
        }
    }

    /* private class NumberButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Button btn = (Button) v;
            StringBuilder input = new StringBuilder();

            // 如果是该元素的第一次输入
            if (isFirst) {
                inEq.append(" ");       // 加上空格作为分隔符

                // 如果是小数点，则补上0.
                if (v.getId() == R.id.btn_point) {
                    input.append("0");
                    input.append(btn.getText().toString());
                    num_first.append(input.toString());
                    inEq.append(input.toString());
                    showEq.append(input.toString());
                    setShowText(tv_equ, showEq);
                    setShowText(tv_result, showResult);
                    isPoint = true;
                    isFirst = false;
                    return;
                }
                isFirst = false;
            }

            // 在数字后输入小数点
            else if (v.getId() == R.id.btn_point) {

                // 如果存在小数点，则不理
                if (isPoint)
                    return;
                else
                    isPoint = true;
            }
            input.append(btn.getText().toString());
            num_first.append(input.toString());
            inEq.append(input.toString());
            showEq.append(input.toString());
            setShowText(tv_equ, showEq);
            setShowText(tv_result, showResult);
            isOp = false;
            isHead = false;
        }
    }*/

    private class OperationButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Button btn = (Button) findViewById(v.getId());

            // 如果前一个是运算符
            if (isOp || isHead) {

                // 输入'-'，作为负号
                if (btn.getText().equals("-")) {
                    inEq.append(" ");
                    inEq.append("N");
                    showEq.append(btn.getText());
                    setShowText(tv_result, showResult);
                    setShowText(tv_equ, showEq);
                    isPos = false;
                }

                // 输入运算符后其他符号（"+", "*" ,"/, ")"）均不接受
                else if (v.getId() != R.id.btn_left)
                    return;

                    // 输入左括号
                else {
                    inEq.append(" ");
                    inEq.append(btn.getText());
                    showEq.append(btn.getText());
                    setShowText(tv_equ, showEq);
                    setShowText(tv_result, showResult);
                    isFirst = true;
                    isPoint = false;
                }
                isHead = false;
                return;
            }

            // 输入右括号，结尾与数字类比
            if (v.getId() == R.id.btn_right) {

                // 如果是开头，或者上一个是运算符，则不接受
                if (isHead)
                    return;
                else {
                    inEq.append(" ");
                    inEq.append(btn.getText());
                    showEq.append(btn.getText());
                    setShowText(tv_equ, showEq);
                    setShowText(tv_result, showResult);
                    isOp = false;
                }
            }

            // 输入等号，计算结果
            if (btn.getText().equals("=")) {
                showResult.setLength(0);
                result = Calculator.countResult(inEq.toString());
                setShowText(tv_result, showResult.append(result));
                showResult.setLength(0);
                showEq.setLength(0);
                clear();
                return;
            }

            // 如果上一个不是运算符，也不是左右括号
            if (!isOp
                    && v.getId() != R.id.btn_left
                    && v.getId() != R.id.btn_right) {
                inEq.append(" ");
                inEq.append(btn.getText());
                showEq.append(btn.getText());
                setShowText(tv_equ, showEq);
                setShowText(tv_result, showResult);
                num_first.setLength(0);
                prevId = btn.getId();
                isFirst = true;
                isPoint = false;
                isOp = true;
            }
        }
    }

    // 重置运算符，首次输入，小数点，存在运算符，数字存储器归空
    public static void clear() {      // 输入等号以后重置的数据
        prevId = R.id.btn_equal;
        isFirst = true;
        isPoint = false;
        isPos = true;
        isOp = false;
        isHead = true;
        num_first.setLength(0);
    }

    public static void back() {
        int sL = showEq.length();
        int iL = inEq.length();

        if (showEq.length() == 0
                || inEq.length() == 0) { // 没有式子时不退格
            tv_equ.setText(initialValue);
            result = 0;
            showEq.setLength(0);
            clear();
            return;
        }
        // 中缀式子非零
        if (showEq.length() != 0
                || inEq.length() != 0) {
            showEq.deleteCharAt(sL - 1);
            if (inEq.length() > 1) {
                inEq.deleteCharAt(iL - 1);
                inEq.deleteCharAt(iL - 2);
            } else {
                clear();
                return;
            }
            setShowText(tv_result, showResult);
            setShowText(tv_equ, showEq);
        }
    }

    public static void setShowText(TextView tv, StringBuilder sb) {
        int sL = sb.length();
        if (sL > 11) {
            tv.setText(sb.substring(sL - 11));
        } else {
            tv.setText(sb.toString());
        }
    }

}