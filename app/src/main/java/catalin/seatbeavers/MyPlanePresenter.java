package catalin.seatbeavers;

import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 22/04/2017.
 */

public class MyPlanePresenter extends BasePresenter implements IPresenter {

    private List<MyImage> listOfSeats=new ArrayList<>();

    MyPlanePresenter() {
        shuffleDrawables(imageRepository.getSeatsDrawables(), drawableList);
    }

    public List<MyImage> getListOfSeats() {
        return listOfSeats;
    }

    @Override
    public List<Integer> getShuffledListOfImages() {
        return drawableList;
    }

    @Override
    public void buildSeatsList(ImageView aImageView) {
        listOfSeats.add(new MyImage(aImageView.getX(), aImageView.getY(), aImageView.getContentDescription(), aImageView.getParent()));

    }
    public int generateMyRandom(int minNumber, int maxNumber) {/*Generate a random number between 5-18 */
        return minNumber + (int) (Math.ceil(Math.random() * maxNumber));
    }


}
