package bupt.ss.mycalculator;

import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2016/7/30.
 */
public class OperationButton implements View.OnClickListener{
    @Override
    public void onClick(View v) {
        Button btn = (Button) v;

        // 如果前一个是运算符
        if (GridCalculator.isOp || GridCalculator.isHead) {

            // 输入'-'，作为负号
            if (btn.getText().equals("-")) {
                GridCalculator.inEq.append(" ");
                GridCalculator.inEq.append("N");
                GridCalculator.showEq.append(btn.getText());
                GridCalculator.setShowText(GridCalculator.tv_result, GridCalculator.showResult);
                GridCalculator.setShowText(GridCalculator.tv_equ, GridCalculator.showEq);
                GridCalculator.isPos = false;
            }

            // 输入运算符后其他符号（"+", "*" ,"/, ")"）均不接受
            else if (v.getId() != R.id.btn_left)
                return;

                // 输入左括号
            else {
                GridCalculator.inEq.append(" ");
                GridCalculator.inEq.append(btn.getText());
                GridCalculator.showEq.append(btn.getText());
                GridCalculator.setShowText(GridCalculator.tv_equ, GridCalculator.showEq);
                GridCalculator.setShowText(GridCalculator.tv_result, GridCalculator.showResult);
                GridCalculator.isFirst = true;
                GridCalculator.isPoint = false;
            }
            GridCalculator.isHead = false;
            return;
        }

        // 输入右括号，结尾与数字类比
        if (v.getId() == R.id.btn_right) {

            // 如果是开头，或者上一个是运算符，则不接受
            if (GridCalculator.isHead)
                return;
            else {
                GridCalculator.inEq.append(" ");
                GridCalculator.inEq.append(btn.getText());
                GridCalculator.showEq.append(btn.getText());
                GridCalculator.setShowText(GridCalculator.tv_equ, GridCalculator.showEq);
                GridCalculator.setShowText(GridCalculator.tv_result, GridCalculator.showResult);
                GridCalculator.isOp = false;
            }
        }

        // 输入等号，计算结果
        if (btn.getText().equals("=")) {
            GridCalculator.showResult.setLength(0);
            GridCalculator.result = Calculator.countResult(GridCalculator.inEq.toString());
            GridCalculator.setShowText(GridCalculator.tv_result, GridCalculator.showResult.append(GridCalculator.result));
            GridCalculator.showResult.setLength(0);
            GridCalculator.showEq.setLength(0);
            GridCalculator.clear();
            return;
        }

        // 如果上一个不是运算符，也不是左右括号
        if (!GridCalculator.isOp
                && v.getId() != R.id.btn_left
                && v.getId() != R.id.btn_right) {
            GridCalculator.inEq.append(" ");
            GridCalculator.inEq.append(btn.getText());
            GridCalculator.showEq.append(btn.getText());
            GridCalculator.setShowText(GridCalculator.tv_equ, GridCalculator.showEq);
            GridCalculator.setShowText(GridCalculator.tv_result, GridCalculator.showResult);
            GridCalculator.num_first.setLength(0);
            GridCalculator.prevId = btn.getId();
            GridCalculator.isFirst = true;
            GridCalculator.isPoint = false;
            GridCalculator.isOp = true;
        }
    }
}
