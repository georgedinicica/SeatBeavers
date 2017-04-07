package catalin.seatbeavers;

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
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NamesActivity extends AppCompatActivity {
    int[] drawableNames = new int[]{R.drawable.alena1, R.drawable.bruno2, R.drawable.cyril3, R.drawable.dana4, R.drawable.eva5};
    float x_Coord = 0;
    float y_Coord = 300;
    ViewGroup redLayout, mainLayout;
    Button checkSolutionButton;
    List<Integer> drawableList = new ArrayList<>();
    private List<Seat> listOfNames = new ArrayList<>();
    private static float my_x = 0;
    String MAIN_TAG = "main";
    String TAG_LINEAR_RED = "linear";
    List<ViewGroup> redLayoutContainer = new ArrayList<>();
    String aChosenAnswerString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aChosenAnswerString = new String();
        setMainLayout(10, 10);
        shuffleDrawables(drawableNames, drawableList);
        addLayoutComponents();

        setContentView(mainLayout);
    }

    private void addLayoutComponents() {
        addSolutionButton();
        addRestartButton();
        setImagesContainerLayout();
        addAllImages(drawableNames.length);
    }

    private void addRestartButton() {
        Button aButton = new Button(this);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        params.addRule(RelativeLayout.BELOW, checkSolutionButton.getId());/** here*#*/
        params.setMargins(20, 20, 20, 20);
        aButton.setText(R.string.restartButtonString);
        aButton.setBackgroundResource(R.drawable.buttonstyle);
        aButton.setLayoutParams(params);
        mainLayout.addView(aButton);
        aButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
    }

    private void addSolutionButton() {

        checkSolutionButton = new Button(this);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        params.setMarginEnd(20);
        checkSolutionButton.setId(R.id.checkSolutionButton);/*HAD TO SET THIS HERE SO UPTOP *# ITFINDS THE RESOURECE*/
        checkSolutionButton.setText(R.string.test);
        checkSolutionButton.setContentDescription("checkSolution");
        checkSolutionButton.setBackgroundResource(R.drawable.buttonstyle);
        checkSolutionButton.setLayoutParams(params);
        mainLayout.addView(checkSolutionButton);
        checkSolutionButton.setOnClickListener(new MyClickListener());
    }

    private void setMainLayout(int xCoord, int yCoord) {

        mainLayout = new RelativeLayout(this);
        mainLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mainLayout.setY(yCoord);
        mainLayout.setX(xCoord);
        mainLayout.setContentDescription(MAIN_TAG);
        mainLayout.setBackgroundColor(Color.LTGRAY);
        mainLayout.setOnDragListener(new My2DragListener());
    }

    private void setImagesContainerLayout() {
        int y = 0;
        for (int i = 0; i < 5; i++) {
            redLayout = new RelativeLayout(this);
            redLayout.setX(y);
            redLayout.setY(0);
            redLayout.setBackgroundColor(Color.RED);
            redLayout.setContentDescription(TAG_LINEAR_RED + (i + 1));
            redLayout.setLayoutParams(new ViewGroup.LayoutParams(300, 108));
            redLayout.setOnDragListener(new My2DragListener());
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

    private void buildSeatsList(ImageView aImageView) {
        listOfNames.add(new Seat(aImageView.getX(), aImageView.getY(), aImageView.getContentDescription(), aImageView.getParent()));
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

    private class MyTouchListener_OLD implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
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

    private class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
//            PlaneActivity.toast(getApplicationContext(),"test");
            checkSolution();

        }
    }
    private String createSolutionString(){
return "";
    }

    private void checkSolution() {
        aChosenAnswerString = new String();
        for (int i = 0; i < redLayoutContainer.size(); i++) {
            if (redLayoutContainer.get(i).getChildCount() == 1) {
                aChosenAnswerString += " " + PlaneActivity.stripNonDigits(redLayoutContainer.get(i).getChildAt(0).getContentDescription());
            }
        }
        PlaneActivity.toast(getApplicationContext(), " filled test" + aChosenAnswerString);
//
//        if(redLayoutContainer.get(0).getChildCount()==0){
//            PlaneActivity.toast(getApplicationContext(), redLayoutContainer.get(0).getContentDescription().toString());
//        }
//        if(redLayoutContainer.get(0).getChildCount()==1){
//            PlaneActivity.toast(getApplicationContext()," filled test");
//
//        }

    }

    private class My2DragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            float x_Coord = 0, y_Coord = 0;
            View draggedImage = (View) event.getLocalState();
//            if(DragEvent.ACTION_DRAG_STARTED==event.getAction()){
//                v.setBackgroundColor(Color.GREEN);
//               // aTextView.setText("");
//              //  checkAnswerLinearLayout.setVisibility(View.INVISIBLE);
//                draggedImage.setVisibility(View.INVISIBLE);
//            }
//            if(DragEvent.ACTION_DRAG_ENDED==event.getAction()){
//                v.setBackgroundColor(getResources().getColor(R.color.colorBright));
//                draggedImage.setVisibility(View.VISIBLE);
//
//            }
//            if(DragEvent.ACTION_DRAG_ENTERED==event.getAction()){
//                v.setBackgroundColor(Color.BLUE);
//            }
//            if(DragEvent.ACTION_DRAG_EXITED==event.getAction()){
//                v.setBackgroundColor(Color.GREEN);
////                      checkAnswerLinearLayout.setVisibility(View.VISIBLE);
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
                    for (Seat p : listOfNames) {
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
                    draggedImage.invalidate();/**/
                    return true;
                }
                v.setVisibility(View.VISIBLE);
                if (!v.getContentDescription().toString().contains(TAG_LINEAR_RED)) {
                    for (Seat s : listOfNames) {
                        if (s.getMyImageDescription() == draggedImage.getContentDescription()) {
                            x_Coord = s.getX_coord();
                            y_Coord = (s.getY_coord());
                            break;
                        }
                    }
                    //redLayout.setBackgroundColor(Color.BLACK);
                    viewDragWork(owner, container, draggedImage, x_Coord, y_Coord);
                    return true;
                }
            }
            if (event.getAction() == DragEvent.ACTION_DRAG_ENDED) {
                v.setVisibility(View.VISIBLE);
            }
            return true;
        }
    }


}

