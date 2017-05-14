package catalin.seatbeavers;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import catalin.seatbeavers.*;
import catalin.seatbeavers.MyImage;

/**
 * Created by Peter on 4. 5. 2017.
 */

public class SK06Presenter extends BasePresenter implements IPresenter {

    private List<MyImage> listOfSK06Names = new ArrayList<>();

    public SK06Presenter() {
        shuffleDrawables(imageRepository.getNamesSK06(),drawableList);
    }

    @Override
    public List<Integer> getShuffledListOfImages() {
        return drawableList;
    }

    @Override
    public void buildSeatsList(ImageView aImageView) {

        listOfSK06Names.add(new MyImage(aImageView.getX(), aImageView.getY(), aImageView.getContentDescription(), aImageView.getParent()));

    }

    @Override
    public int[] getDrawablesList() {
        return imageRepository.getNamesSK06();
    }

    @Override
    public List<catalin.seatbeavers.MyImage> getList() {
        return listOfSK06Names;
    }
}
