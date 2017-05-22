package catalin.seatbeavers;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;
import static java.lang.Math.round;


public abstract class BaseActivity extends AppCompatActivity {
    private final int viewWidth = 200;
    private final int viewHeight = 75;
    private final int aMARGIN = 10;
    private final int aTEXTSIZE = 18;
    DisplayMetrics displaymetrics = new DisplayMetrics();
    DisplayMetrics baseDisplayMetrics = new DisplayMetrics();
    ViewGroup redLayout;
    List<ViewGroup> redLayoutContainer = new ArrayList<>();

    int x_Coord = 0;
    int y_Coord = 300;
    ViewGroup mainLayout;
    LinearLayout checkAnswerLinearLayout;
    Button aCheckButton;
    Button aRestartButton;
    TextView aTextView;
    String MAIN_TAG = "main";
    String TAG_LINEAR_RED = "linear";

    String chosenAnswerString;
    String correctAnswerString;
    public int imageY_Coord;


    public abstract String getCorrectSolutionString();

    protected abstract void addListenersDrag();

    protected abstract void addListenersBtn();
    public void init() {
        getWindowManager().getDefaultDisplay().getRealMetrics(baseDisplayMetrics);  // stores the screen REZ in baseDisplayMEtrics
        setMainLayout();
        addDefaultViews();
        addCheckSolutionView();
     }
    public void oldInitMethod(int width, int height) {
        getWindowManager().getDefaultDisplay().getRealMetrics(baseDisplayMetrics);  // stores the screen REZ in baseDisplayMEtrics
        setMainLayout();
        addDefaultViews();
        addCheckSolutionView();
//        setContentView(mainLayout);
    }

    public void start() {
        setContentView(mainLayout);

    }

    private void setMainLayout() {

        mainLayout = new RelativeLayout(this);
        mainLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)); //ViewGroup.LayoutParams.MATCH_PARENT));

