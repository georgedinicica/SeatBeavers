package catalin.seatbeavers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Catalin on 04/05/2017.
 */

public class WorkActivity extends BaseActivity {
   WorkPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(); //sets the mainLayout, default Views and checkSolutionLayout


//Add container Layout
        mainLayout.setBackgroundResource(R.drawable.alena1);

        setImageY_Coord(setY_Coord(),200);
        setImageY_Coord(setY_Coord(),200);

          setEndPointsLayout(2, 153, 88, 200, setY_Coord());

        addAllImages(presenter, 5, 200, setImageY_Coord(setY_Coord(),200),false);

//TEST Load image


        start();  // sets the LayoutCOntent view to mainLayout

        addListenersBtn();
        addListenersDrag();


    }

    public int setY_Coord() {
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        this.y_Coord = displaymetrics.heightPixels / 2;
        return  this.y_Coord;
    }
    public int setImageY_Coord(int imageY_Coord,int param) {
        this.imageY_Coord = imageY_Coord+200;
        return  this.imageY_Coord;
    }
    @Override
    public String getCorrectSolutionString() {
        return null;
    }

    @Override
    protected void addListenersDrag() {
        for (int i = 0; i < 6; i++) {
            redLayoutContainer.get(i).setOnDragListener(new MyDrag(this,presenter.getList(), MAIN_TAG, TAG_LINEAR_RED));
        }
        mainLayout.setOnDragListener(new MyDrag(this,presenter.getList(), MAIN_TAG, TAG_LINEAR_RED));


    }
    public void addListenersBtn() {
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


    @Override
    public void checkSolution() {
        super.checkSolution();
    }
}
