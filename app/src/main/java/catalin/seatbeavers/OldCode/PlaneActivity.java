package catalin.seatbeavers.OldCode;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import catalin.seatbeavers.OldCode.MyImage;
import catalin.seatbeavers.R;

public class PlaneActivity extends AppCompatActivity {
    /*catalin*/
    int[] linearLayoutRedIDs = new int[]{R.id.linearred1, R.id.linearred2, R.id.linearred3, R.id.linearred4, R.id.linearred5, R.id.linearred6,
            R.id.linearred7, R.id.linearred8, R.id.linearred9, R.id.linearred10, R.id.linearred11, R.id.linearred12,
            R.id.linearred13, R.id.linearred14, R.id.linearred15, R.id.linearred16, R.id.linearred17, R.id.linearred18};
    int[] imageIDs = new int[]{R.id.imag1, R.id.imag2, R.id.imag3, R.id.imag4, R.id.imag5, R.id.imag6, R.id.imag7, R.id.imag8, R.id.imag9,
            R.id.imag10, R.id.imag11, R.id.imag12, R.id.imag13, R.id.imag14, R.id.imag15, R.id.imag16, R.id.imag17, R.id.imag18};
    int[] drawables = new int[]{
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8,
            R.drawable.img9,
            R.drawable.img10,
            R.drawable.img11,
            R.drawable.img12,
            R.drawable.img13,
            R.drawable.img14,
            R.drawable.img15,
            R.drawable.img16,
            R.drawable.img17,
            R.drawable.img18
    };

    int numberOfSeats;
    int miniumNumberOfSeats = 5;
    int maximumNumberOfSeats = 13;
    String updatedAnswerString;
    String correctAnswerString = new String();
    String chosenAnswerString = new String();
    ImageView aImageView;
    TextView aTextView;
    List<Integer> drawableList = new ArrayList<>();
    LinearLayout checkAnswerLinearLayout, redLayout;
    List<MyImage> listOfSeats = new ArrayList<>();
    int x_coord_Position = 0, y_coord_Position = 662; // coordinates used to place images Programatically

