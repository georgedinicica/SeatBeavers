package catalin.seatbeavers;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseActivity extends AppCompatActivity {
    private final int viewWidth = 200;
    private final int viewHeight = 75;
    private final int aMARGIN = 20;
    private final int aTEXTSIZE = 18;

    ViewGroup redLayout;
    List<ViewGroup> redLayoutContainer = new ArrayList<>();

    int x_Coord = 0;
    int y_Coord=300;
    ViewGroup mainLayout;
    LinearLayout checkAnswerLinearLayout;
    Button aCheckButton;
    Button aRestartButton;
    TextView aTextView;
    String MAIN_TAG = "main";
    String TAG_LINEAR_RED = "linear";

    String chosenAnswerString;
    String correctAnswerString;

    public void addLayoutComponents() { /*imagesContainer and imagesNeeded*/
        addDefaultViews();
        addCheckSolutionView();


    }

    public abstract String getCorrectSolutionString();

    public abstract String getChosenSolutionString();

    public void init() {
        setMainLayout(0, 0);
        addDefaultViews();
        addCheckSolutionView();
//        setContentView(mainLayout);
    }

    private void setMainLayout(int xCoord, int yCoord) {

        mainLayout = new RelativeLayout(this);
        mainLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mainLayout.setX(xCoord);
        mainLayout.setY(yCoord);
        mainLayout.setContentDescription(MAIN_TAG);
        mainLayout.setBackgroundColor(Color.LTGRAY);
        //   mainLayout.setOnDragListener(new NamesActivity.My2DragListener());
    }

    private void addDefaultViews() {
        addCheckSolutionBtn(aMARGIN);
        addRestartBtn(aMARGIN);
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
    }

    public void addCheckSolutionView() {
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
        mainLayout.addView(checkAnswerLinearLayout);
    }


    public void addImage(IPresenter presenter,Integer imageID, int x_ImageCoord, int y_ImageCoord) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(imageID);
        imageView.setContentDescription(getResources().getResourceName(imageID));

        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setX(x_ImageCoord);
        imageView.setY(y_ImageCoord);
        //   imageView.setMaxWidth(300);
        //  imageView.setMaxHeight(108);

        imageView.setVisibility(View.VISIBLE);
        mainLayout.addView(imageView);
        imageView.setOnTouchListener(new MyTouch());

        presenter.buildSeatsList(imageView);/*THIS SHOULD BE PLACED SEPARATELY*/

    }
    public void addAllImages(IPresenter presenter, int numberIterations, int xDistance) {/*hardcodings. Could set this as parameters header*/
        for (int i = 0; i < numberIterations; i++) {
            addImage(presenter, presenter.getShuffledListOfImages().get(i), x_Coord, y_Coord);
            x_Coord += xDistance;
        }
    }

    public void setImagesContainerLayout(int numberOfIterations,int h,int w,int distance) {
        int x = 0;
        for (int i = 0; i < numberOfIterations; i++) {
            redLayout = new RelativeLayout(this);
            redLayout.setX(x);
            redLayout.setY(600);
            redLayout.setBackgroundColor(Color.RED);
            redLayout.setContentDescription(TAG_LINEAR_RED + (i + 1));
            redLayout.setLayoutParams(new ViewGroup.LayoutParams(h,w));//300, 108));
//            redLayout.setOnDragListener(new My2DragListener());
     //       redLayout.setOnDragListener(new Drag(namePresenter.getListOfNames(), MAIN_TAG, TAG_LINEAR_RED));

            x += distance;//310;
            redLayoutContainer.add(redLayout);
            mainLayout.addView(redLayout);
        }

    }

    public void checkSolution(String aChosenAnswerString, String correctSolutionString) {
        if (aChosenAnswerString.contains(correctSolutionString)) {
            aTextView.setText(R.string.correctAnswerString);
            checkAnswerLinearLayout.setVisibility(View.VISIBLE);
            checkAnswerLinearLayout.setBackgroundColor(Color.GREEN);
        }
        if (!aChosenAnswerString.contains(correctSolutionString)) {
            checkAnswerLinearLayout.setVisibility(View.VISIBLE);
            checkAnswerLinearLayout.setBackgroundColor(Color.RED);
            aTextView.setText(R.string.incorrectAnswerString);
        }
    }

    @NonNull
    private RelativeLayout.LayoutParams getLayoutParams(int w, int h) {
        return new RelativeLayout.LayoutParams(w, h);
    }

    public void resetSolutionLayout() {
        aTextView.setText("");
        checkAnswerLinearLayout.setVisibility(View.INVISIBLE);
    }


}