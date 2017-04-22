package catalin.seatbeavers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Catalin on 22/04/2017.
 */

public class BasePresenter {
    ImageRepositoryImpl imageRepository = new ImageRepositoryImpl();
    List<Integer> drawableList = new ArrayList<>();
    void shuffleDrawables(int[] resource, List newList) {
        for (int i = 0; i < resource.length; i++) {
            newList.add(resource[i]);
        }
        Collections.shuffle(newList); /*shuffle image items*/
    }
}
