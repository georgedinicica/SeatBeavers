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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NamesActivity extends AppCompatActivity {
    int[] drawableNames = new int[]{R.drawable.alena, R.drawable.bruno, R.drawable.cyril, R.drawable.dana, R.drawable.eva};
    float x_Coord = 0;
    float y_Coord = 300;
    ViewGroup redLayout, bLayout, yellowLayout, mainLayout;
    RelativeLayout myLayout;
    List<Integer> drawableList = new ArrayList<>();
    private List<Seat> listOfNames = new ArrayList<>();
    LinearLayout linearLayout;
    private static float my_x = 0;
    String MAIN_TAG = "main";
    String TAG_LINEAR_RED = "linear";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setMainLayout(10, 10);

        shuffleDrawables(drawableNames, drawableList);

        setImagesContainerLayout();
        addAllImages(drawableNames.length);
        setContentView(mainLayout);
    }

    private void setMainLayout(int xCoord, int yCoord) {

        mainLayout = new RelativeLayout(this);
        mainLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mainLayout.setY(yCoord);
        mainLayout.setX(xCoord);
        mainLayout.setContentDescription(MAIN_TAG);
        mainLayout.setBackgroundColor(Color.LTGRAY);

    }

    private void setImagesContainerLayout() {
        int y = 0;
        for (int i = 0; i < 5; i++) {
            redLayout = new RelativeLayout(this);
            redLayout.setX(y);
            redLayout.setY(0);
            redLayout.setBackgroundColor(Color.RED);
            redLayout.setContentDescription(TAG_LINEAR_RED);
            redLayout.setLayoutParams(new ViewGroup.LayoutParams(300, 108));
            redLayout.setOnDragListener(new My2DragListener());

            y += 310;
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
        //            imageView.setScaleX(1.05f);
        //   imageView.setScaleY(0.95f);
        x_Coord += 308;
        imageView.setVisibility(View.VISIBLE);
        mainLayout.addView(imageView);
        buildSeatsList(imageView);
        imageView.setOnTouchListener(new MyTouchListener_OLD());

    }


    private void buildSeatsList(ImageView aImageView) {
        listOfNames.add(new Seat(aImageView.getX(), aImageView.getY(), aImageView.getContentDescription(), aImageView.getParent()));

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

    private void shuffleDrawables(int[] resource, List newList) {
        for (int i = 0; i < resource.length; i++) {
            newList.add(resource[i]);
        }
        Collections.shuffle(newList); /*shuffle image items*/
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
                    owner.removeView(draggedImage);
                    draggedImage.setX(0);
                    draggedImage.setY(0);
                    container.addView(draggedImage, 0);
                    draggedImage.setVisibility(View.VISIBLE); /***/

                    return true;
                } else if (container.getChildCount() == 1) { /* Swap images  */
                    if (((ViewGroup) draggedImage.getParent()).getContentDescription().equals(MAIN_TAG)) {
//                        v.setBackgroundColor(Color.BLACK);
                        for (Seat p : listOfNames) {
                            if (p.getMyImageDescription().toString().contains(container.getChildAt(0).getContentDescription())) {
                                x_Coord = p.getX_coord() + 10;
                                y_Coord = p.getY_coord();
                                break;
                            }
                        }
                        owner.removeView(draggedImage);
                        replacingImage.setX(x_Coord);
                        replacingImage.setY(y_Coord);
                        draggedImage.setX(0);
                        draggedImage.setY(0);
                        container.addView(draggedImage);
                        container.removeView(replacingImage);
                        owner.addView(replacingImage);
                        v.setVisibility(View.VISIBLE); /***/
                        draggedImage.setVisibility(View.VISIBLE); /***/
                        replacingImage.setVisibility(View.VISIBLE); /***/
                        return true;
                    } else if (((ViewGroup) draggedImage.getParent()).getContentDescription().toString().contains(TAG_LINEAR_RED)) {

                        container.removeView(replacingImage);  /*SWAP linear*/
                        owner.addView(replacingImage);
                        replacingImage.setX(0); /*hard to get*/
                        replacingImage.setY(0);
                        owner.removeView(draggedImage);
                        container.addView(draggedImage);
                        draggedImage.setX(0);
                        draggedImage.setY(0);
                        v.invalidate();
                        draggedImage.setVisibility(View.VISIBLE); /***/
                        replacingImage.invalidate();
                        draggedImage.invalidate();/**/
                        replacingImage.setVisibility(View.VISIBLE); /***/
                        return true;
                    }
//                if(draggedImage.getParent().toString().contains(MAIN_TAG)) {
//
//                }
//                else if(draggedImage.getParent().toString().contains(TAG_LINEAR_RED)) {
//                /**      this should be modified to satisfy the last requirement( add home)*/
//
//
//
//                }
                }
                v.setVisibility(View.VISIBLE);
            }

            if (event.getAction() == DragEvent.ACTION_DRAG_ENDED) {
                v.setVisibility(View.VISIBLE);
            }

            return true;
        }
    }

    private class MyDragListener implements View.OnDragListener {
        float x_Coord, y_Coord;

        @Override
        public boolean onDrag(View v, DragEvent event) {
            View draggedImage = (View) event.getLocalState();
            switch (event.getAction()) {

                case DragEvent.ACTION_DROP:
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    ViewGroup container = (ViewGroup) v;
                    view.setX(0);
                    view.setY(0);
                    container.addView(view);
//                    if(v.getContentDescription().toString().contains("green")) {
//                        v.setBackgroundColor(Color.BLUE);
//                    }
                    view.setVisibility(View.VISIBLE);

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //  v.invalidate();
                    break;
                default:
                    v.invalidate();
                    break;
            }
            return true;
        }
    }


}

