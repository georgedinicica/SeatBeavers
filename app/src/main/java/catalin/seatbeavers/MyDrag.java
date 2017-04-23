package catalin.seatbeavers;


import android.graphics.Color;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class MyDrag implements View.OnDragListener {


    private BaseActivity baseActivity;
    private List<MyImage> listOfNames;
    String MAIN_TAG = "main";
    String TAG_LINEAR_RED = "linear";


    public MyDrag(BaseActivity baseActivity, List<MyImage> listOfNames, String MAIN_TAG, String TAG_LINEAR_RED) {
        this.baseActivity = baseActivity;
        this.listOfNames = listOfNames;
        this.MAIN_TAG = MAIN_TAG;
        this.TAG_LINEAR_RED = TAG_LINEAR_RED;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        float x_Coord = 0, y_Coord = 0;
        final View draggedImage = (View) event.getLocalState();
        int tempColor=v.getSolidColor();
        if (DragEvent.ACTION_DRAG_STARTED == event.getAction()) {
            baseActivity.resetSolutionLayout();
            draggedImage.setVisibility(View.INVISIBLE);
        }

        if (DragEvent.ACTION_DRAG_ENTERED == event.getAction()) {
            if (v.getContentDescription().toString().contains(TAG_LINEAR_RED))
                v.setBackgroundColor(Color.BLUE);

        }
        if (DragEvent.ACTION_DRAG_EXITED == event.getAction()) {
            if (!v.getContentDescription().toString().contains(TAG_LINEAR_RED))
                v.setBackgroundColor(Color.TRANSPARENT);
            if (v.getContentDescription().toString().contains(TAG_LINEAR_RED))
                v.setBackgroundColor(Color.RED);
        }
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
            if (!v.getContentDescription().toString().contains(TAG_LINEAR_RED)) {
//                for (int i = 0; i < listOfNames.size(); i++) {
//                    if (listOfNames.get(i).getMyImageDescription() == draggedImage.getContentDescription()) {
//                        x_Coord = listOfNames.get(i).getX_coord();
//                        y_Coord = listOfNames.get(i).getY_coord();
//                        break;
//                    }
//                }
                    for (MyImage s : listOfNames) {
                        if (s.getMyImageDescription() == draggedImage.getContentDescription()) {
                            x_Coord = s.getX_coord();
                            y_Coord = (s.getY_coord());
                            break;
                        }
                    }
                viewDragWork(owner, container, draggedImage, x_Coord, y_Coord);
                v.setBackgroundColor(tempColor);
                //  redLayout.setBackgroundColor(Color.BLACK);
                return true;
            }
        }
        if (event.getAction() == DragEvent.ACTION_DRAG_ENDED) {

            draggedImage.post(new Runnable() {
                public void run() {
                    draggedImage.setVisibility(View.VISIBLE);
                }

            });
            if (v.getContentDescription().toString().contains(TAG_LINEAR_RED))
            {
                v.setBackgroundColor(Color.RED);
            }
        }
        draggedImage.invalidate();
        v.invalidate();
        return true;
    }

    private void viewDragWork(ViewGroup owner, ViewGroup container, View image, float x, float y) {/*use this method to set the view around */
        owner.removeView(image);
        image.setX(x);
        image.setY(y);
        container.addView(image);
    }
}


