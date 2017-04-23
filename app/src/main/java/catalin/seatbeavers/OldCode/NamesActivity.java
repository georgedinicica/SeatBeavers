package catalin.seatbeavers.OldCode;

import android.content.ClipData;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import catalin.seatbeavers.OldCode.MyImage;
import catalin.seatbeavers.R;

public class NamesActivity extends AppCompatActivity {
    int[] drawableNames = new int[]{R.drawable.alena1, R.drawable.bruno2, R.drawable.cyril3, R.drawable.dana4, R.drawable.eva5};
    float x_Coord = 0;
    float y_Coord = 300;
    ViewGroup redLayout, mainLayout;
    Button aCheckButton;
    Button aRestartButton;

    TextView aTextView;
    LinearLayout checkAnswerLinearLayout;
    List<Integer> drawableList = new ArrayList<>();
    List<MyImage> listOfNames = new ArrayList<>();
    String MAIN_TAG = "main";
    String TAG_LINEAR_RED = "linear";
    List<ViewGroup> redLayoutContainer = new ArrayList<>();
    String aChosenAnswerString;

    @Override /*EACH SUB CLASS ACtivity has its own OnCreate*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aChosenAnswerString = new String();
        setMainLayout(10, 10);
        shuffleDrawables(drawableNames, drawableList);

        addLayoutComponents();

        setContentView(mainLayout);
    }

    /*This is in the base class*/
    private void addLayoutComponents() {
        checkSolutionBtn();
        addRestartButton();
        checkSolutionView();

        addAllImages(drawableNames.length);
        setImagesContainerLayout();
    }

    /*This is in the base class*/
    private void checkSolutionView() {
        checkSolutionTextView();
        checkSolutionLayout();
    }

    /*This is in the base class*/
    private void checkSolutionLayout() {
        checkAnswerLinearLayout = new LinearLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 75);
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        params.addRule(RelativeLayout.BELOW, aTextView.getId());/** here*#*/
        params.setMargins(20, 20, 20, 20);
        checkAnswerLinearLayout.setLayoutParams(params);
        checkAnswerLinearLayout.setVisibility(View.INVISIBLE);
        checkAnswerLinearLayout.setBackgroundColor(Color.GREEN);
        mainLayout.addView(checkAnswerLinearLayout);
    }

    /*This is in the base class*/
    private void checkSolutionTextView() {
        aTextView = new TextView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        params.addRule(RelativeLayout.BELOW, aRestartButton.getId());/** here*#*/
        params.setMargins(20, 20, 20, 20);
        aTextView.setTextSize(18);
        aTextView.setLayoutParams(params);
        aTextView.setId(R.id.aTextView);
        mainLayout.addView(aTextView);
    }

    /*This is in the base class*/
    private void addRestartButton() {
        aRestartButton = new Button(this);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        params.addRule(RelativeLayout.BELOW, aCheckButton.getId());/** here*#*/
        params.setMargins(20, 20, 20, 20);
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

    /*This is in the base class*/
    private void checkSolutionBtn() {

        aCheckButton = new Button(this);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        params.setMarginEnd(20);
        aCheckButton.setId(R.id.checkSolutionButton);/*HAD TO SET THIS HERE SO UPTOP *# ITFINDS THE RESOURECE*/
        aCheckButton.setText(R.string.test);
        aCheckButton.setContentDescription("checkSolution");
        aCheckButton.setBackgroundResource(R.drawable.buttonstyle);
        aCheckButton.setLayoutParams(params);
        mainLayout.addView(aCheckButton);
        aCheckButton.setOnClickListener(new MyClickListener());
    }

    /*This is in the base class*/
    private void setMainLayout(int xCoord, int yCoord) {

        mainLayout = new RelativeLayout(this);
        mainLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mainLayout.setY(yCoord);
        mainLayout.setX(xCoord);
        mainLayout.setContentDescription(MAIN_TAG);
        mainLayout.setBackgroundColor(Color.LTGRAY);

        doTheDrag(listOfNames);

    }
        /*onClickListener*/
    private void doTheDrag(List<MyImage> listOfNames) {
//        mainLayout.setOnDragListener(new My2DragListener());

//        mainLayout.setOnDragListener(new MyDrag(listOfNames, MAIN_TAG, TAG_LINEAR_RED));
    }
    /*This is in the base class*/
    private void setImagesContainerLayout() {
        int y = 0;
        for (int i = 0; i < 5; i++) {
            redLayout = new RelativeLayout(this);
            redLayout.setX(y);
            redLayout.setY(0);
            redLayout.setBackgroundColor(Color.RED);
            redLayout.setContentDescription(TAG_LINEAR_RED + (i + 1));
            redLayout.setLayoutParams(new ViewGroup.LayoutParams(300, 108));
//            redLayout.setOnDragListener(new My2DragListener());
           // redLayout.setOnDragListener(new MyDrag(listOfNames, MAIN_TAG, TAG_LINEAR_RED));

            y += 310;
            redLayoutContainer.add(redLayout);
            mainLayout.addView(redLayout);
        }

    }

    private void addAllImages(int numberIterations) {/*hardcodings. Could set this as parameters header*/
        for (int i = 0; i < numberIterations; i++) {
            addImage(drawableList.get(i));
        }
    }

    private void addImage(Integer anInteger) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(anInteger);
        imageView.setContentDescription(getResources().getResourceName(anInteger));

        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setX(x_Coord);
        imageView.setY(y_Coord);
        imageView.setMaxWidth(300);
        imageView.setMaxHeight(108);

        imageView.setVisibility(View.VISIBLE);
        x_Coord += 308;

        mainLayout.addView(imageView);
        buildSeatsList(imageView);
        imageView.setOnTouchListener(new MyTouchListener_OLD());
    }

    /*This creates the a List of Images*/
    private void buildSeatsList(ImageView aImageView) {
        listOfNames.add(new MyImage(aImageView.getX(), aImageView.getY(), aImageView.getContentDescription(), aImageView.getParent()));
    }

    private void shuffleDrawables(int[] resource, List newList) {
        for (int i = 0; i < resource.length; i++) {
            newList.add(resource[i]);
        }
        Collections.shuffle(newList); /*shuffle image items*/
    }

    private void viewDragWork(ViewGroup owner, ViewGroup container, View image, float x, float y) {/*use this method to set the view around */
        owner.removeView(image);
        image.setX(x);
        image.setY(y);
        container.addView(image);
    }

    public class MyTouchListener_OLD implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                resetSolutionLayout(aTextView, checkAnswerLinearLayout);
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    view.startDragAndDrop(data, shadowBuilder, view, 0);
                    //view.invalidate();
                    //  view.setVisibility(View.VISIBLE);
                } else {
                    ///  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    view.startDrag(data, shadowBuilder, view, 0);
                    //  buildSeatsList(view);
                }
            }
            //    view.setVisibility(View.VISIBLE);
            //  view.invalidate();
            //view.setBackgroundColor(Color.RED);
            return true;
        }

    }


    private void resetSolutionLayout(TextView textView, LinearLayout linearLayout) {
        textView.setText("");
        linearLayout.setVisibility(View.INVISIBLE);
    }

    private class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
