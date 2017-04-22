package catalin.seatbeavers;

import android.content.ClipData;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Catalin on 22/04/2017.
 */

public class MyTouch implements View.OnTouchListener {
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//            resetSolutionLayout(aTextView, checkAnswerLinearLayout);
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.startDragAndDrop(data, shadowBuilder, view, 0);
                //view.invalidate();
                //  view.setVisibility(View.VISIBLE);
            } else {
                ///  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.startDrag(data, shadowBuilder, view, 0);
                //  buildSeatsList(view);
            }
        }
        //    view.setVisibility(View.VISIBLE);
        //  view.invalidate();
        //view.setBackgroundColor(Color.RED);
        return true;
    }

}

