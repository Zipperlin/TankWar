import org.w3c.dom.events.Event;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Missile {
    private int x;
    private int y;
    private Tank.Direction dir;

    private static  final int XSPEED=10;
    private static  final int YSPEED=10;

    public static final int WIDTH=10;
    public static final int HEIGHT=10;

    public Missile(int x, int y, Tank.Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void draw(Graphics g){
        Color c=g.getColor();
        g.setColor(Color.yellow);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);

        move();
    }

    public void move(){
        switch (dir){
            case U:
                y-=YSPEED;
                break;
            case L:
                x-=YSPEED;
                break;
            case R:
                x+=YSPEED;
                break;
            case D:
                y+=YSPEED;
                break;
            case LD:
                x-=XSPEED;
                y+=YSPEED;
                break;
            case LU:
                x-=XSPEED;
                y-=YSPEED;
                break;
            case RD:
                x+=XSPEED;
                y+=YSPEED;
                break;
            case RU:
                x+=XSPEED;
                y-=YSPEED;
                break;
            default:
                //throw new IllegalStateException("Unexpected value: " + dir);
        }
    }


}
