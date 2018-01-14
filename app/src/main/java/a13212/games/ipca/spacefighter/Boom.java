package a13212.games.ipca.spacefighter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by andre on 21/11/2017.
 */

public class Boom {
    private Bitmap bitmap;
    private int x;
    private int y;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Boom(Context context) {

        bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.boom);

        x=-1000;
        y=-1000;
    }
}
