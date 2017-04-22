package catalin.seatbeavers;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MyNameActivity extends BaseActivity {

    MyNamePresenter namePresenter = new MyNamePresenter();

    int x_Coord = 0;
    int y_Coord;//=600;
    DisplayMetrics displaymetrics = new DisplayMetrics();

    String MAIN_TAG = "main";
    String TAG_LINEAR_RED = "linear";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        y_Coord = displaymetrics.heightPixels / 2;
        setImagesContainerLayout(5,300,108,308);
        addAllImages(namePresenter, 5, 305);/*TEST Load image*/
        setContentView(mainLayout);
        addBtnListeners();

        /*Add container Layout*/

        /* do the Drag Customized*/
    }

    @Override
    public String getChosenSolutionString() {
        chosenAnswerString = "1 2 3 4 5";
        return chosenAnswerString;
    }

    @Override
    public String getCorrectSolutionString() {
        correctAnswerString = "1 2 3 4 5";
        return correctAnswerString;
    }




    private void addBtnListeners() {
        aCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSolution(getChosenSolutionString(), getCorrectSolutionString());
            }
        });
        aRestartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreateActivity(v);
            }
        });
    }
}
