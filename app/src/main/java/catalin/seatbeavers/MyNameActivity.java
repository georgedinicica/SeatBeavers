package catalin.seatbeavers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

public class MyNameActivity extends BaseActivity {

    MyNamePresenter namePresenter=new MyNamePresenter();

    int x_Coord = 0;
    int y_Coord;//=600;

/**HAVE TO ADD THE HIDING LAYOUT AGAIN*/
    String MAIN_TAG = "main";
    String TAG_LINEAR_RED = "linear";
    private int NUMBER_OF_ELEMENTS = 5;
    private int SOLUTION_LAYUT_HEIGHT = 300;
    private int SOLUTION_LAYUT_WIDTH = 118;
    private int SOLUTION_LAYOUT_DISTANCE = 308;

    public void setY_Coord() {
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
       this.y_Coord = displaymetrics.heightPixels / 2;

    }

    /*

        */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(); //sets the mainLayout, default Views and checkSolutionLayout
         /*Add container Layout*/

        setY_Coord();
        setImageY_Coord(y_Coord,200);
        setImagesSolutionLayout(NUMBER_OF_ELEMENTS, SOLUTION_LAYUT_HEIGHT, SOLUTION_LAYUT_WIDTH, SOLUTION_LAYOUT_DISTANCE, y_Coord);
        /*TEST Load image*/
        addAllImages(namePresenter, NUMBER_OF_ELEMENTS, SOLUTION_LAYOUT_DISTANCE, imageY_Coord,false);

        setContentView(mainLayout);

//        checkSolution(getChosenSolutionString(),getChosenSolutionString());
        addBtnListeners();
        addDragListeners();


    }

    @Override
    public String getChosenSolutionString() {
        chosenAnswerString = new String();
        for (int i = 0; i < redLayoutContainer.size(); i++) {
            if (redLayoutContainer.get(i).getChildCount() == 1) {
                chosenAnswerString += " " + stripNonDigits(redLayoutContainer.get(i).getChildAt(0).getContentDescription());
            }
        }
        return chosenAnswerString;
    }

    @Override
    public String getCorrectSolutionString() {
        correctAnswerString = "1 2 3 4 5";
        return correctAnswerString;
    }

    private void addDragListeners() {
        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            redLayoutContainer.get(i).setOnDragListener(new MyDrag(this,namePresenter.getList(), MAIN_TAG, TAG_LINEAR_RED));
        }
        mainLayout.setOnDragListener(new MyDrag(this,namePresenter.getList(), MAIN_TAG, TAG_LINEAR_RED));

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

    public void setImageY_Coord(int imageY_Coord,int param) {
        this.imageY_Coord = imageY_Coord+200;
    }
}
