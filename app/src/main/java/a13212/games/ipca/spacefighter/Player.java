package a13212.games.ipca.spacefighter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Created by andre on 13/11/2017.
 */

public class Player {
    private Bitmap bitmap;
    private int speed=100;

    private boolean boosting=false;
    private final int GRAVITY = -10;
    private final int MIN_SPEED=1;
    private final int MAX_SPEED=20;
    private int maxY;
    private int minY;
    private int maxX;
    private int minX;

    private int x,y;

    //combat stuff
    private int hp=1000;

    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }

    private Rect detectColision;

    public Rect getDetectColision() {
        return detectColision;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public Player(Context context, int screenX, int screenY) {
        x=75;
        y=50;
        speed=1;


        bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        maxX=screenX-bitmap.getWidth();
        maxY=screenY-bitmap.getHeight();

        detectColision=new Rect(x,y,bitmap.getWidth(),bitmap.getHeight());
    }

    public void setTouchPosition(int x,int y)
    {
        this.x=x;
        this.y=y;
    }

    public void update()
    {
        if (y < minY)
        {
            y = minY;
        } else if (y > maxY)
        {
            y = maxY;
        }
        if (x > maxX / 2)
        {
            x = maxX / 2;
        }
        detectColision.left = x;
        detectColision.top = y;
        detectColision.right = x + bitmap.getWidth();
        detectColision.bottom = y + bitmap.getHeight();
    }
}
