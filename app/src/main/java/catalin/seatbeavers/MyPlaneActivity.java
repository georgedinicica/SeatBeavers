package catalin.seatbeavers;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;

public class MyPlaneActivity extends BaseActivity {

    int numberOfSeats;
    int miniumNumberOfSeats = 5;
    int maximumNumberOfSeats = 13;

    MyPlanePresenter planePresenter = new MyPlanePresenter();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        numberOfSeats = planePresenter.generateMyRandom(miniumNumberOfSeats, maximumNumberOfSeats);
        init();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        y_Coord = displaymetrics.heightPixels / 2;
        // TODO: THe images should be bigger. SCale doesnt look good.
        setEndPointsLayout(numberOfSeats, 103, 103, 107, (y_Coord + 200));
        addAllImages(planePresenter, numberOfSeats, 110, y_Coord, true);
        setContentView(mainLayout);

        addListenersBtn();
        addListenersDrag();
    }


    @Override
    public String getCorrectSolutionString() {
        String s;
        correctAnswerString = new String();
        int imgNumberConverted;

        for (int i = 0; i < numberOfSeats; i++) {
            s = stripNonDigits(getResources().getResourceName(planePresenter.getShuffledListOfImages().get(i))); /*show the name of the image*/
            imgNumberConverted = Integer.parseInt(s);
            if (imgNumberConverted >= 13 && imgNumberConverted <= 18) {
                correctAnswerString = correctAnswerString + " " + s;
            }
        }
        for (int i = numberOfSeats - 1; i >= 0; i--) {
            s = stripNonDigits(getResources().getResourceName(planePresenter.getShuffledListOfImages().get(i))); /** II 4L-6R   7-12*/
            imgNumberConverted = Integer.parseInt(s);
            if (imgNumberConverted >= 7 && imgNumberConverted <= 12) {
                correctAnswerString = correctAnswerString + " " + s;
            }
        }
        for (int i = 0; i < numberOfSeats; i++) {
            s = stripNonDigits(getResources().getResourceName(planePresenter.getShuffledListOfImages().get(i))); /** III 1L-3R  1-6*/
            imgNumberConverted = Integer.parseInt(s);
            if (imgNumberConverted >= 1 && imgNumberConverted <= 6) {
                correctAnswerString = correctAnswerString + " " + s;
            }
        }
        return correctAnswerString;
    }

    @Override
    protected void addListenersDrag() {
        for (int i = 0; i < redLayoutContainer.size(); i++) {
            redLayoutContainer.get(i).setOnDragListener(new MyDrag(this, planePresenter.getList(), MAIN_TAG, TAG_LINEAR_RED));
        }
        mainLayout.setOnDragListener(new MyDrag(this, planePresenter.getList(), MAIN_TAG, TAG_LINEAR_RED));
    }

    @Override
    public void addListenersBtn() {
        aCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


}




