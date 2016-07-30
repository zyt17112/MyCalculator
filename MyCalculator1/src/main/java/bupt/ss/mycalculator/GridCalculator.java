package bupt.ss.mycalculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class GridCalculator extends Activity {

    // 计算结果
    static float result = 0F;

    // 前一个运算符号
    protected static int prevId = R.id.btn_equal;

    // 显示初始值
    protected final static String INITIAL_VALUE = "0.0";

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



    protected static Button btn_clear;
    protected static Button btn_back;
    protected static TextView tv_equ, tv_result;
    protected static StringBuilder showResult = new StringBuilder(INITIAL_VALUE);
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

        // 设置清除和退格按键事件
        btn_clear.setOnClickListener(new DeleteButton());
        btn_back.setOnClickListener(new DeleteButton());

    }

}