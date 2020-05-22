import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;

public class TankClient extends Frame{

    public static final int GAME_WIDTH=800;
    public static final int GAME_HEIGHT=600;

    Tank myTank=new Tank(50,50,this,true);

    Tank enTank=new Tank(100,100,this,false);

    Wall W1=new Wall(300,200,20,150,this);
    Wall W2=new Wall(300,100,300,20,this);

    Blood blood=new Blood(350,350,10,10,this);

    ArrayList<Tank> listentank=new ArrayList<Tank>();

    Explode e=new Explode(70,70,this);

    Missile m=null;

    Vector<Missile> vecMissile=new Vector<Missile>();

    ArrayList<Explode> listexplode=new ArrayList<Explode>();

    Image offScreenImage =null;

    private class KeyMonitor extends KeyAdapter {
        public void keyPressed(KeyEvent event) {
            myTank.KeyPressed(event);
        }

        public void keyReleased(KeyEvent event) {
            myTank.KeyReleased(event);
        }
    }

    public void update(){

    }

    public void paint(Graphics g){

        if(listentank.size()<=0){
            for(int i=0;i<5;i++){
                listentank.add(new Tank(50+40*(i+1),50,this,false, Tank.Direction.D));
            }
        }

        for(Missile var:vecMissile)
        {
            if(var!=null) {
                var.hittanks(listentank);
                var.hittank(myTank);
                var.hitwall(W1);
                var.hitwall(W2);
                var.draw(g);
            }
        }

        for(int i=0;i<listexplode.size();i++){
            listexplode.get(i).draw(g);
        }

        for(int i=0;i<listentank.size();i++){
            Tank tank=listentank.get(i);
            tank.collidesWithWall(W1);
            tank.collidesWithWall(W2);
            tank.collidesWithTank(listentank);
            tank.draw(g);
        }
        myTank.draw(g);
        W1.draw(g);
        W2.draw(g);

        blood.draw(g);

    }

    public void update(Graphics g){
        if(offScreenImage==null)
        {
            offScreenImage=this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffscreen=offScreenImage.getGraphics();
        Color c=gOffscreen.getColor();
        gOffscreen.setColor(Color.green);
        gOffscreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        paint(gOffscreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    public void launchFrame(){

        for(int i=0;i<10;i++){
            listentank.add(new Tank(50+40*(i+1),50,this,false, Tank.Direction.D));
        }
        this.setLocation(400,300);
        this.setSize(800,600);
        this.setTitle("TankWar");
        //短小不会扩展使用匿名类
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyMonitor());
        setVisible(true);
        setResizable(false);
        setBackground(Color.green);


        new Thread(new PaintThread()).start();
    }

    public static void main(String[] args){
        TankClient tankClient=new TankClient();
        tankClient.launchFrame();

    }

    private class PaintThread implements Runnable {
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
