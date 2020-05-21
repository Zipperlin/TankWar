import org.w3c.dom.events.Event;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Missile {
    private int x;
    private int y;
    private Tank.Direction dir;
    private  TankClient tc;

    private static  final int XSPEED=10;
    private static  final int YSPEED=10;

    public static final int WIDTH=10;
    public static final int HEIGHT=10;

    private boolean good;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    private boolean live=true;

    public Missile(int x, int y, boolean isgood,Tank.Direction dir,TankClient tc) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tc=tc;
        this.good=isgood;
    }

    public void draw(Graphics g){

        if(!this.live){
            this.tc.vecMissile.remove(this);
            return;
        }
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
                break;
                //throw new IllegalStateException("Unexpected value: " + dir);
        }

        if(x<0|| y<0 ||x>TankClient.GAME_WIDTH||y>TankClient.GAME_WIDTH){
            setLive(false);
            tc.vecMissile.remove(this);
        }
    }

    public Rectangle getRect(){
        return new Rectangle(x,y,WIDTH,HEIGHT);
    }

    public boolean hittank(Tank tank){
        if(this.getRect().intersects(tank.getRect())&& (tank.isLive()) && (this.good!=tank.isGood())){


            if(tank.isGood()){
                tank.setBlood(tank.getBlood()-20);
                if(tank.getBlood()<=0){
                    tank.setLive(false);
                }
            }
            else{
                tank.setLive(false);
            }


            this.live=false;
            Explode e=new Explode(x,y,tc);
            tc.listexplode.add(e);

            return true;
        }
        return false;
    }

    public boolean hittanks(ArrayList<Tank> list){
       for(int i=0;i<list.size();i++){
           if(hittank(list.get(i)))
           return true;
       }
        return false;
    }

    public boolean hitwall(Wall w){
        if(this.live&& this.getRect().intersects(w.getRect())){
            this.live=false;
            return false;
        }
        return true;
    }

}
