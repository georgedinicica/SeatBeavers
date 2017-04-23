package catalin.seatbeavers;

import android.widget.ImageView;

import java.util.List;



interface IPresenter {
    List<Integer> getShuffledListOfImages();
    void buildSeatsList(ImageView aImageView);
    int[] getDrawablesList();
    List<MyImage> getList();

}
