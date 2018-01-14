package a13212.games.ipca.spacefighter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by andre on 13/11/2017.
 */

public class GameView extends SurfaceView implements Runnable
{

    private Thread gameThread=null;
    boolean playing=false;
    private Player player;
    private Canvas canvas;
    private SurfaceHolder surfaceholder;
    Paint paint;
    private ArrayList<Star>stars=new ArrayList<>();
    private ArrayList<Enemy>enemies=new ArrayList<>();
    Boom boom;
    int hp;
    int screenX;
    boolean firsthalf;

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        boom=new Boom(context);
        player=new Player(context, screenX,screenY);
        paint=new Paint();
        surfaceholder=getHolder();
        this.screenX=screenX;
        for(int i=0;i<100;i++)
        {
            Star s = new Star(screenX,screenY,context);
            stars.add(s);
        }
        for (int i=0;i<2;i++)
        {
            Enemy e = new Enemy(screenX,screenY,context);
            enemies.add(e);
        }
    }

    @Override
    public void run() {
        while (playing)
        {
            update();
            draw();
            control();
        }
    }

    private void update()
    {
        player.update();
        boom.setX(-1000);
        boom.setY(-1000);

        for(Star s:stars)
        {
            s.Update();
        }
        for(Enemy e:enemies)
        {
            e.Update(player.getSpeed());
            if(Rect.intersects(player.getDetectColision(),e.getDetectColision()))
            {
                boom.setX(e.getX());
                boom.setY(e.getY());
                e.setX(-1000);
                player.setHp(player.getHp()-100);
            }
        }
    }
    private void draw()
    {
        if(surfaceholder.getSurface().isValid())
        {
            canvas=surfaceholder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            paint.setColor(Color.WHITE);
            for (Star s : stars)
            {
                paint.setStrokeWidth(s.getStarWidth());
                canvas.drawPoint(s.getX(),s.getY(),paint);
            }

            for (Enemy e:enemies)
            {
                canvas.drawBitmap(e.getBitmap(),e.getX(),e.getY(),paint);
            }

            canvas.drawBitmap(player.getBitmap(),player.getX(),player.getY(),paint);
            canvas.drawBitmap(boom.getBitmap(),boom.getX(),boom.getY(),paint);

            paint.setColor(Color.GREEN);
            canvas.drawText("HP: " + hp,50,50,paint);

            surfaceholder.unlockCanvasAndPost(canvas);
        }
    }
    private void control()
    {
        try {
            gameThread.sleep(17);
        }
        catch (InterruptedException e)
        {

        }
    }

    public void pause()
    {
        playing=false;
        try {
            gameThread.join();
        }
        catch (InterruptedException e)
        {

        }
    }

    public void resume()
    {
        playing=true;
        gameThread=new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(-event.getX()<screenX/2)
        {
            firsthalf=true;
        }
        else
        {
            firsthalf=false;
        }
        switch (event.getAction() & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_MOVE:
                //drag finger
                if(firsthalf)
                {
                    player.setTouchPosition((int)event.getX(),(int)event.getY());
                }
                break;
        }
        return true;
    }
}