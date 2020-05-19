import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    private int x;
    private int y;
    public static final int XSPEED=5;
    public static final int YSPEED=5;

    public static final int WIDTH=30;
    public static final int HEIGHT=30;

    private TankClient tc;

    public Tank(int x,int y,TankClient tc){
        this(x,y);
        this.tc=tc;
    }

    private boolean bl=false,bu=false,br=false,bd=false;
    enum Direction{
        L,
        LU,
        U,
        RU,
        R,
        RD,
        D,
        LD,
        STOP,
    }

    private Direction ptdir=Direction.STOP;

    private Direction dir=Direction.STOP;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g){
        Color c=g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);

        Integer icount=tc.vecMissile.size();
        g.drawString(icount.toString(),60,60);

        switch (ptdir){
            case U:
                g.drawLine(x+Tank.WIDTH/2,y+Tank.HEIGHT/2,x+Tank.WIDTH/2,y);
                break;
            case L:
                g.drawLine(x,y+Tank.HEIGHT/2,x+Tank.WIDTH/2,y+Tank.HEIGHT/2);
                break;
            case R:
                g.drawLine(x+Tank.WIDTH/2,y+Tank.HEIGHT/2,x+Tank.WIDTH,y+Tank.HEIGHT/2);
                break;
            case D:
                g.drawLine(x+Tank.WIDTH/2,y+Tank.HEIGHT/2,x+Tank.WIDTH/2,y+Tank.HEIGHT);
                break;
            case LD:
                g.drawLine(x+Tank.WIDTH/2,y+Tank.WIDTH/2,x,y+Tank.HEIGHT);
                break;
            case LU:
                g.drawLine(x,y,x+Tank.WIDTH/2,y+Tank.HEIGHT/2);
                break;
            case RD:
                g.drawLine(x+Tank.WIDTH/2,y+Tank.HEIGHT/2,x+Tank.WIDTH,y+Tank.HEIGHT);
                break;
            case RU:
                g.drawLine(x,y,x+WIDTH,y);
                break;
            default:
                //throw new IllegalStateException("Unexpected value: " + dir);
        }

        move();
    }
    
    void move(){
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
        if(this.dir!=Direction.STOP)
            this.ptdir=this.dir;
    }

    public void KeyPressed(KeyEvent e){
        int key= e.getKeyCode();
        switch (key)
        {
            case KeyEvent.VK_RIGHT:
               br=true;
                break;
            case KeyEvent.VK_LEFT:
                bl=true;
                break;
            case KeyEvent.VK_UP:
                bu=true;
                break;
            case KeyEvent.VK_DOWN:
                bd=true;
                break;
            case KeyEvent.VK_CONTROL:
                //tc.m=fire();
                tc.vecMissile.add(fire());
                break;
            default:
                break;
        }

        locateDirection();
    }

    public void KeyReleased(KeyEvent e){
        int key= e.getKeyCode();
        switch (key)
        {
            case KeyEvent.VK_RIGHT:
                br=false;
                break;
            case KeyEvent.VK_LEFT:
                bl=false;
                break;
            case KeyEvent.VK_UP:
                bu=false;
                break;
            case KeyEvent.VK_DOWN:
                bd=false;
                break;

            default:
                break;
        }

        locateDirection();
    }

    void locateDirection(){
        if(bl&&!bu&&!br&&!bd) dir=Direction.L;
        else if(bl&&bu&&!br&&!bd) dir=Direction.LU;
        else if(!bl&&bu&&!br&&!bd) dir=Direction.U;
        else if(!bl&&bu&&br&&!bd) dir=Direction.RU;
        else if(!bl&&!bu&&br&&!bd) dir=Direction.R;
        else if(!bl&&!bu&&!br&&bd) dir=Direction.D;
        else if(!bl&&!bu&&br&&bd) dir=Direction.RD;
        else if(bl&&!bu&&!br&&bd) dir=Direction.LD;
        else if(!bl&&!bu&&!br&&!bd) dir=Direction.STOP;

    }

    public Missile fire(){
        int x=this.x+ Tank.WIDTH/2-Missile.WIDTH/2;
        int y=this.y+Tank.HEIGHT/2-Missile.HEIGHT/2;
        Missile m=new Missile(x,y,ptdir);
        return m;
    }
}
