import java.awt.*;

public class Blood {
    private  int x,y;
    private int w,h;
    private TankClient tc;

    int[][] positions={{350,300},{360,300},{375,275}};

    private int step=0;

    public void draw(Graphics g){
        Color c=g.getColor();
        g.setColor(Color.MAGENTA);

        g.fillRect(x,y,w,h);
        g.setColor(c);

        move();
    }

    public Blood(int x,int y,int w,int h,TankClient tc){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        this.tc=tc;
    }

    public void move(){
        if(step==positions.length){
            step=0;
        }
        step++;
        x=positions[step][0];
        y=positions[step][1];
    }
}
