package catalin.seatbeavers;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;

public class MyPlaneActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainLayout(10, 10);
//        shuffleDrawables(drawableNames, drawableList);
        addLayoutComponents();
        setContentView(mainLayout);
        addBtnListeners();

    }

    private void addBtnListeners() {
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

    @Override
    public String createSolutionString() {
        return "1 2 3 4 5";
    }


    public void checkSolution() {
        chosenSolution();
        if (aChosenAnswerString.contains(createSolutionString())) {
            aTextView.setText(R.string.correctAnswerString);
            checkAnswerLinearLayout.setVisibility(View.VISIBLE);
            checkAnswerLinearLayout.setBackgroundColor(Color.GREEN);
        }
        if (!aChosenAnswerString.contains(createSolutionString())) {
            checkAnswerLinearLayout.setVisibility(View.VISIBLE);
            checkAnswerLinearLayout.setBackgroundColor(Color.RED);
            aTextView.setText(R.string.incorrectAnswerString);
        }

    }

    private void chosenSolution() {
        aChosenAnswerString = new String();

    }


}

