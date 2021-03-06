import java.awt.*;

public class Wall {
    private TankClient tc;
    int x,y,w,h;

    public void draw(Graphics g){
        Color c=g.getColor();
        g.setColor(Color.BLACK);

        g.fillRect(x,y,w,h);
        g.setColor(c);
    }

    public Rectangle getRect(){
        return new Rectangle(x,y,w,h);
    }

    public Wall(int x,int y,int w,int h,TankClient tc){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.tc=tc;
    }


}
