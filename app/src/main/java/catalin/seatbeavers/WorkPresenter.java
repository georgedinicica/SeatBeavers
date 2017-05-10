package catalin.seatbeavers;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 04/05/2017.
 */

public class WorkPresenter extends BasePresenter implements IPresenter {
    private List<MyImage> listOfNames = new ArrayList<>();

    public WorkPresenter() {
        shuffleDrawables(imageRepository.getSeatsDrawables(), drawableList);
    }

    @Override
    public List<Integer> getShuffledListOfImages() {
        return drawableList;
    }


    @Override
    public void buildSeatsList(ImageView aImageView) {
        listOfNames.add(new MyImage(aImageView.getX(), aImageView.getY(), aImageView.getContentDescription(), aImageView.getParent()));

    }

    @Override
    public int[] getDrawablesList() {
       return imageRepository.getNamesDrawable();
    }

    @Override
    public List<MyImage> getList() {
        return listOfNames;
    }
}
