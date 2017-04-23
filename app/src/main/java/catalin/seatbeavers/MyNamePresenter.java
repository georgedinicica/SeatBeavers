package catalin.seatbeavers;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class MyNamePresenter extends BasePresenter implements IPresenter {
    private List<MyImage> listOfNames = new ArrayList<>();


    MyNamePresenter() {
        shuffleDrawables(imageRepository.getNamesDrawable(), drawableList);
    }

    public List<MyImage> getList() {
        return listOfNames;
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


}
