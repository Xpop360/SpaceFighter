package a13212.games.ipca.spacefighter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by andre on 14/11/2017.
 */

public class Enemy {
    private int x;
    private int y;
    private int speed = 1;
    private int maxY;
    private int minY;
    private int maxX;
    private int minX;
    private Bitmap bitmap;

    private Rect detectColision;

    public Rect getDetectColision() {
        return detectColision;
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

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Enemy(int x, int y, Context context) {
        this.maxX = x;
        this.maxY = y;
        minX = 0;
        minY = 0;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);

        Random generator = new Random();
        speed = generator.nextInt(6)+10;

        x = maxX;
        y = generator.nextInt(maxY)-bitmap.getHeight();
        detectColision=new Rect(x,y,bitmap.getWidth(),bitmap.getHeight());
    }

    public void Update(int playerSpeed) {
        x -= playerSpeed;
        x -= speed;

        if (x < 0-bitmap.getWidth()) {
            x = maxX;
            Random generator = new Random();
            y = generator.nextInt(maxY) - bitmap.getHeight();
            speed = generator.nextInt(10) + 10;
        }
        detectColision.left=x;
        detectColision.top=y;
        detectColision.right=x+bitmap.getWidth();
        detectColision.bottom=y+bitmap.getHeight();
    }
}
