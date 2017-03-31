package catalin.seatbeavers;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.LayoutInflater;
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
int[] drawableNames=new int[]{R.drawable.alena,R.drawable.bruno,R.drawable.cyril,R.drawable.dana,R.drawable.eva};
    float x_Coord=0;float y_Coord=200;
    ViewGroup alayout,bLayout;
    List<Integer> drawableList = new ArrayList<>();
    private List<Seat> listOfNames=new ArrayList<>();
LinearLayout linearLayout;
    private static float my_x=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shuffleDrawables(drawableNames,drawableList);
setAlayout();
        addAllImages();
//        LayoutInflater li=getLayoutInflater();
//        li.inflate(R.layout.activity_names, (ViewGroup) alayout, false);
//
////        linearLayout=(LinearLayout) findViewById(R.id.linearName);
////        linearLayout.setVisibility(View.VISIBLE);
////        View layout2 = LayoutInflater.from(this).inflate(R.layout.activity_names, linearLayout, false);
//      ;
//        //addImage();

//     linearLayout.addView(layout2);
        //   findViewById(R.id.linearName).setOnDragListener(new MyDragListener());
    }

    private void setAlayout() {
        ViewGroup v=new LinearLayout(this);
        v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        v.setBackgroundColor(Color.YELLOW);
        ViewGroup w=new RelativeLayout(this);
        w.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100));
        w.setBackgroundColor(Color.GREEN);
        w.setId(5);
        alayout= new RelativeLayout(this);
        alayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                350));
        alayout.setBackgroundColor(Color.RED);
        alayout.addView(w);

        v.addView(alayout);
        //alayout.addView(findViewById(R.id.linearNames));
        setContentView(v);
        w.setOnDragListener(new MyDragListener());
        //
        //setContentView(R.layout.activity_names);
//        bLayout=new LinearLayout(this);
//        bLayout.setBackgroundColor(Color.GREEN);
//        bLayout.setLayoutParams(new ViewGroup.LayoutParams(400,300));
//        setContentView(bLayout);
    }

    private void addAllImages() {
        for(int i=0;i<drawableNames.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(drawableList.get(i));
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setX(x_Coord);
            imageView.setY(y_Coord);
            imageView.setScaleX(0.4f);
            imageView.setMaxWidth(7);
            imageView.setMaxHeight(30);
            imageView.setScaleY(0.4f);
            x_Coord+=150;
            imageView.setVisibility(View.VISIBLE);
            alayout.addView(imageView);
            buildSeatsList(imageView);
            imageView.setOnTouchListener(new MyTouchListener_OLD());
        }
    }

    private void buildSeatsList(ImageView aImageView) {
        listOfNames.add(new Seat(aImageView.getX(),aImageView.getY(),aImageView.getContentDescription(),aImageView.getParent()));

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

    private void shuffleDrawables(int[]resource,List newList) {
        for (int i = 0; i < resource.length; i++) {
            newList.add(resource[i]);
        }
        Collections.shuffle(newList); /*shuffle image items*/
    }


    private class MyDragListener implements View.OnDragListener {
        float x_Coord,y_Coord;
        @Override
        public boolean onDrag(View v, DragEvent event) {
            View draggedImage = (View) event.getLocalState();
            switch (event.getAction()) {

                case DragEvent.ACTION_DROP:
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    ViewGroup container = (ViewGroup) v;
                view.setX(my_x+=50);
                    view.setY(0);
                                 container.addView(view);

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

