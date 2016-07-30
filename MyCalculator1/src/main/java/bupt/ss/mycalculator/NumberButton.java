package bupt.ss.mycalculator;

import android.view.View;
import android.widget.Button;

/**
 * 数字按键事件
 * Created by Administrator on 2016/7/30.
 */
public class NumberButton implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        StringBuilder input = new StringBuilder();

        // 如果是该元素的第一次输入
        if (GridCalculator.isFirst) {
            GridCalculator.inEq.append(" ");       // 加上空格作为分隔符

            // 如果是小数点，则补上0.
            if (v.getId() == R.id.btn_point) {
                input.append("0");
                input.append(btn.getText().toString());
                GridCalculator.num_first.append(input.toString());
                GridCalculator.inEq.append(input.toString());
                GridCalculator.showEq.append(input.toString());
                Display.setShowText(GridCalculator.tv_equ, GridCalculator.showEq);
                Display.setShowText(GridCalculator.tv_result, GridCalculator.showResult);
                GridCalculator.isPoint = true;
                GridCalculator.isFirst = false;
                return;
            }
            GridCalculator.isFirst = false;
        }

        // 在数字后输入小数点
        else if (v.getId() == R.id.btn_point) {

            // 如果存在小数点，则不理
            if (GridCalculator.isPoint)
                return;
            else
                GridCalculator.isPoint = true;
        }
        input.append(btn.getText().toString());
        GridCalculator.num_first.append(input.toString());
        GridCalculator.inEq.append(input.toString());
        GridCalculator.showEq.append(input.toString());
        Display.setShowText(GridCalculator.tv_equ, GridCalculator.showEq);
        Display.setShowText(GridCalculator.tv_result, GridCalculator.showResult);
        GridCalculator.isOp = false;
        GridCalculator.isHead = false;
    }
}