//            PlaneActivity.toast(getApplicationContext(),"test");
            checkSolution();
//            PlaneActivity.toast(getApplicationContext(), listOfNames.get(0).getMyImageDescription().toString());
        }

    }

    private String createSolutionString() {
        return "1 2 3 4 5";
    }

    private void checkSolution() {
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
        for (int i = 0; i < redLayoutContainer.size(); i++) {
            if (redLayoutContainer.get(i).getChildCount() == 1) {
                //aChosenAnswerString += " " + stripNonDigits(redLayoutContainer.get(i).getChildAt(0).getContentDescription());
//                aChosenAnswerString="cha"
            }
        }
    }


    public class My2DragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            float x_Coord = 0, y_Coord = 0;
            View draggedImage = (View) event.getLocalState();
            if (DragEvent.ACTION_DRAG_STARTED == event.getAction()) {
                //resetSolutionLayout(); //clears textView and hides CheckSolutionLayout
                draggedImage.setVisibility(View.INVISIBLE);
            }
            if (DragEvent.ACTION_DRAG_ENDED == event.getAction()) {

                draggedImage.setVisibility(View.VISIBLE);
//
            }
//            if(DragEvent.ACTION_DRAG_ENTERED==event.getAction()){
//                v.setBackgroundColor(Color.BLUE);
//            }
//            if(DragEvent.ACTION_DRAG_EXITED==event.getAction()){
//                v.setBackgroundColor(Color.GREEN);
            // checkAnswerLinearLayout.setVisibility(View.VISIBLE);
//            }
            if (DragEvent.ACTION_DROP == event.getAction()) {

                ViewGroup owner = (ViewGroup) draggedImage.getParent();
                ViewGroup container = (ViewGroup) v;
                View replacingImage = container.getChildAt(0);

                if (container.getChildCount() == 0) { /*add new image in empty place*/
                    viewDragWork(owner, container, draggedImage, 0, 0);/***/
                    return true;
                }
                if (container.getChildCount() == 1 && ((ViewGroup) draggedImage.getParent()).getContentDescription().equals(MAIN_TAG)) {
                    for (MyImage p : listOfNames) {
                        if (p.getMyImageDescription().toString().contains(container.getChildAt(0).getContentDescription())) {
                            x_Coord = p.getX_coord() + 10;
                            y_Coord = p.getY_coord();
                            break;
                        }
                    }

                    viewDragWork(owner, container, draggedImage, 0, 0);
                    viewDragWork(container, owner, replacingImage, x_Coord, y_Coord);
                    return true;
                }
                if (container.getChildCount() == 1 && ((ViewGroup) draggedImage.getParent()).getContentDescription().toString().contains(TAG_LINEAR_RED)) {
                    viewDragWork(container, owner, replacingImage, 0, 0);
                    viewDragWork(owner, container, draggedImage, 0, 0);
                    v.invalidate();
                    replacingImage.invalidate();
                    draggedImage.invalidate();
                    return true;
                }
//                v.setVisibility(View.VISIBLE);
                if (!v.getContentDescription().toString().contains(TAG_LINEAR_RED)) {
                    for (int i = 0; i < listOfNames.size(); i++) {

                        if (listOfNames.get(i).getMyImageDescription() == draggedImage.getContentDescription()) {
                            x_Coord = listOfNames.get(i).getX_coord();
                            y_Coord = listOfNames.get(i).getY_coord();
                            break;

                        }

                    }
//                    for (Seat s : listOfNames) {
//                        if (s.getMyImageDescription() == draggedImage.getContentDescription()) {
//                            x_Coord = s.getX_coord();
//                            y_Coord = (s.getY_coord());
//                            break;
//                        }
//                    }
                    viewDragWork(owner, container, draggedImage, x_Coord, y_Coord);
                    //  redLayout.setBackgroundColor(Color.BLACK);
                    //return true;
                }
            }
            if (event.getAction() == DragEvent.ACTION_DRAG_ENDED) {
                v.setVisibility(View.VISIBLE);
            }
            v.invalidate();
            return true;
        }

    }


}