//        mainLayout.setY(height);
        mainLayout.setContentDescription(MAIN_TAG);
        mainLayout.setBackgroundColor(Color.LTGRAY);
        //   mainLayout.setOnDragListener(new NamesActivity.My2DragListener());
    }

    private void addDefaultViews() {
        addCheckSolutionBtn(aMARGIN);
        addRestartBtn(aMARGIN);
    }

    public float getScaleFactor(float width, float height) {
        this.getWindowManager().getDefaultDisplay().getRealMetrics(baseDisplayMetrics);
//        float k1 = (float) (getWindowManager().getDefaultDisplay().getWidth() - 220) / width;
//        float k2 = (float) (getWindowManager().getDefaultDisplay().getHeight() - 50) / height; // 50 is the height of the status bar, should be some call (or hide the status bar)

        float screenWidth = (float) ((baseDisplayMetrics.widthPixels -220) / width);/*the 220 is the right space for buttons*/

        float screenHeight = (float) ((baseDisplayMetrics.heightPixels) / height);//-(displaymetrics.heightPixels-baseDisplayMetrics.heightPixels)) / height;
        return min(screenWidth, screenHeight);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Toast.makeText(getApplicationContext(), "Activity ended", Toast.LENGTH_SHORT).show();

    }

    public void recreateActivity(View view) {
        this.recreate();
        Toast.makeText(getApplicationContext(), "Activity recreated", Toast.LENGTH_SHORT).show();

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
        RelativeLayout.LayoutParams params = getLayoutParams(100, ActionBar.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        params.addRule(RelativeLayout.BELOW, aRestartButton.getId());/** here*#*/
        params.setMargins(margin, margin, margin, margin);
        aTextView.setTextSize(textSize);
        aTextView.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
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

    public void addAnImage(IPresenter presenter, int imageWidth, int imageHeight, int imageID, int x_ImageCoord, int y_ImageCoord,float scaleFactor) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(imageID);
        imageView.setContentDescription("img" + getResources().getResourceName(imageID));

//        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setLayoutParams(new ViewGroup.LayoutParams(round(imageWidth*scaleFactor),round(imageHeight*scaleFactor)));// make as pARAMS
        imageView.setX(x_ImageCoord);
        imageView.setY(y_ImageCoord);
//        imageView.setMaxWidth(100);
//        imageView.setMaxHeight(108);

        imageView.setVisibility(View.VISIBLE);
        mainLayout.addView(imageView);
        imageView.setOnTouchListener(new MyTouch());

        presenter.buildSeatsList(imageView);/*THIS SHOULD BE PLACED SEPARATELY*/

    }

    public void addImage(IPresenter presenter, Integer imageID, int w, int h, int x_ImageCoord, int y_ImageCoord, boolean scaleFlag) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(imageID);
        imageView.setContentDescription("img" + getResources().getResourceName(imageID));

//        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setLayoutParams(new ViewGroup.LayoutParams(w, h));// make as pARAMS
        imageView.setX(x_ImageCoord);
        imageView.setY(y_ImageCoord);
//        imageView.setMaxWidth(100);
//        imageView.setMaxHeight(108);

        if (scaleFlag) {
            imageView.setScaleX(1.18f);

            imageView.setScaleY(1.18f);
        }
        imageView.setVisibility(View.VISIBLE);
        mainLayout.addView(imageView);
        imageView.setOnTouchListener(new MyTouch());


        presenter.buildSeatsList(imageView);/*THIS SHOULD BE PLACED SEPARATELY*/
    }

    public void addAllMyImages(IPresenter presenter, int numberIterations, int viewWidth, int viewHeight, int xCoord, int yCoord, int xDistance,float scaleFactor) {/*hardcodings. Could set this as parameters header*/
        int myX = xCoord;
        for (int i = 0; i < numberIterations; i++) {
            addAnImage(presenter,  viewWidth, viewHeight, presenter.getShuffledListOfImages().get(i), myX,yCoord,scaleFactor);
            myX = (int) (myX+viewWidth*scaleFactor+xDistance);
        }
    }
    public ViewGroup setWorkingSpace(float width, float height, int backgroundResource) {
        RelativeLayout containerLayout = new RelativeLayout(this);
        containerLayout.setLayoutParams(new ViewGroup.LayoutParams(round(width), round(height))); // here I would like to ask the picture to know its size
        containerLayout.setBackgroundColor(Color.BLACK);
        containerLayout.setBackgroundResource(backgroundResource);
        return containerLayout;
    }

    /**
     * CHANGED THE DISTANCE CALCULUS now it s about to be set just the distance between images
     */
    public void addAllImages(IPresenter presenter, int numberIterations, int viewWidth, int viewHeight, int xDistance, int yCoord, boolean b) {/*hardcodings. Could set this as parameters header*/
        for (int i = 0; i < numberIterations; i++) {
            addImage(presenter, presenter.getShuffledListOfImages().get(i), viewWidth, viewHeight, x_Coord, yCoord, b);

            x_Coord += xDistance + viewWidth;
        }
    }

    public void addChildToMainLayout(ViewGroup child) {
        mainLayout.addView(child);
    }

    public void setEndPointsLayout(int numberOfIterations, int h, int w, int distance, int y_Coord) {
        int x = 0;
        for (int i = 0; i < numberOfIterations; i++) {
            redLayout = new RelativeLayout(this);
            redLayout.setX(x);
            redLayout.setY(y_Coord);
            redLayout.setBackgroundColor(Color.RED);
            redLayout.setContentDescription(TAG_LINEAR_RED + (i + 1));
            redLayout.setLayoutParams(new ViewGroup.LayoutParams(h, w));//300, 108));
//            redLayout.setOnDragListener(new My2DragListener());
            //       redLayout.setOnDragListener(new Drag(namePresenter.getList(), MAIN_TAG, TAG_LINEAR));

            x += distance;//310;
            redLayoutContainer.add(redLayout);
            mainLayout.addView(redLayout);
        }

    }

    public void setEndPointsLayoutXY(int numberOfIterations, int w, int h, int distance, int yCoord, int xCoord,float scaleFactor) {

        for (int i = 0; i < numberOfIterations; i++) {
            redLayout = new RelativeLayout(this);
            redLayout.setX(xCoord*scaleFactor);
            redLayout.setY(yCoord*scaleFactor);
            redLayout.setBackgroundColor(Color.RED);
            redLayout.setContentDescription(TAG_LINEAR_RED + (i + 1));
            redLayout.setLayoutParams(new ViewGroup.LayoutParams(round(w*scaleFactor), round(h*scaleFactor)));//300, 108));
//            redLayout.setOnDragListener(new My2DragListener());
            //       redLayout.setOnDragListener(new Drag(namePresenter.getList(), MAIN_TAG, TAG_LINEAR));

            xCoord += distance;//310;
            redLayoutContainer.add(redLayout);
            mainLayout.addView(redLayout);
        }

    }

    public String getChosenSolutionString() {
        chosenAnswerString = new String();
        for (int i = 0; i < redLayoutContainer.size(); i++) {
            if (redLayoutContainer.get(i).getChildCount() == 1) {
                chosenAnswerString += stripNonDigits(redLayoutContainer.get(i).getChildAt(0).getContentDescription()) + " ";
            }
        }
        return chosenAnswerString;

    }

    public static String stripNonDigits(final CharSequence input) {
        final StringBuilder sb = new StringBuilder(
                input.length());
        for (int i = 0; i < input.length(); i++) {
            final char c = input.charAt(i);
            if (c > 47 && c < 58) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public boolean testCorrectAnswer() {
        if (getChosenSolutionString().equals(getCorrectSolutionString())) {
            return true;
        } else
            return false;
    }

    public int getViewHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    public int getUnusableScreenSpace() {
        getWindowManager().getDefaultDisplay().getRealMetrics(baseDisplayMetrics); /*for getRealmetrics 1536 for metrics 1440\*/
        return baseDisplayMetrics.heightPixels - getViewHeight();
    }

    public int getResourceWidth(Context context, int resource) {
        return ResourcesCompat.getDrawable(context.getResources(), resource, null).getMinimumWidth();
    }

    public int getResourceHeight(Context context,int resource ){
        return ResourcesCompat.getDrawable(context.getResources(), resource, null).getMinimumHeight();
    }
    public int getViewWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }
    public int getScreenWidthSize(){
        getWindowManager().getDefaultDisplay().getRealMetrics(baseDisplayMetrics);
        return baseDisplayMetrics.widthPixels;
    }
    public int getScreenHeightSize(){

        getWindowManager().getDefaultDisplay().getRealMetrics(baseDisplayMetrics);
        return baseDisplayMetrics.heightPixels;
    }

    public void checkSolution() {


        boolean ok = testCorrectAnswer();
        if (ok) {

            aTextView.setText(R.string.correctAnswerString);
            checkAnswerLinearLayout.setVisibility(View.VISIBLE);
            checkAnswerLinearLayout.setBackgroundColor(Color.GREEN);
        }
        //if (!chosenAnswerString.contains(correctAnswerString)) {
        if (!ok) { // FIXED check solution bug
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