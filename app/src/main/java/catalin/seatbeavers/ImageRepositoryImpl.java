package catalin.seatbeavers;

/**
 * Created by Catalin on 18/04/2017.
 */

public class ImageRepositoryImpl implements ImageRepository {
    int[] drawableNames = new int[]{R.drawable.alena1, R.drawable.bruno2, R.drawable.cyril3, R.drawable.dana4, R.drawable.eva5};

    @Override
    public int[] getDrawableNames() {
        return drawableNames;
    }
}
