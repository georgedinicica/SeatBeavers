package catalin.seatbeavers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

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

    public int setY_Coord() {
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
       this.y_Coord = displaymetrics.heightPixels / 2;
        return  this.y_Coord;
    }

    /*

        */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(); //sets the mainLayout, default Views and checkSolutionLayout
         /*Add container Layout*/

        setImageY_Coord(setY_Coord(),200);
        setEndPointsLayout(NUMBER_OF_ELEMENTS, SOLUTION_LAYUT_HEIGHT, SOLUTION_LAYUT_WIDTH, SOLUTION_LAYOUT_DISTANCE, setY_Coord());
        /*TEST Load image*/
        addAllImages(namePresenter, NUMBER_OF_ELEMENTS, SOLUTION_LAYOUT_DISTANCE, setImageY_Coord(setY_Coord(),200),false);

        start();  // sets the LayoutCOntent view to mainLayout

        addListenersBtn();
        addListenersDrag();


    }


    @Override
    public String getCorrectSolutionString() {
        correctAnswerString = "1 2 3 4 5";
        return correctAnswerString;
    }

    protected void addListenersDrag() {
        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            redLayoutContainer.get(i).setOnDragListener(new MyDrag(this,namePresenter.getList(), MAIN_TAG, TAG_LINEAR_RED));
        }
        mainLayout.setOnDragListener(new MyDrag(this,namePresenter.getList(), MAIN_TAG, TAG_LINEAR_RED));

    }


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


    public int setImageY_Coord(int imageY_Coord,int param) {
        this.imageY_Coord = imageY_Coord+200;
            return  this.imageY_Coord;
    }
}
