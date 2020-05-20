import java.awt.*;

public class Explode {
    int x,y;
    private boolean live=true;

    private TankClient tc;

    public Explode(int x,int y,TankClient tc){
            this.x=x;
            this.y=y;
            this.tc=tc;
    }

    int[] diameter={4,7,12,20,26,32};
    int step=0;

    public void draw(Graphics g){

        if(!this.live){
            tc.listexplode.remove(tc);
            return;
        }
        Color c=g.getColor();
        g.setColor(Color.ORANGE);

        if(step==diameter.length){
            live=false;
            step=0;
            return;
        }

        g.fillOval(x,y,diameter[step],diameter[step]);
        g.setColor(c);
        step++;


    }


}
