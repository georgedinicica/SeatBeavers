package catalin.seatbeavers;

import java.util.Collections;
import java.util.List;

/**
 * Created by Catalin on 18/04/2017.
 */

public class ImageRepositoryImpl  {
    int[] drawableNames = new int[]{R.drawable.alena1, R.drawable.bruno2, R.drawable.cyril3, R.drawable.dana4, R.drawable.eva5};

    int[] drawablesSeats = new int[]{
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

    public int[] getSeatsDrawables() {
        return drawablesSeats;
    }


    public int[] getNamesDrawable() {
        return drawableNames;
    }



}
