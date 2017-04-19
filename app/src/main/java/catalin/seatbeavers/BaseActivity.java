package catalin.seatbeavers;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public abstract class BaseActivity extends AppCompatActivity {
    private final int viewWidth = 200;
    private final int viewHeight = 75;
    private final int aMARGIN = 20;
    private final int aTEXTSIZE = 18;

    ViewGroup mainLayout;
    LinearLayout checkAnswerLinearLayout;
    Button aCheckButton;
    Button aRestartButton;
    TextView aTextView;
    String MAIN_TAG = "main";
    String aChosenAnswerString;

    public void addLayoutComponents() {
        addCheckSolutionBtn(aMARGIN);
        addRestartBtn(aMARGIN);
        checkSolutionView();
        mainLayout.addView(checkAnswerLinearLayout);
    }

    public void setMainLayout(int xCoord, int yCoord) {

        mainLayout = new RelativeLayout(this);
        mainLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mainLayout.setY(yCoord);
        mainLayout.setX(xCoord);
        mainLayout.setContentDescription(MAIN_TAG);
        mainLayout.setBackgroundColor(Color.LTGRAY);
        //   mainLayout.setOnDragListener(new NamesActivity.My2DragListener());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        PlaneActivity.toast(this, "Activity ended");
    }


    public void recreateActivity(View view) {
        this.recreate();
        PlaneActivity.toast(this, "Activity recreated");
    }

    public abstract String createSolutionString();

    public abstract void checkSolution();

    private void addCheckSolutionBtn(int margin) {

        aCheckButton = new Button(this);

        RelativeLayout.LayoutParams params = getLayoutParams(viewWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        params.setMarginEnd(margin);
        aCheckButton.setId(R.id.checkSolutionButton);/*HAD TO SET THIS HERE SO UPTOP *# ITFINDS THE RESOURECE*/
        aCheckButton.setText(R.string.test);
        aCheckButton.setContentDescription("checkSolution");
        aCheckButton.setBackgroundResource(R.drawable.buttonstyle);
        aCheckButton.setLayoutParams(params);
        mainLayout.addView(aCheckButton);

    }

    private void addRestartBtn(int margin) {
        aRestartButton = new Button(this);

        RelativeLayout.LayoutParams params = getLayoutParams(viewWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        params.addRule(RelativeLayout.BELOW, aCheckButton.getId());/** here*#*/
        params.setMargins(margin, margin, margin, margin);

        aRestartButton.setText(R.string.restartButtonString);
        aRestartButton.setId(R.id.aRestartButton);
        aRestartButton.setBackgroundResource(R.drawable.buttonstyle);
        aRestartButton.setLayoutParams(params);
        mainLayout.addView(aRestartButton);
        aRestartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
    }

    private void checkSolutionView() {
        addCheckSolutionTextView(aMARGIN, aTEXTSIZE);
        addCheckSolutionLayout(aMARGIN);
    }

    private void addCheckSolutionTextView(int margin, int textSize) {
        aTextView = new TextView(this);
        RelativeLayout.LayoutParams params = getLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        params.addRule(RelativeLayout.BELOW, aRestartButton.getId());/** here*#*/
        params.setMargins(margin, margin, margin, margin);
        aTextView.setTextSize(textSize);
        aTextView.setLayoutParams(params);
        aTextView.setId(R.id.aTextView);
        mainLayout.addView(aTextView);
    }

    private void addCheckSolutionLayout(int margin) {
        checkAnswerLinearLayout = new LinearLayout(this);
        RelativeLayout.LayoutParams params = getLayoutParams(viewWidth, viewHeight);
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        params.addRule(RelativeLayout.BELOW, aTextView.getId());/** here*#*/
        params.setMargins(margin, margin, margin, margin);
        checkAnswerLinearLayout.setLayoutParams(params);
        checkAnswerLinearLayout.setVisibility(View.INVISIBLE);
        checkAnswerLinearLayout.setBackgroundColor(Color.GREEN);
//        mainLayout.addView(checkAnswerLinearLayout);
    }

    @NonNull
    private RelativeLayout.LayoutParams getLayoutParams(int w, int h) {
        return new RelativeLayout.LayoutParams(w, h);
    }


}