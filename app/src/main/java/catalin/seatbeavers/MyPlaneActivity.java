package catalin.seatbeavers;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MyPlaneActivity extends BaseActivity {

    int numberOfSeats;
    int miniumNumberOfSeats = 5;
    int maximumNumberOfSeats = 13;

    List<MyImage> drawablesList = new ArrayList<>();
    MyPlanePresenter planePresenter = new MyPlanePresenter();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        numberOfSeats=planePresenter.generateMyRandom(miniumNumberOfSeats,maximumNumberOfSeats);
        init();
        setImagesContainerLayout(numberOfSeats,100,100,102);
        addAllImages(planePresenter, numberOfSeats, 93);
        setContentView(mainLayout);
        addBtnListeners();
    }


    @Override
    public String getChosenSolutionString() {
        chosenAnswerString = "1";
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

