package a13212.games.ipca.spacefighter;

import android.content.Context;

import java.util.Random;

/**
 * Created by andre on 14/11/2017.
 */

public class Star {
    private int x;
    private int y;
    private int speed=0;
    private int maxY;
    //private int minY;
    private int maxX;
    //private int minX;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Star(int x, int y, Context context) {
        this.maxX = x;
        this.maxY = y;
        //minX=0;
        //minY=0;

        Random generator=new Random();
        speed=generator.nextInt(10);

        x=generator.nextInt(maxX);
        y=generator.nextInt(maxY);
    }

    public void Update()
    {
        x-=speed;

        if(x<0)
        {
            x=maxX;
            Random generator=new Random();
            y=generator.nextInt(maxY);
            speed=generator.nextInt(10);
        }
    }

    public float getStarWidth()
    {
        Random generator=new Random();
        float width=generator.nextFloat()*4f;

        return width;
    }
}