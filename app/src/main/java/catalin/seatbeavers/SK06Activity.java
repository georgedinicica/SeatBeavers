package catalin.seatbeavers;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import static java.lang.Math.min;
import static java.lang.Math.round;

/**
 * Created by Peter on 4. 5. 2017.
 */


/**
 * Updated by Catalin on 10. 5. 2017.
 */

//TODO:  status bar size
// TODO: drawable dimension
// TODO: getScaleFactor(884,494+4+40) hide the ---  yk1 k2 k(min k1,k2)
public class SK06Activity extends BaseActivity {
    int xPos[] = {116, 122, 338, 591, 363, 552};
    int yPos[] = {277, 120, 51, 139, 326, 434};// y[2]=61;
    SK06Presenter presenter = new SK06Presenter();
    float myw = 850;//getResourceWidth(this,R.drawable.sk06_pozadie);
    float myh = 528 + 84 + 30;//getResourceHeight(this,R.drawable.sk06_pozadie) + 84 + 30;

    float scaleFactor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* THIS iS NOT neeDed*/
        //        setContentView(R.layout.activity_main);

        /* oldInitMethod(getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight());*/

        init(); /*changed the call to surpass the deprecation*/

        /*float myw = 850;//getResourceWidth(this,R.drawable.sk06_pozadie);
        float myh = 528+84+30;//getResourceHeight(this,R.drawable.sk06_pozadie) + 84 + 30;
        float k1 = (float) (getWindowManager().getDefaultDisplay().getWidth() - 220) / myw;
        float k2 = (float) (getWindowManager().getDefaultDisplay().getHeight() - 50) / myh; // 50 is the height of the status bar, should be some call (or hide the status bar)
        float k = min(k1, k2);*/


        scaleFactor = getScaleFactor(myw, myh); // there should be a new call getScaleFactor(850,528+84+30);
        // there should be a new call getScaleFactor(850,528+84+30);

        /* easier to call inside methods*/
        float workingSpaceWidth = getScreenWidthSize() - 220;
        float workingSpaceHeight = getScreenHeightSize()- getUnusableScreenSpace() - (BitmapFactory.decodeResource(getResources(), R.drawable.alena1).getHeight() * scaleFactor);//  - 90;

        /*        // Adding the temporary container Layout for the endpoints
        RelativeLayout containerLayout = new RelativeLayout(this);
        containerLayout.setLayoutParams(new ViewGroup.LayoutParams(round(850 * k), round(528 * k))); // here I would like to ask the picture to know its size
        containerLayout.setBackgroundColor(Color.BLACK);
        containerLayout.setBackgroundResource(R.drawable.sk06_pozadie);
        // there should be a call setWorkingSpace(x,y,R.drawable.sk06_pozadie) // x,y is the top left corner (nice if this could be called repeatedly) */

        // call setWorkingSpace() returns  a Layout Type Object
        addChildToMainLayout(setWorkingSpace(workingSpaceWidth, workingSpaceHeight, R.drawable.sk06_pozadie));

//        mainLayout.addView(containerLayout);

        for (int i = 0; i < xPos.length; i++) {
            setEndPointsLayoutXY(1, 110, 58, 0, yPos[i], xPos[i], scaleFactor);
        }

        addAllMyImages(presenter, 6, 150,  58, 0, (int) workingSpaceHeight+5, 5,scaleFactor);//the distance can be now also negative
        /*      for (int i = 0; i < xPos.length; i++) {
            setEndPointsLayoutXY(1, round(156 * k), round(84 * k), 0, (int) (yPos[i] * k), (int) (xPos[i] * k), 1);//(156,84)  // the round(xxx*k) should be applied by setEndPointsLayoutXY
        }

        addAllImages(presenter, 6, round(150 * k), round(58 * k), 5, round((528 + 30) * k), false);  // the round(xxx*k) should be applied by addAllImages
        //int=getResources().getDisplayMetrics();*/

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
