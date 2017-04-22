package catalin.seatbeavers;

import android.widget.ImageView;

import java.util.List;

/**
 * Created by Catalin on 22/04/2017.
 */

interface IPresenter {
    List<Integer> getShuffledListOfImages();
    void buildSeatsList(ImageView aImageView);
    }
