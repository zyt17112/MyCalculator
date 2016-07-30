package bupt.ss.mycalculator;

import android.widget.TextView;

/**
 * Created by Administrator on 2016/7/30.
 */
public class Display {

    public static void back() {
        int sL = GridCalculator.showEq.length();
        int iL = GridCalculator.inEq.length();

        if (GridCalculator.showEq.length() == 0
                || GridCalculator.inEq.length() == 0) { // 没有式子时不退格
            GridCalculator.tv_equ.setText(GridCalculator.INITIAL_VALUE);
            GridCalculator.result = 0;
            GridCalculator.showEq.setLength(0);
            clear();
            return;
        }

        // 中缀式子非零
        if (GridCalculator.showEq.length() != 0
                || GridCalculator.inEq.length() != 0) {
            GridCalculator.showEq.deleteCharAt(sL - 1);
            if (GridCalculator.inEq.length() > 1) {
                GridCalculator.inEq.deleteCharAt(iL - 1);
                GridCalculator.inEq.deleteCharAt(iL - 2);
            } else {
                clear();
                return;
            }
            setShowText(GridCalculator.tv_result, GridCalculator.showResult);
            setShowText(GridCalculator.tv_equ, GridCalculator.showEq);
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

    // 重置运算符，首次输入，小数点，存在运算符，数字存储器归空
    public static void clear() {      // 输入等号以后重置的数据
        GridCalculator.prevId = R.id.btn_equal;
        GridCalculator.isFirst = true;
        GridCalculator.isPoint = false;
        GridCalculator.isPos = true;
        GridCalculator.isOp = false;
        GridCalculator.isHead = true;
        GridCalculator.num_first.setLength(0);
    }
}