    //k
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plane);
        //y_coord_Position=findViewById(R.id.activity_plane).getHeight()/2;
        initialise();/* sets the Number of Plane Seats and the Red Check Answer Rectangle*/
        shuffleDrawables(); /*shuffle image items*/
        imagesLayoutComponent(x_coord_Position, y_coord_Position);/* connects the image Resources @drawables with the Blue layout*/
        addDragDropComponent();/*sets  the listeners */
        saveCorrectOrderOfImages();/*creates the correct answer string*/

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        toast(this, "Activity ended");
    }

    public void recreateActivity(View view) {
        this.recreate();
        toast(this, "Activity recreated");
    }

    public void checkSolution(View view) {
        updatedAnswerString = new String(); /**this line initialises the string creation at every check */
        chosenAnswerString = createSolutionString();
        if (chosenAnswerString.contains(correctAnswerString)) {
            aTextView.setText(R.string.correctAnswerString);
            aTextView.setVisibility(View.VISIBLE);
            checkAnswerLinearLayout.setBackgroundColor(Color.GREEN);
            checkAnswerLinearLayout.setVisibility(View.VISIBLE);
        } else {
            aTextView.setText("You have not answered CORRECTLY");
            aTextView.setVisibility(View.VISIBLE);
            checkAnswerLinearLayout.setBackgroundColor(Color.RED);
            checkAnswerLinearLayout.setVisibility(View.VISIBLE);

        }
    }

    private int generateMyRandom(int minNumber, int maxNumber) {/*Generate a random number between 5-18 */
        return minNumber + (int) (Math.ceil(Math.random() * maxNumber));
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

    private void initialise() {
        checkAnswerLinearLayout = (LinearLayout) findViewById(R.id.testAnswerLayout);
        aTextView = (TextView) findViewById(R.id.textView);
        aTextView.setVisibility(View.INVISIBLE);
        numberOfSeats = generateMyRandom(miniumNumberOfSeats, maximumNumberOfSeats);


    }

    private void shuffleDrawables() {
        for (int i = 0; i < drawables.length; i++) {
            drawableList.add(drawables[i]);
        }
        Collections.shuffle(drawableList); /*shuffle image items*/
    }

    private void imagesLayoutComponent(int x, int y) {
        int x_coord = x, y_coord = y;
        findViewById(R.id.activity_plane).setOnDragListener(new MyDragListener()); /*sets the Customized Drag listener For the main activity*/

        for (int i = 0; i < numberOfSeats; i++) {
            /* the loop places the images on the layout*/

            aImageView = (ImageView) findViewById(imageIDs[i]);
            aImageView.setImageResource(drawableList.get(i));
            aImageView.setContentDescription("img" + stripNonDigits(getResources().getResourceName(drawableList.get(i))));
            aImageView.setX(x_coord);
            aImageView.setY(y_coord);
            x_coord += 114;
            aImageView.setVisibility(View.VISIBLE);

            aImageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    Point p = new Point(1, 1);
                    Point s = new Point(44, 44);
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    shadowBuilder.onProvideShadowMetrics(s, p);

                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            view.startDragAndDrop(data, shadowBuilder, view, 0);
                            view.setDuplicateParentStateEnabled(true);

                        } else {
                            view.startDrag(data, shadowBuilder, view, 0);
                        }
                        return true;
                    }

                    view.invalidate();
                    return false;
                }
            });
            buildSeatsList(aImageView);
        }
    }

    private void buildSeatsList(View aImageView) {
        listOfSeats.add(new MyImage(aImageView.getX(), aImageView.getY(), aImageView.getContentDescription(), aImageView.getParent()));
    }

    private void addDragDropComponent() {
        for (int i = 0; i < numberOfSeats; i++) {
            findViewById(linearLayoutRedIDs[i]).setVisibility(View.VISIBLE);
            findViewById(linearLayoutRedIDs[i]).setOnDragListener(new View.OnDragListener() {
                float x_Coord, y_Coord;

                @Override
                public boolean onDrag(View v, DragEvent event) {
                    View draggedImage = (View) event.getLocalState();

                    if (DragEvent.ACTION_DRAG_STARTED == event.getAction()) {
                        v.setBackgroundColor(Color.GREEN);
                        aTextView.setText("");
                        checkAnswerLinearLayout.setVisibility(View.INVISIBLE);
                        draggedImage.setVisibility(View.INVISIBLE);
                    }
                    if (DragEvent.ACTION_DRAG_ENDED == event.getAction()) {
                        v.setBackgroundColor(getResources().getColor(R.color.colorBright));
                        draggedImage.setVisibility(View.VISIBLE);

                    }
                    if (DragEvent.ACTION_DRAG_ENTERED == event.getAction()) {
                        v.setBackgroundColor(Color.BLUE);
                    }
                    if (DragEvent.ACTION_DRAG_EXITED == event.getAction()) {
                        v.setBackgroundColor(Color.GREEN);
//                      checkAnswerLinearLayout.setVisibility(View.VISIBLE);
                    }
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
                            if (draggedImage.getParent().toString().contains("activity_plane")) {
                                for (MyImage p : listOfSeats) {
                                    if (p.getMyImageDescription() == container.getChildAt(0).getContentDescription()) {
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
                            } else if (draggedImage.getParent().toString().contains("linear")) {
/**      this should be modified to satisfy the last requirement( add home)*/

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
                        }
                        v.setVisibility(View.VISIBLE);
                    }
                    if (event.getAction() == DragEvent.ACTION_DRAG_ENDED) {
                        v.setVisibility(View.VISIBLE);
                    }

                    return true;
                }
            });

        }
    }

    private void saveCorrectOrderOfImages() {
        String s;
        int imgNumberConverted;
        for (int i = 0; i < numberOfSeats; i++) {
            s = stripNonDigits(getResources().getResourceName(drawableList.get(i))); /*show the name of the image*/
            //s=stripNonDigits(getResources().getResourceName(drawableList.get(i)).toString()); //show the name of the image
            //    s=stripNonDigits(s);
            //correctAnswerList.add(s);
            imgNumberConverted = Integer.parseInt(s);
            if (imgNumberConverted >= 13 && imgNumberConverted <= 18) {
                correctAnswerString = correctAnswerString + " " + s;
            }
        }
        for (int i = numberOfSeats - 1; i >= 0; i--) {
            s = stripNonDigits(getResources().getResourceName(drawableList.get(i))); /** II 4L-6R   7-12*/
            imgNumberConverted = Integer.parseInt(s);
            if (imgNumberConverted >= 7 && imgNumberConverted <= 12) {
                correctAnswerString = correctAnswerString + " " + s;
            }
        }
        for (int i = 0; i < numberOfSeats; i++) {
            s = stripNonDigits(getResources().getResourceName(drawableList.get(i))); /** III 1L-3R  1-6*/
            imgNumberConverted = Integer.parseInt(s);
            if (imgNumberConverted >= 1 && imgNumberConverted <= 6) {
                correctAnswerString = correctAnswerString + " " + s;
            }
        }
    }

    private String createSolutionString() {
        for (int i = 0; i < numberOfSeats; i++) {
            redLayout = (LinearLayout) findViewById(linearLayoutRedIDs[i]);
            if (redLayout.getChildCount() > 0) {
                String s = stripNonDigits(redLayout.getChildAt(0).getContentDescription());
                updatedAnswerString = updatedAnswerString + " " + s;
            }
        }
        return updatedAnswerString;
    }

    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
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

    private class MyDragListener implements View.OnDragListener {
        float x_Coord, y_Coord;

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
//                      v.setBackgroundColor(targetShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DRAG_LOCATION:
                    break;
                case DragEvent.ACTION_DROP:  // Dropped, reassign View to ViewGroup
                    if (DragEvent.ACTION_DROP == event.getAction()) {
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        ViewGroup container = (ViewGroup) v;

                        for (MyImage s : listOfSeats) {
                            if (s.getMyImageDescription() == view.getContentDescription()) {
                                x_Coord = s.getX_coord();
                                y_Coord = (s.getY_coord());
                                break;
                            }
                        }
                        view.setX(x_Coord);
                        view.setY(y_Coord);
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                        break;

                    }
//                    break;
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
