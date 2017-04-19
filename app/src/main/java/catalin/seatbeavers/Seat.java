package catalin.seatbeavers;

import android.view.ViewParent;


class Seat {
    String resourceName;
    private float x_coord;
    private float y_coord;
    private CharSequence description;
    ViewParent aviewParent;

    public Seat(float x_coord, float y_coord, CharSequence description, ViewParent aviewParent) {
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.description = description;
        this.aviewParent = aviewParent;
    }

    public ViewParent getAviewParent() {
        return aviewParent;
    }

    float getX_coord() {
        return x_coord;
    }


    float getY_coord() {
        return y_coord;
    }

    CharSequence getMyImageDescription() {
        return description;
    }
}

