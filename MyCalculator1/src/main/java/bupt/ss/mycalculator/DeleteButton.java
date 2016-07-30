package bupt.ss.mycalculator;

import android.app.Activity;
import android.view.View;

/**
 *
 * Created by Administrator on 2016/7/30.
 */
public class DeleteButton extends Activity implements View.OnClickListener{
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clear:
                Display.clear();
                GridCalculator.isHead = true;
                GridCalculator.result = 0F;
                GridCalculator.showEq.setLength(0);
                GridCalculator.inEq.setLength(0);
                GridCalculator.showResult.setLength(0);
                GridCalculator.showResult.append(GridCalculator.result);
                Display.setShowText(GridCalculator.tv_equ, GridCalculator.showResult);
                Display.setShowText(GridCalculator.tv_result, GridCalculator.showResult);
                break;
            case R.id.btn_back:
                Display.back();
            default:
                break;
        }
    }
}
