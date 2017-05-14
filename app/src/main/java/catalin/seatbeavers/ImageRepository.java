package catalin.seatbeavers;

/**
 * Created by Catalin on 18/04/2017.
 */

public class ImageRepository {
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

    int[] drawableSK06 = new int [] {R.drawable.sk06_cintorin1,R.drawable.sk06_ihrisko2,R.drawable.sk06_kaviaren3,R.drawable.sk06_pekaren4,R.drawable.sk06_strom5,R.drawable.sk06_susedka6};

    public int[] getSeatsDrawables() {
        return drawablesSeats;
    }


    public int[] getNamesDrawable() {
        return drawableNames;
    }

    public int[] getNamesSK06() { return drawableSK06; }



}
