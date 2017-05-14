package catalin.seatbeavers;

import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by Peter on 4. 5. 2017.
 */


/**
 * Updated by Catalin on 10. 5. 2017.
 */
public class SK06Activity extends BaseActivity {
    int xPos[] = {116, 122, 338, 591, 363, 552};
    int yPos[] = {277, 120, 51, 139, 326, 434};// y[2]=61;
    SK06Presenter presenter = new SK06Presenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight());

        // Adding the temporary container Layout for the endpoints
        RelativeLayout containerLayout = new RelativeLayout(this);
        containerLayout.setLayoutParams(new ViewGroup.LayoutParams(getWindowManager().getDefaultDisplay().getWidth() - 220, getWindowManager().getDefaultDisplay().getHeight() - 230));
        containerLayout.setBackgroundColor(Color.BLACK);
        containerLayout.setBackgroundResource(R.drawable.sk06_pozadie);

        mainLayout.addView(containerLayout);

        for (int i = 0; i < xPos.length; i++) {
            setEndPointsLayoutXY(1, 156, 84, 0, (int) (yPos[i] * 2), (int) (xPos[i] * 2));//(156,84)
        }
        addAllImages(presenter, 6, 156, 84, 165, getWindowManager().getDefaultDisplay().getHeight() - 200, false);

        start();

        addListenersBtn();
        addListenersDrag();

    }

    @Override
    public String getCorrectSolutionString() {
        correctAnswerString = "061 062 063 064 065 066 "; // the last space has to be there when writing the answer string
        return correctAnswerString;
    }

    @Override
    protected void addListenersDrag() {
        for (int i = 0; i < 6; i++) {
            redLayoutContainer.get(i).setOnDragListener(new MyDrag(this, presenter.getList(), MAIN_TAG, TAG_LINEAR_RED));
        }
        mainLayout.setOnDragListener(new MyDrag(this, presenter.getList(), MAIN_TAG, TAG_LINEAR_RED));

    }


    @Override
    protected void addListenersBtn() {
        aCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: check solution has ok=1 testAnswer that compares 2 Strings
                checkSolution();

            }
        });
        aRestartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreateActivity(v);
            }
        });
    }

  /*  @Override
    public void checkSolution() {
        super.checkSolution();
    }
*/
}
